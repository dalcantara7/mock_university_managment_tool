package org.university.hardware;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

import org.university.software.*;

public class Classroom implements Serializable {
	private String roomNumber;
	private ArrayList<Course> course = new ArrayList<Course>();
	private ArrayList<Integer> schedule = new ArrayList<Integer>();
	
	public Classroom() {
		this.roomNumber = "unknown";
	}

	public void setRoomNumber(String roomNum) {
		this.roomNumber = roomNum;
	}

	public String getRoomNumber() {
		return this.roomNumber;
	}
	
	public ArrayList<Course> getCourse() {
		return this.course;
	}
	
	public ArrayList<Integer> getSchedule() {
		return this.schedule;
	}
	
	public void addCourse(Course c6) {
		
		if(detectConflict(c6) == true) {
		}
		
		else {
			//c6.setRoomAssigned(this);
			this.course.add(c6);
			this.schedule.addAll(c6.getSchedule());
		}
	}
	
	public boolean detectConflict(Course aCourse) {
		boolean conflictFlag = false;
			
		//if(this.getClass().getName() != "org.university.people.Staff") {
			for (int i = 0; i < this.getCourse().size(); ++i) {
				for (int j = 0; j < this.getCourse().get(i).getSchedule().size(); ++j) {
					for (int k = 0; k < aCourse.getSchedule().size(); ++k) {
						if (this.getCourse().get(i).getSchedule().get(j)/100 == aCourse.getSchedule().get(k)/100 ) {
							if (this.getCourse().get(i).getSchedule().get(j)%100 == aCourse.getSchedule().get(k)%100) {
								conflictFlag = true;
								System.out.print(aCourse.getDepartment().getDepartmentName() + aCourse.getCourseNumber() + " conflicts with " + this.getCourse().get(k).getDepartment().getDepartmentName() + this.getCourse().get(k).getCourseNumber() + ". ");
								System.out.print("Conflicting time slot " );
								returnScheduleConflict(this.getSchedule().get(i));
								System.out.println(aCourse.getDepartment().getDepartmentName() + aCourse.getCourseNumber() + " course cannot be added to " + this.getRoomNumber() + "'s Schedule.");
								return conflictFlag;
							}
						}
					}
				}
			//}
		}
		return conflictFlag;
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
	
	public String returnScheduleConflict(Integer c1) {
		StringBuilder build = new StringBuilder();
		switch(c1 / 100) {
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
			build.append("Thurs ");
			break;
		case 5 :
			build.append("Fri ");
			break;
		default :
			build.append("invalid day ");
			break;
			
		}
		
		switch(c1 % 100) {
		case 1 :
			build.append("8:00am to 9:15am");
			break;
		case 2 :
			build.append("9:30am to 10:45am");
			break;
		case 3 :
			build.append("11:00am to 12:15pm");
			break;
		case 4 :
			build.append("12:30pm to 1:45pm");
			break;
		case 5 :
			build.append("2:00pm to 3:15pm");
			break;
		case 6 :
			build.append("3:30pm to 4:45pm");
			break;
		default :
			build.append("invalid time ");
			break;
		}
		
		build.append(". ");
		String result = build.toString();
	return result;
}

}
