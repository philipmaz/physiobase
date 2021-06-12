package pl.physiobase.path;

import pl.physiobase.patient.Patient;

import javax.persistence.*;

@Entity
@Table(name="imagepaths")
public class Imagepath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;
    @ManyToOne
    private Patient patient;

    public Imagepath() {
    }

    public Patient getPatient() {
        return patient;
    }

    public Imagepath setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public long getId() {
        return id;
    }

    public Imagepath setId(long id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Imagepath setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
