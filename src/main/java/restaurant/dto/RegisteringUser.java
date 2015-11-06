package restaurant.dto;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class RegisteringUser {

    @Size(min = 1, max = 50)
    private String name;

    @Column(unique = true)
    @Size(min = 1, max = 100)
    @Email
    private String email;

    @Size(min = 6, max = 50)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
