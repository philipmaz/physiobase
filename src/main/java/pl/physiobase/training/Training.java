package pl.physiobase.training;

import javax.persistence.*;

@Entity
@Table(name="trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String date;
    private String description;


    public Training() {
    }

    public long getId() {
        return id;
    }

    public Training setId(long id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Training setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Training setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
