# 网络基础

## 流量控制、拥塞控制

- 流量控制：发送者发送过快，接收端来不及接收，有可能造成分组丢失，为控制发送速度。
  - 实现：滑动窗口，保证分组无差错、有序接收
  - 死锁：发送者受到窗口为0的应答，开启计时，到时重试，直到不是0，表示报文丢失，重置发送窗口再发送

- 拥堵控制：它是防止过多的数据注入到网络中，出现网络负载过大的情况
  - 解决： 
    - 慢开始：不要一开始就发送大量的数据，先探测一下网络的拥塞程度，由小到大逐渐增加拥塞窗口的大小
    - 快重传、快恢复


    
**HTTPS加密过程**

- client 请求 server 获取证书公钥，解析证书（无效弹出警告）
- 生成随机值，用公钥加密随机值成密钥 ，发给server
- server 用私钥解密得到随机值，与信息混合进行对称加密，发给 client



# Java基础

基础

面向对象三大特性：封装、继承、多态



**JVM运行时区域**：堆、虚拟机栈、本地方法栈、程序计数器、方法区（元空间）

**栈帧**：局部变量表、操作数栈、动态链接、返回地址

**GC Root**：虚拟机栈、方法区中静态属性、常量、native引用的对象



**双亲委派优点**：重复加载、恶意替换

**双亲委派破坏**：自定义类加载器，重写 loadClass 方法



**JVM类加载过程**：加载验证准备解析初始化

**Java对象创建过程**：类加载、对象分配空间、初始化0、null值、设置对象头、执行构造方法



## 垃圾回收

- - CMS：最短回收停顿时间为目标，标记清除	 https://cloud.tencent.com/developer/article/1624694

- - - - 4个问题及解决：

- - - - - **垃圾碎片**：`-XX:CMSFullGCsBeforeCompaction=n` n=0次cms后压缩
        - **remark时间长**：`-XX:+CMSScavengeBeforeRemark` remark前做一次YGC，减少年轻代对老年代的无效引用

- - - - - **concurrent mode failure：**cms过程中old空间不足，强制stw，设定内存占用率达60%时cms
        - **promotion failed：**	YCG，S空间不足直达老年代，空间也不足

- - G1：JDK7、逻辑分代、复制				https://juejin.cn/post/6844903894196158471

- - - - 4个阶段

- - - - - 阶段1：新生代GC：

- - - - - - - eden区满，启动回收，所有eden都清空，survivor也会被收集一部分数据，至少仍存在一个survivor区

- - - - - 阶段2：并发标记周期 （过程与CMS很类似）

- - - - - - - 初始标记（STW）：	根可达、伴随一次YGC
            - 根区域扫描：		扫描survivor区，标记可直达老年代的对象

- - - - - - - 并发标记：			与CMS类似，该阶段可以被一次Young GC打断
            - 重新标记（STW）：	修正处理

- - - - - - - 独占清理（STW）：	计算各个区域的存活对象和GC回收比例，然后进行排序，识别**混合收集区**域
            - 并发清理：			清理那些完全空闲的区域

- - - - - 阶段3：混合收集

- - - - - - - 针对**混合收集区**比例较高的区域进行回收操作，先回收垃圾比例较高的区域
            - 混合回收，是因为这段时期，新生代和老年代GC都会同时进行

- - - - - 阶段4：Full GC（不是一定会执行，看情况来定、SerialOld）

- - - - - - - 并发失败、晋升失败、疏散失败、巨型对象分配失败

    

## GC优化

- jstat
  - jstat -gc：可以查看 s0、s1、eden、老年代、元空间的容量、已用内存、、YGC、FullGC、GC总次数、用时

- jmap
  - jmap -histo <pid> 类对应数量，占用内存大小（类大小降序）
  - jmap -heap <pid> 查看堆相关参数及新生代老年代各区内存使用情况
  - jmap -dump:format=b,file={fileName} <pid> 生成 dump 文件。结合 jhat 命令将 dump 文件转换为 html 在浏览器查看，jhat 提供类似 sql 的 oql 查询语句。或者用其他 dump 文件分析工具分析

- jstack <pid>
  - 重点关注 WAITING、BLOCKED的
  - waiting on <0x000000070f107520> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
  - 假如有一个进程中100个线程，很多线程都在waiting on <xx> ，一定要找到是哪个线程持有这把锁 
  - 怎么找？搜索jstack dump的信息，找<xx> ，看哪个线程持有这把锁RUNNABLE 

    
## CPU飙高

- - top查看进程	top -Hp <pid>查看耗CPU线程
  - jstack <pid> 	重点关注 WAITING、BLOCKED的

- - jmap - histo 4655 | head -20  	查找有多少对象产生
  - 线上尽量不用，影响性能，可以在测试环境用

- - - 图形界面 jconsole、jvisualVM
    - 导出文件 jmap -dump:format=b,file=xxx pid ：
    
- - minorGC频繁，增加空间

- - - - 单次 Minor GC 时间是否增加：Minor GC时间更多取决于GC后存活对象的数量，而非Eden区的大小

- - - - - - 复制时间：扫描时间 + 复制时间（主要），

    
# Java多线程


## 锁优化

- - 使用层面

- - - - 锁粗化、细化
      - 减少锁的时间

- - - - 读写分离
      - cas：	volatiled+cas非常高效，加锁会导致线程的上下文切换

- - 系统层面

- - - - 自适应自旋锁
      - 锁消除	虚拟机即时编译器优化，

- - - - 锁升级：	synchronized的无偏轻重



#### ThreadLocal

- - **描述：**通常情况下，我们创建的变量都是可以被任何一个线程访问到的，每个线程有属于自己的专属本地变量
  - **实现：**每个 Thread 都有个 ThreadLocalMap,key是ThreadLocal，value是T

- - **内存泄露**：

- - - - key 为 ThreadLocal 的弱引用，ThreadLocalMap实现中已经考虑了这种情况，在调用 set()、get()、remove() 方法的时候，会清理掉 key 为 null 的记录，若不调则泄露
      - 因此使⽤完ThreadLocal ⽅法后，最好⼿动调⽤ remove() ⽅法





#### Volatile

- - 作用：	保证可见性、禁止指令重排序
  - 底层实现	加入volatile关键字，汇编层，有lock前缀指令，lock 相当于内存屏障

- - - - - - 它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；
          - 它会强制将对缓存的修改操作立即写入主存；

- - - - - - 如果是写操作，它会导致其他CPU中对应的缓存行无效。



#### AtomicInteger 和 LongAdder	

- - [AtomicInteger](https://github.com/summerHearts/JavaArchitecture/blob/master/Concurrent/浅谈AtomicInteger实现原理.md)

- - - - Java语言中，++i和i++操作并不是线程安全的：i++ 为 i = i +1 三步：读取，修改，写入
      - JDK1.5之后的java.util.concurrent.atomic包里，多了一批原子处理类。AtomicBoolean、AtomicInteger、AtomicLong、AtomicReference

- - - - 实现：对int的封装，volatile、unsafe实现（unsafe底层：硬件提供CAS）
      - 方法：public final int get()、getAndSet(int newValue)、getAndIncrement、getAndDecrement、getAndAdd(int delta)

- - LongAdder

- - - - JDK8，分段锁CAS
      - 实现：Cell[]

- - - - 过程：先用base，casBase()失败时创建Cell[]

- - 比较

- - - - LongAdder cell[] 合并问题（不准）
      - 大量并发LongAdder

- - - - AtomicInteger 有高级用法

    
## AQS思想

- - 全称为（AbstractQueuedSynchronizer）抽象队列式同步器，concurrent包的基石，核心双向双端队列 + 信号state

- - - - **volatile int** **state** 字段:		volatile修饰，CAS 操作，
      - **Node** **head、tail**			获取锁失败，封装成 Node节点插入队尾，释放锁时，唤醒头节点的next节点尝试占用锁

- - ReentrantLock

- - - - 相比synchronized：等待可中断、公平锁、绑定多个条件、tryLock释放，全局变量

- - CountDownLatch
  - CyclicBarrier

- - Semaphore
  - Exchanger



## 线程顺序

- - 线程的join方法：thread2.join();
  - 主线程的join方法：

- - 线程的wait方法、flag
  - 线程池

- - JUC.Condition | CountDownLatch | CyclicBarrier | Sephmore

    
# Mysql

**三范式**

1NF： 字段不可分割

2NF：满足1NF，表中的字段必须完全依赖于全部主键而非部分主键 (一般我们都会做到)

3NF：满足2NF，非主键列之间，不存在依赖，只直接依赖主键

BCNF：不存在依赖


#### 索引优化

- - 走索引

- - - - like 'xx%'
      - in

- - 不走索引

- - - - 负向条件：!=、<>、not in、not exists、not like 等
      - 范围条件：<、<=、>、>=、between等

- - - - 函数，包含默认类型转换
      - NULL值 与 is null, is not null

- - 不建议建索引

- - - - 频繁更新的字段
      - 区分度不大的字段（性别）

- - 最左匹配 index(a,b,c)

- - - - where b and c 不走
      - 可以使用 where a, b order by c

- - - - 可以使用 select c where a,b 避免回表



#### Explain

- - 使用目的

- - - - 表的读取顺序、操作类型
      - 索引使用情况

- - - - 表之间引用
      - 每张表有多少行被优化器查询

- - 注意点

- - - - type字段最好

- - - - - - ALL代表全表查询，必须要优化
          - index：	全索引扫描。MySQL在扫描表时按索引次序进行而不是行

- - - - - - range：	索引范围扫描
          - ref		也叫索引查找,他返回所有匹配某单个值的行,它可能会找到多个符合条件行

- - - - possible_keys 显示了查询可以使用哪些索引
      - key	使用索引，看看索引有没有成功用到

- - - - key_len	索引长度，一般越短越好
      - rows 	为了找到所需的值而要读取的行数



#### 三大日志-binlog、redo log和undo log			

- - binlog：记录数据库执行的写入性操作(不包括查询)信息，以二进制的形式保存在磁盘中	https://segmentfault.com/a/1190000023827696

- - - - 使用场景：主从复制、数据恢复
      - 刷盘机制：默认每次事务提交（还有系统判断、N个事务）

- - - - 日志格式： STATMENT 、 ROW 和 MIXED（存语句、存行、混合）

- - redo log：保存事务提交之后log

- - - - 确保事务的持久性，防止在发生故障的时间点，尚有脏页未写入磁盘，重启根据redo log进行重做，持久性

- - undo log：保存事务提交之前的log

- - - - 保存了事务发生之前的数据的一个版本，可以用于回滚，同时实现MVCC、版本链、readview

- - 区别binlog、redo log

- - - - 实现：redo log 是 InnoDB 引擎的、binlog 是 Server 层实现的所有引擎都可以使用
      - 大小：redo log 循环写入大小是固定，binlog 可通过配置参数，多个

- - - - 场景：redo log 崩溃恢复，binlog主从复制和数据恢复



#### Mysql死锁

- - 背景

- - - - 并发控制有两种方式，一个是 MVCC，一个是两阶段锁协议
      - 两阶段锁协议：事务必须分两个阶段对数据加锁和解锁，在对任何数据进行读、写操作之前，事务首先要获得对该数据的封锁

- - 产生原因

- - - - 两阶段锁协议不要求数据一次性加锁，且无顺序要求

- - MySQL两种方式：于性能原因，一般都是使用死锁检测来进行处理死锁

- - - - 等待直到超时
      - 发起死锁检测，主动回滚一条

- - 死锁检测：	以事务为顶点、锁为边的有向图，判断有向图是否存在环，存在即有死锁
  - 回滚：	检测到死锁之后，选择插入更新或者删除的行数最少的事务回滚

- - 如何避免

- - - - 收集

- - - - - - 利用命令 SHOW ENGINE INNODB STATUS查看死锁原因
          - 调试阶段开启 innodb_print_all_deadlocks，收集所有死锁日志

- - - - 避免

- - - - - - 使用事务，不使用 lock tables
          - 保证没有长事务

- - - - - - 操作完之后立即提交事务，特别是在交互式命令行中
          - 尝试降低隔离级别

- - - - - - 修改多个表或者多个行的时候，将修改的顺序保持一致
          - 创建索引，可以使创建的锁更少





# Redis-



#### 应用场景

- - - 缓存、简单消息队列、分布式锁、共享Session

#### 数据类型

- - - String	SDS，
    - list		ziplist、linkedList（双向队列，可消息，可）

- - - set		intSet、hashtable，交并集合
    - zset		ziplist、skiplist，热搜榜，有排名Set

- - - hash		ziplist、hashtable



#### 跳表

- - - 结构：先实现单向链表，然后在此之上每隔一个建立链表，然后依次往上形成多层
    - 查询：先跳，如果某后续节点大于查找值，则往下层继续查； 复杂度 O(logn)  

- - - 不用红黑树？

- - - - - 效果一样，跳表实现简单
        - 并发跳表有优势，rb 的插入删除需要 rebalance

- - - - - 区间查找

- - - 平衡

- - - - - 随机函数



#### 过期策略

- - - 过期策略：过期处理方式

- - - - - 惰性过期：对内存不友好
        - 定期过期：每隔100ms，查询一部分数据

- - - 淘汰策略：内存不足时

- - - - - 报错
        - allkey，删除LRU

- - - - - allkey，随机删除
        - 设过期时间的，LRU

- - - - - 设过期时间的，随机
        - 设过期时间的，更早过期的

#### LRU 一般实现

- - - 双向链表+头尾节点   ( LinkedHashMap )
    - get：

- - - - - 循环查找为空，返回空
        - move node to last



#### LRU的 Redis 实现			https://www.cnblogs.com/hapjin/p/10933405.html

- - - 额外的存储存放 next 和 prev 指针，牺牲比较大
    - 做法：维护了一个大小固定的pool，每次随机取值，将idle time 大的加进pool，



#### 雪崩、穿透、击穿
- **雪崩：**随机时间、热点不过期
- **穿透：**接口层校验、缓存无效值、布隆过滤器
- **击穿：**热点不过期、互斥锁





#### RDB 持久化

- - - **RDB** 手动执行、服务期执行，二进制文件

- - - - - 默认

900 1	：900s 内存在 1 次执行，则 save 

300 10 	：300s 内存在 10 次执行，则 save 

60 10000	：60s 内存在 1w 次执行，则 save 

- - - - - 机制：条件选择器，serverCron，每100ms 执行一次判断

- - - **AOF** Append Only File 记录redis命令来去记录数据库的变更

- - - - - AOF 越来越大占空间，多语句冗余
        - 默认关闭，打开则会走 aof_buf 缓冲区

- - - - - - - appendfsync always：将 aof_buf 里面的直接写到磁盘，优点：数据不丢失，缺点：效率低
            - appendsync everysec： 每秒进行一次同步（默认）

- - - - - - - appendsync no：把同步交给操作系统，自己不干预

- - - **AOF重写** 解决 AOF 文件体积膨胀问题

- - - - - 实现方式：Redis 服务器创建一个新的 AOF 文件特定时间替代现有 AOF 文件，两个状态都是相同的，但是新文件经过特殊处理，不会包含任何冗余命令，体积就小很多
        - 原理：首先从数据库中读取键现在的值，然后用一条命令去记录键值对，代替之前记录该键值对的多个命令

- - - - - fork 子进程去重写，主进程不会被阻塞

#### Redis为什么这么快
- 完全基于内存、两个哈希表、渐进性 rehash、查找和操作的时间复杂度都是O(1)
- 数据结构简单、专门设计过
- 单线程，避免频繁上下文切换、锁
- 多路复用IO：多个网络连接，复用同一个线程

# Spring

#### Spring事务	隔离级别

- TransactionDefinition接口中定义了五个不同的事务隔离级别 
  - ISOLATION_DEFAULT 这是一个PlatfromTransactionManager默认的隔离级别，同步数据库默认隔离级别
  - ISOLATION_READ_UNCOMMITTED 	    读未提交
  - ISOLATION_READ_COMMITTED 		读提交
  - ISOLATION_REPEATABLE_READ		可重复读
  - ISOLATION_SERIALIZABLE			串行化
  

#### Spring事务传播类型			https://zhuanlan.zhihu.com/p/148504094

- - - 七种类型分别是：REQUIRED、SUPPORTS、MANDATORY、REQUIRES_NEW、NOT_SUPPORTED、NEVER、NESTED
    - 零事务

- - - - - PROPAGATION_NOT_SUPPORTED 	以非事务方式运行，如果有事务存在，挂起当前事务
        - PROPAGATION_NEVER 			以非事务方式运行，如果有事务存在，抛出异常

- - - 同一个事务中

- - - - - PROPAGATION_REQUIRED(默认)	支持当前事务，如果不存在，就新建一个
        - PROPAGATION_SUPPORTS 	支持当前事务，如果不存在，就不使用事务

- - - - - PROPAGATION_MANDATORY 	支持当前事务，如果不存在，抛出异常

- - - 俩事务

- - - - - PROPAGATION_REQUIRES_NEW 	如果有事务存在，挂起当前事务，创建一个新的事务
        - PROPAGATION_NESTED 			如果当前事务存在，则嵌套事务执行

    
#### Spring注入Bean方式

- - - set方法
    - 构造器方法

- - - 静态工厂
    - 实例工厂

- - - @Autowired或者@Resource：

- - - - - Autowired默认使用byType, 若存在多个结果，则会使用byName，可通过Qualifier指定name
        - Resouce有name和type属性，分别对应byName和byType方式，如果属性不赋值则默认使用byName，查询不根据byType进行查询



#### spring bean 生命周期					https://www.cnblogs.com/tuyang1129/p/12861617.html

- - - 实例、与赋值

- - - - - 实例化 bean
        - 依赖注入（设置对象属性）

- - - 初始化

- - - - - 检查实现 Aware 相关接口，并设置相关依赖		BeanNameAware、ApplicationContextAware
        - BeanPostProcessor前置处理 				BeforeInitialization

- - - - - 实现 InitializingBean 接口 afterPropertiesSet 
        - 是否配置自定义的 init-method 方法

- - - - - BeanPostProcessor 后置处理 				AfterInitialization

- - - 使用Bean
    - 销毁阶段

- - - - - 实现 DisposableBean 接口					在容器销毁时，会调用bean的destroy方法
        - 是否配置自定义的 destory-method 方法
        

#### Spring中IOC						https://www.cnblogs.com/tuyang1129/p/12861617.html

- - - 被调用者实例创建的过程交由Spring容器完成，注入调用者，即控制反转、依赖注入，天然单例；开发人员不需要频繁的创建对象，降低bean之间的耦合，更专注于业务代码

- - - IOC实现：

- - - - - 1. 容器解析xml配置文件，提取信息，对每个bean封装成一个BeanDefinitionHolder对象，BeanDefinition对象(配置、类型、依赖、属性)、名称、别名
        - 2.创建 ConcurrentHashMap，k是bean名称，v是BeanDefinition对象的引用

- - - - - 3.创建bean，遍历2的map，获取BeanDefinition，判断有无延迟加载，以及依赖，并创建bean
        - 4.BeanDefinition对bean进行注入

- - - - - 5.创建ConcurrentHashMap，将创建好的单例bean保存在其中。我们在代码中可以通过容器的getBean方法


#### Spring的AOP						https://www.cnblogs.com/tuyang1129/p/12878549.html

- - - aop即Aspect Oriented Program面向切面编程，在不影响原有逻辑上，横向推展新功能
    - aop思想：是将核心业务功能和周边功能(切面)分别独立进行开发，然后把两个功能编织到一起，减少重复代码，降低模块耦合，其中心思想即让关注点代码和业务代码分离

- - - Spring的AOP实现原理--动态代理，只有在类没有实现接口时才会使用CGLib

- - - - - JDK的动态代理，用拦截器（须实现InvocationHandler）+ 反射机制生成一个代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理
        - CGLib 用ASM框架 直接操作字节码，生成类的子类，重写类的方法完成代理

- - - - - 使用：bean实现接口，用Jdk，否则cglib，也可以强制使用cglib
        - 速度：JDK6之前，cglib快，1.8JDK快
        

#### Spring循环依赖问题

- 什么是循环依赖 
  - bean之间相互依赖，形成了一个闭环；比如：A依赖于B、B依赖于C、C依赖于A

- 如何检测 
  - 列表来记录，bean创建之前去查， 
  - singletonsCurrentlyInCreation

- Spring如何解决循环依赖--单例
  - 3级缓存 
    - 单例bean的缓存  singletonObjects  ConcurrentHashMap (256)
    - 早期暴露的bean的缓存  earlySingletonObjects  HashMap<>(16)

  - 单例bean工厂的缓存  singletonFactories  HashMap<>(16)
    - 过程 
      - 从容器中获取serviceA，3个缓存找不到，准备创建
      - 调 serviceA 构造器得到serviceA实例，丢进3级缓存（此时serviceA还未填充属性，未进行其他任何初始化的操作） 
      - serviceA填充属性，发现要注入serviceB，然后向容器获取serviceB，3个缓存找不到，准备创建
      - 调 serviceB的构造器，丢进3级缓存，然后准备填充属性，发现需要注入serviceA
      - 容器发现 serviceA 在第3级缓存中，从3移除，存到2级中，然后返回给serviceB
      - 最后 serviceB 完成创建后，将自己丢到1级缓存中，并从2、3缓存中移除
      - serviceB将自己返回给serviceA，A完成创建，并移到1级缓存中


#### spring mvc 流程

- dispatcherServlet
- HandlerMapping
- ViewResolver

dispatcherServlet（实现 servlet）接收请求 --> 根据 url 查找对应 handler --> HandlerMapping 响应执行链（Interceptor + Handler(Controller)） --> 请求 Handler（controller） --> controller 执行 --> 响应 ModelAndView --> ViewResolver 视图解析(将数据填充到 jsp、freemarker...) --> 响应 




#### springboot启动流程 			[第一篇](https://javamana.com/2021/03/20210331160539740c.html) 	[第二篇](https://blog.csdn.net/hfmbook/article/details/100507083)

- - SpringApplication的创建

- - - - 设置配置类
      - 推断Web容器：基于Netty的WebFlux、servlet的web、不是web

- - - - 从spring.factories中加载ApplicationContextInitializer（初始化构造器）
      - 从spring.factories中加载ApplicationListener

- - - - 推断启动类：RuntimeException获得栈中的包含"main"的方法名

- - SpringApplication的启动

- - - - 应用启动计时器
      - 启动监听器模块 	SpringApplicationRunListener， 通告 starting

- - - - 配置环境模块 	ConfigurableEnvironment
      - 配置彩蛋

- - - - 创建并初始化上下文 ApplicationContext

- - WebServer的创建与启动
  - DispatcherServlet的注册





- - SpringApplication的创建

- - - - 初始化对象，配置基本参数收集加载资源、推断Web容器、推断启动类
      - 从 **spring.factories** 中加载 ApplicationContextInitializer、ApplicationListener

- - SpringApplication的启动

- - - - （非关键）StopWatch 计时、集合收集异常
      - spring.factories 配置文件中加载**SpringApplicationRunListeners**，用来发射启动过程中内置的一些生命周期事件，标志每个不同启动阶段

- - - - 准备环境变量 **ConfigurableEnvironment**
      - 创建 **applicationcontext** 容器，根据不同类型环境，servlet 环境 AnnotationConfigServletWebServerApplicationContext

- - - - 加载 **FailureAnalyzers** 对象，报告SpringBoot启动过程中的异常；
      - 为刚创建的容器对象做一些初始化工作

- - - - **刷新容器**
      - afterRefresh

- - - - **callRunners**，ApplicationRunner和CommandLineRunner的run方法，实现spring容器启动后需要做的一些东西比如加载一些业务数据等

- - **刷新容器**

- - - - 





![img](https://cdn.nlark.com/yuque/0/2021/png/175105/1634897761816-2acaed93-aeb3-4676-9a06-06bec3122a9b.png)









# 消息


# 分布式


#### ACID、CAP、BASE理论

- ACID：强一致性
- CAP：一致性、可用性、分区容错三选二（ZAB、paxos）
- BASE：基本可用、软状态、最终一致性



**2PC	P是指准备阶段，C是指提交阶段**

- 阶段
  - 准备阶段：协调者会发送 prepare 事务请求，参与者执行（但不提交），写Undo、Redo日志，收到响应
  - 提交阶段：协调者根据各参与者的反馈，都是 Yes，执行Redo日志进行**Commit**，否则执行Undo日志进行**Rollback**

- 优点：原理简单、实现方便 
- 缺点：单点故障、阻塞、数据不一致

**3PC 将提交阶段分为CanCommit、PreCommit、DoCommit三个阶段**

- **CanCommit**：发送canCommit请求，并开始等待
- **PreCommit**：收到全部Yes，写Undo、Redo日志。超时或者No，则中断
- **DoCommit**：执行Redo日志进行**Commit**，执行Undo日志进行**Rollback**  

- - - 区别是第二步，参与者**自身增加了超时**，如果**失败可以及时释放资源**



**TCC 补偿事务（**三阶段需保证幂等**）**

- **Try** 阶段：这个阶段说的是对各个服务的资源做检测以及对资源进行**锁定或者预留** 
- **Confirm** 阶段：这个阶段说的是在各个服务中**执行实际的操作**
- **Cancel** 阶段：如果任何一个服务的业务方法执行出错，那么就需要**进行补偿**/回滚 

- - - 缺点：TCC 的 Try、Confirm 和 Cancel 操作功能要按具体业务来实现，业务耦合度较高，提高了开发成本


    
#### 单点登录SSO

- - 认证中心
  - 分布式Session

- - - - 基于JWT的Token，数据从cache或者数据库中获取
      - 基于Tomcat的Redis，简单配置conf文件

- - - - 基于Spring的Redis，支持SpringCloud和Springb

#### 脑裂问题

- - - 产生：leader假死又恢复、两间物理机房断连
    - 解决：

- - - - - 法定人数（Quorum）默认
        - 冗余通信的方式（防止一种误判）

- - - - - 共享资源的方式：能看到共享资源就表示在集群中，能够获得共享资源的锁的就是Leader，看不到共享资源的，就不在集群中











# 框架

#### Sentinel原理			https://springboot.io/t/topic/2799

- - 资源都对应一个资源名称（resourceName），每次资源调用都会创建一个 Entry 对象，Entry 创建时，也会创建一系列功能插槽（slot chain），这些插槽有不同的职责
  - 简介：

- - - - 请求打过来， Entry 会为每一个资源创建一个处理链 ProcessorSlotChain，默认8个，各司其职，全部通过放行
      - Sentinel 流量控制为核心，底层的流量统计是以滑动窗口来完成qps统计的，具体实现是通过名为 LeapArray 

- - 重要插槽 StatisticSlot

- - - - 底层采用高性能的滑动窗口数据结构 LeapArray 来统计实时的秒级指标数据
      - LeapArray 内部是数组，元素是 MetricBucket，窗口时间内通过的请求数、block、异常数、RT(响应时间)这些指标，LongAdder 修饰



#### MQ



**保证消息不丢失**

- - - - **r**abbitMq方案

- - - - - - 生产者：	开启RabbitMQ事务同步 （同步，不推荐）

- - - - - - - - ​    开启confirm模式 （异步，推荐）

- - - - - - MQ：	开启持久化
          - 消费者：	关闭 RabbitMq 自动 ACK

- - - - kafka

- - - - - - 生产者：producer 设置 `acks=all` 要求每条数据，必须是写入所有 replica 之后，才能认为是写成功了

- - - - - - - - producer 端设置 `retries=MAX` 写入失败，就无限重试

- - - - - - kafka： topic 设置 replication.factor 参数，大于 1，每个 partition 至少 2 个副本

- - - - - - - - 服务端设置 min.insync.replicas 参数：大于 1，要求一个 leader 至少感知到有至少一个 follower 还跟自己保持联系

- - - - - - 消费者：可以取消 自动提交 offset，并做好幂等


    
#### Dubbo



**负载均衡**

- 加权随机（默认）
- 加权轮询
- 最少活跃优先 + 加权随机
- 最短响应优先 + 加权随机
- 一致性 Hash	

**RPC调用过程**

1. 调用者（客户端Client）以本地调用的方式发起调用； 
2. Client stub（客户端存根）收到调用后，负责将被调用的方法名、参数等打包编码成特定格式的能进行网络传输的消息体；

　　3. Client stub将消息体通过网络发送给服务端；

　　4. Server stub（服务端存根）收到通过网络接收到消息后按照相应格式进行拆包解码，获取方法名和参数；

　　5. Server stub根据方法名和参数进行本地调用；

　　6. 被调用者（Server）本地调用执行后将结果返回给server stub；

　　7. Server stub将返回值打包编码成消息，并通过网络发送给客户端；

　　8. Client stub收到消息后，进行拆包解码，返回给Client；

　　9. Client得到本次RPC调用的最终结果

![img](https://cdn.nlark.com/yuque/0/2021/png/175105/1635089627141-2d9f212c-a25e-452c-960a-a49f07aad8bf.png)



# 算法

#### 大数方案

- - URL黑名单（布隆过滤器）
  - 100亿个URL中重复的URL 	--- hash分流、hash拆解

- - TOPK搜索 				--- 海量搜索词汇，找到最热TOP100词汇的方法（哈希分流 + 小根堆）
  - 100亿整数小内存取中位数 	--- 分批载入，二进制头位0/1分为俩文件，定位其一，再二进制次高位0/1分...

- - 短域名系统（缓存）		--- 放号器，62进制（a-zA-Z0-9），Redis 存储映射关系，即sBc -> URL
  - 海量评论入库			--- 热搜评论的读写，消息队列异步入库、热评定时载到缓存

- - 线/并发用户数（Redis）	--- 用户ID 写入ZSORT队列，权重为当前时间，Zrange计算一分钟之内的用户数，Zrem删除

----

# 其他

什么是DoS、DDoS、DRDoS攻击？如何防御？
- 拒绝服务（DoS）攻击和分布式拒绝服务（DDoS）
- 通过从单一机器（DoS），多台机器（DDos）发送恶意流量

finally块一定会执行吗？
- try之前报错
- System.exit(0);
- 注意：finally 有返回值则按照finally返回，没有执行但保存try的返回值