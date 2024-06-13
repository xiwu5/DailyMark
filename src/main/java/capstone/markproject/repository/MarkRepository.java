package capstone.markproject.repository;

import capstone.markproject.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/*
    MarkRepository facilitates seamless interaction with the database for Mark entities,
    offering both standard CRUD operations and custom query methods tailored to specific data retrieval needs.
 */
public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query("SELECT m FROM Mark m WHERE m.title LIKE %:keyword%")
    List<Mark> findByTitleContaining(@Param("keyword") String keyword);

    @Query("SELECT m FROM Mark m WHERE m.user.id = :userId AND m.clickDate = :date")
    List<Mark> findByUserId(@Param("userId") Long userId, @Param("date") LocalDate date);

    @Query("SELECT m FROM Mark m ORDER BY m.createdDate DESC")
    List<Mark> findAllOrderByCreatedDateDesc();

    // Add the new method to check if marks exist on a specific date
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mark m WHERE m.clickDate = :date")
    boolean existsByDate(@Param("date") LocalDate date);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mark m WHERE m.clickDate = :date AND m.user.id = :userId")
    boolean existsByDateAndUserId(@Param("date") LocalDate date, @Param("userId") Long userId);

}
