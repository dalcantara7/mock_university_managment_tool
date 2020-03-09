package org.university.software;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.university.people.*;
import org.university.hardware.Classroom;
import org.university.hardware.Department;

public class Course implements Serializable  {
	private String courseName;
	private int courseNum;
	private Classroom classroom;
	private Department courseDept;
	private ArrayList<Integer> schedule = new ArrayList<Integer>();
	private ArrayList<Person> studentRoster = new ArrayList<Person>();
	private Professor professor;
	
	public Course() {
		this.courseName = "unknown";
		this.courseNum = 000;
	}
	
	public Course(Department d1, int cn1) {
		this.setDepartment(d1);
		this.setCourseNumber(cn1);
	}
	
	public String getName() {
		return this.courseName;
	}

	public void setName(String courseName) {
		this.courseName = courseName;
	}
	
	public ArrayList<Integer> getSchedule() {
		return this.schedule;
	}

	public void setSchedule(int courseDT) {
		this.schedule.add(courseDT);
	}
	
	public int getCourseNumber() {
		return this.courseNum;
	}

	public void setCourseNumber(int courseNum) {
		this.courseNum = courseNum;
	}

	public ArrayList<Person> getStudentRoster() {
		return this.studentRoster;
	}
	
	public void addStudent(Person p1) {
		if (this.getStudentRoster().contains(p1)) {}
		else
			this.studentRoster.add(p1);
	}

	public Department getDepartment() {
		return this.courseDept;
	}
	
	public void setDepartment(Department deptName) {
		this.courseDept = deptName;
	}

	public Classroom getRoomAssigned() {
		return this.classroom;
	}
	
	public void setRoomAssigned(Classroom cr2) {
			this.classroom = cr2;
			cr2.addCourse(this);
	}
	
	public Professor getProfessor() {
		return this.professor;
	}
	
	public void setProfessor(Professor p1) {
		this.professor = p1;
	}

	public String printSchedule() {
		
		Collections.sort(this.schedule, new Comparator<Integer>() {
			public int compare(Integer s1, Integer s2) {
				return Integer.valueOf(s1.compareTo(s2));
			}
		});
		
		String schedule = returnSchedule(this);
		return schedule;
	}
	
	public String returnSchedule(Course c1) {
		StringBuilder build = new StringBuilder();
		for (int i = 0; i< c1.getSchedule().size(); i++) {
			switch(c1.getSchedule().get(i) / 100) {
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
			
			switch(c1.getSchedule().get(i) % 100) {
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
			
			build.append(c1.getRoomAssigned().getRoomNumber() + "\n");
		}
		String result = build.toString();
		return result;
	}
}
