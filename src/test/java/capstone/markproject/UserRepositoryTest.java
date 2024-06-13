package capstone.markproject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import capstone.markproject.entity.User;
import capstone.markproject.repository.UserRepository;

public class UserRepositoryTest {

    private UserRepository userRepository = mock(UserRepository.class);

    @Test
    public void testFindByUsername() {
        // Mock behavior of UserRepository.findByUsername()
        String username = "johnkk@example.com";
        User expectedUser = new User("Johnkk", username, "johnkk@example.com", 1L);
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        // Call the method under test
        User user = userRepository.findByUsername(username);

        // Assert the result
        assertNotNull("User should not be null", user);
        assertEquals("johnkk@example.com", user.getEmail());
    }


}
