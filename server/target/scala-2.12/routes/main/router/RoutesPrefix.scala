// @GENERATOR:play-routes-compiler
// @SOURCE:/home/filip/Programovanie/Play/kixit/server/conf/routes
// @DATE:Wed Nov 27 18:54:04 CET 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
