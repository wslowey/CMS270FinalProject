import java.util.ArrayList;
import java.util.List;

//
//Includes methods for course, students, professors, and department
//

public class CourseManagement {
    private List<Course> courses;
    private List<Student> students;
    private List<Professor> professors;
    private List<Department> departments;
    
    public CourseManagement() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.departments = new ArrayList<>();
    }
    
   
    //create a course
    public Course createCourse(String name, String type, int creditCount, String startTime, 
                               String endTime, Professor professor, Department department, 
                               String building, int roomNumber, int crn) {
        Course course = new Course(name, type, creditCount, startTime, endTime, 
                                   professor, department, building, roomNumber, crn);
        courses.add(course);
        if (professor != null) {
            professor.addCourse(course);
        }
        System.out.println("Course created: " + course.getName() + " (CRN: " + crn + ")");
        return course;
    }
    
    //drop a course
    public boolean dropCourse(int crn) {
        Course course = findCourseByCRN(crn);
        if (course != null) {
            // Remove all students from the course
            for (Student student : new ArrayList<>(course.getEnrolledStudents())) {
                student.dropCourse(course);
            }
            // Remove from professor's schedule
            if (course.getProfessor() != null) {
                course.getProfessor().dropCourse(course);
            }
            courses.remove(course);
            System.out.println("Course dropped: " + course.getName() + " (CRN: " + crn + ")");
            return true;
        }
        System.out.println("Course not found with CRN: " + crn);
        return false;
    }
    
    //change the course time
    public boolean changeCourseTime(int crn, String newStartTime, String newEndTime) {
        Course course = findCourseByCRN(crn);
        if (course != null) {
            course.changeTime(newStartTime, newEndTime);
            return true;
        }
        System.out.println("Course not found with CRN: " + crn);
        return false;
    }
    
    //change course location
    public boolean changeCourseLocation(int crn, String newBuilding, int newRoomNumber) {
        Course course = findCourseByCRN(crn);
        if (course != null) {
            course.changeLocation(newBuilding, newRoomNumber);
            return true;
        }
        System.out.println("Course not found with CRN: " + crn);
        return false;
    }
    
    //find a course
    public Course findCourseByCRN(int crn) {
        for (Course course : courses) {
            if (course.getCrn() == crn) {
                return course;
            }
        }
        return null;
    }
    
    //add a student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getName());
    }
    
    //find a student by its ID
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    //add the student to a class
    public boolean enrollStudent(int studentId, int crn) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCRN(crn);
        
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return false;
        }
        if (course == null) {
            System.out.println("Course not found with CRN: " + crn);
            return false;
        }
        
        if (student.addCourse(course)) {
            System.out.println(student.getName() + " enrolled in " + course.getName());
            return true;
        }
        return false;
    }
    
    //drop student by class
    public boolean dropStudentFromCourse(int studentId, int crn) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCRN(crn);
        
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return false;
        }
        if (course == null) {
            System.out.println("Course not found with CRN: " + crn);
            return false;
        }
        
        if (student.dropCourse(course)) {
            System.out.println(student.getName() + " dropped from " + course.getName());
            return true;
        }
        return false;
    }
    
    
    //add a professor
    public void addProfessor(Professor professor) {
        professors.add(professor);
        System.out.println("Professor added: " + professor.getName());
    }
    
    // find a professor by ID
    public Professor findProfessorById(int id) {
        for (Professor professor : professors) {
            if (professor.getId() == id) {
                return professor;
            }
        }
        return null;
    }
    
    //assign a professor to a class
    public boolean assignProfessorToCourse(int professorId, int crn) {
        Professor professor = findProfessorById(professorId);
        Course course = findCourseByCRN(crn);
        
        if (professor == null) {
            System.out.println("Professor not found with ID: " + professorId);
            return false;
        }
        if (course == null) {
            System.out.println("Course not found with CRN: " + crn);
            return false;
        }
        
        course.setProfessor(professor);
        System.out.println(professor.getName() + " assigned to " + course.getName());
        return true;
    }
    
    // remove a professor
    public boolean removeProfessorFromCourse(int crn) {
        Course course = findCourseByCRN(crn);
        
        if (course == null) {
            System.out.println("Course not found with CRN: " + crn);
            return false;
        }
        
        Professor oldProfessor = course.getProfessor();
        course.setProfessor(null);
        if (oldProfessor != null) {
            System.out.println(oldProfessor.getName() + " removed from " + course.getName());
        }
        return true;
    }
    
    //add a department
    public void addDepartment(Department department) {
        departments.add(department);
        System.out.println("Department added: " + department.getName());
    }
    
    //find a department
    public Department findDepartmentByReference(String reference) {
        for (Department dept : departments) {
            if (dept.getReference().equalsIgnoreCase(reference)) {
                return dept;
            }
        }
        return null;
    }
  
   
    
    //display courses
    public void displayAllCourses() {
        System.out.println("\n=== ALL COURSES ===");
        if (courses.isEmpty()) {
            System.out.println("No courses in the system.");
        } else {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }
    
    //display all the students
    public void displayAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            for (Student student : students) {
                System.out.println(student + " - Enrolled in " + 
                                 student.getEnrolledCourses().size() + " courses");
            }
        }
    }
    
    //display the professors
    public void displayAllProfessors() {
        System.out.println("\n=== ALL PROFESSORS ===");
        if (professors.isEmpty()) {
            System.out.println("No professors in the system.");
        } else {
            for (Professor professor : professors) {
                System.out.println(professor + " - Teaching " + 
                                 professor.getCourses().size() + " courses");
            }
        }
    }
    
    // Getters
    public List<Course> getCourses() {
        return courses;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public List<Professor> getProfessors() {
        return professors;
    }
    
    public List<Department> getDepartments() {
        return departments;
    }
    
    //Course roster display for GUI
    public String getRosterDisplay(int crn) {
        Course course = findCourseByCRN(crn);
        if (course == null) {
            return "Course not found with CRN: " + crn;
        }
        
        StringBuilder display = new StringBuilder();
        display.append("=== Roster for ").append(course.getName())
               .append(" (CRN: ").append(course.getCrn()).append(") ===\n");
        display.append("Professor: ")
               .append(course.getProfessor() != null ? course.getProfessor().getName() : "TBA")
               .append("\n");
        display.append("Enrolled Students (").append(course.getEnrolledStudents().size()).append("):\n");
        
        if (course.getEnrolledStudents().isEmpty()) {
            display.append("  No students enrolled.\n");
        } else {
            for (Student student : course.getEnrolledStudents()) {
                display.append("  - ").append(student.toString()).append("\n");
            }
        }
        
        return display.toString();
    }
    
    //Student Schedule Display for GUI
    public String getStudentSchdule(int ID) {
    	//make string builder 
    	StringBuilder schedule = new StringBuilder();
    	
    	//look for student in list
    	for (Student student : students) {
            if (student.getId() == ID) {//if found get their schedule
            	schedule.append("\n=== Schedule for " + student.getName() + " ===");
                if (student.getEnrolledCourses().isEmpty()) {//if no courses
                    schedule.append("No courses enrolled.");
                } else {//if courses
                    for (Course course : student.getEnrolledCourses()) {
                        schedule.append(course);
                    }
                    return schedule.toString();
                }
            }
        }
    	schedule.append("\n=== No Student Found with ID" + ID);
    	return schedule.toString();
    }
}