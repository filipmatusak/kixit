// @GENERATOR:play-routes-compiler
// @SOURCE:/home/filip/Programovanie/Play/kixit/server/conf/routes
// @DATE:Wed Nov 27 18:54:04 CET 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:6
package com.matusakfilip.kixit.controllers {

  // @LINE:6
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def socket(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "ws")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
