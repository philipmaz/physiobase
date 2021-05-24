package physiobase.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String sex;
    private String pesel;
    private String address;
    private String phoneNumber;
    private String email;
    @OneToMany
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Visit> visits=new ArrayList<>();
    private boolean isAgreement;

    public boolean getIsAgreement() {
        return isAgreement;
    }

    public Patient setIsAgreement(boolean agreement) {
        isAgreement = agreement;
        return this;
    }

    public Patient() {
    }

    public long getId() {
        return id;
    }

    public Patient setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Patient setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Patient setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Patient setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getPesel() {
        return pesel;
    }

    public Patient setPesel(String pesel) {
        this.pesel = pesel;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Patient setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Patient setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Patient setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public Patient setVisits(List<Visit> visits) {
        this.visits = visits;
        return this;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", pesel='" + pesel + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", visits=" + visits +
                ", isAgreement=" + isAgreement +
                '}';
    }
}
