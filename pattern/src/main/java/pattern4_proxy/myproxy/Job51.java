package pattern4_proxy.myproxy;



import java.io.Serializable;
import java.lang.reflect.Method;


public class Job51 implements MyInvocationHandler , Serializable {
    private Object target;
    public Object getInstance(Object target) throws Exception{
        this.target = target;
        Class<?> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(),clazz.getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("介绍公司");
        System.out.println("安排面试");
        Object obj = method.invoke(this.target,args);
        System.out.println("上班");
        return obj;
    }


}
