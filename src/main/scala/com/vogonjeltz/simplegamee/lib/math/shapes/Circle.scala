package com.vogonjeltz.simplegamee.lib.math.shapes

import com.vogonjeltz.simplegamee.lib.math.Vect

/**
  *
  * @param _centre The centre of the circle
  * @param radius The radius of the circle
  */
class Circle (private val _centre: Vect, val radius: Double) extends Shape[Circle](_centre){

  override def translate(v: Vect) = new Circle(position + v, radius)

  override def contains(v: Vect) = ???

}
