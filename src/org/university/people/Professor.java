package org.university.people;
import java.util.Collections;
import java.util.Comparator;

import org.university.hardware.Department;
import org.university.software.Course;

import java.io.Serializable;
import java.util.ArrayList;

public class Professor extends Employee implements Serializable {
	private Department dept;

	public void addCourse(Course c6) {
		if(c6.getProfessor() != null) {
			System.out.println("The professor cannot be assigned to this course because professor " + c6.getProfessor().getName() + " is already assigned to the course " + c6.getName() + ".");
		}
		
		else if(detectConflict(c6) == true) {
		}
		
		else {
			c6.setProfessor(this);
			this.courses.add(c6);
			this.schedule.addAll(c6.getSchedule());
		}
	}
	
	public Department getDepartment() {
		return this.dept;
	}
	
	public void setDepartment(Department d1) {
		this.dept = d1;
		this.dept.addProfessor(this);
	}
	
	public ArrayList<Course> getCoursesTeaching() {
		return this.courses;
	}

	public double earns() {
		double earns = 200*this.getPayRate();
		return earns;
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
		String result;
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
			result = build.toString();
		return result;
	}
	
}
