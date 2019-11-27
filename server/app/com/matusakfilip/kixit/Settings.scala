package com.matusakfilip.kixit

import com.typesafe.config.ConfigFactory

class Settings {
  val conf = ConfigFactory.load()

  val redisUrl = conf.getString("redis.url")
  val redisPort = conf.getInt("redis.port")
}
