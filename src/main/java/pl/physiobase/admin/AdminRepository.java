package pl.physiobase.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin , Long> {

    @Query("Select e FROM Admin e WHERE e.email= ?1")
    Admin findByEmail(String email);
}
