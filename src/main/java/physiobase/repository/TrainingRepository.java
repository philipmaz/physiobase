package physiobase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import physiobase.entity.Training;
import physiobase.entity.Visit;

import javax.transaction.Transactional;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT e FROM Training e WHERE e.date = ?1")
    List<Training> findAllByDate(String date);


    Training findById(long id);



}
