package org.university.people;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import java.util.ArrayList;

import javax.swing.*;

import org.university.software.Course;

public abstract class Person implements Serializable  {
	protected String name;
	protected ArrayList<Integer> schedule = new ArrayList<Integer>();
	protected ArrayList<Course> courses = new ArrayList<Course>();
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Integer> getSchedule() {
		return this.schedule;
	}
	
	public ArrayList<Course> getCourse() {
		return this.courses;
	}
	
	public boolean detectConflict(Course aCourse) {
		boolean conflictFlag = false;
			
		if(this.getClass().getName() != "org.university.people.Staff") {
			for (int i = 0; i < this.getCourse().size(); ++i) {
				for (int j = 0; j < this.getCourse().get(i).getSchedule().size(); ++j) {
					for (int k = 0; k < aCourse.getSchedule().size(); ++k) {
						if (this.getCourse().get(i).getSchedule().get(j)/100 == aCourse.getSchedule().get(k)/100 ) {
							if (this.getCourse().get(i).getSchedule().get(j)%100 == aCourse.getSchedule().get(k)%100) {
								conflictFlag = true;
								JFrame frame = new JFrame("Error Adding Student to Course");
								JLabel message = new JLabel(aCourse.getDepartment().getDepartmentName() + aCourse.getCourseNumber() + " course cannot be added to " + this.getName() + "'s Schedule. " + aCourse.getDepartment().getDepartmentName() + aCourse.getCourseNumber() + " conflicts with " + this.getCourse().get(i).getDepartment().getDepartmentName() + this.getCourse().get(i).getCourseNumber() + ". ");
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
								return true;
							}
							else {
							}
						}
					}
				}
			}
		}
		return conflictFlag;
	}
	
	public abstract void addCourse(Course aCourse);

	public void returnScheduleP(Integer c1) {
			switch(c1 / 100) {
			case 1 :
				System.out.print("Mon ");
				break;
			case 2 :
				System.out.print("Tue ");
				break;
			case 3 :
				System.out.print("Wed ");
				break;
			case 4 :
				System.out.print("Thu ");
				break;
			case 5 :
				System.out.print("Fri ");
				break;
			default :
				System.out.print("invalid day ");
				break;
				
			}
			
			switch(c1 % 100) {
			case 1 :
				System.out.print("8:00am to 9:15am");
				break;
			case 2 :
				System.out.print("9:30am to 10:45am");
				break;
			case 3 :
				System.out.print("11:00am to 12:15pm");
				break;
			case 4 :
				System.out.print("12:30pm to 1:45pm");
				break;
			case 5 :
				System.out.print("2:00pm to 3:15pm");
				break;
			case 6 :
				System.out.print("3:30pm to 4:45pm");
				break;
			default :
				System.out.print("invalid time ");
				break;
			}
			
			System.out.println(".");
			
		return;
	}
	
}


