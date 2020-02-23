package com.stepyen.xlearn.activity.java.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * date：2020-01-08
 * author：stepyen
 * description：学习反射
 */
public class ReflectTest {
    public static void main(String[] args) {

        testInvokeMethod();

        testInvokeMethodHasImplements();
    }


    public static void testInvokeMethod() {

        // 调用方法
        try {
            Class<?> userClass = Class.forName("com.stepyen.xlearn.activity.java.reflect.User");
            User user = (User) userClass.newInstance();
            Method getNameMethod =  userClass.getMethod("getName");
            getNameMethod.setAccessible(true);

            String name = (String) getNameMethod.invoke(user);

            System.out.println("user name :"+name);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
    public static void testInvokeMethodHasImplements() {
        // 调用方法-参数是接口
        try {
            Class<?> userClass = Class.forName("com.stepyen.xlearn.activity.java.reflect.User");

            User user = (User) userClass.newInstance();

            Class<?> onChangeNameListenClass = Class.forName("com.stepyen.xlearn.activity.java.reflect.OnChangeNameListen");

            Method setOnChangeNameListenMethod =  userClass.getMethod("setOnChangeNameListen",onChangeNameListenClass);

            TestInvocationHandler handler = new TestInvocationHandler();
            Object proxyInstance = Proxy.newProxyInstance(userClass.getClassLoader(), new Class[]{onChangeNameListenClass}, handler);

            setOnChangeNameListenMethod.invoke(user,proxyInstance);

            user.setName("xiaming123");
            user.setName("xiaming1231");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }



   public static class TestInvocationHandler implements InvocationHandler {

       @Override
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           System.out.println("method: "+method.getName());
           System.out.println("method   args: "+args[0]);
           return null;
       }
   }
}
