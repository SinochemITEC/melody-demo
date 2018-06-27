# melody-demo
melody2.X的演示代码

### 使用提示
- 默认path为127.0.0.1 默认端口为8080，启动时请和tomcat端口对应，如需修改可以在play.properties中修改
- 项目使用了redis，需要在项目启动前启动redis，默认配置信息如下，如需修改可以在play.properties中修改
```
    redis.host=127.0.0.1
    #redis.host=127.0.0.1
    redis.port=6379
    redis.timeout=30000
    redis.password=123456
    redis.db=5
```

