package pattern3_prototype;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Prototype {

    public static void main(String[] args) {
        String url="name=tim&age=20&address=广州";
        Student student=parseWithoutPrototype(url);
        System.out.println(student.toString());

        student=parseWithPrototype(url);
        System.out.println(student.toString());
    }
    private static Student parseWithPrototype(String paramurl){
        Student student=new Student();
        /*Class clazz=pattern3_prototype.Student.class;
        Object obj = clazz.newInstance();*/
        Map paramMap=paramToMap(paramurl);
        try {
            org.apache.commons.beanutils.BeanUtils.populate(student, paramMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return student;
    }
    private static Student parseWithoutPrototype(String paramurl){
        Student student=new Student();
        String[] params=paramurl.split("&");
        for(String param:params){
            String[] fields= param.split("=");
            if(fields.length==2){
                if(fields[0].equals("name")){
                    student.name=fields[1];
                }else if(fields[0].equals("age")){
                    student.age=Integer.parseInt(fields[1]);
                }else if(fields[0].equals("address")){
                    student.address=fields[1];
                }
            }
        }
        return student;
    }

    public static Map<String, String> paramToMap(String paramStr) {
        String[] params = paramStr.split("&");
        Map<String, String> resMap = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                resMap.put(key, value);
            }
        }
        return resMap;
    }

}


