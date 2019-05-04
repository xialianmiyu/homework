package dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user")
@Data
public class User implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String address;
    private Integer age;
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
