package cn.cat.netty.es.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Getter
@Document(indexName = "stack")
public class User {
    @Id
    private String id;
    private String name;   //姓名
    private Integer age;   //年龄
    private String level;  //级别
    private Date entryDate;//时间
    private String mobile; //电话
    private String email;  //邮箱
    private String address;//地址

    public User() {
    }

    public User(String id, String name, Integer age, String level, Date entryDate, String mobile, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.level = level;
        this.entryDate = entryDate;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
