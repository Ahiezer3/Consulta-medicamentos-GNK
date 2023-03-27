
package models;

import java.util.Date;

/**
 * Class for User model.
 * @author abi_h
 * @since 24/03/2023
 */
public class User {
    
    private Long id;
    private String name;
    private String lastname;
    private Date birthday;
    private String password;
    private Date date_register;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate_register() {
        return date_register;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
    }
    
}
