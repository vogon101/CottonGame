package com.vogonjeltz.simplegamee.lib.core.gameObjects

import com.vogonjeltz.simplegamee.lib.core.GameObject
import com.vogonjeltz.simplegamee.lib.math.Transform
import com.vogonjeltz.simplegamee.lib.math.shapes.Box
import com.vogonjeltz.simplegamee.lib.render.OpenGL.Colour
import com.vogonjeltz.simplegamee.lib.render.renderers.QuadRenderer

/**
  * Created by Freddie on 14/12/2016.
  */
abstract class BoxGameObject extends GameObject {

  override val transform: Transform[Box]

  val renderer = new QuadRenderer(transform.shape_m _, Colour.RED)

  override def render() = renderer.render()

}
