# springboot发送rabbitmq延时消息

#### 主要用途

订单超过半小时自动取消，不需要定时进询数据库，减轻系统压力；订单超时问题可以定时任务查询数据库，增大服务器和数据库的压力，这两个要根据数据库数量量的大小而定，如果数据表数据量不大，不必要使用rabbitmq延时消息的方式，需要多维护一个rabbitmq，其稳定性问题也是值考虑的事。
#### 使用rabbitmq延时消息注意
1、使用延时消息要与redis配合使用，客户下订单数据记录到redis中，并记录未付款状态，并发送延时消息，如果客户付了款，redis中改变其状态，同时修改数据库订单状态，但不能失效或删除。rabbit延时消息过来，查询redis中订单状态，如果已付款把redis记录一并清除，不再去访问数据库了，如果还未付款，修改订单状态为取消状态；
2、还有一个注意的问题，防止rabbit和redis挂后，重启服务对订单的影响，要使用相应的容灭机制，比如重启补救机制，重发延时消息的redis数据等，使用宕机时有处理的方法。

#### 安装方式

使用docker安装，最主要rabbitmq安装延时消息插件。

#### docker 命令

- 使用dockerfile构建镜像文（制作含有延时消息插件的镜像）

```linux
docker build -t myrabbitmq:3.8 .
```

- 运行容器

```
docker run -d  --name rabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=root -e RABBITMQ_DEFAULT_PASS=123456 myrabbitmq:3.8
```



