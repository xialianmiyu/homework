
import dto.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest1 {
    public static void main(String[] args) {
        System.out.println(select("select * from user"));
    }
    private static List<User> select(String sql) {
        List<User> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1、加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //2、建立连接
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gupao", "root", "123456");
            //3、创建语句集
            pstm = con.prepareStatement(sql);
            //4、执行语句集
            rs = pstm.executeQuery();
            while (rs.next()) {
                User instance = mapperRow(rs, rs.getRow());
                result.add(instance);
            }


            //5、获取结果集
        } catch (Exception e) {
            e.printStackTrace();
        }
        //6、关闭结果集、关闭语句集、关闭连接
        finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static User mapperRow(ResultSet rs, int i) throws Exception {
        User instance = new User();
        instance.setId(rs.getInt("id"));
        instance.setName(rs.getString("name"));
        instance.setAge(rs.getInt("age"));
        instance.setAddress(rs.getString("address"));
        return instance;
    }
}