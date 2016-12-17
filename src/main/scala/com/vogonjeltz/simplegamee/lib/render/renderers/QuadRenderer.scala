package com.vogonjeltz.simplegamee.lib.render.renderers

import com.vogonjeltz.simplegamee.lib.math.shapes.Box
import com.vogonjeltz.simplegamee.lib.render.OpenGL.Colour
import com.vogonjeltz.simplegamee.lib.render.Renderer
import org.lwjgl.opengl.GL11._

/**
  * Created by Freddie on 11/12/2016.
  */
class QuadRenderer(_b: () => Box, colour: Colour) extends Renderer{

  override def render() = {
    val box = _b()
    box.frame(colour) wrap {

      glBegin(GL_QUADS)

      glVertex2d(-box.width/2, -box.height/2)
      glVertex2d(box.width/2, -box.height/2)
      glVertex2d(box.width/2, box.height/2)
      glVertex2d(-box.width/2, box.height/2)

      glEnd()

    }
  }

}
