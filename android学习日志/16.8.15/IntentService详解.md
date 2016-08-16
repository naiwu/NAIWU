
	IntentService是Service类的子类，用来处理异步请求。
	客户端可以通过startService(Intent)方法传递请求给IntentService。
	IntentService在onCreate()函数中通过HandlerThread单独开启一个线程来处理
	所有Intent请求对象（通过startService的方式发送过来的）所对应的任务，这样以免事务处理阻塞主线程。
	执行完所一个Intent请求对象所对应的工作之后，如果没有新的Intent请求达到，则自动停止Service；
	否则执行下一个Intent请求所对应的任务。
	
	IntentService在处理事务时，还是采用的Handler方式，创建一个名叫ServiceHandler的内部Handler，
	并把它直接绑定到HandlerThread所对应的子线程。 ServiceHandler把
	处理一个intent所对应的事务都封装到叫做onHandleIntent的虚函数；因此我们直接实现虚函数onHandleIntent，
	再在里面根据Intent的不同进行不同的事务处理就可以了。
	
	另外，IntentService默认实现了Onbind（）方法，返回值为null。
	
	　　使用IntentService需要两个步骤：
	
	　　1、写构造函数
	
	　　2、实现虚函数onHandleIntent，并在里面根据Intent的不同进行不同的事务处理就可以了。
	
	好处：处理异步请求的时候可以减少写代码的工作量，比较轻松地实现项目的需求
	
	注意：IntentService的构造函数一定是参数为空的构造函数，然后再在其中调用super("name")这种形式的构造函数。
	
	因为Service的实例化是系统来完成的，而且系统是用参数为空的构造函数来实例化Service的


----------
