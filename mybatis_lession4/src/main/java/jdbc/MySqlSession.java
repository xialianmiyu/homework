package jdbc;

public class MySqlSession {

    private MyConfiguration configuration;
    private MyExecutor executor;

    public MySqlSession(MyConfiguration configuration) {
        this.configuration = configuration;
        this.executor = new MyExecutor(configuration);
    }

    public <T> T selectOne(String statementId, Object paramater){

        String sql = MyConfiguration.sqlMapping.getString(statementId);
        if(null != sql && !"".equals(sql)){
            return executor.query(sql, paramater );
        }
        return null;
    }

    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz, this);
    }
}
