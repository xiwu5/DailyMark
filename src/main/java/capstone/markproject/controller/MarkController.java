package capstone.markproject.controller;
/*
    Create standard controllers to serve Thymeleaf templates.
    These controllers will handle requests for pages and pass data to the templates:
       - Manages CRUD operations for marks (showMarkForm, saveMark, updateMark, deleteMark).
       - Handles displaying mark details (showMarkDetail).
       - Checks for existing marks (checkMarksOnDate).
       - Integrates with user authentication (@AuthenticationPrincipal) to associate marks with users.
 */

import capstone.markproject.entity.Mark;
import capstone.markproject.entity.User;
import capstone.markproject.repository.UserRepository;
import capstone.markproject.service.MarkService;
import capstone.markproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/marks")
public class MarkController {
    //private static final long MAX_FILE_SIZE = 1024L * 1024L * 1024L; // 1 GB
    //public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    private static final Logger logger = LoggerFactory.getLogger(MarkController.class);
    @Autowired
    private MarkService markService;

    @Autowired
    private UserService userService;
    private Object userDetails;

    private Mark mark;



    @GetMapping("/new")
    public String showMarkForm(Model model) {
        model.addAttribute("mark", new Mark());
        logger.warn("CELINE showMarkForm");
        return "mark_form";
    }


    @GetMapping("/{id}")
    public String showMarkDetail(@PathVariable Long id, Model model) {
        Mark mark = markService.getMarkById(id).orElseThrow(() -> new IllegalArgumentException("Invalid mark Id:" + id));
        model.addAttribute("mark", mark);
        return "mark_detail";
    }


    /*
     Return all marks in the model
     */
//    @GetMapping
//    public String listAllMarks(Model model) {
//        List<Mark> marks = markService.getAllMarks();
//        model.addAttribute("marks", marks);
//        return "mark_lists";
//    }




    @GetMapping
    public String listMarks(@RequestParam("date") String date,
                            @AuthenticationPrincipal UserDetails userDetails,
                            Model model) {
        logger.warn("user selected :" + date);
        // List marks by user id and date
        Long userId = getUserIdFromUserDetails(userDetails);

        List<Mark> marks = markService.findMarksByUserId(userId, date);

        model.addAttribute("marks", marks);
        model.addAttribute("date", date);
        return "mark_lists";
    }






//    @PostMapping("/marks") With photo function
//    public String saveMark(@ModelAttribute Mark mark, @RequestParam("image") MultipartFile file) {
//        if (!file.isEmpty()) {
//            try {
//                // Ensure the upload directory exists
//                Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
//                if (!Files.exists(uploadPath)) {
//                    Files.createDirectories(uploadPath);
//                }
//
//                // Save file to the server
//                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//                Files.write(fileNameAndPath, file.getBytes());
//
//                // Create a new Photo object
////                Photo photo = new Photo();
////                photo.setBytes(file.getBytes());
//
//                // Set the Photo object to the Mark entity
////                mark.setPhoto(photo);
////                photo.setMark(mark); // Ensure bidirectional relationship
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Handle file upload error
//                return "redirect:/marks/new?error=fileUploadError";
//            }
//        }
//
//        // Save or update the Mark entity
//        markService.saveOrUpdateMark(mark);
//
//        // Redirect to the list view
//        return "redirect:/marks";
//    }


//    @PostMapping//Don't put anything here
//    public String saveMark(@ModelAttribute Mark mark,
//                           @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails,
//                           @RequestParam("clickDate") LocalDate clickDate) {
//        User user = userService.findByUsername(userDetails.getUsername());
//        mark.setUser(user);
//        mark.setDate(clickDate); // Set the clickDate in the Mark object
//        // Logic to save the mark
//        markService.saveOrUpdateMark(mark);
//
//        // Redirect to the mark list page
//        return "mark_lists";
//    }

    @PostMapping//Don't put anything here
    public String saveMark(@ModelAttribute Mark mark,
                           @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails,
                           @RequestParam("clickDate") LocalDate clickDate) {
        logger.warn("save mark : yes" );
        User user = userService.findByUsername(userDetails.getUsername());
        mark.setUser(user);
        mark.setDate(clickDate);
        markService.saveOrUpdateMark(mark);

        return "redirect:/marks?date=" + clickDate.toString();
    }

    @PostMapping("/{id}")
    public String updateMark(@PathVariable Long id, @ModelAttribute Mark mark,
                             @RequestParam("date") String date) {

        markService.updateMark(id, mark);
        return "redirect:/marks?date=" + date;
    }

    @PostMapping("/{id}/delete")
    public String deleteMark(@PathVariable("id") Long id, @RequestParam("date") String date) {
        logger.warn("date us :" + date);
        markService.deleteMark(id);
        return "redirect:/marks?date=" + date;
    }

//    @GetMapping("/check")
//    @ResponseBody
//    /*
//      Check Marks on date, if there's a date, direct to check_list, otherwise to check_form
//     */
//    public Map<String, String> showCheckList(@RequestParam("date") String date, Model model) {
//        LocalDate localDate = LocalDate.parse(date);
//        Map<String, String> response = new HashMap<>();
//
//        logger.warn(" CELINE function called: " + date);
//        // Check if there are marks on the given date
//        if (markService.hasMarksOnDate(date)) {
//            logger.warn("system has date here");
//            response.put("url", "/marks?date=" + date);
//        } else {
//            model.addAttribute("clickDate", localDate);
//            logger.warn(" CELINE system donnot has date here");
//            response.put("url", "/marks/new?date=" + date);
//        }
//        return response;
//    }

    @GetMapping("/check")
    @ResponseBody
    public Map<String, String> checkMarksOnDate(@RequestParam("date") String date,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        //LocalDate localDate = LocalDate.parse(date);
        logger.warn("userDetails: " + userDetails);

        Long userId = getUserIdFromUserDetails(userDetails);
        logger.warn("userId: " + userId);

        boolean hasMarks = markService.hasMarksOnDate(date, userId);

        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> response = new HashMap<>();
        if (hasMarks) {
            logger.warn("System has marks for the date: {}", date);
            List<Mark> marks = markService.findMarksByUserId(userId, date);

            response.put("url", "/marks?date=" + date);
        } else {
            logger.warn("System does not have marks for the date: {}", date);
            response.put("url", "/marks/new?date=" + date);
        }
        return response;
    }

    @Autowired
    private UserRepository userRepository;

    public Long getUserIdFromUserDetails(UserDetails userDetails) {
        // Get the username from the UserDetails
        String username = userDetails.getUsername();

        // Retrieve the corresponding user entity based on the username
        User user = userRepository.findByUsername(username);

        // Check if the user entity is not null
        if (user != null) {
            // Return the user's ID
            return user.getId();
        } else {
            // Handle the case where the user is not found
            return null;
        }
    }
}
