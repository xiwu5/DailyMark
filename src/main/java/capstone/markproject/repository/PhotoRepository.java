package capstone.markproject.repository;

import capstone.markproject.entity.Photo;
import capstone.markproject.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByMark(Mark mark);
}
