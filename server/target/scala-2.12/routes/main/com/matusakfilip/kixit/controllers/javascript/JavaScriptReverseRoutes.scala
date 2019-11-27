// @GENERATOR:play-routes-compiler
// @SOURCE:/home/filip/Programovanie/Play/kixit/server/conf/routes
// @DATE:Wed Nov 27 18:54:04 CET 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:6
package com.matusakfilip.kixit.controllers.javascript {

  // @LINE:6
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def socket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.matusakfilip.kixit.controllers.Application.socket",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ws"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.matusakfilip.kixit.controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}
