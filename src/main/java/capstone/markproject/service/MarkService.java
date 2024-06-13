package capstone.markproject.service;

import capstone.markproject.entity.Mark;
import capstone.markproject.repository.MarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
    MarkService encapsulates the business logic for managing Mark entities within the application,
    providing methods to perform CRUD operations and additional functionality like checking for
    marks on specific dates and updating entity details based on ID.
 */

@Service
public class MarkService {
    private static final Logger logger = LoggerFactory.getLogger(MarkService.class);

    @Autowired
    private MarkRepository markRepository;

    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    public Optional<Mark> getMarkById(Long id) {
        return markRepository.findById(id);
    }

    public Mark createMark(Mark mark) {
        return markRepository.save(mark);
    }

    public boolean hasMarksOnDate(String date, Long userId) {
        LocalDate localDate = LocalDate.parse(date);
        return markRepository.existsByDateAndUserId(localDate, userId);

    }

    public List<Mark> findMarksByUserId(Long userId, String date) {
        LocalDate localDate = LocalDate.parse(date);
        return markRepository.findByUserId(userId, localDate);
    }


    public Mark updateMark(Long id, Mark markDetails) {
        Mark mark = markRepository.findById(id).orElseThrow(() -> new RuntimeException("Mark not found with id " + id));
        mark.setTitle(markDetails.getTitle());
        mark.setDescription(markDetails.getDescription());
        return markRepository.save(mark);
    }

    public void deleteMark(Long id) {
        markRepository.deleteById(id);
    }

    public void saveOrUpdateMark(Mark mark) {
        if (mark.getId() != null) {
            // If the mark already has an ID, it means it's an existing mark, so update it
            markRepository.save(mark);
        } else {
            // If the mark doesn't have an ID, it means it's a new mark, so save it
            markRepository.save(mark);
        }
    }
}
