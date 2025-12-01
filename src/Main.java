import javax.swing.*;
import java.awt.*;

/**
 * Demo class showing how to use the Course Management
 */
public class Main {
    public static void main(String[] args) {
    	//GUI manager
    	SwingUtilities.invokeLater(()-> new CourseRegistrationGUI().GUIrun());
    	
        // Create the management system
        CourseManagement cms = new CourseManagement();
        
        
        System.out.println("=== ROLLINS COURSE MANAGEMENT SYSTEM ===\n");
        
        // Create departments
        Department csDept = new Department("Computer Science", "CMS");
        Department busDept = new Department("Business", "BUS");
        cms.addDepartment(csDept);
        cms.addDepartment(busDept);
        
        // Create professors
        Professor profMyers = new Professor("D Myers", 101, csDept);
        Professor profSardy = new Professor("M Sardy", 102, busDept);
        cms.addProfessor(profMyers);
        cms.addProfessor(profSardy);
        
        // Create students
        Student alice = new Student("Alice Johnson", 1001, 2);
        Student bob = new Student("Bob Smith", 1002, 3);
        Student charlie = new Student("Charlie Davis", 1003, 1);
        cms.addStudent(alice);
        cms.addStudent(bob);
        cms.addStudent(charlie);
        
        System.out.println("\n--- Creating Courses ---");
        
        // Create courses
        Course cs120 = cms.createCourse("Intro to Computer Science", "LECTURE", 4, 
                                        "10:00", "10:50A", profMyers, csDept, 
                                        "BUSH", 160, 15557);
        
        Course cs430 = cms.createCourse("Artificial Intelligence", "LECTURE", 4,
                                        "11:00", "11:50A", profMyers, csDept,
                                        "BUSH", 160, 15565);
        
        Course bus348 = cms.createCourse("Investments", "LECTURE", 4,
                                         "1:00", "2:15P", profSardy, busDept,
                                         "FAIRBK", 107, 15687);
        
        System.out.println("\n--- Student Enrollment ---");
        
        // Enroll students in courses
        cms.enrollStudent(1001, 15557); // Alice -> CS120
        cms.enrollStudent(1001, 15565); // Alice -> CS430
        cms.enrollStudent(1002, 15557); // Bob -> CS120
        cms.enrollStudent(1002, 15687); // Bob -> BUS348
        cms.enrollStudent(1003, 15557); // Charlie -> CS120
        
        // Display course rosters
        cs120.displayRoster();
        bus348.displayRoster();
        
        // Display student schedules
        alice.displaySchedule();
        bob.displaySchedule();
        
        System.out.println("\n--- Dropping Courses ---");
        
        // Bob drops CS120
        cms.dropStudentFromCourse(1002, 15557);
        
        // Display updated roster
        cs120.displayRoster();
        bob.displaySchedule();
        
        System.out.println("\n--- Changing Course Details ---");
        
        // Change time of CS430
        cms.changeCourseTime(15565, "2:00", "3:15P");
        
        // Change location of BUS348
        cms.changeCourseLocation(15687, "CSS", 230);
        
        // Display updated course info
        System.out.println("\nUpdated courses:");
        System.out.println(cs430);
        System.out.println(bus348);
        
        System.out.println("\n--- Professor Management ---");
        
        // Remove professor from a course
        cms.removeProfessorFromCourse(15687);
        System.out.println(bus348);
        
        // Reassign professor
        cms.assignProfessorToCourse(102, 15687);
        System.out.println(bus348);
        
        System.out.println("\n--- Dropping a Course ---");
        
        // Drop an entire course (this will remove all enrollments)
        cms.dropCourse(15565); // Drop CS430
        
        // Alice's schedule should now only show CS120
        alice.displaySchedule();
        
        System.out.println("\n--- System Summary ---");
        
        // Display all courses, students, and professors
        cms.displayAllCourses();
        cms.displayAllStudents();
        cms.displayAllProfessors();
        
        System.out.println("\n=== END OF DEMO ===");
    }
}
