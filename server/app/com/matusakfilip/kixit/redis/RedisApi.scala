package com.matusakfilip.kixit.redis

import com.matusakfilip.kixit.Settings
import com.redis.RedisClientPool

trait RedisApi {
  def createHashMap: RedisHashMap
}

class RedisApiImpl(url: String,
                   port: Int) extends RedisApi {
  val redisClients = new RedisClientPool(url, port)

  override def createHashMap: RedisHashMap = ???
}

object RedisApi {
  def apply(settings: Settings): RedisApi = new RedisApiImpl(settings.redisUrl, settings.redisPort)
}

class RedisHashMap(clientPool: RedisClientPool) {

}
