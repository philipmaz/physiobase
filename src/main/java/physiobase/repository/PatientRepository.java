package physiobase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import physiobase.entity.Patient;
import physiobase.entity.Visit;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT DISTINCT e FROM Patient e LEFT JOIN FETCH e.visits")
    List<Patient> findAllPatientsWithVisits();

    @Query("SELECT DISTINCT e FROM Patient e LEFT JOIN FETCH e.visits WHERE e.lastName LIKE ?1%")
    List<Patient> findAllByLastName(String lastName);

    @Query("SELECT DISTINCT e FROM Patient e LEFT JOIN FETCH e.visits WHERE e.id=?1")
    Patient findByIdWithVisits(long id);

    Patient findById(long id);
}
