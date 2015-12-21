package net.codejava.spring.model;

import javax.persistence.*;

/**
 * Created by Zeus on 20.12.15.
 */
@Entity
@Table(name = "phones")
public class phones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phoneid")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

   @ManyToOne(cascade=CascadeType.ALL)
   private User user;


   // @Id
    //@GeneratedValue
    //@Column(name = "phone_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
//cascade=CascadeType.ALL


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
       this.user = user;
    }
}
