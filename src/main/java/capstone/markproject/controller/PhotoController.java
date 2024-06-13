package capstone.markproject.controller;

import capstone.markproject.entity.Photo;
import capstone.markproject.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable Long id) {
        return photoService.getPhotoById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }
}
