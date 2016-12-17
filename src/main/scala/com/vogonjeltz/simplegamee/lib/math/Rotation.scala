package com.vogonjeltz.simplegamee.lib.math

/**
  * An angle
  */
sealed trait Rotation {

  protected val DEG2RAD: Double = 180 / Math.PI
  protected val RAD2DEG: Double = Math.PI / 180

  /**
    * @return The angle in degrees
    */
  def toDeg: Double

  /**
    * @return The angle in degrees
    */
  def toRad: Double

}

object Rotation {

  /**
    * @return A rotation of zero degrees
    */
  def zero = Deg(0)

}

/**
  * @param r The angle in radians
  */
case class Rad(r: Double) extends Rotation {

  def toDeg = r * DEG2RAD

  def toRad = r

}

object Rad {

  /**
    * Converts an angle in degrees to radians
    * @param d The angle in degrees
    * @return The angle in radians
    */
  def apply(d : Deg):Rad = Rad(d.toRad)

}

/**
  * @param d The angle in degress
  */
case class Deg(d: Double) extends Rotation {

  def toDeg = d

  def toRad = d * RAD2DEG

}

object Deg {

  /**
    * Converts an angle in radians to degrees
    * @param r The angle in radians
    * @return The angle in degrees
    */
  def apply(r: Rad):Deg = Deg(r.toDeg)

}