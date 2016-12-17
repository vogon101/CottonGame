package com.vogonjeltz.simplegamee.lib.core

import com.vogonjeltz.simplegamee.lib.math.Transform

/**
  * Created by Freddie on 11/12/2016.
  */
abstract class GameObject extends Renderable with Updateable {

  //TODO: Make GO generic on Transform shape

  val transform: Transform[_]

}
