package org.university.hardware;

import java.util.ArrayList;
import org.university.people.*;
import org.university.software.Course;
import java.io.Serializable;

public class Department implements Serializable {
	private String name; 
	private ArrayList<Student> studentsRoster = new ArrayList<Student>();
	private ArrayList<Course> courseList = new ArrayList<Course>();
	private ArrayList<Professor> profList = new ArrayList<Professor>();
	private ArrayList<Staff> staffList = new ArrayList<Staff>();
	
	public Department() {
		this.name = "unknown";
	}
	
	public String getDepartmentName() {
		return this.name;
	}

	public void setDepartmentName(String deptName) {
		this.name = deptName;
	}

	public void addCourse(Course courseName) {
		this.courseList.add(courseName);
		
		if(courseName.getDepartment()!= this) {
			courseName.setDepartment(this);
		}	
	}
	
	public ArrayList<Student> getStudentList() {
		return this.studentsRoster;
	}

	
	public void addStudent(Student studentName) {
		if (this.getStudentList().contains(studentName)) {}
		else
			this.studentsRoster.add(studentName);
		
		if (studentName.getDepartment() != this) 
			studentName.setDepartment(this);
	}
	
	public ArrayList<Professor> getProfessorList() {
		return this.profList;
	}
	
	public void addProfessor(Professor p1) {
		this.profList.add(p1);
	}
	

	public void addStaff(Staff sf1) {
		this.staffList.add(sf1);
	}

	public ArrayList<Staff> getStaffList() {
		return this.staffList;
	}
	
	public ArrayList<Course> getCourseList() {
		return this.courseList;
	}

	public void printProfessorList() {
		for (int i = 0; i < this.getProfessorList().size(); i++) 
			System.out.println(this.getProfessorList().get(i).getName());
	}

	public void printStaffList() {
		for (int i = 0; i < this.getStaffList().size(); i++) 
			System.out.println(this.getStaffList().get(i).getName());
		
	}

	public void printCourseList() {
		for (int i = 0; i < this.getCourseList().size(); i++) 
			System.out.println(this.getCourseList().get(i).getDepartment().getDepartmentName() + this.getCourseList().get(i).getCourseNumber());
		
	}

	public void printStudentList() {
		for (int i = 0; i < this.getStudentList().size(); i++) 
			System.out.println(this.getStudentList().get(i).getName());
		
	}

}
