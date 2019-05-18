package jdbc;

import mapper.User;

import java.sql.*;
import java.util.ResourceBundle;

public class MyExecutor {


    ResourceBundle jdbcInfo;
    public MyExecutor(ResourceBundle info) {
        this.jdbcInfo=info;
    }

    public <T> T query(String sql, Object paramater) {
        Connection conn = null;
        Statement stmt = null;
        User user = new User();

        try {
            // 注册 JDBC 驱动
            Class.forName(jdbcInfo.getString("jdbc.driver"));

            // 打开连接
            conn = DriverManager.getConnection(jdbcInfo.getString("jdbc.url"), jdbcInfo.getString("jdbc.username"), jdbcInfo.getString("jdbc.password"));

            // 执行查询
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(String.format(sql, paramater));

            // 获取结果集
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String addr = rs.getString("address");
                Integer age = rs.getInt("age");

                user.setId(id);
                user.setName(name);
                user.setAddr(addr);
                user.setAge(age);
            }
            System.out.println(user);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return (T)user;
    }

    }
