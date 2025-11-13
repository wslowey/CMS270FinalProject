import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String name;
    private int id;
    private Department department;
    private List<Course> courses; // Track courses professor is teaching
    
    public Professor(String name, int id, Department department) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.courses = new ArrayList<>();
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    // Add a course to professor's teaching schedule
    public boolean addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }
    
    // Remove a course from professor's teaching schedule
    public boolean dropCourse(Course course) {
        return courses.remove(course);
    }
    
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}