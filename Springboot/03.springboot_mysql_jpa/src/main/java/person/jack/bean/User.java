package person.jack.bean;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class User {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_age")
    private String userAge;

    @Column(name = "user_sex")
    private String userSex;

    public User(){
        //System.out.println("对象实例化！");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
