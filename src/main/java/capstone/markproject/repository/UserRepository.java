package capstone.markproject.repository;

import capstone.markproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    UserRepository facilitates efficient database interaction for User entities,
    offering standard CRUD operations and a specialized query method (findByUsername)
    to retrieve a user entity based on their username.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
