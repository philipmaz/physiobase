package physiobase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import physiobase.entity.Visit;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT e FROM Visit e WHERE e.date = ?1")
    List<Visit> findAllByDate(String date);

//    @Query("DELETE FROM Visit e ")
//    List<Visit>


    Visit findById(long id);



}
