
import javax.swing.*;
import java.awt.*;

public class CourseRegistrationGUI extends JFrame{
	
	//create managment system
    private final CourseManagement rcms = new CourseManagement();
    
	//layout parent
	static CardLayout layout;
	static JPanel cards;
	
    public void GUIrun() {

        setTitle("Course Managment");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // main card layout
        layout = new CardLayout();
        cards = new JPanel(layout);

        // Build all screens
        JPanel menuPanel = buildMainMenu();
        JPanel addDeptPanel = addDepartment();

        // Add them to CardLayout
        cards.add(menuPanel, "MENU");
        cards.add(addDeptPanel,"ADD_DEPARTMENT");

        add(cards);
        setVisible(true);
    }
	
	JPanel buildMainMenu() {
		//make panel
		JPanel main = new JPanel(new GridLayout(17,1));
		
		//add title to panel
		JLabel title = new JLabel("Welcome to the Rollins College Course Managment System", SwingConstants.CENTER);
		
		//create buttons for all functions
		JButton addDepartment = new JButton("Add Department");
		JButton addProfessor = new JButton("Add Professor");
		JButton addStudent = new JButton("Add Student");
		JButton createCourse = new JButton("Create Course");
		JButton removeCourse = new JButton("Remove Course");
		JButton studentAddCourse = new JButton("Add Course for Students");
		JButton studentDropCourse = new JButton("Drop Course for Students");
		JButton seeCourseInfo = new JButton("Course Roster");
		JButton seeStudentSchedule = new JButton("Student Schedule");
		JButton changeCourse = new JButton("Change Course Details");
		JButton professorAddCourse = new JButton("Add Course for Professors");
		JButton professorDropCourse = new JButton("Drop Course for Professors");
		JButton seeStudentList = new JButton("See Current Students");
		JButton seeProfessorList = new JButton("See Current Professors");
		JButton seeCourseList = new JButton("See Current Courses");
		JButton exit = new JButton("Exit");
		
		//Navigation
		addDepartment.addActionListener(e->layout.show(cards, "ADD_DEPARTMENT"));
		addProfessor.addActionListener(e->layout.show(cards, "ADD_PROFESSOR"));
		addStudent.addActionListener(e->layout.show(cards, "ADD_STUDENT"));
		createCourse.addActionListener(e->layout.show(cards, "CREATE_COURSE"));
		removeCourse.addActionListener(e->layout.show(cards, "REMOVE_COURSE"));
		studentAddCourse.addActionListener(e->layout.show(cards, "STUDENT_ADD_COURSE"));
		studentDropCourse.addActionListener(e->layout.show(cards, "STUDENT_DROP_COURSE"));
		seeCourseInfo.addActionListener(e->layout.show(cards, "SEE_COURSE_DETAILS"));
		seeStudentSchedule.addActionListener(e->layout.show(cards, "SEE_STUDENT_SCHEDULE"));
		changeCourse.addActionListener(e->layout.show(cards, "CHANGE_COURSE_DETAILS"));
		professorAddCourse.addActionListener(e->layout.show(cards, "PROFESSOR_ADD_COURSE"));
		professorDropCourse.addActionListener(e->layout.show(cards, "PROFESSOR_DROP_COURSE"));
		seeStudentList.addActionListener(e->layout.show(cards, "SEE_STUDENT_LIST"));
		seeProfessorList.addActionListener(e->layout.show(cards, "SEE_PROFESSOR_LIST"));
		seeCourseList.addActionListener(e->layout.show(cards, "SEE_COURSE_LIST"));
		
		exit.addActionListener(e->dispose());
		
		//add label to panel
		main.add(title);
		
		//add buttons to main panel
		main.add(addDepartment);
		main.add(addProfessor);
		main.add(addStudent);
		main.add(createCourse);
		main.add(removeCourse);
		main.add(studentAddCourse);
		main.add(studentDropCourse);
		main.add(seeCourseInfo);
		main.add(seeStudentSchedule);
		main.add(changeCourse);
		main.add(professorAddCourse);
		main.add(professorDropCourse);
		main.add(seeStudentList);
		main.add(seeProfessorList);
		main.add(seeCourseList);
		main.add(exit);
		
		return main;
	}
	
	JPanel addDepartment() {
		//give page a layout
		JPanel dep = new JPanel(new GridLayout(4,2));
		
		//make text field lables
		JLabel deptname = new JLabel("Department Name");
		JLabel deptref = new JLabel("Department Refrence");
		
		//make text fields
		JTextField deptnameField = new JTextField();
		JTextField deptrefField = new JTextField();
		
		//make button to add department
		JButton addDept = new JButton("Add Department");
		//make back button
		JButton returnToMenu = new JButton("Return to Menu");
		
		//add button functions
		addDept.addActionListener(e->{
			rcms.addDepartment( new Department(deptnameField.getText(),deptrefField.getText()));
		});
		
		returnToMenu.addActionListener(e -> layout.show(cards, "MENU"));
		
		//add things to panel
		dep.add(deptname);
		dep.add(deptnameField);
		dep.add(deptref);
		dep.add(deptrefField);
		dep.add(returnToMenu);
		dep.add(addDept);
		
		return dep;
	}

}
