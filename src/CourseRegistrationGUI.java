import javax.swing.*;
import java.awt.*;

public class CourseRegistrationGUI extends JFrame{
	
	//create management system
    private final CourseManagement rcms;
    
    // Constructor
    public CourseRegistrationGUI() {
        this.rcms = new CourseManagement();
        // Load the Rollins catalog into the system
        // Make sure rollins_catalog_spring2026.csv is in your project root
        this.rcms.importCoursesFromCsv("rollins_catalog_spring2026.csv");
    }

    
	//layout parent
	static CardLayout layout;
	static JPanel cards;
	
	//card layout for changing course details
	static CardLayout courseLayout;
	static JPanel courseCards;
	
	//user role tracking
	private String userRole = ""; // "ADMIN", "PROFESSOR", "STUDENT"
	private int userId = 0;
	
	//Main running program
    public void GUIrun() {
        setTitle("Course Management - Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layout = new CardLayout();
        cards = new JPanel(layout);

        // Build login screen first
        JPanel loginPanel = buildLoginScreen();
        
        // Build all other screens
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
        JPanel professorAddCoursePanel = professorAdd();
        JPanel professorDropCoursePanel = professorDrop();
        JPanel seeAllStudentsPanel = seeStudents();
        JPanel seeAllProfessorsPanel = seeProfessors();
        JPanel seeAllCoursesPanel = seeCourses();

        // Add them to CardLayout
        cards.add(loginPanel, "LOGIN");
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
        cards.add(professorAddCoursePanel,"PROFESSOR_ADD_COURSE");
        cards.add(professorDropCoursePanel,"PROFESSOR_DROP_COURSE");
        cards.add(seeAllStudentsPanel,"SEE_STUDENT_LIST");
        cards.add(seeAllProfessorsPanel,"SEE_PROFESSOR_LIST");
        cards.add(seeAllCoursesPanel,"SEE_COURSE_LIST");

        add(cards);
        layout.show(cards, "LOGIN"); // Start with login screen
        setVisible(true);
    }
    
    //----LOGIN SCREEN----
    JPanel buildLoginScreen() {
    	JPanel loginPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.insets = new Insets(10, 10, 10, 10);
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	
    	JLabel titleLabel = new JLabel("Rollins College Course Management System", SwingConstants.CENTER);
    	titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    	
    	JLabel roleLabel = new JLabel("Select Role:");
    	JComboBox<String> roleCombo = new JComboBox<>(new String[]{"Admin", "Professor", "Student"});
    	
    	JLabel idLabel = new JLabel("User ID:");
    	JTextField idField = new JTextField(15);
    	
    	JLabel passwordLabel = new JLabel("Password:");
    	JPasswordField passwordField = new JPasswordField(15);
    	
    	JButton loginButton = new JButton("Login");
    	JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
    	
    	loginButton.addActionListener(e -> {
    	    try {
    	        String role = (String) roleCombo.getSelectedItem();
    	        Integer id = CourseManagement.parseInteger(idField.getText());
    	        String password = new String(passwordField.getPassword());
    	        
    	        if (id == null) {
    	            statusLabel.setText("Please enter a valid User ID");
    	            statusLabel.setForeground(Color.RED);
    	            return;
    	        }
    	        
    	        if (password.trim().isEmpty()) {
    	            statusLabel.setText("Please enter a password");
    	            statusLabel.setForeground(Color.RED);
    	            return;
    	        }
    	        
    	        // Accept any password for simplicity
    	        userRole = role.toUpperCase();
    	        userId = id;
    	        
    	        // REBUILD THE MENU AFTER LOGIN
    	        JPanel newMenu = buildMainMenu();
    	        cards.add(newMenu, "MENU_NEW");
    	        cards.remove(cards.getComponent(1)); // Remove old menu
    	        cards.add(newMenu, "MENU", 1); // Add new menu at position 1
    	        
    	        setTitle("Course Management - " + role);
    	        layout.show(cards, "MENU");
    	        
    	        // Clear fields
    	        idField.setText("");
    	        passwordField.setText("");
    	        statusLabel.setText("");
    	        
    	    } catch (Exception ex) {
    	        statusLabel.setText("Login error: " + ex.getMessage());
    	        statusLabel.setForeground(Color.RED);
    	    }
    	});
    	
    	// Layout components
    	gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
    	loginPanel.add(titleLabel, gbc);
    	
    	gbc.gridwidth = 1; gbc.gridy = 1;
    	loginPanel.add(roleLabel, gbc);
    	gbc.gridx = 1;
    	loginPanel.add(roleCombo, gbc);
    	
    	gbc.gridx = 0; gbc.gridy = 2;
    	loginPanel.add(idLabel, gbc);
    	gbc.gridx = 1;
    	loginPanel.add(idField, gbc);
    	
    	gbc.gridx = 0; gbc.gridy = 3;
    	loginPanel.add(passwordLabel, gbc);
    	gbc.gridx = 1;
    	loginPanel.add(passwordField, gbc);
    	
    	gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
    	loginPanel.add(loginButton, gbc);
    	
    	gbc.gridy = 5;
    	loginPanel.add(statusLabel, gbc);
    	
    	return loginPanel;
    }
	
    //----MAIN MENU FUNCTIONS----
	JPanel buildMainMenu() {
		JPanel main = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
		
		JLabel title = new JLabel("Welcome to the Rollins College Course Management System", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 14));
		
		// Admin buttons (full access)
		JButton addDepartment = new JButton("Add Department");
		JButton addProfessor = new JButton("Add Professor");
		JButton addStudent = new JButton("Add Student");
		JButton createCourse = new JButton("Create Course");
		JButton removeCourse = new JButton("Remove Course");
		JButton changeCourse = new JButton("Change Course Details");
		JButton seeStudentList = new JButton("See Current Students");
		JButton seeProfessorList = new JButton("See Current Professors");
		JButton seeCourseList = new JButton("See Current Courses");
		
		// Professor buttons
		JButton professorAddCourse = new JButton("Add Course for Professors");
		JButton professorDropCourse = new JButton("Drop Course for Professors");
		JButton seeCourseInfo = new JButton("Course Roster");
		
		// Student buttons
		JButton studentAddCourse = new JButton("Add Course for Students");
		JButton studentDropCourse = new JButton("Drop Course for Students");
		JButton seeStudentSchedule = new JButton("Student Schedule");
		
		JButton logout = new JButton("Logout");
		JButton exit = new JButton("Exit");
		
		// Navigation for admin buttons
		addDepartment.addActionListener(e->{setTitle("Add Department"); layout.show(cards, "ADD_DEPARTMENT");});
		addProfessor.addActionListener(e->{setTitle("Add Professor"); layout.show(cards, "ADD_PROFESSOR");});
		addStudent.addActionListener(e->{setTitle("Add Student"); layout.show(cards, "ADD_STUDENT");});
		createCourse.addActionListener(e->{setTitle("Create Course"); layout.show(cards, "CREATE_COURSE");});
		removeCourse.addActionListener(e->{setTitle("Remove Course"); layout.show(cards, "REMOVE_COURSE");});
		changeCourse.addActionListener(e->{setTitle("Change Course Details"); layout.show(cards, "CHANGE_COURSE_DETAILS");});
		seeStudentList.addActionListener(e->{setTitle("Full Student List"); layout.show(cards, "SEE_STUDENT_LIST");});
		seeProfessorList.addActionListener(e->{setTitle("Full Professor List"); layout.show(cards, "SEE_PROFESSOR_LIST");});
		seeCourseList.addActionListener(e->{setTitle("Full Course List"); layout.show(cards, "SEE_COURSE_LIST");});
		
		// Navigation for professor buttons
		professorAddCourse.addActionListener(e->{setTitle("Add Course for Professors"); layout.show(cards, "PROFESSOR_ADD_COURSE");});
		professorDropCourse.addActionListener(e->{setTitle("Drop Course for Professors"); layout.show(cards, "PROFESSOR_DROP_COURSE");});
		seeCourseInfo.addActionListener(e->{setTitle("Full Course Details"); layout.show(cards, "SEE_COURSE_DETAILS");});
		
		// Navigation for student buttons
		studentAddCourse.addActionListener(e->{setTitle("Add Course for Students"); layout.show(cards, "STUDENT_ADD_COURSE");});
		studentDropCourse.addActionListener(e->{setTitle("Drop Course for Students"); layout.show(cards, "STUDENT_DROP_COURSE");});
		seeStudentSchedule.addActionListener(e->{setTitle("Student Schedule"); layout.show(cards, "SEE_STUDENT_SCHEDULE");});
		
		logout.addActionListener(e -> {
			userRole = "";
			userId = 0;
			setTitle("Course Management - Login");
			layout.show(cards, "LOGIN");
		});
		exit.addActionListener(e->dispose());
		
		buttonPanel.add(title);
		
		// Add buttons based on role
		if (userRole.equals("ADMIN")) {
			buttonPanel.add(addDepartment);
			buttonPanel.add(addProfessor);
			buttonPanel.add(addStudent);
			buttonPanel.add(createCourse);
			buttonPanel.add(removeCourse);
			buttonPanel.add(changeCourse);
			buttonPanel.add(studentAddCourse);
			buttonPanel.add(studentDropCourse);
			buttonPanel.add(seeCourseInfo);
			buttonPanel.add(seeStudentSchedule);
			buttonPanel.add(professorAddCourse);
			buttonPanel.add(professorDropCourse);
			buttonPanel.add(seeStudentList);
			buttonPanel.add(seeProfessorList);
			buttonPanel.add(seeCourseList);
		} else if (userRole.equals("PROFESSOR")) {
			buttonPanel.add(professorAddCourse);
			buttonPanel.add(professorDropCourse);
			buttonPanel.add(seeCourseInfo);
			buttonPanel.add(seeCourseList);
		} else if (userRole.equals("STUDENT")) {
			buttonPanel.add(studentAddCourse);
			buttonPanel.add(studentDropCourse);
			buttonPanel.add(seeStudentSchedule);
			buttonPanel.add(seeCourseList);
		}
		
		buttonPanel.add(logout);
		buttonPanel.add(exit);
		
		main.add(buttonPanel, BorderLayout.CENTER);
		return main;
	}
	
	//----ADD DEPARTMENT----
	JPanel addDepartment() {
		JPanel dep = new JPanel(new GridLayout(4,2));
		JLabel deptname = new JLabel("Department Name");
		JLabel deptref = new JLabel("Department Reference");
		JTextField deptnameField = new JTextField();
		JTextField deptrefField = new JTextField();
		JButton addDept = new JButton("Add Department");
		JButton returnToMenu = new JButton("Return to Menu");
		
		addDept.addActionListener(e->{
			try {
				String name = deptnameField.getText();
				String ref = deptrefField.getText();
				if (name == null || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Department name cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (ref == null || ref.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Department reference cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				rcms.addDepartment(new Department(name, ref));
				JOptionPane.showMessageDialog(this, "Department added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				deptnameField.setText(""); deptrefField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error adding department: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
		
		dep.add(deptname); dep.add(deptnameField); dep.add(deptref); dep.add(deptrefField);
		dep.add(returnToMenu); dep.add(addDept);
		return dep;
	}
	
	//----ADD PROFESSOR----
	JPanel addProfessor() {
		JPanel prof = new JPanel(new GridLayout(4,2));
		JLabel profname = new JLabel("Professor Name");
		JLabel profID = new JLabel("Professor ID Number");
		JLabel profdept = new JLabel("Professor Department Reference");
		JTextField profnameField = new JTextField();
		JTextField profIDField = new JTextField();
		JTextField profdeptField = new JTextField();
		JButton addProf = new JButton("Add Professor");
		JButton returnToMenu = new JButton("Return to Menu");
		
		addProf.addActionListener(e->{
			try {
				String name = profnameField.getText();
				Integer id = CourseManagement.parseInteger(profIDField.getText());
				String deptRef = profdeptField.getText();
				if (name == null || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Professor name cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (id == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid professor ID number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (deptRef == null || deptRef.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Department reference cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Department dept = rcms.findDepartmentByReference(deptRef);
				if (dept == null) {
					JOptionPane.showMessageDialog(this, "Department not found with reference: " + deptRef, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				rcms.addProfessor(new Professor(name, id, dept));
				JOptionPane.showMessageDialog(this, "Professor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				profnameField.setText(""); profIDField.setText(""); profdeptField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error adding professor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
		
		prof.add(profname); prof.add(profnameField); prof.add(profID); prof.add(profIDField);
		prof.add(profdept); prof.add(profdeptField); prof.add(returnToMenu); prof.add(addProf);
		return prof;
	}
	
	//----ADD STUDENT----
	JPanel addStudent() {
		JPanel addStu = new JPanel(new GridLayout(4,2));
		JLabel stuName = new JLabel("Student Name");
		JLabel stuID = new JLabel("Student ID Number");
		JTextField stuNameField = new JTextField();
		JTextField stuIDField = new JTextField();
		JButton addstu = new JButton("Add Student");
		JButton returnToMenu = new JButton("Return to Menu");
		
		addstu.addActionListener(e->{
			try {
				String name = stuNameField.getText();
				Integer id = CourseManagement.parseInteger(stuIDField.getText());
				if (name == null || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Student name cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (id == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid student ID number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				rcms.addStudent(new Student(name, id, 0));
				JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				stuNameField.setText(""); stuIDField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
		
		addStu.add(stuName); addStu.add(stuNameField); addStu.add(stuID); addStu.add(stuIDField);
		addStu.add(returnToMenu); addStu.add(addstu);
		return addStu;
	}
	
	//----CREATE COURSE----
	JPanel CreateCourse() {
		JPanel createCourse = new JPanel(new GridLayout(12,2));
		JLabel courseName = new JLabel("Course Name");
		JLabel courseType = new JLabel("Course Type");
		JLabel creditHours = new JLabel("Course Credit Hours");
		JLabel startTime = new JLabel("Start Time");
		JLabel endTime = new JLabel("End Time");
		JLabel courseProf = new JLabel("Course Professor ID");
		JLabel coursedept = new JLabel("Course Department Reference");
		JLabel building = new JLabel("Building");
		JLabel room = new JLabel("Room Number");
		JLabel crn = new JLabel("Course Reference Number (CRN)");
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
		JButton makeCourse = new JButton("Create Course");
		JButton returnToMenu = new JButton("Return to Menu");
		
		makeCourse.addActionListener(e->{
			try {
				String name = courseNameField.getText();
				String type = courseTypeField.getText();
				Integer credits = CourseManagement.parseInteger(creditHoursField.getText());
				String start = startTimeField.getText();
				String end = endTimeField.getText();
				Integer profID = CourseManagement.parseInteger(courseprofField.getText());
				String deptRef = coursedeptfield.getText();
				String bldg = buildingField.getText();
				Integer roomNum = CourseManagement.parseInteger(roomField.getText());
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				
				if (name == null || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Course name cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (credits == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid credit hours number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (start == null || start.trim().isEmpty() || end == null || end.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Start and end times cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (profID == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid professor ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (roomNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid room number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Professor p = rcms.findProfessorById(profID);
				if (p == null) {
					JOptionPane.showMessageDialog(this, "Professor not found with ID: " + profID, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Department d = rcms.findDepartmentByReference(deptRef);
				if (d == null) {
					JOptionPane.showMessageDialog(this, "Department not found with reference: " + deptRef, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Course created = rcms.createCourse(name, type, credits, start, end, p, d, bldg, roomNum, crnNum);
				if (created != null) {
					JOptionPane.showMessageDialog(this, "Course created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					courseNameField.setText(""); courseTypeField.setText(""); creditHoursField.setText("");
					startTimeField.setText(""); endTimeField.setText(""); courseprofField.setText("");
					coursedeptfield.setText(""); buildingField.setText(""); roomField.setText(""); crnField.setText("");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error creating course: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
		
		createCourse.add(courseName); createCourse.add(courseNameField); createCourse.add(courseType); createCourse.add(courseTypeField);
		createCourse.add(creditHours); createCourse.add(creditHoursField); createCourse.add(startTime); createCourse.add(startTimeField);
		createCourse.add(endTime); createCourse.add(endTimeField); createCourse.add(courseProf); createCourse.add(courseprofField);
		createCourse.add(coursedept); createCourse.add(coursedeptfield); createCourse.add(building); createCourse.add(buildingField);
		createCourse.add(room); createCourse.add(roomField); createCourse.add(crn); createCourse.add(crnField);
		createCourse.add(returnToMenu); createCourse.add(makeCourse);
		return createCourse;
	}
	
	//----REMOVE COURSE----
	JPanel removeCourse() {
		JPanel removeCourse = new JPanel(new GridLayout(2,2));
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JButton deleteCourse = new JButton("Remove Course");
		JButton returnToMenu = new JButton("Return To Menu");
		
		deleteCourse.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this course?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					if (rcms.dropCourse(crnNum)) {
						JOptionPane.showMessageDialog(this, "Course removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
						crnField.setText("");
					} else {
						JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error removing course: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
		
		removeCourse.add(crn); removeCourse.add(crnField); removeCourse.add(returnToMenu); removeCourse.add(deleteCourse);
		return removeCourse;
	}
	
	//----STUDENT ADD COURSE----
	JPanel studentAdd() {
		JPanel studentAdd = new JPanel(new GridLayout(4,2));
		JLabel studentID = new JLabel("Student ID Number");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField IDField = new JTextField();
		JTextField crnField = new JTextField();
		JButton addCourse = new JButton("Add Course");
		JButton returnToMenu = new JButton("Return To Menu");
		
		// Pre-fill student ID if logged in as student
		if (userRole.equals("STUDENT")) {
			IDField.setText(String.valueOf(userId));
			IDField.setEditable(false);
		}
		
		addCourse.addActionListener(e->{
			try {
				Integer studentID_val = CourseManagement.parseInteger(IDField.getText());
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				if (studentID_val == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid student ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (rcms.enrollStudent(studentID_val, crnNum)) {
					JOptionPane.showMessageDialog(this, "Student enrolled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					if (!userRole.equals("STUDENT")) {
						IDField.setText("");
					}
					crnField.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "Failed to enroll student. Check console for details.", "Error", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error enrolling student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
		
		studentAdd.add(studentID); studentAdd.add(IDField); studentAdd.add(crn); studentAdd.add(crnField);
		studentAdd.add(returnToMenu); studentAdd.add(addCourse);
		return studentAdd;
	}
	
	//----STUDENT DROP COURSE----
	JPanel studentDrop() {
		JPanel studentDrop = new JPanel(new GridLayout(4,2));
		JLabel studentID = new JLabel("Student ID Number");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField IDField = new JTextField();
		JTextField crnField = new JTextField();
		JButton dropCourse = new JButton("Drop Course");
		JButton returnToMenu = new JButton("Return To Menu");
		
		// Pre-fill student ID if logged in as student
		if (userRole.equals("STUDENT")) {
			IDField.setText(String.valueOf(userId));
			IDField.setEditable(false);
		}
		
		dropCourse.addActionListener(e->{
			try {
				Integer studentID_val = CourseManagement.parseInteger(IDField.getText());
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				if (studentID_val == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid student ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (rcms.dropStudentFromCourse(studentID_val, crnNum)) {
					JOptionPane.showMessageDialog(this, "Student dropped from course successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					if (!userRole.equals("STUDENT")) {
						IDField.setText("");
					}
					crnField.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "Failed to drop student. Check console for details.", "Error", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error dropping student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
		
		studentDrop.add(studentID); studentDrop.add(IDField); studentDrop.add(crn); studentDrop.add(crnField);
		studentDrop.add(returnToMenu); studentDrop.add(dropCourse);
		return studentDrop
				;
	}
	
	//----COURSE ROSTER----
	JPanel courseRoster() {
	    JPanel courseRoster = new JPanel(new BorderLayout());
	    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
	    JLabel crn = new JLabel("Course Reference Number");
	    JTextField crnField = new JTextField();
	    JTextArea rosterDisplay = new JTextArea(15, 50);
	    rosterDisplay.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(rosterDisplay);
	    JButton getCourseRoster = new JButton("Show Course Roster");
	    JButton returnToMenu = new JButton("Return To Menu");
	    
	    getCourseRoster.addActionListener(e -> {
	    	try {
		        Integer crnNum = CourseManagement.parseInteger(crnField.getText());
		        if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
		        String rosterText = rcms.getRosterDisplay(crnNum);
		        rosterDisplay.setText(rosterText);
		        crnField.setText("");
	    	} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error displaying roster: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
	    });
	    returnToMenu.addActionListener(e -> {setTitle("Course Management"); layout.show(cards, "MENU");});
	    
	    inputPanel.add(crn); inputPanel.add(crnField); inputPanel.add(returnToMenu); inputPanel.add(getCourseRoster);
	    courseRoster.add(inputPanel, BorderLayout.NORTH); courseRoster.add(scrollPane, BorderLayout.CENTER);
	    return courseRoster;
	}
	
	//----STUDENT SCHEDULE----
	JPanel seeSchedule() {
	    JPanel studentSchedule = new JPanel(new BorderLayout());
	    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
	    JLabel ID = new JLabel("Student ID Number");
	    JTextField IDField = new JTextField();
	    JTextArea scheduleDisplay = new JTextArea(15, 50);
	    scheduleDisplay.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(scheduleDisplay);
	    JButton getStudentSchedule = new JButton("Show Student Schedule");
	    JButton returnToMenu = new JButton("Return To Menu");
	    
	    // Pre-fill student ID if logged in as student
	    if (userRole.equals("STUDENT")) {
	    	IDField.setText(String.valueOf(userId));
	    	IDField.setEditable(false);
	    }
	    
	    getStudentSchedule.addActionListener(e->{
	    	try {
		    	Integer studentID = CourseManagement.parseInteger(IDField.getText());
		    	if (studentID == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid student ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
		    	String schedule = rcms.getStudentSchedule(studentID);
		    	scheduleDisplay.setText(schedule);
		    	if (!userRole.equals("STUDENT")) {
		    		IDField.setText("");
		    	}
	    	} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error displaying schedule: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
	    });
	    returnToMenu.addActionListener(e -> {setTitle("Course Management"); layout.show(cards, "MENU");});
	    
	    inputPanel.add(ID); inputPanel.add(IDField); inputPanel.add(returnToMenu); inputPanel.add(getStudentSchedule);
	    studentSchedule.add(inputPanel, BorderLayout.NORTH); studentSchedule.add(scrollPane, BorderLayout.CENTER);
	    return studentSchedule;
	}
	
	//----CHANGE COURSE DETAILS SELECTION----
	JPanel changeCourseDetailsSelection() {
		JPanel changeCourseDetails = new JPanel(new BorderLayout());
		courseLayout = new CardLayout();
		courseCards = new JPanel(courseLayout);
	    
		JPanel changeCourseMenuPanel = changeCourseMenu();
		JPanel changeCourseNamePanel = changeCourseName();
		JPanel changeCourseTypePanel = changeType();
		JPanel changeCourseCreditCountPanel = changeCreditCount();
		JPanel changeTimePanel = changeTime();
		JPanel changeProfPanel = changeProf();
		JPanel changeDeptPanel = changeDept();
		JPanel changeBuildingPanel = changeBuilding();
		JPanel changeRoomNumPanel = changeRoom();
		JPanel changeRefNumPanel = changeRefNum();
		
		courseCards.add(changeCourseMenuPanel,"COURSE_MENU");
		courseCards.add(changeCourseNamePanel,"CHANGE_NAME");
		courseCards.add(changeCourseTypePanel,"CHANGE_TYPE");
		courseCards.add(changeCourseCreditCountPanel,"CHANGE_CREDIT_COUNT");
		courseCards.add(changeTimePanel,"CHANGE_TIME");
		courseCards.add(changeProfPanel,"CHANGE_PROF");
		courseCards.add(changeDeptPanel,"CHANGE_DEPT");
		courseCards.add(changeBuildingPanel,"CHANGE_BUILDING");
		courseCards.add(changeRoomNumPanel,"CHANGE_ROOM_NUMBER");
		courseCards.add(changeRefNumPanel,"CHANGE_REFERENCE_NUMBER");

		changeCourseDetails.add(courseCards,BorderLayout.CENTER);
		return changeCourseDetails;
	}
	
	//----CHANGE COURSE MENU----
	JPanel changeCourseMenu() {
		JPanel courseMenu = new JPanel(new GridLayout(12,1));
		JButton changeName = new JButton("Change Course Name");
		JButton changeType = new JButton("Change Course Type");
		JButton changeCreditCount = new JButton("Change Course Credit Count");
		JButton changeTime = new JButton("Change Course Time");
		JButton changeProf = new JButton("Change Course Professor");
		JButton changeDept = new JButton("Change Course Department");
		JButton changeBuilding = new JButton("Change Course Building");
		JButton changeRoomNum = new JButton("Change Course Room Number");
		JButton changeCRN = new JButton("Change Course Reference Number");
		JButton returnToMenu = new JButton("Return To Menu");

		changeName.addActionListener(e->{setTitle("Change Course Name"); courseLayout.show(courseCards, "CHANGE_NAME");});
		changeType.addActionListener(e->{setTitle("Change Course Type"); courseLayout.show(courseCards, "CHANGE_TYPE");});
		changeCreditCount.addActionListener(e->{setTitle("Change Course CreditCount"); courseLayout.show(courseCards, "CHANGE_CREDIT_COUNT");});
		changeTime.addActionListener(e->{setTitle("Change Course Time"); courseLayout.show(courseCards, "CHANGE_TIME");});
		changeProf.addActionListener(e->{setTitle("Change Course Professor"); courseLayout.show(courseCards, "CHANGE_PROF");});
		changeDept.addActionListener(e->{setTitle("Change Course Department"); courseLayout.show(courseCards, "CHANGE_DEPT");});
		changeBuilding.addActionListener(e->{setTitle("Change Course Building"); courseLayout.show(courseCards, "CHANGE_BUILDING");});
		changeRoomNum.addActionListener(e->{setTitle("Change Course Room Number"); courseLayout.show(courseCards, "CHANGE_ROOM_NUMBER");});
		changeCRN.addActionListener(e->{setTitle("Change Course Reference Number"); courseLayout.show(courseCards, "CHANGE_REFERENCE_NUMBER");});
		returnToMenu.addActionListener(e -> {setTitle("Course Management"); layout.show(cards, "MENU");});
		
		JLabel changeCourseSelection = new JLabel("What would you like to change about the course?",SwingConstants.CENTER);
		courseMenu.add(changeCourseSelection,BorderLayout.NORTH);
		courseMenu.add(changeName); courseMenu.add(changeType); courseMenu.add(changeCreditCount); courseMenu.add(changeTime);
		courseMenu.add(changeProf); courseMenu.add(changeDept); courseMenu.add(changeBuilding); courseMenu.add(changeRoomNum);
		courseMenu.add(changeCRN); courseMenu.add(returnToMenu);
		return courseMenu;
	}
	
	//----CHANGE COURSE NAME----
	JPanel changeCourseName() {
		JPanel changeName = new JPanel(new GridLayout(4,2));
		JLabel crn = new JLabel("Course CRN");
		JLabel name = new JLabel("New Course Name");
		JTextField crnField = new JTextField();
		JTextField nameField = new JTextField();
		JButton nameChange = new JButton("Change Name");
		JButton returnToSelection = new JButton("Return to Selection");
		
		nameChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				String newName = nameField.getText();
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newName == null || newName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Course name cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				course.setName(newName);
				JOptionPane.showMessageDialog(this, "Course name updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				crnField.setText(""); nameField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing course name: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeName.add(crn); changeName.add(crnField); changeName.add(name); changeName.add(nameField);
		changeName.add(returnToSelection); changeName.add(nameChange);
		return changeName;
	}
	
	//----CHANGE COURSE TYPE----
	JPanel changeType() {
		JPanel changeType = new JPanel(new GridLayout(4,2));
		JLabel type = new JLabel("New Course Type");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField typeField = new JTextField();
		JButton typechange = new JButton("Change Course Type");
		JButton returnToSelection = new JButton("Return to Selection");
		
		typechange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				String newType = typeField.getText();
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				course.setType(newType);
				JOptionPane.showMessageDialog(this, "Course type updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				crnField.setText(""); typeField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing course type: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeType.add(crn); changeType.add(crnField); changeType.add(type); changeType.add(typeField);
		changeType.add(returnToSelection); changeType.add(typechange);
		return changeType;
	}
	
	//----CHANGE COURSE CREDIT COUNT----
	JPanel changeCreditCount() {
		JPanel changeCreditCount = new JPanel(new GridLayout(4,2));
		JLabel creditCount = new JLabel("New Course Credit Count");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField creditCountField = new JTextField();
		JButton creditCountChange = new JButton("Change Course Credit Count");
		JButton returnToSelection = new JButton("Return to Selection");
		
		creditCountChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				Integer newCredits = CourseManagement.parseInteger(creditCountField.getText());
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newCredits == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid credit count", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				course.setCreditCount(newCredits);
				JOptionPane.showMessageDialog(this, "Course credit count updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				crnField.setText(""); creditCountField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing credit count: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeCreditCount.add(crn); changeCreditCount.add(crnField); changeCreditCount.add(creditCount); changeCreditCount.add(creditCountField);
		changeCreditCount.add(returnToSelection); changeCreditCount.add(creditCountChange);
		return changeCreditCount;
	}
	
	//----CHANGE COURSE TIME----
	JPanel changeTime() {
		JPanel changeTime = new JPanel(new GridLayout(5,2));
		JLabel StartTime = new JLabel("New Course Start Time");
		JLabel EndTime = new JLabel("New Course End Time");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField startTimeField = new JTextField();
		JTextField endTimeField = new JTextField();
		JButton TimeChange = new JButton("Change Course Time");
		JButton returnToSelection = new JButton("Return to Selection");
		
		TimeChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				String newStart = startTimeField.getText();
				String newEnd = endTimeField.getText();
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newStart == null || newStart.trim().isEmpty() || newEnd == null || newEnd.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Start and end times cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (rcms.changeCourseTime(crnNum, newStart, newEnd)) {
					JOptionPane.showMessageDialog(this, "Course time updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					crnField.setText(""); startTimeField.setText(""); endTimeField.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing course time: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeTime.add(crn); changeTime.add(crnField); changeTime.add(StartTime); changeTime.add(startTimeField);
		changeTime.add(EndTime); changeTime.add(endTimeField); changeTime.add(returnToSelection); changeTime.add(TimeChange);
		return changeTime;
	}
	
	//----CHANGE COURSE PROFESSOR----
	JPanel changeProf() {
		JPanel changeProf = new JPanel(new GridLayout(4,2));
		JLabel profID = new JLabel("New Professor ID Number");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField profIDField = new JTextField();
		JButton profChange = new JButton("Change Course Professor");
		JButton returnToSelection = new JButton("Return to Selection");
		
		profChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				Integer newProfID = CourseManagement.parseInteger(profIDField.getText());
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newProfID == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid professor ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (rcms.assignProfessorToCourse(newProfID, crnNum)) {
					JOptionPane.showMessageDialog(this, "Professor assigned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					crnField.setText(""); profIDField.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "Failed to assign professor. Check console for details.", "Error", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing professor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeProf.add(crn); changeProf.add(crnField); changeProf.add(profID); changeProf.add(profIDField);
		changeProf.add(returnToSelection); changeProf.add(profChange);
		return changeProf;
	}
	
	//----CHANGE COURSE DEPARTMENT----
	JPanel changeDept() {
		JPanel changeDept = new JPanel(new GridLayout(4,2));
		JLabel deptRef = new JLabel("New Department Reference");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField deptRefField = new JTextField();
		JButton deptChange = new JButton("Change Course Department");
		JButton returnToSelection = new JButton("Return to Selection");
		
		deptChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				String newDeptRef = deptRefField.getText();
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newDeptRef == null || newDeptRef.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Department reference cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Department dept = rcms.findDepartmentByReference(newDeptRef);
				if (dept == null) {
					JOptionPane.showMessageDialog(this, "Department not found with reference: " + newDeptRef, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				course.setDepartment(dept);
				JOptionPane.showMessageDialog(this, "Department updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				crnField.setText(""); deptRefField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing department: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeDept.add(crn); changeDept.add(crnField); changeDept.add(deptRef); changeDept.add(deptRefField);
		changeDept.add(returnToSelection); changeDept.add(deptChange);
		return changeDept;
	}
	
	//----CHANGE COURSE BUILDING----
	JPanel changeBuilding() {
		JPanel changeBuild = new JPanel(new GridLayout(4,2));
		JLabel Building = new JLabel("New Course Building");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField BuildingField = new JTextField();
		JButton buildChange = new JButton("Change Course Building");
		JButton returnToSelection = new JButton("Return to Selection");
		
		buildChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				String newBuilding = BuildingField.getText();
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newBuilding == null || newBuilding.trim().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Building name cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				course.setBuilding(newBuilding);
				JOptionPane.showMessageDialog(this, "Building updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				crnField.setText(""); BuildingField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing building: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeBuild.add(crn); changeBuild.add(crnField); changeBuild.add(Building); changeBuild.add(BuildingField);
		changeBuild.add(returnToSelection); changeBuild.add(buildChange);
		return changeBuild;
	}
	
	//----CHANGE COURSE ROOM NUMBER----
	JPanel changeRoom() {
		JPanel changeRoom = new JPanel(new GridLayout(4,2));
		JLabel roomNum = new JLabel("New Course Room Number");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField RoomNumField = new JTextField();
		JButton roomChange = new JButton("Change Course Room Number");
		JButton returnToSelection = new JButton("Return to Selection");
		
		roomChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				Integer newRoom = CourseManagement.parseInteger(RoomNumField.getText());
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newRoom == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid room number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				course.setRoomNumber(newRoom);
				JOptionPane.showMessageDialog(this, "Room number updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				crnField.setText(""); RoomNumField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing room number: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeRoom.add(crn); changeRoom.add(crnField); changeRoom.add(roomNum); changeRoom.add(RoomNumField);
		changeRoom.add(returnToSelection); changeRoom.add(roomChange);
		return changeRoom;
	}
	
	//----CHANGE COURSE REFERENCE NUMBER----
	JPanel changeRefNum() {
		JPanel changeRefNum = new JPanel(new GridLayout(4,2));
		JLabel refNum = new JLabel("New Course Reference Number");
		JLabel crn = new JLabel("Current Course Reference Number");
		JTextField crnField = new JTextField();
		JTextField refNumField = new JTextField();
		JButton refNumChange = new JButton("Change Course Reference Number");
		JButton returnToSelection = new JButton("Return to Selection");
		
		refNumChange.addActionListener(e->{
			try {
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				Integer newCRN = CourseManagement.parseInteger(refNumField.getText());
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid current CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (newCRN == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid new CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (rcms.findCourseByCRN(newCRN) != null) {
					JOptionPane.showMessageDialog(this, "A course with CRN " + newCRN + " already exists", "Duplicate CRN", JOptionPane.WARNING_MESSAGE);
					return;
				}
				course.setCrn(newCRN);
				JOptionPane.showMessageDialog(this, "Course reference number updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				crnField.setText(""); refNumField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error changing CRN: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		returnToSelection.addActionListener(e->{setTitle("Change Course Details"); courseLayout.show(courseCards,"COURSE_MENU");});
		
		changeRefNum.add(crn); changeRefNum.add(crnField); changeRefNum.add(refNum); changeRefNum.add(refNumField);
		changeRefNum.add(returnToSelection); changeRefNum.add(refNumChange);
		return changeRefNum;
	}
	
	//----PROFESSOR ADD COURSE----
	JPanel professorAdd() {
		JPanel professorAdd = new JPanel(new GridLayout(4,2));
		JLabel profID = new JLabel("Professor ID Number");
		JLabel crn = new JLabel("Course Reference Number");
		JTextField IDField = new JTextField();
		JTextField crnField = new JTextField();
		JButton addCourse = new JButton("Add Course");
		JButton returnToMenu = new JButton("Return To Menu");

		// Pre-fill professor ID if logged in as professor
		if (userRole.equals("PROFESSOR")) {
			IDField.setText(String.valueOf(userId));
			IDField.setEditable(false);
		}

		addCourse.addActionListener(e->{
			try {
				Integer profID_val = CourseManagement.parseInteger(IDField.getText());
				Integer crnNum = CourseManagement.parseInteger(crnField.getText());
				if (profID_val == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid professor ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (crnNum == null) {
					JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Professor prof = rcms.findProfessorById(profID_val);
				if (prof == null) {
					JOptionPane.showMessageDialog(this, "Professor not found with ID: " + profID_val, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Course course = rcms.findCourseByCRN(crnNum);
				if (course == null) {
					JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (prof.addCourse(course)) {
					JOptionPane.showMessageDialog(this, "Course added to professor successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Professor is already teaching this course", "Already Assigned", JOptionPane.INFORMATION_MESSAGE);
				}
				if (!userRole.equals("PROFESSOR")) {
					IDField.setText("");
				}
				crnField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error adding course to professor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		returnToMenu.addActionListener(e ->{
			setTitle("Course Management"); 
			layout.show(cards, "MENU");
		});
		
		professorAdd.add(profID); 
		professorAdd.add(IDField); 
		professorAdd.add(crn); 
		professorAdd.add(crnField);
		professorAdd.add(returnToMenu); 
		professorAdd.add(addCourse);
		
		return professorAdd;
	}
//----PROFESSOR DROP COURSE----
JPanel professorDrop() {
	JPanel professorDrop = new JPanel(new GridLayout(4,2));
	JLabel profID = new JLabel("Professor ID Number");
	JLabel crn = new JLabel("Course Reference Number");
	JTextField IDField = new JTextField();
	JTextField crnField = new JTextField();
	JButton dropCourse = new JButton("Drop Course");
	JButton returnToMenu = new JButton("Return To Menu");
	
	// Pre-fill professor ID if logged in as professor
	if (userRole.equals("PROFESSOR")) {
		IDField.setText(String.valueOf(userId));
		IDField.setEditable(false);
	}
	
	dropCourse.addActionListener(e->{
		try {
			Integer profID_val = CourseManagement.parseInteger(IDField.getText());
			Integer crnNum = CourseManagement.parseInteger(crnField.getText());
			if (profID_val == null) {
				JOptionPane.showMessageDialog(this, "Please enter a valid professor ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (crnNum == null) {
				JOptionPane.showMessageDialog(this, "Please enter a valid CRN", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Professor prof = rcms.findProfessorById(profID_val);
			if (prof == null) {
				JOptionPane.showMessageDialog(this, "Professor not found with ID: " + profID_val, "Not Found", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Course course = rcms.findCourseByCRN(crnNum);
			if (course == null) {
				JOptionPane.showMessageDialog(this, "Course not found with CRN: " + crnNum, "Not Found", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (prof.dropCourse(course)) {
				JOptionPane.showMessageDialog(this, "Course dropped from professor successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Professor is not teaching this course", "Not Assigned", JOptionPane.INFORMATION_MESSAGE);
			}
			if (!userRole.equals("PROFESSOR")) {
				IDField.setText("");
			}
			crnField.setText("");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error dropping course from professor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	});
	returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
	
	professorDrop.add(profID); professorDrop.add(IDField); professorDrop.add(crn); professorDrop.add(crnField);
	professorDrop.add(returnToMenu); professorDrop.add(dropCourse);
	return professorDrop;
}

//----SEE ALL STUDENTS----
JPanel seeStudents() {
	JPanel seeAllStudents = new JPanel(new BorderLayout());
	JLabel title = new JLabel("Full Student List",SwingConstants.CENTER);
	JTextArea StudentDisplay = new JTextArea();
	StudentDisplay.setEditable(false);
	JScrollPane scrollPane = new JScrollPane(StudentDisplay);
	JButton refreshButton = new JButton("Refresh List");
	JButton returnToMenu = new JButton("Return To Menu");
	JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	buttonPanel.add(returnToMenu); buttonPanel.add(refreshButton);
	
	refreshButton.addActionListener(e -> {
		String Students = rcms.displayAllStudentsForGUI();
		StudentDisplay.setText(Students);
	});
	String Students = rcms.displayAllStudentsForGUI();
	StudentDisplay.setText(Students);
	returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
	
	seeAllStudents.add(title,BorderLayout.NORTH); seeAllStudents.add(scrollPane, BorderLayout.CENTER);
	seeAllStudents.add(buttonPanel,BorderLayout.SOUTH);
	return seeAllStudents;
}

//----SEE ALL PROFESSORS----
JPanel seeProfessors() {
	JPanel seeAllProfessors = new JPanel(new BorderLayout());
	JLabel title = new JLabel("Full Professor List",SwingConstants.CENTER);
	JTextArea ProfessorDisplay = new JTextArea();
	ProfessorDisplay.setEditable(false);
	JScrollPane scrollPane = new JScrollPane(ProfessorDisplay);
	JButton refreshButton = new JButton("Refresh List");
	JButton returnToMenu = new JButton("Return To Menu");
	JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	buttonPanel.add(returnToMenu); buttonPanel.add(refreshButton);
	
	refreshButton.addActionListener(e -> {
		String Professors = rcms.displayAllProfessorsForGUI();
		ProfessorDisplay.setText(Professors);
	});
	String Professors = rcms.displayAllProfessorsForGUI();
	ProfessorDisplay.setText(Professors);
	returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
	
	seeAllProfessors.add(title,BorderLayout.NORTH); seeAllProfessors.add(scrollPane, BorderLayout.CENTER);
	seeAllProfessors.add(buttonPanel,BorderLayout.SOUTH);
	return seeAllProfessors;
}

//----SEE ALL COURSES----
JPanel seeCourses() {
	JPanel seeAllCourses = new JPanel(new BorderLayout());
	JLabel title = new JLabel("Full Course List",SwingConstants.CENTER);
	JTextArea CourseDisplay = new JTextArea();
	CourseDisplay.setEditable(false);
	JScrollPane scrollPane = new JScrollPane(CourseDisplay);
	JButton refreshButton = new JButton("Refresh List");
	JButton returnToMenu = new JButton("Return To Menu");
	JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	buttonPanel.add(returnToMenu); buttonPanel.add(refreshButton);
	
	refreshButton.addActionListener(e -> {
		String Courses = rcms.displayAllCoursesForGUI();
		CourseDisplay.setText(Courses);
	});
	String Courses = rcms.displayAllCoursesForGUI();
	CourseDisplay.setText(Courses);
	returnToMenu.addActionListener(e ->{setTitle("Course Management"); layout.show(cards, "MENU");});
	
	seeAllCourses.add(title,BorderLayout.NORTH); seeAllCourses.add(scrollPane, BorderLayout.CENTER);
	seeAllCourses.add(buttonPanel,BorderLayout.SOUTH);
	return seeAllCourses;
}

//----MAIN METHOD----
public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
		CourseRegistrationGUI gui = new CourseRegistrationGUI();
		gui.GUIrun();
	});
}
}
		
		