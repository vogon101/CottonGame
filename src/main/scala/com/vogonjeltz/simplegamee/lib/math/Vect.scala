package com.vogonjeltz.simplegamee.lib.math

/**
  * 2D Vector class
  * @param x X-ordinate of the vector
  * @param y Y-ordinate of the vector
  */
case class Vect(x: Double, y: Double) {

  /**
    * Subtract a vector (simply subtracts the ordinates)
    * @param that Vector to subtract
    * @return
    */
  def - (that: Vect) = Vect (x - that.x, y - that.y)

  /**
    * Add a vector
    * @param that Vector to add
    * @return
    */
  def + (that: Vect) = Vect (x + that.x, y + that.y)

  /**
    * Divide by a scalar (divides each ordinate)
    * @param scalar The scalar to divide by
    * @return
    */
  def / (scalar: Double) = Vect(x / scalar, y / scalar)

  /**
    * Multiply the vector by a scalar
    * @param scalar The scalar to multiply by
    * @return
    */
  def * (scalar: Double) = Vect(x * scalar, y * scalar)

}

object Vect {

  val ZERO = Vect(0,0)

}
