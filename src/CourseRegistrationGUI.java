
import javax.swing.*;
import java.awt.*;

public class CourseRegistrationGUI extends JFrame{
	
	//create managment system
    private final CourseManagement rcms = new CourseManagement();
    
	//layout parent
	static CardLayout layout;
	static JPanel cards;
	
	//Main running program
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
        JPanel addProfPanel = addProfessor();
        JPanel addStuPanel = addStudent();
        JPanel createCoursePanel = CreateCourse();
        JPanel removeCoursePanel = removeCourse();

        // Add them to CardLayout
        cards.add(menuPanel, "MENU");
        cards.add(addDeptPanel,"ADD_DEPARTMENT");
        cards.add(addProfPanel,"ADD_PROFESSOR");
        cards.add(addStuPanel,"ADD_STUDENT");
        cards.add(createCoursePanel,"CREATE_COURSE");
        cards.add(removeCoursePanel,"REMOVE_COURSE");

        add(cards);
        setVisible(true);
    }
	
    
    //----MAIN MENU FUNCTIONS----
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
		addDepartment.addActionListener(e->{
			setTitle("Add Department");
			layout.show(cards, "ADD_DEPARTMENT");
		});
		addProfessor.addActionListener(e->{
			setTitle("Add Professor");
			layout.show(cards, "ADD_PROFESSOR");
		});
		addStudent.addActionListener(e->{
			setTitle("Add Student");
			layout.show(cards, "ADD_STUDENT");
		});
		createCourse.addActionListener(e->{
			setTitle("Create Course");
			layout.show(cards, "CREATE_COURSE");
		});
		removeCourse.addActionListener(e->{
			setTitle("RemoveCourse");
			layout.show(cards, "REMOVE_COURSE");
		});
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
	
	//----ADD DEPARTMENT FUNCTIONS----
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
			
			deptnameField.setText("");
			deptrefField.setText("");
		});
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Managment");
			layout.show(cards, "MENU");
		});
		
		//add things to panel
		dep.add(deptname);
		dep.add(deptnameField);
		dep.add(deptref);
		dep.add(deptrefField);
		dep.add(returnToMenu);
		dep.add(addDept);
		
		return dep;
	}
	
	//----ADD PROFESSOR FUNCTIONS----
	JPanel addProfessor() {
		//make panel
		JPanel prof = new JPanel(new GridLayout(4,2));
		
		//make labels for text fields
		JLabel profname = new JLabel("Professor Name");
		JLabel profID = new JLabel("Professor ID Number");
		JLabel profdept = new JLabel("Professor Department");
		
		//make text fields
		JTextField profnameField = new JTextField();
		JTextField profIDField = new JTextField();
		JTextField profdeptField = new JTextField();
		
		//make button to add professor
		JButton addProf = new JButton("Add Professor");
		JButton returnToMenu = new JButton("Return to Menu");
		
		//add button funcitons
		addProf.addActionListener(e->{
			String deptName = profdeptField.getText();
			Department dept = rcms.findDepartmentByReference(deptName);
			rcms.addProfessor(new Professor(profnameField.getText(),
					Integer.parseInt(profIDField.getText()),dept));
			
			profnameField.setText("");
			profIDField.setText("");
			profdeptField.setText("");
		});
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Managment");
			layout.show(cards, "MENU");
		});  
		
		//add everything to panel
		prof.add(profname);
		prof.add(profnameField);
		prof.add(profID);
		prof.add(profIDField);
		prof.add(profdept);
		prof.add(profdeptField);
		prof.add(returnToMenu);
		prof.add(addProf);

		return prof;
	}
	
	//----ADD STUDENT FUNCTIONS----
	JPanel addStudent() {
		//make panel
		JPanel addStu = new JPanel(new GridLayout(4,2));
		
		//make labels for text fields
		JLabel stuName = new JLabel("Student Name");
		JLabel stuID = new JLabel("Student ID Number");
		
		//make text fields
		JTextField stuNameField = new JTextField();
		JTextField stuIDField = new JTextField();
		
		//make buttons
		JButton addstu = new JButton("Add Student");
		JButton returnToMenu = new JButton("Return to Menu");
		
		//button functionality
		addstu.addActionListener(e->{
			rcms.addStudent(new Student(stuNameField.getText(),Integer.parseInt(stuIDField.getText()),0));
			
			stuNameField.setText("");
			stuIDField.setText("");
		});
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Managment");
			layout.show(cards, "MENU");
		});
		
		//add things to panel
		addStu.add(stuName);
		addStu.add(stuNameField);
		addStu.add(stuID);
		addStu.add(stuIDField);
		addStu.add(returnToMenu);
		addStu.add(addstu);
		
		return addStu;
	}
	
	//----CREATE COURSE FUNCTIONS-----  
	JPanel CreateCourse() {
		//make panel
		JPanel createCourse = new JPanel(new GridLayout(4,1));
		
		//make labels for text fields
		JLabel courseName = new JLabel("Course Name");
		JLabel courseType = new JLabel("Course Type");
		JLabel creditHours = new JLabel("Course Credit Hours");
		JLabel startTime = new JLabel("Start Time");
		JLabel endTime = new JLabel("End Time");
		JLabel courseProf = new JLabel("Course Professor");
		JLabel coursedept = new JLabel("Course Department");
		JLabel building = new JLabel("Building");
		JLabel room = new JLabel("Room Number");
		JLabel crn = new JLabel("Course Refrence Number (CRN)");
		
		//make text fields
		JTextField courseNameField = new JTextField();
		JTextField courseTypeField = new JTextField();
		JTextField creditHoursField = new JTextField();
		JTextField startTimeField = new JTextField();
		JTextField endTimeField = new JTextField();
		JTextField courseprofField = new JTextField();
		JTextField coursedeptfield = new JTextField();
		JTextField buildingField = new JTextField();
		JTextField roomField = new JTextField();
		JTextField crnField = new JTextField();
		
		//make buttons
		JButton makeCourse = new JButton("Create Course");
		JButton returnToMenu = new JButton("Return to Menu");
		
		//button functionality
		makeCourse.addActionListener(e->{
			int profID = Integer.parseInt(courseprofField.getText());
			Professor p = rcms.findProfessorById(profID);
			String deptRef = coursedeptfield.getText();
			Department d = rcms.findDepartmentByReference(deptRef);
			rcms.createCourse(courseNameField.getText(), 
					courseTypeField.getText(), 
					Integer.parseInt(creditHoursField.getText()), 
					startTimeField.getText(), 
					endTimeField.getText(), 
					p, 
					d, 
					buildingField.getText(), 
					Integer.parseInt(roomField.getText()), 
					Integer.parseInt(crnField.getText()));
			
			courseNameField.setText("");
			courseTypeField.setText("");
			creditHoursField.setText("");
			startTimeField.setText("");
			endTimeField.setText("");
			courseprofField.setText("");
			coursedeptfield.setText("");
			buildingField.setText("");
			roomField.setText("");
			crnField.setText("");
		});
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Managment");
			layout.show(cards, "MENU");
		});
		
		//add things to panel
		createCourse.add(courseName);
		createCourse.add(courseNameField);
		createCourse.add(courseType);
		createCourse.add(courseTypeField);
		createCourse.add(creditHours);
		createCourse.add(creditHoursField);
		createCourse.add(startTime);
		createCourse.add(startTimeField);
		createCourse.add(endTime);
		createCourse.add(endTimeField);
		createCourse.add(courseProf);
		createCourse.add(courseprofField);
		createCourse.add(coursedept);
		createCourse.add(coursedeptfield);
		createCourse.add(building);
		createCourse.add(buildingField);
		createCourse.add(room);
		createCourse.add(roomField);
		createCourse.add(crn);
		createCourse.add(crnField);
		createCourse.add(returnToMenu);
		createCourse.add(makeCourse);

		return createCourse;
	}
	
	//----REMOVE COURSE FUNCTIONS----
	JPanel removeCourse() {
		//make panel
		JPanel removeCourse = new JPanel(new GridLayout(2,2));
		
		//make labels for text fields
		JLabel crn = new JLabel("Course Refrence Number");
		
		//make text field
		JTextField crnField = new JTextField();
		
		//make buttons
		JButton deleteCourse = new JButton("Remove Course");
		JButton returnToMenu = new JButton("Return To Menu");
		
		//button functionality
		deleteCourse.addActionListener(e->{
			rcms.dropCourse(Integer.parseInt(crnField.getText()));
			
			crnField.setText("");
		});
		
		//add things to panel
		removeCourse.add(crn);
		removeCourse.add(crnField);
		removeCourse.add(returnToMenu);
		removeCourse.add(deleteCourse);
		
		return removeCourse;
	}

}
