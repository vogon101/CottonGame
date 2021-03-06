package com.vogonjeltz.simplegamee.lib.core

/**
  * Sync class allows a method to be called a specific number of times per second without blocking the main thread
  * It does not achieve the rate perfectly but it is good enough for most purposes
  * @param rate The number of times per second to call the functions
  * @param actions The function to be called of type (Option[I]) => R
  * @tparam I Type of the argument to the function (Wrapped in an option)
  * @tparam R Return type of the function, can be Unit
  */
class Sync[I, R](val rate: Int, val actions: (Option[I]) => R) {

  /**
    * Secondary constructor for use with a function that does not take an argument
    * @param rate The number of times per second to call the functions
    * @param actions The function to be called, of type () => R
    */
  def this(rate: Int, actions: () => R) {
    this(rate, (x: Option[Any]) => actions())
  }

  private val SECOND_IN_NANO = Math.pow(1000,3)
  private val CALL_SPACING = SECOND_IN_NANO / rate

  private var _lastTime : Double = System.nanoTime().toDouble
  private var _deltaTime: Double = 0

  private var _lastSecondTime: Double = System.nanoTime().toDouble
  private var _callsThisSecond: Int = 0
  private var _callsLastSecond: Int = 0

  /**
    * Call this in a mainloop every time it loops. This will call the function (actions) if it is the correct time. It
    * will limit it to the rate
    * @param argument Argument to the function, defaults to None
    * @return Returns None if function not called otherwise Some(function return value)
    */
  def call (argument: Option[I] = None): Option[R] = {

    val newTime = System.nanoTime().toDouble

    if (newTime - lastTime >= CALL_SPACING) {

      _deltaTime = newTime - lastTime
      _lastTime = newTime

      if (_lastTime - _lastSecondTime <= SECOND_IN_NANO) _callsThisSecond += 1
      else {
        _callsLastSecond = _callsThisSecond
        _lastSecondTime = _lastTime
        _callsThisSecond = 0
        //Log.info("Updates last second: " + _callsLastSecond)
      }

      Some(actions(argument))

    } else None

  }

  /**
    * Timestamp of the last call of the function
    * @return
    */
  def lastTime: Double = _lastTime

  /**
    * Time since the last call in seconds
    * @return
    */
  def deltaTime: Double = _deltaTime / SECOND_IN_NANO

  /**
    * The number of calls in the last second (can be used as the calls per second)
    * @return
    */
  def callsLastSecond = _callsLastSecond

}
