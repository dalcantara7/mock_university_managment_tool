package org.university.people;

import org.university.software.Course;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

import org.university.hardware.*;

public class Staff extends Employee implements Serializable  {
	private int hoursWorked;
	private Department dept;
	private Course classTaking;
	
	public Staff() {
		this.hoursWorked = 0;
		this.classTaking = new Course();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Course getClassTaking() {
		return this.classTaking;
	}
	
	public void addCourse(Course c1) {
		if (this.classTaking.getName() != "unknown") {
			System.out.print(this.getClassTaking().getDepartment().getDepartmentName() + this.classTaking.getCourseNumber() + " is removed from " + this.getName() + "'s schedule(Staff can only take one class at a time).");
			this.getClassTaking().getStudentRoster().remove(this);
			c1.addStudent(this);
			this.classTaking = c1;
			System.out.println(" " + this.classTaking.getDepartment().getDepartmentName() + this.classTaking.getCourseNumber() + " has been added to " + this.getName() + "'s Schedule.");
		}
		else {
			this.classTaking = c1;
			this.schedule.addAll(c1.getSchedule());
			c1.addStudent(this);
		}
		return;
	}
	
	public Department getDept() {
		return this.dept;
	}
	
	public void setDept(Department dept) {
		this.dept = dept;
		this.dept.addStaff(this);
	}
	
	public double earns() {
		double earns = this.hoursWorked*this.getPayRate();
		return earns;
	}

	public int getMonthlyHours() {
		return this.hoursWorked;
	}

	public void setMonthlyHours(int i) {
		this.hoursWorked = i;
	}

	public String printSchedule() {
		
		for (int i = 0; i < this.courses.size(); ++i) {
			Collections.sort(this.courses.get(i).getSchedule());
		}
		
		Collections.sort(this.schedule);
		
			Collections.sort(this.courses, new Comparator<Course>(){
					public int compare(Course c1, Course c2) {
						return Integer.valueOf(c1.getSchedule().get(0)).compareTo(c2.getSchedule().get(0));
					}
				});
		
		
		String schedule = returnSchedule(this);
		return schedule;
		
	}
	
	public String returnSchedule(Staff sf1) {
		StringBuilder build = new StringBuilder();
		for (int i = 0; i< sf1.getClassTaking().getSchedule().size(); i++) {
			switch(sf1.getClassTaking().getSchedule().get(i) / 100) {
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
			
			switch(sf1.getClassTaking().getSchedule().get(i) % 100) {
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
			build.append(sf1.getClassTaking().getDepartment().getDepartmentName() + sf1.getClassTaking().getCourseNumber() + " " + sf1.getClassTaking().getName() + "\n");
		}
		String result = build.toString();
		return result;
	}

}
