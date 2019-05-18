package jdbc;



import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class MyConfiguration {
    public  static final ResourceBundle sqlMapping;
    public static final ResourceBundle jdbcInfo;
    static {
        sqlMapping=ResourceBundle.getBundle("sql");
        jdbcInfo=ResourceBundle.getBundle("mybatis");
    }
    public <T> T getMapper(Class clazz, MySqlSession sqlSession){
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new MyMapperProxy(sqlSession));
    }
}
