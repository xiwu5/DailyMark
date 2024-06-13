package capstone.markproject.service;

import capstone.markproject.entity.Photo;
import capstone.markproject.entity.Mark;
import capstone.markproject.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getPhotosByMark(Mark mark) {
        return photoRepository.findByMark(mark);
    }

    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
