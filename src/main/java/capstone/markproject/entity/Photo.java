package capstone.markproject.entity;

import jakarta.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "photo_bytes", columnDefinition = "BLOB")
    private byte[] bytes;

    @OneToOne
    @JoinColumn(name = "mark_id")
    private Mark mark;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
