
import javax.swing.*;
import java.awt.*;

public class CourseRegistrationGUI extends JFrame{
	
	//create managment system
    private final CourseManagement rcms = new CourseManagement();
    
	//layout parent
	static CardLayout layout;
	static JPanel cards;
	
	//card layout for changing course details
	static CardLayout courseLayout;
	static JPanel courseCards;
	
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
        JPanel studentAddCoursePanel = studentAdd();
        JPanel studentDropCoursePanel = studentDrop();
        JPanel courseRosterPanel = courseRoster();
        JPanel studentSchedulePanel = seeSchedule();
        JPanel changeCourseDetailsPanel = changeCourseDetailsSelection();

        // Add them to CardLayout
        cards.add(menuPanel, "MENU");
        cards.add(addDeptPanel,"ADD_DEPARTMENT");
        cards.add(addProfPanel,"ADD_PROFESSOR");
        cards.add(addStuPanel,"ADD_STUDENT");
        cards.add(createCoursePanel,"CREATE_COURSE");
        cards.add(removeCoursePanel,"REMOVE_COURSE");
        cards.add(studentAddCoursePanel,"STUDENT_ADD_COURSE");
        cards.add(studentDropCoursePanel,"STUDENT_DROP_COURSE");
        cards.add(courseRosterPanel,"SEE_COURSE_DETAILS");
        cards.add(studentSchedulePanel,"SEE_STUDENT_SCHEDULE");
        cards.add(changeCourseDetailsPanel,"CHANGE_COURSE_DETAILS");

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
		studentAddCourse.addActionListener(e->{
			setTitle("Add Course for Students");
			layout.show(cards, "STUDENT_ADD_COURSE");
		});
		studentDropCourse.addActionListener(e->{
			setTitle("Drop Course for Students");
			layout.show(cards, "STUDENT_DROP_COURSE");
		});
		seeCourseInfo.addActionListener(e->{
			setTitle("Full Course Details");
			layout.show(cards, "SEE_COURSE_DETAILS");
		});
		seeStudentSchedule.addActionListener(e->{
			setTitle("Student Schedule");
			layout.show(cards, "SEE_STUDENT_SCHEDULE");
		});
		changeCourse.addActionListener(e->{
			setTitle("Change Course Details");
			layout.show(cards, "CHANGE_COURSE_DETAILS");
		});
		professorAddCourse.addActionListener(e->{
			setTitle("Add Course for Professors");
			layout.show(cards, "PROFESSOR_ADD_COURSE");
		});
		professorDropCourse.addActionListener(e->{
			setTitle("Drop Course for Professors");
			layout.show(cards, "PROFESSOR_DROP_COURSE");
		});
		seeStudentList.addActionListener(e->{
			setTitle("Full Student List");
			layout.show(cards, "SEE_STUDENT_LIST");
		});
		seeProfessorList.addActionListener(e->{
			setTitle("Full Professor List");
			layout.show(cards, "SEE_PROFESSOR_LIST");
		});
		seeCourseList.addActionListener(e->{
			setTitle("Full Course List");
			layout.show(cards, "SEE_COURSE_LIST");
		});
		
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
					Integer.parseInt(crnField.getText())
					);
			
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
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Managment");
			layout.show(cards, "MENU");
		});
		
		//add things to panel
		removeCourse.add(crn);
		removeCourse.add(crnField);
		removeCourse.add(returnToMenu);
		removeCourse.add(deleteCourse);
		
		return removeCourse;
	}
	
	//----ADD COURSE FOR STUDENTS FUNCTIONS-----
	JPanel studentAdd() {
		//make panel 
		JPanel studentAdd = new JPanel(new GridLayout(4,2));
		
		//make text field labels
		JLabel studentID = new JLabel("Student ID Number");
		JLabel crn = new JLabel("Course Refrence Number");
		
		//make text field
		JTextField IDField = new JTextField();
		JTextField crnField = new JTextField();
		
		//make buttons
		JButton addCourse = new JButton("Add Course");
		
		JButton returnToMenu = new JButton("Return To Menu");
		
		//add button functions
		addCourse.addActionListener(e->{
			rcms.enrollStudent(Integer.parseInt(IDField.getText()), Integer.parseInt(crnField.getText()));
			
			IDField.setText("");
			crnField.setText("");
		});
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Managment");
			layout.show(cards, "MENU");
		});
		
		//add things to panel
		
		studentAdd.add(studentID);
		studentAdd.add(IDField);
		studentAdd.add(crn);
		studentAdd.add(crnField);
		studentAdd.add(returnToMenu);
		studentAdd.add(addCourse);
		
		return studentAdd;
	}
	
	//----DROP COURSE FOR STUDENDS FUNTIONS----
	JPanel studentDrop() {
		//make Panel
		JPanel studentDrop = new JPanel(new GridLayout(4,2));
		
		//make text field labels
		JLabel studentID = new JLabel("Student ID Number");
		JLabel crn = new JLabel("Course Refrence Number");
		
		//make text fields
		JTextField IDField = new JTextField();
		JTextField crnField = new JTextField();
		
		//make buttons
		JButton dropCourse = new JButton("Drop Course");
		JButton returnToMenu = new JButton("Return To Menu");
		
		//add button functionality
		dropCourse.addActionListener(e->{
			rcms.dropStudentFromCourse(Integer.parseInt(IDField.getText()), Integer.parseInt(crnField.getText()));
			
			IDField.setText("");
			crnField.setText("");
		});
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Managment");
			layout.show(cards, "MENU");
		});
		
		//add things to panel
		studentDrop.add(studentID);
		studentDrop.add(IDField);
		studentDrop.add(crn);
		studentDrop.add(crnField);
		studentDrop.add(returnToMenu);
		studentDrop.add(dropCourse);
		
		return studentDrop;
	}
	
	JPanel courseRoster() {
		// make panel
	    JPanel courseRoster = new JPanel(new BorderLayout());
	    
	    // make input panel to get crn
	    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
	    JLabel crn = new JLabel("Course Reference Number");
	    JTextField crnField = new JTextField();
	    
	    // Text area to display roster
	    JTextArea rosterDisplay = new JTextArea(15, 50);
	    rosterDisplay.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(rosterDisplay);
	    
	    // Buttons
	    JButton getCourseRoster = new JButton("Show Course Roster");
	    JButton returnToMenu = new JButton("Return To Menu");
	    
	    // Button functionality
	    getCourseRoster.addActionListener(e -> {
	        String rosterText = rcms.getRosterDisplay(Integer.parseInt(crnField.getText()));
	        rosterDisplay.setText(rosterText);
	        
	        crnField.setText(""); 
	    });
	    
	    returnToMenu.addActionListener(e -> {
	        setTitle("Course Management");
	        layout.show(cards, "MENU");
	    });
	    
	    // Add things to input panel
	    inputPanel.add(crn);
	    inputPanel.add(crnField);
	    inputPanel.add(returnToMenu);
	    inputPanel.add(getCourseRoster);
	    
	    //add things to overall panel
	    courseRoster.add(inputPanel, BorderLayout.NORTH);
	    courseRoster.add(scrollPane, BorderLayout.CENTER);
	    
	    return courseRoster;
	}
	
	//----SEE STUDENT SCHEDULE FUNCTIONS----
	JPanel seeSchedule() {
		// make panel
	    JPanel studentSchedule = new JPanel(new BorderLayout());
	    
	    // make input panel to get crn
	    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
	    JLabel ID = new JLabel("Course Reference Number");
	    JTextField IDField = new JTextField();
	    
	    // Text area to display roster
	    JTextArea scheduleDisplay = new JTextArea(15, 50);
	    scheduleDisplay.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(scheduleDisplay);
	    
	    // Buttons
	    JButton getStudentSchedule = new JButton("Show Student Schedule");
	    JButton returnToMenu = new JButton("Return To Menu");
	    
	    //button functionality
	    getStudentSchedule.addActionListener(e->{
	    	String schedule = rcms.getStudentSchdule(Integer.parseInt(IDField.getText()));
	    	scheduleDisplay.setText(schedule);
	    	
	    	IDField.setText("");
	    });
	    
	    returnToMenu.addActionListener(e -> {
	        setTitle("Course Management");
	        layout.show(cards, "MENU");
	    });
	    
	    //add things to input panel
	    inputPanel.add(ID);
	    inputPanel.add(IDField);
	    inputPanel.add(returnToMenu);
	    inputPanel.add(getStudentSchedule);
	    
	    //add things to overall panel
	    studentSchedule.add(inputPanel, BorderLayout.NORTH);
	    studentSchedule.add(scrollPane, BorderLayout.CENTER);
	    
	    return studentSchedule;
	}
	
	//----CHANGE COURSE DETAILS FUNCTIONS----
	JPanel changeCourseDetailsSelection() {
		//make panel
		JPanel changeCourseDetails = new JPanel(new BorderLayout());

		//add card layouts
		courseLayout = new CardLayout();
		courseCards = new JPanel(courseLayout);
	    
	    //make panels for card layout
		JPanel changeCourseMenuPanel = changeCourseMenu();
		JPanel changeCourseNamePanel = changeCourseName();
		JPanel changeCourseTypePanel = changeType();
		JPanel changeCourseCreditCountPanel = changeCreditCount();
		JPanel changeTimePanel = changeTime();
		
		//add panels to card layout
		courseCards.add(changeCourseMenuPanel,"COURSE_MENU");
		courseCards.add(changeCourseNamePanel,"CHANGE_NAME");
		courseCards.add(changeCourseTypePanel,"CHANGE_TYPE");
		courseCards.add(changeCourseCreditCountPanel,"CHANGE_CREDIT_COUNT");
		courseCards.add(changeTimePanel,"CHANGE_TIME");

		//add cards to panel
		changeCourseDetails.add(courseCards,BorderLayout.CENTER);
		
		return changeCourseDetails;
	}
	
	//----CHANGE COURSE DETAILS FUNCTIONS----
	//--------SUBSECTION: CHANGE COURSE MENU FUNTIONS----
	JPanel changeCourseMenu() {
		//make panel
		JPanel courseMenu = new JPanel(new GridLayout(12,1));
		//make buttons to access screens
				JButton changeName = new JButton("Change Course Name");
				JButton changeType = new JButton("Change Course Type");
				JButton changeCreditCount = new JButton("Change Course Credit Count");
				JButton changeTime = new JButton("Change Course Time");
				JButton changeProf = new JButton("Change Course Professor");
				JButton changeDept = new JButton("Change Course Department");
				JButton changeBuilding = new JButton("Change Course Building");
				JButton changeRoomNum = new JButton("Change Course Room Number");
				JButton changeCRN = new JButton("Change Course Refrence Number");
				JButton returnToMenu = new JButton("Return To Menu");

				//button functionality
				changeName.addActionListener(e->{
					setTitle("Change Course Name");
					courseLayout.show(courseCards, "CHANGE_NAME");
				});
				changeType.addActionListener(e->{
					setTitle("Change Course Type");
					courseLayout.show(courseCards, "CHANGE_TYPE");
				});
				changeCreditCount.addActionListener(e->{
					setTitle("Change Course CreditCount");
					courseLayout.show(courseCards, "CHANGE_CREDIT_COUNT");
				});
				changeTime.addActionListener(e->{
					setTitle("Change Course Time");
					courseLayout.show(courseCards, "CHANGE_TIME");
				});
				changeProf.addActionListener(e->{
					setTitle("Change Course Professor");
					courseLayout.show(courseCards, "CHANGE_PROF");
				});
				changeDept.addActionListener(e->{
					setTitle("Change Course Department");
					courseLayout.show(courseCards, "CHANGE_DEPT");
				});
				changeBuilding.addActionListener(e->{
					setTitle("Change Course Building");
					courseLayout.show(courseCards, "CHANGE_BUILDING");
				});
				changeRoomNum.addActionListener(e->{
					setTitle("Change Course Room Number");
					courseLayout.show(courseCards, "CHANGE_ROOM_NUMBER");
				});
				changeCRN.addActionListener(e->{
					setTitle("Change Course Refrence Number");
					courseLayout.show(courseCards, "CHANGE_REFRENCE NUMBERs");
				});
				returnToMenu.addActionListener(e -> {
			        setTitle("Course Management");
			        layout.show(cards, "MENU");
			    });
				
				//make label for panel
				JLabel changeCourseSelection = new JLabel("What would you like to change about the course?",SwingConstants.CENTER);
				
				//add label to panel
				courseMenu.add(changeCourseSelection,BorderLayout.NORTH);
				
				//add buttons to panel
				courseMenu.add(changeName);
				courseMenu.add(changeType);
				courseMenu.add(changeCreditCount);
				courseMenu.add(changeTime);
				courseMenu.add(changeProf);
				courseMenu.add(changeDept);
				courseMenu.add(changeBuilding);
				courseMenu.add(changeRoomNum);
				courseMenu.add(changeCRN);
				courseMenu.add(returnToMenu);
				
				return courseMenu;
	}
	
	//----CHANGE COURSE DETAILS FUNCTIONS----
	//--------SUBSECTION: CHANGE COURSE NAME FUNCTIONS----
	JPanel changeCourseName() {
		//make panel
		JPanel changeName = new JPanel(new GridLayout(4,2));
		//make label for text field
		JLabel crn = new JLabel("Course CRN");
		JLabel name = new JLabel("New Course Name");
		//make text field
		JTextField crnField = new JTextField();
		JTextField nameField = new JTextField();
		//make buttons
		JButton nameChange = new JButton("Change Name");
		JButton returnToSelection = new JButton("Return to Selection");
		//button functionality
		nameChange.addActionListener(e->{
			Course course = CourseManagement.findCourseByCRN(Integer.parseInt(crnField.getText()));
			course.setName(nameField.getText());
			
			crnField.setText("");
			nameField.setText("");
		});
		returnToSelection.addActionListener(e->{
			setTitle("Change Course Details");
			courseLayout.show(courseCards,"COURSE_MENU");
		});
		//add things to panel
		changeName.add(crn);
		changeName.add(crnField);
		changeName.add(name);
		changeName.add(nameField);
		changeName.add(returnToSelection);
		changeName.add(nameChange);
		
		return changeName;
	}
	
	//----CHANGE COURSE DETAILS FUNCTIONS----
	//--------SUBSECTION: CHANGE COURSE TYPE FUNCTIONS----
	JPanel changeType() {
		//make panel
		JPanel changeType = new JPanel(new GridLayout(4,2));
		//make labels for fields
		JLabel type = new JLabel("New Course Type");
		JLabel crn = new JLabel("Course Refrence Number");
		//make text fields
		JTextField crnField = new JTextField();
		JTextField typeField = new JTextField();
		//make buttons
		JButton typechange = new JButton("Change Course Type");
		JButton returnToSelection = new JButton("Return to Selection");
		//button funcitonality
		typechange.addActionListener(e->{
			Course course = CourseManagement.findCourseByCRN(Integer.parseInt(crnField.getText()));
			course.setType(typeField.getText());
			
			crnField.setText("");
			typeField.setText("");
		});
		returnToSelection.addActionListener(e->{
			setTitle("Change Course Details");
			courseLayout.show(courseCards,"COURSE_MENU");
		});
		//add things to panel
		changeType.add(crn);
		changeType.add(crnField);
		changeType.add(type);
		changeType.add(typeField);
		changeType.add(returnToSelection);
		changeType.add(typechange);
		
		return changeType;
	}
	
	//----CHANGE COURSE DETAILS FUNCTIONS----
	//--------SUBSECTION: CHANGE COURSE CREDIT COUNT FUNCTIONS----
	JPanel changeCreditCount() {
		//make panel
		JPanel changeCreditCount = new JPanel(new GridLayout(4,2));
		//make labels for fields
		JLabel creditCount = new JLabel("New Course Credit Count");
		JLabel crn = new JLabel("Course Refrence Number");
		//make text fields
		JTextField crnField = new JTextField();
		JTextField creditCountField = new JTextField();
		//make buttons
		JButton creditCountChange = new JButton("Change Course Credit Count");
		JButton returnToSelection = new JButton("Return to Selection");
		//button funcitonality
		creditCountChange.addActionListener(e->{
			Course course = CourseManagement.findCourseByCRN(Integer.parseInt(crnField.getText()));
			course.setCreditCount(Integer.parseInt(creditCountField.getText()));
			
			crnField.setText("");
			creditCountField.setText("");
		});
		returnToSelection.addActionListener(e->{
			setTitle("Change Course Details");
			courseLayout.show(courseCards,"COURSE_MENU");
		});
		//add things to panel
		changeCreditCount.add(crn);
		changeCreditCount.add(crnField);
		changeCreditCount.add(creditCount);
		changeCreditCount.add(creditCountField);
		changeCreditCount.add(returnToSelection);
		changeCreditCount.add(creditCountChange);
		
		return changeCreditCount;
	}
	
	//----CHANGE COURSE DETAILS FUNCTIONS----
	//--------SUBSECTION: CHANGE COURSE TIME FUNCTIONS----
	JPanel changeTime() {
		//make panel
		JPanel changeTime = new JPanel(new GridLayout(4,2));
		//make labels for fields
		JLabel StartTime = new JLabel("New Course Start Time");
		JLabel EndTime = new JLabel("New Course End Time");
		JLabel crn = new JLabel("Course Refrence Number");
		//make text fields
		JTextField crnField = new JTextField();
		JTextField startTimeField = new JTextField();
		JTextField endTimeField = new JTextField();
		//make buttons
		JButton TimeChange = new JButton("Change Course Time");
		JButton returnToSelection = new JButton("Return to Selection");
		//button funcitonality
		TimeChange.addActionListener(e->{
			CourseManagement.changeCourseTime(
					Integer.parseInt(crnField.getText()),
					startTimeField.getText(),
					endTimeField.getText()
					);
			
			crnField.setText("");
			startTimeField.setText("");
			endTimeField.setText("");
		});
		returnToSelection.addActionListener(e->{
			setTitle("Change Course Details");
			courseLayout.show(courseCards,"COURSE_MENU");
		});
		//add things to panel
		changeTime.add(crn);
		changeTime.add(crnField);
		changeTime.add(StartTime);
		changeTime.add(startTimeField);
		changeTime.add(EndTime);
		changeTime.add(endTimeField);
		changeTime.add(returnToSelection);
		changeTime.add(TimeChange);
		
		return changeTime;
	}
	
}