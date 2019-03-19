package pattern1_factory.simple;



import pattern1_factory.Computer;
import pattern1_factory.Dell;
import pattern1_factory.Lenovo;

public class SimpleFactory {

    public Computer getComputer(String name){
        if("lenovo".equals(name)){
            return new Lenovo();
        }else if("dell".equals(name)){
            return new Dell();
        }else{
            return null;
        }
    }
}
