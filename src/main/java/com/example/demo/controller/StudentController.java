import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Student;
import com.example.demo.service.SubmitService;
@CrossOrigin(origins = "")
@RestController
public class SubmitController {
    private SubmitService submitService;
    public SubmitController(SubmitService submitService) {
        this.submitService = submitService;
    }
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return submitService.insertStudent(student);
    }
    @GetMapping("/getAll")
    public List<Student> getAll() {
        return submitService.getAllStudent();
    }
    @GetMapping("/get/{id}")
    public Student get(@PathVariable Long id) {
        return submitService.getStudentById(id);
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody Student modification) {
        Optional<Student> student = submitService.getStudentById(id);
        if(student.isPresent()) {
            modification.setId(id);
            submitService.insertStudent(modification);
            return "Updated Success";
        }
        return "Id not found";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Optional<Student> student = submitService.getStudentById(id);
        if(student.isPresent()) {
            submitService.deleteStudent(id);
            return "Deleted Success";
        }
        return "Id Not found";
    }
}