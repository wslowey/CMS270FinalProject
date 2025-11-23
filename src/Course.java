import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private String type;
    private int creditCount;
    private String startTime;
    private String endTime;
    private Professor professor;
    private Department department;
    private String building;
    private int roomNumber;
    private int crn; // course reference number
    private List<Student> enrolledStudents; // Track enrolled students
    
    public Course(String name, String type, int creditCount, String startTime, String endTime, 
                  Professor professor, Department department, String building, int roomNumber, int crn) {
        this.name = name;
        this.type = type;
        this.creditCount = creditCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.professor = professor;
        this.department = department;
        this.building = building;
        this.roomNumber = roomNumber;
        this.crn = crn;
        this.enrolledStudents = new ArrayList<>();
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getCreditCount() {
        return creditCount;
    }
    
    public void setCreditCount(int creditCount) {
        this.creditCount = creditCount;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void setProfessor(Professor professor) {
        // Remove from old professor's courses
        if (this.professor != null) {
            this.professor.dropCourse(this);
        }
        this.professor = professor;
        // Add to new professor's courses
        if (professor != null) {
            professor.addCourse(this);
        }
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public String getBuilding() {
        return building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public int getCrn() {
        return crn;
    }
    
    public void setCrn(int crn) {
        this.crn = crn;
    }
    
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    // Change the time of the course
    public void changeTime(String newStartTime, String newEndTime) {
        this.startTime = newStartTime;
        this.endTime = newEndTime;
        System.out.println("Course time changed to: " + newStartTime + " - " + newEndTime);
    }
    
    // Change the location of the course
    public void changeLocation(String newBuilding, int newRoomNumber) {
        this.building = newBuilding;
        this.roomNumber = newRoomNumber;
        System.out.println("Course location changed to: " + newBuilding + " " + newRoomNumber);
    }
    
    // Add a student to the course
    public boolean addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }
    
    // Remove a student from the course
    public boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }
    
    // Display course roster in system
    public void displayRoster() {
        System.out.println("\n=== Roster for " + name + " (CRN: " + crn + ") ===");
        System.out.println("Professor: " + (professor != null ? professor.getName() : "TBA"));
        System.out.println("Enrolled Students (" + enrolledStudents.size() + "):");
        if (enrolledStudents.isEmpty()) {
            System.out.println("  No students enrolled.");
        } else {
            for (Student student : enrolledStudents) {
                System.out.println("  - " + student);
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("CRN %d: %s | %s Credits | %s-%s | %s %d | Prof: %s", 
            crn, name, creditCount, startTime, endTime, 
            building, roomNumber, 
            (professor != null ? professor.getName() : "TBA"));
    }
}