package jdbc;

import mapper.UserMapper;

public class Test {
    public static void main(String[] args) {
        MyConfiguration configuration =  new MyConfiguration();
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MyExecutor(configuration.jdbcInfo));
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.selectUserById(1);
    }
}
