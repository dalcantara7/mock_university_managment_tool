package org.university.software;

import org.university.hardware.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

public class University implements Serializable {
	protected ArrayList<Classroom> classroomList = new ArrayList<Classroom>();
	protected ArrayList<Department> departmentList = new ArrayList<Department>();
	
	
	public ArrayList<Department> getDepartmentList() {
		return this.departmentList;
	}
	
	public void setDepartmentList(ArrayList<Department> departmentList) {
		this.departmentList = departmentList;
	}
	
	public ArrayList<Classroom> getClassroomList() {
		return classroomList;
	}
	
	public void setClassroomList(ArrayList<Classroom> classroomList) {
		this.classroomList = classroomList;
	}
	
	public void printAll() {

		System.out.println("List of departments:");
		for (int i = 0; i < this.getDepartmentList().size(); ++i)
			System.out.println(this.getDepartmentList().get(i).getDepartmentName());
		
		System.out.print("\n");

		System.out.println("Classroom list:");
		for (int i = 0; i < this.getClassroomList().size(); ++i)
			System.out.println(this.getClassroomList().get(i).getRoomNumber());
		
		System.out.print("\n");
		
		for (int i = 0; i < this.getDepartmentList().size(); ++i) {
			System.out.println("The professor list for department " + this.getDepartmentList().get(i).getDepartmentName());
			
			for (int j = 0; j < this.getDepartmentList().get(i).getProfessorList().size(); ++j)
				System.out.println(this.getDepartmentList().get(i).getProfessorList().get(j).getName());
			
			System.out.print("\n");
		}
		
		System.out.print("\n");
		
		for (int i = 0; i < this.getDepartmentList().size(); ++i) {
			System.out.println("The course list for department " + this.getDepartmentList().get(i).getDepartmentName());
			for (int j = 0; j < this.getDepartmentList().get(i).getCourseList().size(); ++j)
				System.out.println(this.getDepartmentList().get(i).getDepartmentName() + this.getDepartmentList().get(i).getCourseList().get(j).getCourseNumber());
			
			System.out.print("\n");
		}
		
		for (int i = 0; i < this.getClassroomList().size(); ++i) {
			System.out.println("The schedule for classroom " + this.getClassroomList().get(i).getRoomNumber());
			System.out.println(this.getClassroomList().get(i).printSchedule());
			
			System.out.print("\n");
		}
		
		System.out.print("\n");
		
		for (int i = 0; i < this.getDepartmentList().size(); ++i) {
			System.out.println("Department " + this.getDepartmentList().get(i).getDepartmentName()+ "\n");
			
			System.out.println("Printing Professor schedules:\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getProfessorList().size(); ++j) {
				System.out.println("The schedule for Prof. " + this.getDepartmentList().get(i).getProfessorList().get(j).getName() + ":");
				System.out.println(this.getDepartmentList().get(i).getProfessorList().get(j).printSchedule());
				
				System.out.print("\n");
			}
			
			System.out.println("Printing Student Schedules\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getStudentList().size(); ++j) {
				System.out.println("The Schedule for Student " + this.getDepartmentList().get(i).getStudentList().get(j).getName() + ":");
				System.out.println(this.getDepartmentList().get(i).getStudentList().get(j).printSchedule());
				
				System.out.print("\n");
			}
			
			System.out.println("Printing Staff Schedules:\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getStaffList().size(); ++j) {
				System.out.println("The Schedule for Employee " + this.getDepartmentList().get(i).getStaffList().get(j).getName() + ":");
				System.out.println(this.getDepartmentList().get(i).getStaffList().get(j).printSchedule());
				System.out.println("\n");
				System.out.println("Staff : " + this.getDepartmentList().get(i).getStaffList().get(j).getName() + " earns $ " + this.getDepartmentList().get(i).getStaffList().get(j).earns() + "this month");
				System.out.println("");
			}
			
			System.out.println("The rosters for courses offered by " + this.getDepartmentList().get(i).getDepartmentName() + "\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getCourseList().size(); ++j) {
				System.out.println("The roster for course " + this.getDepartmentList().get(i).getDepartmentName() + this.getDepartmentList().get(i).getCourseList().get(j).getCourseNumber());
				//System.out.print("\n");
				for (int k = 0; k < this.getDepartmentList().get(i).getCourseList().get(j).getStudentRoster().size(); ++k)
					System.out.println(this.getDepartmentList().get(i).getCourseList().get(j).getStudentRoster().get(k).getName());
				System.out.print("\n");
			}
			
		}
		
	}
	
	public void printAllScroll() {
		
		//create area to for text to be displayed
		JPanel textPanel;
		JFrame frame;
		JTextArea textArea;
		JScrollPane scrollPanel;
		JButton okButton = new JButton("OK");
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		frame = new JFrame("University Info");
		textArea = new JTextArea(40,40);
		scrollPanel = new JScrollPane(textArea);
		textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		textPanel.add(scrollPanel);
		textPanel.add(okButton);
		
		textPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 5, 40));
		
		frame.setSize(600, 600);
		frame.setLocation(500, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.add(textPanel);
		frame.setVisible(true);
		
		textArea.append("List of departments:\n");
		for (int i = 0; i < this.getDepartmentList().size(); ++i)
			textArea.append(this.getDepartmentList().get(i).getDepartmentName() + "\n");
		
		textArea.append("\n");

		textArea.append("Classroom list:\n");
		for (int i = 0; i < this.getClassroomList().size(); ++i)
			textArea.append(this.getClassroomList().get(i).getRoomNumber() + "\n");
		
		textArea.append("\n");
		
		for (int i = 0; i < this.getDepartmentList().size(); ++i) {
			textArea.append("The professor list for department " + this.getDepartmentList().get(i).getDepartmentName() + "\n");
			
			for (int j = 0; j < this.getDepartmentList().get(i).getProfessorList().size(); ++j)
				textArea.append(this.getDepartmentList().get(i).getProfessorList().get(j).getName() + "\n");
			
			textArea.append("\n");
		}
		
		//textArea.append("\n\n");
		
		for (int i = 0; i < this.getDepartmentList().size(); ++i) {
			textArea.append("The course list for department " + this.getDepartmentList().get(i).getDepartmentName() + "\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getCourseList().size(); ++j)
				textArea.append(this.getDepartmentList().get(i).getDepartmentName() + this.getDepartmentList().get(i).getCourseList().get(j).getCourseNumber() + "\n");
			
			textArea.append("\n");
		}
		
		for (int i = 0; i < this.getClassroomList().size(); ++i) {
			textArea.append("The schedule for classroom " + this.getClassroomList().get(i).getRoomNumber() + "\n");
			textArea.append(this.getClassroomList().get(i).printSchedule());
			
			textArea.append("\n");
		}
		
		//textArea.append("\n\n");
		
		for (int i = 0; i < this.getDepartmentList().size(); ++i) {
			textArea.append("Department " + this.getDepartmentList().get(i).getDepartmentName() + "\n\n");
			
			textArea.append("Printing Professor schedules:\n\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getProfessorList().size(); ++j) {
				textArea.append("The schedule for Prof. " + this.getDepartmentList().get(i).getProfessorList().get(j).getName() + ":\n");
				textArea.append(this.getDepartmentList().get(i).getProfessorList().get(j).printSchedule());
				
				textArea.append("\n");
			}
			
			textArea.append("Printing Student Schedules\n\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getStudentList().size(); ++j) {
				textArea.append("The Schedule for Student " + this.getDepartmentList().get(i).getStudentList().get(j).getName() + ":\n");
				textArea.append(this.getDepartmentList().get(i).getStudentList().get(j).printSchedule());
				
				textArea.append("\n");
			}
			
			textArea.append("Printing Staff Schedules:\n\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getStaffList().size(); ++j) {
				textArea.append("The Schedule for Employee " + this.getDepartmentList().get(i).getStaffList().get(j).getName() + ":\n");
				textArea.append(this.getDepartmentList().get(i).getStaffList().get(j).printSchedule());
				textArea.append("\n\n");
				//textArea.append("Staff : " + this.getDepartmentList().get(i).getStaffList().get(j).getName() + " earns $" + this.getDepartmentList().get(i).getStaffList().get(j).earns() + " this month\n");
				//textArea.append("\n");
			}
			
			textArea.append("The rosters for courses offered by " + this.getDepartmentList().get(i).getDepartmentName() + "\n\n");
			for (int j = 0; j < this.getDepartmentList().get(i).getCourseList().size(); ++j) {
				textArea.append("The roster for course " + this.getDepartmentList().get(i).getDepartmentName() + this.getDepartmentList().get(i).getCourseList().get(j).getCourseNumber() + "\n");
				for (int k = 0; k < this.getDepartmentList().get(i).getCourseList().get(j).getStudentRoster().size(); ++k)
					textArea.append(this.getDepartmentList().get(i).getCourseList().get(j).getStudentRoster().get(k).getName() + "\n");
				textArea.append("\n");
			}
			
		}
		
	}

	public static void saveData(University univ1) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream( "university.ser" );		//the Employee object makes its way to serial data in the file Employee.ser
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(univ1);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }		
		
	}
	public static University loadData() {
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		University univ=null;
			
		try
		{
			fileIn = new FileInputStream("university.ser");
			objIn = new ObjectInputStream(fileIn);
			univ = (University) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		return univ;
	}
}
