package physiobase.entity;

import javax.persistence.*;

@Entity
@Table(name="visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String date;
    private String description;
    private long painScale;
    private String codeICD;


    public Visit() {
    }

    public long getId() {
        return id;
    }

    public Visit setId(long id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Visit setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Visit setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getPainScale() {
        return painScale;
    }

    public Visit setPainScale(long painScale) {
        this.painScale = painScale;
        return this;
    }

    public String getCodeICD() {
        return codeICD;
    }

    public Visit setCodeICD(String codeICD) {
        this.codeICD = codeICD;
        return this;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", painScale=" + painScale +
                ", codeICD='" + codeICD + '\'' +
                '}';
    }
}
