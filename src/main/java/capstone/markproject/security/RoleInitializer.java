package capstone.markproject.security;

import capstone.markproject.entity.Role;
import capstone.markproject.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
    RoleInitializer is a Spring component that initializes predefined roles
    in the application’s database using RoleRepository,
    ensuring these roles are available for managing user access and permissions.
 */

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create admin role if it doesn't exist
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        // Create user role if it doesn't exist
        if (!roleRepository.existsByName("ROLE_USER")) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
    }
}

