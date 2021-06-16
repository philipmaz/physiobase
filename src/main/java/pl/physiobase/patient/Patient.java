package pl.physiobase.patient;

import pl.physiobase.path.Imagepath;
import pl.physiobase.training.Training;
import pl.physiobase.visit.Visit;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "First Name: can not be blank.")
    private String firstName;
    @NotBlank(message = "Last Name: can not be blank.")
    private String lastName;
    @NotBlank(message = "Gender: can not be blank.")
    private String sex;
    @Size(min=11, max=11, message = "Pesel: must have exactly 11 characters.")
    private String pesel;
    private String address;
    private String phoneNumber;
    @Pattern(regexp="^\\S+@\\S+$", message = "Email: provide correct email address. Must contain '@'.")
    private String email;
    private String diagnostic;

    @OneToMany(mappedBy = "patient")
    private List<Imagepath> imagePaths=new ArrayList<>();


    @OneToMany(mappedBy = "patient")           //(cascade = CascadeType.REMOVE, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<Visit> visits = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    //    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private List<Training> trainings=new ArrayList<>();
    @AssertTrue(message = "Data processing: Statement must be checked and agreed.")
    private boolean isAgreement;




    public Patient() {
    }

    public List<Imagepath> getImagePaths() {
        return imagePaths;
    }

    public Patient setImagePaths(List<Imagepath> imagePaths) {
        this.imagePaths = imagePaths;
        return this;
    }

    public boolean getIsAgreement() {
        return isAgreement;
    }

    public Patient setIsAgreement(boolean agreement) {
        isAgreement = agreement;
        return this;
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

    public List<Training> getTrainings() {
        return trainings;
    }

    public Patient setTrainings(List<Training> trainings) {
        this.trainings = trainings;
        return this;
    }

    public boolean isAgreement() {
        return isAgreement;
    }

    public Patient setAgreement(boolean agreement) {
        isAgreement = agreement;
        return this;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public Patient setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
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
                ", diagnostic='" + diagnostic + '\'' +
                ", visits=" + visits +
                ", trainings=" + trainings +
                ", isAgreement=" + isAgreement +
                '}';
    }
}
