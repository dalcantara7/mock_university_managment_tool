package org.university.people;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.university.hardware.Department;
import org.university.software.Course;

public class Student extends Person implements Serializable {
	private Department dept;
	private int unitsCompleted;
	private int unitsNeeded;
	
	public Student() {
		this.name = "unknown";
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Department getDepartment() {
		return this.dept;
	}

	public void setDepartment(Department studentDeptName) {
		this.dept = studentDeptName;
		this.dept.addStudent(this);
	}
	
	public ArrayList<Course> getCourseList() {
		return this.courses;
	}

	public void addCourse(Course courseName) {
		if (this.detectConflict(courseName) == true) {
		}
		else {
			this.courses.add(courseName);
			this.getSchedule().addAll(courseName.getSchedule());
			courseName.addStudent(this);
		}
	}

	public void dropCourse(Course courseName) {
		if (this.getCourseList().contains(courseName)) {
			this.getCourseList().remove(courseName);
			courseName.getStudentRoster().remove(this);
			JFrame frame = new JFrame("Successfully Dropped Student from Course");
			JLabel message = new JLabel(this.getName() + " was removed from " + courseName.getDepartment().getDepartmentName() + courseName.getCourseNumber() + ".");
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			JPanel buttonPanel = new JPanel();
			JPanel structure = new JPanel();
			structure.setLayout(new BoxLayout(structure, BoxLayout.PAGE_AXIS));
			structure.add(message);
			buttonPanel.add(okButton);
			structure.add(buttonPanel);
			frame.add(structure);
			frame.setSize(600, 100);
			frame.setLocation(400, 300);
			frame.pack();
			frame.setVisible(true);
			return;
		}
		else {
			//System.out.println("The course " + courseName.getDepartment().getDepartmentName() + courseName.getCourseNumber() + " could not be dropped because " + this.getName() + " is not enrolled in " +  courseName.getDepartment().getDepartmentName() + courseName.getCourseNumber() + ".");
			JFrame frame = new JFrame("Error Dropping Student from Course");
			JLabel message = new JLabel(courseName.getDepartment().getDepartmentName() + courseName.getCourseNumber() + " cannot be dropped from " + this.getName() + "'s Schedule. " + this.getName() + " Is not enrolled in " + courseName.getDepartment().getDepartmentName() + courseName.getCourseNumber() + ".");
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			JPanel buttonPanel = new JPanel();
			JPanel structure = new JPanel();
			structure.setLayout(new BoxLayout(structure, BoxLayout.PAGE_AXIS));
			structure.add(message);
			buttonPanel.add(okButton);
			structure.add(buttonPanel);
			frame.add(structure);
			frame.setSize(600, 100);
			frame.setLocation(400, 300);
			frame.pack();
			frame.setVisible(true);
		}
	}
	
	public int getUnitsCompleted() {
		return this.unitsCompleted;
	}
	
	public void setCompletedUnits(int unitsCompleted) {
		this.unitsCompleted = unitsCompleted;
	}
	
	public int getUnitsNeeded() {
		return this.unitsNeeded;
	}
	
	public void setRequiredCredits(int unitsNeeded) {
		this.unitsNeeded = unitsNeeded;
	}
	
	public String printSchedule() {
		
		ArrayList<Integer> assocSchedule = new ArrayList<Integer>();
		ArrayList<Course> assocCourse = new ArrayList<Course>();
		
		for (int k = 0; k <= 5; ++k) {
			for (int l = 0; l <= 6; ++l ) {
				for (int i = 0; i < this.getCourse().size(); ++i) {
					for (int j = 0; j < this.getCourse().get(i).getSchedule().size(); ++j) {
						if (this.getCourse().get(i).getSchedule().get(j) / 100 == k) {
							if (this.getCourse().get(i).getSchedule().get(j) % 100 == l) {
								assocSchedule.add(this.getCourse().get(i).getSchedule().get(j));
								assocCourse.add(this.getCourse().get(i));
							}
						}
					}
				}
			}
		}
		String schedule = returnSchedule(assocCourse, assocSchedule);
		return schedule;
	}
	
	public String returnSchedule(ArrayList<Course> c1, ArrayList<Integer> sch1) {
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < sch1.size(); ++i) {
		switch(sch1.get(i) / 100) {
		case 1 :
			build.append("Mon ");
			break;
		case 2 :
			build.append("Tue ");
			break;
		case 3 :
			build.append("Wed ");
			break;
		case 4 :
			build.append("Thu ");
			break;
		case 5 :
			build.append("Fri ");
			break;
		default :
			build.append("invalid day ");
			break;
			
		}
		
		switch(sch1.get(i) % 100) {
		case 1 :
			build.append("8:00am to 9:15am ");
			break;
		case 2 :
			build.append("9:30am to 10:45am ");
			break;
		case 3 :
			build.append("11:00am to 12:15pm ");
			break;
		case 4 :
			build.append("12:30pm to 1:45pm ");
			break;
		case 5 :
			build.append("2:00pm to 3:15pm ");
			break;
		case 6 :
			build.append("3:30pm to 4:45pm ");
			break;
		default :
			build.append("invalid time ");
			break;
		}
		build.append(c1.get(i).getDepartment().getDepartmentName() + c1.get(i).getCourseNumber() + " " + c1.get(i).getName() + "\n");
		}
		String result = build.toString();
	return result;
}

	public int requiredToGraduate() {
		int remainingCreds = this.getUnitsNeeded() - this.getUnitsCompleted();
		
		return remainingCreds;
	}

}
