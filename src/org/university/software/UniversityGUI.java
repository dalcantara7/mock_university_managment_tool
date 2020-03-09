package org.university.software;

import org.university.hardware.Department;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UniversityGUI extends JFrame {
	private JMenuBar menuBar;
	private JMenu adminMenu;
	private JMenu fileMenu;
	private JMenu studentMenu;
	private University univ1;
	
	//submenus
	
	//File
	
	private JMenuItem fileSave;
	private JMenuItem fileLoad;
	private JMenuItem exit;
	
	//Student
	
	private JMenuItem addCourse;
	private JMenuItem dropCourse;
	private JMenuItem printSchedule;
	
	//Admin
	
	private JMenuItem printInfo;
	
	//other methods
	
	public UniversityGUI(String windowTitle, University univ) {
		super(windowTitle);
		
		univ1 = univ;
		
		setSize(400,200);
		setLocation(200,50);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("<HTML><center>Welcome to the University." +
				"<BR>Choose an action from the above menus.</center></HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setVisible(true);
		
	}
	
	public void buildGUI() {
		menuBar = new JMenuBar();
		
		//File fields
		
		fileMenu = new JMenu("File");
		
		fileSave = new JMenuItem("Save");
		fileLoad = new JMenuItem("Load");
		
		exit = new JMenuItem("Exit");
		
		fileSave.addActionListener(new MenuListener());
		fileLoad.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());
		
		fileMenu.add(fileSave);
		fileMenu.add(fileLoad);
		fileMenu.add(exit);
		
		menuBar.add(fileMenu);
		
		//Students fields
		
		studentMenu = new JMenu("Student");
		
		addCourse = new JMenuItem("Add Course");
		dropCourse = new JMenuItem("Drop Course");
		printSchedule = new JMenuItem("Print Schedule");
		
		addCourse.addActionListener(new MenuListener());
		dropCourse.addActionListener(new MenuListener());
		printSchedule.addActionListener(new MenuListener());
		
		studentMenu.add(addCourse);
		studentMenu.add(dropCourse);
		studentMenu.add(printSchedule);
		
		menuBar.add(studentMenu);
		
		//Admin fields
		
		adminMenu = new JMenu("Administrator");
		
		printInfo = new JMenuItem("Print All Info");
		
		printInfo.addActionListener(new MenuListener());
		
		adminMenu.add(printInfo);
		
		menuBar.add(adminMenu);
		
		setJMenuBar(menuBar);
		
	}
	
	private class MenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem)(e.getSource());
			
			if(source.equals(fileSave)) 
				University.saveData(univ1);
			else if (source.equals(fileLoad))
				univ1 = University.loadData();
			else if (source.equals(exit)) 
				System.exit(0);
			else if (source.equals(printInfo))
				handlePrintInfo(univ1);
			else if (source.equals(addCourse))
				LaunchAddCourse(univ1);
			else if (source.equals(dropCourse))
				LaunchDropCourse(univ1);
			else if (source.equals(printSchedule))
				LaunchPrintSchedule();
		}
		
		private void handlePrintInfo(University univ1) {
			if(univ1 != null) {
				univ1.printAllScroll();
			}
			else {
				JOptionPane.showMessageDialog(null, "No University", "Error", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	public void LaunchAddCourse(University univ1) {
		//create area to for text to be displayed
		JFrame frame = new JFrame("Add Course");
		JPanel coursePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel framePanel = new JPanel();
		
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);
		
		framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.PAGE_AXIS));
		coursePanel.setLayout(new GridLayout(3,1));
		buttonPanel.setLayout(new FlowLayout());
		
		JLabel studentNameLabel = new JLabel("Student Name:");
		JLabel deptFieldLabel = new JLabel("Department:");
		JLabel courseLabel = new JLabel("Course #:");
		
		JTextField studentNameField = new JTextField();
		JTextField deptField = new JTextField();
		JTextField courseField = new JTextField();
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentNameField.getText().isEmpty() || deptField.getText().isEmpty() || courseField.getText().isEmpty()) {
					LaunchError("Please fill in all fields");
				}
				else {
					handleAddCourse(studentNameField.getText(), deptField.getText(), Integer.parseInt(courseField.getText()));
					frame.dispose();
				}
			}
		});

		coursePanel.add(studentNameLabel);
		coursePanel.add(studentNameField);
		coursePanel.add(deptFieldLabel);
		coursePanel.add(deptField);
		coursePanel.add(courseLabel);
		coursePanel.add(courseField);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		coursePanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 5, 40));
		
		frame.setSize(600, 600);
		frame.setLocation(500, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		framePanel.add(coursePanel);
		framePanel.add(buttonPanel);
		frame.add(framePanel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void handleAddCourse(String studentName, String deptName, Integer courseNum) {
		Course toAdd;
		int validEntry = validEntry(studentName, deptName, courseNum);
		
		exitLoop:
		for (int k = 0; k < univ1.getDepartmentList().size(); ++k) {
			if (univ1.getDepartmentList().get(k).getDepartmentName().equals(deptName)) {
				for (int l = 0; l < univ1.getDepartmentList().get(k).getCourseList().size(); ++l) {
					if (univ1.getDepartmentList().get(k).getCourseList().get(l).getCourseNumber() == courseNum) {
						toAdd = univ1.getDepartmentList().get(k).getCourseList().get(l);
						for (int i = 0; i < univ1.getDepartmentList().size(); ++i) {
							for (int j = 0; j < univ1.getDepartmentList().get(i).getStudentList().size(); ++j) {
								if (validEntry == 0) {
									univ1.getDepartmentList().get(i).getStudentList().get(j).addCourse(toAdd);
									return;
								}
								else if (validEntry == 1) { //student doesn't exist
									LaunchError("Student ''" + studentName + "'' does not exist.");	
									//System.out.println("Error 7");
									break exitLoop;
								}
							}
						}
					}
					else if (validEntry == 3) { //courseNum doesn't exist
						LaunchError("CourseNum ''" + courseNum.toString() + "'' does not exist.");	
						//System.out.println("Error 3");
						break exitLoop;
					}
				}
			}
			else if (validEntry == 2) { //department doesn't exist
				LaunchError("Department ''" + deptName + "'' does not exist.");	
				//System.out.println("Error 2");
				break exitLoop;
			}
		}
		if (validEntry == 7) { //unknown error
			LaunchError("Unknown Error");
		}
	}
	
	public int validEntry(String studentName, String deptName, Integer courseNum) {
		int flag = 7;
		
		for(int i = 0; i < univ1.getDepartmentList().size(); ++i) {
			if(univ1.getDepartmentList().get(i).getDepartmentName().equals(deptName)) {
				flag = 7;
				break;
			}
			else 
				flag = 2; //department doesn't exist
		}
		
		if(flag != 7)
			return flag;
		
		nextcheck:
		for(int i = 0; i < univ1.getDepartmentList().size(); ++i) {
			for(int j = 0; j < univ1.getDepartmentList().get(i).getCourseList().size(); ++j) {
				if(univ1.getDepartmentList().get(i).getCourseList().get(j).getCourseNumber() == courseNum) {
					flag = 7;
					break nextcheck;
				}
				else
					flag = 3; //courseNum doesn't exist
			}
		}
		
		if(flag != 7)
			return flag;
		
		for(int i = 0; i < univ1.getDepartmentList().size(); ++i) {
			for(int j = 0; j < univ1.getDepartmentList().get(i).getStudentList().size(); ++j) {
				if (univ1.getDepartmentList().get(i).getStudentList().get(j).getName().equals(studentName))
					return 0;
				else
					flag = 1; //student doesn't exist
			}
		}
		return flag; //if flag doesn't change, returns 7 for unknown error
	}
	
	public void LaunchError(String message) { //FIXME ADD 
		JFrame frame = new JFrame("Error Processing Request");
		JLabel text = new JLabel(message);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		JPanel buttonPanel = new JPanel();
		JPanel structure = new JPanel();
		structure.setLayout(new BoxLayout(structure, BoxLayout.PAGE_AXIS));
		structure.add(text);
		buttonPanel.add(okButton);
		structure.add(buttonPanel);
		frame.add(structure);
		frame.setSize(600, 100);
		frame.setLocation(400, 300);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void LaunchDropCourse(University univ1) {
		//create area to for text to be displayed
		JFrame frame = new JFrame("Drop Course");
		JPanel coursePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel framePanel = new JPanel();
		
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);
		
		framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.PAGE_AXIS));
		coursePanel.setLayout(new GridLayout(3,1));
		buttonPanel.setLayout(new FlowLayout());
		
		JLabel studentNameLabel = new JLabel("Student Name:");
		JLabel deptFieldLabel = new JLabel("Department:");
		JLabel courseLabel = new JLabel("Course #:");
		
		JTextField studentNameField = new JTextField("");
		JTextField deptField = new JTextField("");
		JTextField courseField = new JTextField("");
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentNameField.getText().isEmpty() || deptField.getText().isEmpty() || courseField.getText().isEmpty()) {
					LaunchError("Please fill in all fields");
				}
				else {
					handleDropCourse(studentNameField.getText(), deptField.getText(), Integer.parseInt(courseField.getText()));
					frame.dispose();
				}
			}
		});

		coursePanel.add(studentNameLabel);
		coursePanel.add(studentNameField);
		coursePanel.add(deptFieldLabel);
		coursePanel.add(deptField);
		coursePanel.add(courseLabel);
		coursePanel.add(courseField);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		coursePanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 5, 40));
		
		frame.setSize(600, 600);
		frame.setLocation(500, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		framePanel.add(coursePanel);
		framePanel.add(buttonPanel);
		frame.add(framePanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void handleDropCourse(String studentName, String deptName, Integer courseNum) {
		Course toDrop;
		int validEntry = validEntry(studentName, deptName, courseNum);
		
		exitLoop:
		for (int k = 0; k < univ1.getDepartmentList().size(); ++k) {
			if (univ1.getDepartmentList().get(k).getDepartmentName().equals(deptName)) {
				for (int l = 0; l < univ1.getDepartmentList().get(k).getCourseList().size(); ++l) {
					if (univ1.getDepartmentList().get(k).getCourseList().get(l).getCourseNumber() == courseNum) {
						toDrop = univ1.getDepartmentList().get(k).getCourseList().get(l);
						for (int i = 0; i < univ1.getDepartmentList().size(); ++i) {
							for (int j = 0; j < univ1.getDepartmentList().get(i).getStudentList().size(); ++j) {
								if (validEntry == 0) {
										univ1.getDepartmentList().get(i).getStudentList().get(j).dropCourse(toDrop);
										return;
								}
								else if (validEntry == 1) { //student doesn't exist
									LaunchError("Student ''" + studentName + "'' does not exist.");	
									//System.out.println("Error 7");
									break exitLoop;
								}
							}
						}
					}
					else if (validEntry == 3) { //courseNum doesn't exist
						LaunchError("Course Number ''" + courseNum.toString() + "'' does not exist.");	
						//System.out.println("Error 3");
						break exitLoop;
					}
				}
			}
			else if (validEntry == 2) { //department doesn't exist
				LaunchError("Department ''" + deptName + "'' does not exist.");	
				//System.out.println("Error 2");
				break exitLoop;
			}
		}
		if (validEntry == 7) { //unknown error
			LaunchError("Unknown Error");
		}
	}
	
	
	public void LaunchPrintSchedule() {
		//create area to for text to be displayed
		JFrame frame = new JFrame("Print Schedule");
		JPanel coursePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel framePanel = new JPanel();
		
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);
		
		framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.PAGE_AXIS));
		coursePanel.setLayout(new GridLayout(3,1));
		buttonPanel.setLayout(new FlowLayout());
		
		JLabel studentNameLabel = new JLabel("Student Name:");
		
		JTextField studentNameField = new JTextField("");
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentNameField.getText().isEmpty()) {
					LaunchError("Please fill in all fields");
				}
				else {
					handlePrintSchedule((studentNameField.getText()));
					frame.dispose();
				}
			}
		});

		coursePanel.add(studentNameLabel);
		coursePanel.add(studentNameField);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		coursePanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 5, 40));
		
		frame.setSize(600, 600);
		frame.setLocation(500, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		framePanel.add(coursePanel);
		framePanel.add(buttonPanel);
		frame.add(framePanel);
		frame.pack();
		frame.setVisible(true);
	
	}
	
	public void handlePrintSchedule(String studentName) {
		int validEntry = validStudent(studentName);
		
		if (validEntry == 0) {
			for(int i = 0;  i < univ1.getDepartmentList().size(); ++i) {
				for (int j = 0; j < univ1.getDepartmentList().get(i).getStudentList().size(); ++j) {
					if(univ1.getDepartmentList().get(i).getStudentList().get(j).getName().equals(studentName)) {
						JFrame frame = new JFrame("Student " + univ1.getDepartmentList().get(i).getStudentList().get(j).getName() + "'s Schedule");
						JTextArea schedule = new JTextArea(univ1.getDepartmentList().get(i).getStudentList().get(j).printSchedule());
						JButton okButton = new JButton("OK");
						okButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.dispose();
							}
						});
						JPanel buttonPanel = new JPanel();
						JPanel structure = new JPanel();
						structure.setLayout(new BoxLayout(structure, BoxLayout.PAGE_AXIS));
						structure.add(schedule);
						buttonPanel.add(okButton);
						structure.add(buttonPanel);
						frame.add(structure);
						frame.setSize(600, 100);
						frame.setLocation(400, 300);
						frame.pack();
						frame.setVisible(true);
					}
						
				}
			}
		}
		else
			LaunchError("Student ''" + studentName + "'' does not exist.");
	}
	
	public int validStudent(String studentName) {
		int flag = 7;
	
		for (int i = 0; i < univ1.getDepartmentList().size(); ++i) {
			for (int j = 0; j < univ1.getDepartmentList().get(i).getStudentList().size(); ++j) {
				if (univ1.getDepartmentList().get(i).getStudentList().get(j).getName().equals(studentName))
					return flag = 0;
				else
					flag = 1; //student doesn't exist
			}
		}
		
		return flag;
	}

}
