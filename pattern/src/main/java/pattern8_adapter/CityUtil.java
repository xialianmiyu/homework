package pattern8_adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityUtil {


    public static List qryCity(){
        List list=new ArrayList();
        //mock qry by db
        list.add(new City("北京","beijing","001"));
        list.add(new City("广州","guangzhou","004"));
        list.add(new City("上海","shanghai","002"));
        list.add(new City("深圳","shenzhen","003"));
        return list;

    }

    public static List qryCityOrderByCode(){
        List list=qryCity();

        Collections.sort(list);
        return list;
    }

}
