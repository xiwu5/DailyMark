package capstone.markproject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    Mark entity class encapsulates the structure and behavior related to marks in the application,
    facilitating database operations and maintaining associations with other entities like User and Photo.
    (One-to-One with Photo and Many-to-One with User)
 */
@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;

    private LocalDateTime createdDate; // Date the user filled in the form
    private LocalDate clickDate; // Date recorded when user clicked


    @OneToOne(mappedBy = "mark", cascade = CascadeType.ALL, orphanRemoval = true)
    private Photo photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Mark() {
        this.createdDate = LocalDateTime.now();
    }

    // Constructor with user parameter
    public Mark(User user, String title, String category, String description, LocalDate clickDate) {
        this();
        this.user = user;
        this.title = title;
        this.description = description;
        this.category = category;
        this.clickDate = clickDate;

    }

    // Getters and setters for other attributes
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    public LocalDate getDate() {
        return clickDate;
    }

    public void setDate(LocalDate clickDate) {
        this.clickDate = clickDate;
    }
}
