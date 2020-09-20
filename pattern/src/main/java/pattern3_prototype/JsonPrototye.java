package pattern3_prototype;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;

public class JsonPrototye {

    public static void main(String[] args) {
        Person person1=new Person();
        Address addr=new Address();
        person1.setName("tom");
        person1.setAddr(addr);

        String json=sendMsg(person1);
        Person person2=(Person)receiveMsg(json,Person.class);

    }
    public static String sendMsg(Object object){

        return JSON.toJSONString(object);
    }
    public static Object receiveMsg(String json,Class T){
        return JSON.parseObject(json,T);
    }
}

class Person {
    private String name;
    private Address addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }
}

class Address {
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
