package pattern3_prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 给手机号，发送营销短信
 */
public class SendMessagePropoty {
    public static void main(String[] args) throws CloneNotSupportedException {
        sendMsg();
    }

    private static void sendMsg() throws CloneNotSupportedException {
        Message msg = new Message("MSG01", "您好，现在申卡将获取100块京东抵用券");
        List<String> mobileList = qryMobileList();
        for (int i = 0; i < mobileList.size(); i++) {
            Message clone = msg.clone();
            clone.setMobile(mobileList.get(i));
            System.out.println("发送短信:" + clone);
        }
    }

    private static List<String> qryMobileList() {
        System.out.println("模拟查询数据库");
        List<String> mobileList = new ArrayList<>();
        mobileList.add("13523615231");
        mobileList.add("13625334962");
        mobileList.add("15362412187");
        mobileList.add("13254846324");
        mobileList.add("15846234846");
        mobileList.add("14513626263");
        return mobileList;
    }
}

class Message implements Cloneable {

    private String content;//短信内容
    private String msgTempNo;//短信里其他固定属性
    private String mobile;//手机号

    public Message(String msgTempNo, String content) {
        this.msgTempNo = msgTempNo;
        this.content = content;
    }

    @Override
    protected Message clone() throws CloneNotSupportedException {
        return (Message) super.clone();
    }

    public String getMsgTempNo() {
        return msgTempNo;
    }

    public void setMsgTempNo(String msgTempNo) {
        this.msgTempNo = msgTempNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgTempNo='" + msgTempNo + '\'' +
                ", content='" + content + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
