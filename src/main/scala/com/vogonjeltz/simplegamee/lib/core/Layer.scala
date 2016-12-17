package com.vogonjeltz.simplegamee.lib.core


/**
  * Created by Freddie on 11/12/2016.
  */
class Layer extends Renderable with Updateable {

  //TODO: Immutable Layer?

  var _objects: List[GameObject] = List()

  override def render() = {
    _objects.foreach(_.render())
  }

  override def update() = _objects.foreach(_.update())

  def objects = _objects

  def addObject(o: GameObject) = _objects = objects ::: List(o)

}
