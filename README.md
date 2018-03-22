# aidl 使用demo

标签（空格分隔）： aidl binder stub proxy

---
![demo.gif](https://upload-images.jianshu.io/upload_images/3748728-8a8ea72bb828b038.gif?imageMogr2/auto-orient/strip)
主要包括两个部分：


## 利用aidl接口实现进程间通信
1、创建aidl文件
    我们在src的Package下创建一个aidl文件后，（Android Studio->File->New->AIDL->AIDL file），系统会自动这个.aidl文件放到一个aidl的目录下。
    ![示意图.png](https://upload-images.jianshu.io/upload_images/3748728-d86a6ef50b2d0b10.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
    编译完成后，android studio会自动在对于的build目下生成.java文件，路径为（....\AIDLmaster\app\build\generated\source\aidl\debug\com\gupo\jiucheng\aidl_master）

2、创建一个Service暴露AIDL接口并实现AIDL的接口函数：[AutoService][1]


3、在activity中通过bindService绑定这个服务，即可以获得AIDL的接口调用的引用：[AutoActivity][2]


**注意：** demo中，aidl接口中除了传递基本数据类型外，还有自定义的数据[Student.java][3]类型，实现自定义数据的传递需要两步：

 - 添加一个自定义对象类，并且要实现Parcelable接口
 - 在AIDL目录下的相同Pacage下添加一个同名的aidl文件

    
**in、out & inout**  
我们在使用“[void setStudent(in Student data)][4]”这个接口的参数有一个“in”修饰符。

<blockquote>in、out和inout表示数据的流向。大家可以把AIDL的客户端和服务端理解成两个进程（其实大多数情况也是这样才会使用AIDL），从客户端流向服务端用in表示，表示这个对象是从客户端中传递到服务端，在服务端修改这个对象不会对客户端输入的对象产生影响
。而out则表示，数据只能从服务端影响客户端，即客户端输入这个参数时，服务端并不能获取到客户端的具体实例中的数据，而是生成一个默认数据，但是服务端对这个默认数据的修改会影响到客户端的这个类对象实例发生相应的改变。
</blockquote>


  
## 手动方式创建AIDL（不依赖AIDL工具，手写远程AIDL的代码完成跨进程通信）

AIDL的代码生成器，已经根据.aidl文件自动帮我们生成Proxy、Stub（抽象类）两个类，并且把客户端代理mRemote的transact()过程以及服务器端的onTtransact()过程默认实现好了，我们只需要在服务端继承Stub，实现自己的业务方法即可。  

不过这一过程我们可以自己实现，也加深对aidl、对Binder机制的理解。

具体代码参见[manual部分][5]
 


  [1]: https://github.com/yjiucheng/AIDL-master/blob/master/app/src/main/java/com/gupo/jiucheng/aidl_master/auto/AutoService.java
  [2]: https://github.com/yjiucheng/AIDL-master/blob/master/app/src/main/java/com/gupo/jiucheng/aidl_master/auto/AutoActivity.java
  [3]: https://github.com/yjiucheng/AIDL-master/blob/master/app/src/main/java/com/gupo/jiucheng/aidl_master/auto/bean/Student.java
  [4]: https://github.com/yjiucheng/AIDL-master/blob/master/app/src/main/aidl/com/gupo/jiucheng/aidl_master/auto/IAutoAidl.aidl
  [5]: https://github.com/yjiucheng/AIDL-master/tree/master/app/src/main/java/com/gupo/jiucheng/aidl_master/manual
