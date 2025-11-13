import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int id;
    private int year;
    private List<Course> enrolledCourses; // Track enrolled courses
    
    public Student(String name, int id, int year) {
        this.name = name;
        this.id = id;
        this.year = year;
        this.enrolledCourses = new ArrayList<>();
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
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    
    // Add a course to student's schedule
    public boolean addCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.addStudent(this); // Update course enrollment
            return true;
        }
        System.out.println("Student is already enrolled in this course.");
        return false;
    }
    
    // Drop a course from student's schedule
    public boolean dropCourse(Course course) {
        if (enrolledCourses.remove(course)) {
            course.removeStudent(this); // Update course enrollment
            return true;
        }
        System.out.println("Student is not enrolled in this course.");
        return false;
    }
    
    // Display student's schedule
    public void displaySchedule() {
        System.out.println("\n=== Schedule for " + name + " ===");
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (Course course : enrolledCourses) {
                System.out.println(course);
            }
        }
    }
    
    @Override
    public String toString() {
        return name + " (ID: " + id + ", Year: " + year + ")";
    }
}