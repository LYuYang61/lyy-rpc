### 1、RPC（远程过程调用）

- 用于在分布式系统中实现远程方法调用，使得应用程序能够像调用本地方法一样调用远程服务，它通常基于特定的通信协议和编码机制，比如 TCP 和自定义协议头实现消息传输

- 它和 HTTP 协议这种常用于在客户端和服务器之间传输超文本文档，在 Web 应用程序之间进行通信的协议不同，RPC 更多地运用于服务端之间的通信



### 2、 RPC 简要调用流程

![img](https://qingwu-oss.oss-cn-heyuan.aliyuncs.com/lian/img/image-20240412182543830.png)

- 客户端应用程序调用本地的 stub 或代理对象，而不需要关心远程服务的具体位置和通信细节
- 客户端将调用的方法名、参数等信息传递给本地 stub 或代理对象，序列化之后通过网络发送到远程服务器
- 服务器接收到请求后，反序列化调用信息，恢复出方法名和参数
- 服务器根据接收到的方法名和参数，在远程执行相应的方法，得到结果序列化后，通过网络发送回客户端
- 客户端接收到结果后，反序列化得到方法执行的返回值



### 3、RPC 框架的模块设计

![RPC 框架](https://qingwu-oss.oss-cn-heyuan.aliyuncs.com/lian/img/RPC%20%E6%A1%86%E6%9E%B6.png)

- **消费者代理模块：** 基于代理模式实现，在服务消费者对服务提供者进行远程调用时，不需要考虑是如何实现调用的，直接调用写好的服务接口即可 
- **注册中心：** 基于开源的分布式键值存储系统 Etcd 实现注册中心
  - 服务提供者在启动时，会把自己能提供的服务信息（比如服务名称、地址、端口等信息）提交到注册中心，并且当服务上线、下线的时候通知到注册中心
  - 服务消费者在调用服务时，会通过服务注册中心查询所需服务的地址和其他信息，从而完成调用。 
- **本地服务注册器：** 服务提供端存储服务名称和实现类的映射关系，便于后续根据服务名获取到对应的实现类，从而通过反射完成调用
  - 例如消费者需要调用 userService 服务的 getUser 方法，可以发送请求，参数为 service=userService, method=getUser，然后请求处理器会根据 service 从服务注册器种找到对应的服务实现类，通过反射调用 method 指定的方法
- **负载均衡：** 当有多个服务和服务提供者节点时，服务消费者需要确认请求哪一个服务和节点。因此我们设计了路由模块，通过实现不同算法的负载均衡器，帮助服务消费者选择一个服务节点发起调用
- **自定义 RPC 协议：** 自主实现了基于 Vert.x（TCP）和自定义请求头的网络传输协议，并自主实现了对字节数组的编码 / 解码器，可以完成性能更高的请求和响应
- **请求处理器：** 服务提供者在接收到请求后，可以通过请求处理器解析请求，从本地服务注册器中找到服务实现类并调用方法
- **重试和容错模块：** 在调用出错时，进行重试、降级、故障转移等操作，保障服务的可用性和稳定性



### 4、调用流程

- **服务提供者启动服务：** 启动基于 Vert.x 的 TCP 服务器，并将服务信息注册到本地服务注册器和注册中心
- **服务消费者调用服务：** 消费者代理模块从注册中心获取到服务提供者的调用信息列表，通过负载均衡算法确定一个调用地址，并构造基于 RPC 协议的请求。然后发起请求，消息经过编码器处理
- **服务提供者处理请求：** 服务提供者通过解码器还原消息，根据消息内容，从本地服务注册器找到服务实现类，通过反射进行调用
- **服务提供者响应请求：** 服务提供者将调用结果进行封装，通过编码器处理响应消息；服务消费者收到消息后，通过解码器还原消息，从而完成调用
- **服务消费端重试和容错：** 如果调用失败，服务消费端会根据预设好的策略进行重试、或者进行故障转移、降级等容错操作



### 5、配置信息

- 基本通用配置：RPC 项目名称、版本号、序列化器
- 服务消费方配置：模拟调用、负载均衡器、重试策略、容错策略
- 服务提供方配置：服务器主机名和端口号
- 注册中心配置：注册中心类型、地址、用户名密码、超时时间等，由于注册中心配置较多，我们单独写了一个 RegistryConfig 封装配置信息
- 如何管理配置信息：实现了 RpcApplication 全局应用类，相当于 holder，存放了项目全局用到的配置对象，通过双检锁单例模式保证只会创建一个配置对象实例
- 如何读取配置信息：写了一个单独的 ConfigUtils 工具类。通过 Hutool 的 Setting 模块的 props.toBean方法读取 application.properties 文件内容，并转换成 RpcConfig 对象。还通过在配置文件名后拼接环境的方式实现了多环境配置文件的读取



### 6、Vert.x 网络通信

- **Vert.x 提供了高层次的抽象：** 它不仅仅是一个网络框架，还是一个完整的异步编程框架，提供了包括 HTTP 服务器 / 客户端、WebSocket、事件总线、数据库访问、定时器等在内的多种构建高性能应用程序的工具和各种网络编程的 API
- **高性能：** Vert.x 基于事件循环机制，采用单线程或少量线程处理大量并发连接，可以有效减少线程上下文切换的开销，提高服务器的吞吐量和响应速度。更适合 RPC 框架这种需要处理大量 TCP 连接的应用场景
- **对于 TCP 半包粘包的情况：** Vert.x 提供了 RecordParser 类，能更方便地解决，不用自己去设计算法来处理
- 使用 TCP 协议完成网络传输，相比于 HTTP 协议的性能更高，因为 HTTP 头信息是比较大的，会影响传输性能；HTTP 本身属于无状态协议，这意味着每个 HTTP 请求都是独立的，每次请求 / 响应都要重新建立和关闭连接，也会影响性能



### 7、自定义消息结构

- 魔数：作用是安全校验，防止服务器处理了非框架发来的乱七八糟的消息（类似 HTTPS 的安全证书）
- 版本号：保证请求和响应的一致性（类似 HTTP 协议有 1.0/2.0 等版本）

- 序列化方式：来告诉服务端和客户端如何解析数据（类似 HTTP 的 Content-Type 内容类型）

- 类型：标识是请求还是响应？或者是心跳检测等其他用途。（类似 HTTP 有请求头和响应头）

- 状态：如果是响应，记录响应的结果（类似 HTTP 的 200 状态代码）

- 请求 id：唯一标识某个请求，因为 TCP 是双向通信的，需要有个唯一标识来追踪每个请求。

- 请求体数据长度: 保证能够完整地获取 body 内容信息(因为 TCP 协议本身会存在半包和粘包问题，每次传输的数据可能是不完整的)

- 因为 TCP 协议本身会存在半包和粘包问题，每次传输的数据可能是不完整的，所以我们需要在消息头中新增一个字段**请求体数据长度**，保证能够完整地获取 body 内容信息

![img](https://qingwu-oss.oss-cn-heyuan.aliyuncs.com/lian/img/1710041073941-f7fabd8c-cbc1-4d2f-b4af-bdba4677758a.png)



### 8、TCP 半包粘包问题

- 当客户端向服务端连续发送多条消息时：

  - 半包是指服务端单次收到的数据比客户端单次发送的数据少
  - 粘包是指服务端收到的单次收到的数据比客户端单次发送的数据多

- 解决策略：

  - 在消息头中设置请求体的长度，服务端接收时，判断每次消息的长度是否符合预期，不完整就不读，留到下一次接收到消息时再读取

  - 代码中使用了 Vert.x 框架中内置的 RecordParser 完美解决半包粘包，它的作用是：保证下次读取到特定长度的字符。因为消息体的长度是不固定的，所以设计 RPC 时要通过调整 RecordParser 的固定长度（变长）来解决

    设计思路是：将读取完整的消息拆分为 2 次： 

    1. 先完整读取请求头信息，由于请求头信息长度是固定的，可以使用 RecordParser保证每次都完整读取
    2. 再根据请求头长度信息更改 RecordParser的固定长度，保证完整获取到请求体



### 9、序列化和反序列化器

- 序列化：将 Java 对象转为可传输的字节数组
- 反序列化：将字节数组转换为 Java 对象
- 首先定义了序列化器接口，提供了序列化、反序列化方法
- 实现了 JDK 原生序列化、Kryo、Hessian、JSON 等多种序列化器
- 可配置化：我通过工厂模式 + 单例模式提供了根据配置文件动态获取序列化器实例的方法
- 可扩展：通过自定义的 SPI 机制，实现了序列化器的自主扩展、或者覆盖默认的实现 



### 10、Etcd注册中心

- Etcd 的性能很高，而且它和云原生有着密切的关系，通常被作为云原生应用的基础设施，存储一些元信息。比如经典的容器管理平台 k8s 就使用了 Etcd 来存储集群配置信息、状态信息、节点信息等
- Etcd 采用 Raft 一致性算法来保证数据的一致性和可靠性，具有高可用性、强一致性、分布式特性等特点
- Etcd 非常简单易用，提供了简单的 API、数据的过期机制、数据的监听和通知机制等，完美满足注册中心的实现诉求
- Etcd 使用层次化的键值对来存储数据，支持类似于文件系统路径的层次结构，能够很灵活地单 key 查询、按前缀查询、按范围查询

- Lease（租约）：用于对键值对进行 TTL 超时设置，即设置键值对的过期时间。当租约过期时，相关的键值对将被自动删除
- Watch（监听）：可以监视特定键的变化，当键的值发生变化时，会触发相应的通知



### 11、服务下线

- 主动下线：服务提供者项目正常退出时，利用 JVM 的 ShutdownHook，主动从注册中心移除注册信息
- 被动下线：服务提供者项目异常退出时，可以利用 Etcd 的 key 过期机制自动移除



### 12、缓存数据一致性

- 当第一次获取到某个服务的注册信息后，会对该服务下的所有 key 使用 etcd 的 watch 方法进行监听
- 当监听到 key 被移除的 DELETE 事件时，就表示对应的服务提供者节点已经下线，此时清空本地服务注册信息缓存即可

![img](https://qingwu-oss.oss-cn-heyuan.aliyuncs.com/lian/img/1709985301370-6bfd5391-9748-40ab-a85e-90a262a857dc.jpeg)







### 13、负载均衡

- **轮询**（Round Robin）：按照循环的顺序将请求分配给每个服务器，适用于各服务器性能相近的情况
- **随机**（Random）：随机选择一个服务器来处理请求，适用于服务器性能相近且负载均匀的情况



### 14、重试机制

- **固定重试间隔**（Fixed Retry Interval）：在每次重试之间使用固定的时间间隔
- **指数退避重试**（Exponential Backoff Retry）：在每次失败后，重试的时间间隔会以指数级增加，以避免请求过于密集

- **随机延迟重试**（Random Delay Retry）：在每次重试之间使用随机的时间间隔，以避免请求的同时发生



### 15、容错机制

- Fail-Over 故障转移：一次调用失败后，切换一个其他节点再次进行调用，也算是一种重试
- Fail-Back 失败自动恢复：系统的某个功能出现调用失败或错误时，通过其他的方法，恢复该功能的正常。可以理解为降级，比如重试、调用其他服务等
- Fail-Safe 静默处理：系统出现部分非重要功能的异常时，直接忽略掉，不做任何处理，就像错误没有发生过一样
- Fail-Fast 快速失败：系统出现调用错误时，立刻报错，交给外层调用方处理



### 16、mock服务

- RPC 框架的核心功能是调用其他远程服务
- 但是在实际开发和测试过程中，有时可能无法直接访问真实的远程服务，或者访问真实的远程服务可能会产生不可控的影响
- 例如网络延迟、服务不稳定等。在这种情况下，就需要**使用 mock 服务来模拟远程服务的行为，以便进行接口的测试、开发和调试**。 mock 是指模拟对象，**通常用于测试代码中，特别是在单元测试中，便于我们跑通业务流程**

  实现步骤：

  1. 首先，我给 RPC 框架全局配置增加了 boolean 类型的 mock 属性，用于控制是否开启 Mock
  2. 服务消费者通过代理工厂获取代理对象时，如果发现全局配置中的 mock 值为 true，那么将返回 Mock 的服务代理（而不是发送请求的服务代理）
  3. 在 Mock 服务代理中，会根据方法的返回值类型，来生成特定的默认值对象，如 boolean 类型返回 ture 或 false，int 则会返回 0 等，还可以通过 Faker 等类库来随机生成模拟值



### 17、SPI 机制

- SPI（Service Provider Interface）服务提供接口是 Java 的重要机制，主要用于实现模块化开发和插件化扩展

- SPI 机制**允许服务提供者通过特定的配置文件将自己的实现注册到系统中，然后系统通过反射机制动态加载这些实现**，而不需要修改原始框架的代码，从而实现了系统的解耦、提高了可扩展性

- 一个典型的 SPI 应用场景是 JDBC（Java 数据库连接库），不同的数据库驱动程序开发者可以使用 JDBC 库，然后定制自己的数据库驱动程序

- 此外，我们使用的主流 Java 开发框架中，几乎都使用到了 SPI 机制，比如 Servlet 容器、日志框架、ORM 框架、Spring 框架

- 虽然 Java 内置了 ServiceLoader 来实现 SPI，但是如果想定制多个不同的接口实现类，就没办法在框架中指定使用哪一个了，也就无法实现像 “通过配置快速指定序列化器” 这样的需求

- 该项目自定义了 SPI 机制的实现，能够给每个自行扩展的类指定键名 

  比如读取如下配置文件，能够得到一个 **序列化器名称 => 序列化器实现类对象**的映射，之后就可以根据用户配置的序列化器名称动态加载指定实现类对象了

  ```properties
  jdk=com.lyy.lyyrpc.serializer.JdkSerializer
  hessian=com.lyy.lyyrpc.serializer.HessianSerializer
  json=com.lyy.lyyrpc.serializer.JsonSerializer
  kryo=com.lyy.lyyrpc.serializer.KryoSerializer
  ```

具体实现方法如下：

1. 指定 SPI 的配置目录，并且将配置再分为系统内置 SPI 和用户自定义 SPI，便于区分优先级和维护
2. 编写 SpiLoader 加载器，实现读取配置、加载实现类的方法
   - 用 Map 来存储已加载的配置信息 键名 => 实现类
   - 通过 Hutool 工具库提供的 ResourceUtil.getResources 扫描指定路径，读取每个配置文件，获取到 键名 => 实现类 信息并存储在 Map 中
   - 定义获取实例方法，根据用户传入的接口和键名，从 Map 中找到对应的实现类，然后通过反射获取到实现类对象。可以维护一个对象实例缓存，创建过一次的对象从缓存中读取即可
3. 重构序列化器工厂，改为从 SPI 加载指定的序列化器对象。使用静态代码块调用 SPI 的加载方法，在工厂首次加载时，就会调用 SpiLoader 的 load 方法加载序列化器接口的所有实现类，之后就可以通过调用 getInstance 方法获取指定的实现类对象了



### 18、JDK动态代理

- 允许开发者在运行时动态地创建代理类，可用于接口的代理
- 作为 Java 标准库的一部分，无需引入任何第三方依赖即可使用
- 相比于 CGLIB 等动态生成字节码的动态代理实现，JDK 动态代理的性能更高
- 能够在不同的 Java 平台上运行，具有良好的跨平台性



### 19、工厂模式

- 更灵活：通过工厂模式提供的 “根据类型获取代理类” 的方法，我能够根据需要动态地选择合适的代理对象类型进行创建
- 解耦合：将创建代理对象的过程进行封装，调用方不需要了解具体的对象创建细节，使代码更利于维护



### 20、测试

- etcd.exe --config-file etcd.conf

1. 两个服务提供者，分别注册user, order服务，正常测试
2. 一个服务提供者，注册user, order服务，正常测试
3. 一个服务提供者，注册user,随机时间间隔重试
4. 一个服务提供者，注册user,指数退避时间间隔重试
5. 一个服务提供者，注册user,固定时间间隔重试，快速失败容错
6. 一个服务提供者，注册user,固定时间间隔重试，降级到mock服务容错
7. 多个服务提供者，均注册user,轮询负载均衡，正常测试
8. 多个服务提供者，均注册user,随机负载均衡，正常测试
9. 多个服务提供者，均注册user,轮询负载均衡，固定时间间隔重试，故障转移容错

