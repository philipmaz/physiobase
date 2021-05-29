package pl.physiobase.admin;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp="^\\S+@\\S+$", message = "Email: provide correct email address. Must contain '@'.")
    private String email;
    @Size(min=5, message = "Password: Must contain at least 5 characters.")
    private String password;

    public Admin() {
    }

    public long getId() {
        return id;
    }

    public Admin setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Admin setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
