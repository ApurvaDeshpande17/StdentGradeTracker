import java.util.ArrayList;
import java.util.List;

public class Grade {
	private String name;
	private double grade;
	private double totalMark;
	private List<Double> grades;
	private List<Double> totalMarks;
	private List<Double> marksObtained;

	public Grade(String name, double grade, double totalMarks) {
		this.name = name;
		this.grade = grade;
		this.totalMark = totalMarks;
		this.grades = new ArrayList<>();
		this.totalMarks = new ArrayList<>();
		this.marksObtained = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public double getMarksObtained() {
		marksObtained.add(grade);
		return grade;
	}

	public double getTotalMarks() {
		totalMarks.add(totalMark);
		return totalMark;
	}

}
