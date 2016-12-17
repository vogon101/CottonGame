package com.vogonjeltz.simplegamee.lib.core

import com.vogonjeltz.simplegamee.lib.math.Vect
import com.vogonjeltz.simplegamee.lib.render.GraphicsManager
import com.vogonjeltz.simplegamee.lib.render.OpenGL.Render
import com.vogonjeltz.simplegamee.lib.utils.config.DisplaySettings

/**
  * Created by Freddie on 11/12/2016.
  */
class GameManager(val displaySettings: DisplaySettings, private var _map: GameMap) {

  //TODO: Immutable

  var running = true

  private var _updateFunctions: List[()=>Unit] = List()

  private var _offset = Vect.ZERO

  val updateSync = new Sync(displaySettings.updateSpeed, gameTick _)

  def offset: Vect = _offset
  def offset(t: Vect): Unit = _offset = offset + t

  Render.setOffsetFunction(() => offset)

  val graphicsManager = new GraphicsManager(displaySettings)

  GameManager.setInstance(this)

  def mainloop() = {

    graphicsManager.init()

    while (running) {

      running = !graphicsManager.render(render _)
      updateSync.call()

    }

  }

  def render() = map.render()

  def gameTick() = {

    updateFunctions.foreach(_())
    map.update()

  }

  def lastTime: Double = updateSync.lastTime
  def deltaTime: Double = updateSync.deltaTime

  def updateFunctions = _updateFunctions
  def attachUpdateListener (f : () => Unit) = _updateFunctions = f :: updateFunctions

  def ups = updateSync.callsLastSecond

  def map = _map

}

object GameManager {

  private var _instance: GameManager = _

  //TODO: Refactor this pattern? Options?
  def apply() = _instance

  def setInstance(i : GameManager, force: Boolean = false) =
    if (_instance == null || force) _instance = i
    else throw new Exception("Instance of GameObject already exists, use force")

}