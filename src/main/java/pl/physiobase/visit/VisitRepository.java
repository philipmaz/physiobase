package pl.physiobase.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.physiobase.visit.Visit;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT e FROM Visit e WHERE e.date = ?1")
    List<Visit> findAllByDate(String date);

    Visit findById(long id);



}
