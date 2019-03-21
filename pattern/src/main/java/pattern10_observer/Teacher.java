package pattern10_observer;

import com.google.common.eventbus.Subscribe;

import java.util.Observable;
import java.util.Observer;


public class Teacher{
    private String name;
    public Teacher(String name){
        this.name = name;
    }

    @Subscribe
    public void update( Object quest) {

        Question question = (Question)quest;
        System.out.println("===============================");
        System.out.println(name + "老师，你好！\n" +
        "您收到了一个的提问，希望您解答，问题内容如下：\n" +
        question.getContent() + "\n" +
        "提问者：" + question.getUserName());
    }
}
