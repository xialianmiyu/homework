package pattern4_proxy.myproxy;

/**
 * 通过网站代理找工作
 */
public class Test {
    public static void main(String[] args) {
        try {
            Person obj = (Person) new Job51().getInstance(new My());
            System.out.println(obj.getClass());
            obj.findJob();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
