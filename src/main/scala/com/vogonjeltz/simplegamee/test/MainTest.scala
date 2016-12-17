package com.vogonjeltz.simplegamee.test

import com.vogonjeltz.simplegamee.lib.core.gameObjects.BoxGameObject
import com.vogonjeltz.simplegamee.lib.core.{GameManager, GameMap, Layer}
import com.vogonjeltz.simplegamee.lib.math.{Transform, Vect}
import com.vogonjeltz.simplegamee.lib.math.shapes.Box
import com.vogonjeltz.simplegamee.lib.render.OpenGL.Colour
import com.vogonjeltz.simplegamee.lib.utils.Log
import com.vogonjeltz.simplegamee.lib.utils.config.DisplaySettings

import scala.util.Random

/**
  * Created by Freddie on 11/12/2016.
  */
object MainTest extends App {

  val game = new GameManager(new DisplaySettings {_title = "Look at this"}, new GameMap())

  //TODO: Maybe move (0,0) to bottom left
  //TODO: Make this easier
  game.attachUpdateListener(() => {
    game.graphicsManager.setTitle("Game | " + game.ups + " UPS | " + game.graphicsManager.fps + " FPS")
  })

  Log.setLevel(1)
  game.map.addLayer(new Layer())
  game.map(0).addObject(new TestGO())
  game.map(0).addObject(new SimpleBoxGO(Colour.GREEN, new Box(Vect(10,10), Vect(20,30))))

  game.mainloop()

}

class SimpleBoxGO(val colour: Colour, val box: Box) extends BoxGameObject {

  val transform = new Transform(box)

  override def update() = {}

}

class TestGO extends BoxGameObject {

  val moveSpeed = 100

  val transform = new Transform (new Box(10,10,20,20))

  override def render() = renderer.render()


  override def update() = {

    val i = Random.nextInt(10000) - 4999
    val j = Random.nextInt(10000) - 4999

    //Log.success(GameManager().deltaTime.toString)
    transform.translate(Vect(moveSpeed * GameManager().deltaTime + i/1000, moveSpeed * GameManager().deltaTime + j/1000))

  }

}
