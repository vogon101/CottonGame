package com.vogonjeltz.simplegamee.lib.render.OpenGL

import com.vogonjeltz.simplegamee.lib.math.Vect
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11._

/**
  * Created by Freddie on 11/12/2016.
  */
object Render {

  private var _offsetFunction: () => Vect = () => Vect(0,0)

  //TODO: Should the frame know about colour
  def withContext( frame: Frame )( actions: => Unit ): Unit = {

    glPushMatrix()

    val colourBuffer = BufferUtils.createFloatBuffer( 16 )
    if ( frame.colour.isDefined ) glGetFloat( GL_CURRENT_COLOR, colourBuffer )

    for (p <- frame.position) glTranslated(p.x, p.y, 0)
    for (r <- frame.rotation) glRotated(r.toDeg, 0, 0, 1)
    for (c <- frame.colour)   glColor3d(c.r, c.g, c.b)

    glTranslated(_offsetFunction().x, _offsetFunction().y, 0)

    actions

    glPopMatrix()
    if ( frame.colour.isDefined ) glColor3d( colourBuffer.get( 0 ).toDouble, colourBuffer.get( 1 ).toDouble, colourBuffer.get( 2 ).toDouble )

  }

  def setOffsetFunction(f: () => Vect) = _offsetFunction = f



}
