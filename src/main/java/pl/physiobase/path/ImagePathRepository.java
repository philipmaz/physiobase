package pl.physiobase.path;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.physiobase.training.Training;

import java.util.List;

public interface ImagePathRepository extends JpaRepository<Imagepath, Long> {

}
