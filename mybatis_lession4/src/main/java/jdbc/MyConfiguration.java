package jdbc;

import com.mysql.jdbc.MySQLConnection;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class MyConfiguration {
    public  static final ResourceBundle sqlMapping;
    static {
        sqlMapping=ResourceBundle.getBundle("sql");

    }
    public <T> T getMapper(Class clazz, MySqlSession sqlSession){
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new MyMapperProxy(sqlSession));
    }
}
