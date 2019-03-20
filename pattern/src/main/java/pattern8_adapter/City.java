package pattern8_adapter;

public class City implements Comparable {
    private String name;
    private String pinyin;
    private String code;

    public City(String name, String pinyin, String code) {
        this.name = name;
        this.pinyin = pinyin;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        City city= (City) o;
        return Integer.parseInt(this.code)-Integer.parseInt(city.code);
    }
}
