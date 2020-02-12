package com.stepyen.xlearn.java.DesignPattern;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * date：2020-01-17
 * author：stepyen
 * description： 代理模式
 */
public class JavaProxy {


    public static void main(String[] args) {
        // 静态代理模式
        UserDao userDao = new UserDao();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
        userDaoProxy.save();

        // 动态代理模式
        // 目标对象
        IUserDao target = new UserDao();
        System.out.println(target.getClass()); // 【原始的类型 class com.stepyen.javalib.DesignMode.proxy.UserDao】
        // 给目标对象，创建代理对象
       IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());  // class com.sun.proxy.$Proxy0 内存中动态生成的代理对象
        // 执行方法   【代理对象】
        proxy.save();


        // Cglib代理    暂时还没有见过
    }


    public interface IUserDao {
        void save();
    }



    public static class UserDao implements IUserDao {

        @Override
        public void save() {
            System.out.println("----保存数据!----");
        }
    }





    public static class ProxyFactory {

        //维护一个目标对象
        private Object target;

        public ProxyFactory(Object target) {
            this.target = target;
        }

        //给目标对象生成代理对象
        public Object getProxyInstance() {
            return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("开始事务2");
                            //执行目标对象方法
                            Object returnValue = method.invoke(target, args);
                            System.out.println("提交事务2");
                            return returnValue;
                        }
                    }
            );
        }
    }




    public static class UserDaoProxy implements IUserDao {

        private UserDao mUserDao;

        public UserDaoProxy(UserDao userDao) {
            this.mUserDao = userDao;
        }

        @Override
        public void save() {
            if (mUserDao != null) {
                System.out.println("保存事务开始");
                mUserDao.save();
                System.out.println("保存事务结束");
            }
        }
    }


}
