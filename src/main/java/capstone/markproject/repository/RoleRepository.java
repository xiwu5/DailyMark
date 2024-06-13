package capstone.markproject.repository;

import capstone.markproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String roleAdmin);
}
