package capstone.markproject.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

/*
    User entity class encapsulates the structure and behavior related to users in the application,
    facilitating database operations and maintaining associations with other entities like Mark and Role.
 */
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Mark> marks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    // Constructors
    public User() {
    }

    public User(String username, String email, String password, Long id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    // Getters and setters

    public Set<Role> getRoles() {
        return roles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
