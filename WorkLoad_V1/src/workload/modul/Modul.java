package workload.modul;

import java.io.Serializable;

public class Modul implements Serializable{
	
	private int semester;
	private String name;
	private int credits;
	
	public Modul(int semester, String name, int credits) {
		this.semester = semester;
		this.name = name;
		this.credits = credits;
	}
	public Modul() {
		this.semester = 1;
		this.name = "test";
		this.credits = 1;
	}
	
	public Modul(String semester, String name, String credits) {
		this.semester = Integer.parseInt(semester);
		this.name = name;
		this.credits = Integer.parseInt(credits);
	}
	
	public String toString() {
		return name;
	}
	
	public String toString(boolean nurName) {
		if (nurName) {
			return name;
		} else {
			return semester + " " + name + " " + credits;
		}
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	

}
