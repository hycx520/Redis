package com.test.RedisTest.Tool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnect {
	JedisPoolConfig poolConfig=new JedisPoolConfig();
	public RedisConnect() {
		poolConfig.setMaxTotal(50);// 最大连接数，连接全部用完，进行等待
		poolConfig.setMinIdle(10); // 最小空余数
		poolConfig.setMaxIdle(30); // 最大空余数
	}

	public Jedis ConnectRedis(String RedisIP,Integer Redisport) {
		JedisPool pool = new JedisPool(poolConfig, RedisIP,Redisport);
		return pool.getResource();	
	}
	
	public static void main(String[] args) {
		RedisConnect rc=new RedisConnect();
		Jedis jedis=rc.ConnectRedis("localhost", 6379);
		for(int i=1;i<=800;i++) {
			String status=jedis.set("name"+i, "盛诚"+i);
			System.out.println(status);
			System.out.println(jedis.get("name"+i)+"-----"+i);
		}
		jedis.close();
		
	}

}
