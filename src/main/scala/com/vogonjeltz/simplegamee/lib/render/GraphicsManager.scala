package com.vogonjeltz.simplegamee.lib.render

import com.vogonjeltz.simplegamee.lib.core.Sync
import com.vogonjeltz.simplegamee.lib.utils.Log
import com.vogonjeltz.simplegamee.lib.utils.config.DisplaySettings
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.{Display, DisplayMode}

/**
  * Created by Freddie on 11/12/2016.
  */
class GraphicsManager(val displaySettings: DisplaySettings) {

  val frameSync = new Sync(displaySettings.fpsCap, (f : Option[() => Unit]) => doRender(f.get))

  def init(): Unit = {
    // Initialize OpenGL
    Display.setDisplayMode( new DisplayMode( displaySettings.width, displaySettings.height ) )
    Display.create()
    Display.setTitle( displaySettings.title )

    glMatrixMode( GL_MODELVIEW )
    glLoadIdentity()
    glViewport( 0, 0, displaySettings.width, displaySettings.height )

    glMatrixMode( GL_PROJECTION )
    glLoadIdentity()
    glOrtho( 0, Display.getWidth.toDouble, 0, Display.getHeight.toDouble, 1, -1 )

    glMatrixMode( GL_MODELVIEW )
    glLoadIdentity()
  }

  def setTitle(t: String) = Display.setTitle(t)

  def clearScreen(): Unit ={
    glClearColor( 0f, 0f, 0f, 1.0f )
    glClear( GL_COLOR_BUFFER_BIT )
  }

  def render(f: () => Unit) = {
    frameSync.call(Some(f)).getOrElse(false)
  }

  def doRender(f: () => Unit): Boolean = {

    clearScreen()

    Log.verbose("Starting Render")

    f()

    Display.update()

    Log.verbose("Screen rendered")

    if ( Display.isCloseRequested ) {
      Log.warn("Close requested")
      true
    } else false

  }

  def fps = frameSync.callsLastSecond

}
