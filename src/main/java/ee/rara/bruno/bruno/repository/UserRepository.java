package ee.rara.bruno.bruno.repository;

import ee.rara.bruno.bruno.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
