package com.vogonjeltz.simplegamee.lib.core

import com.vogonjeltz.simplegamee.lib.utils.Log

/**
  * Created by Freddie on 11/12/2016.
  */
class GameMap extends Renderable with Updateable {

  private var _layers: List[Layer] = List()

  def render() = {
    _layers.foreach(_.render())
  }

  def update() = _layers.foreach(_.update())

  def layers = _layers

  def apply(i: Int): Layer = _layers(i)

  def addLayer(l : Layer) = {
    _layers = layers ::: List(l)
    Log.success(layers.toString)
  }

}
