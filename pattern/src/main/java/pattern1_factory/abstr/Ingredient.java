package pattern1_factory.abstr;

public class Ingredient {
    String name;

    public Ingredient(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
class Cpu extends Ingredient{
    public Cpu(String name){
        super(name);
    }
}
class Board extends Ingredient{
    public Board(String name){
        super(name);
    }
}
class Cache extends Ingredient{
    public Cache(String name){
        super(name);
    }
}

class LenovoCpu extends Cpu{
    public LenovoCpu(String name){
        super(name);
    }
}
class DellCpu extends Cpu{
    public DellCpu(String name){
        super(name);
    }
}
class LenovoBoard extends Board{
    public LenovoBoard(String name){
        super(name);
    }
}
class DellBoard extends Board{
    public DellBoard(String name){
        super(name);
    }
}

class LenovoCache extends Cache{
    public LenovoCache(String name){
        super(name);
    }
}
class DellCache extends Cache{
    public DellCache(String name){
        super(name);
    }
}