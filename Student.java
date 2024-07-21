import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

	double assignmentWeight;
	double projectWeight;
	double examWeight;
	private String name;
	private List<Grade> assignmentGrade;
	private List<Grade> projectGrade;
	private List<Grade> examGrade;
	private List<Double> weight;

	// storing attendance by student's name, hence using MAP data structure.
	private Map<String, Boolean> attendance;
	private List<String> notes;

	public Student(String name) {
		this.name = name;
		this.assignmentGrade = new ArrayList<>();
		this.weight = new ArrayList<>();
		this.attendance = new HashMap<>();
		this.notes = new ArrayList<>();
		this.projectGrade = new ArrayList<>();
		this.examGrade = new ArrayList<>();
	}

	public void addAssignmentGrade(String assignmentName, double grade, double totalMarks) {
		assignmentGrade.add(new Grade(assignmentName, grade, totalMarks));
	}

	public void addProjectGrade(String projName, double grade, double totalMarks) {
		projectGrade.add(new Grade(projName, grade, totalMarks));
	}

	public void addExamGrade(String examName, double grade, double totalMarks) {
		examGrade.add(new Grade(examName, grade, totalMarks));
	}

	public double calculateOverallGrade() {
		double totalMarksObtained = 0;
		double totalMarks = 0;
		double totalGradeA = 0;
		double totalGradeP = 0;
		double totalGradeE = 0;
		for (Grade grade : assignmentGrade) {
			totalMarksObtained += grade.getMarksObtained();
			totalMarks += grade.getTotalMarks();
			totalGradeA = (totalMarksObtained / totalMarks) * 100 * assignmentWeight;
		}
		for (Grade grade : projectGrade) {
			totalMarksObtained += grade.getMarksObtained();
			totalMarks += grade.getTotalMarks();
			totalGradeP = (totalMarksObtained / totalMarks) * 100 * projectWeight;
		}
		for (Grade grade : examGrade) {
			totalMarksObtained += grade.getMarksObtained();
			totalMarks += grade.getTotalMarks();
			totalGradeE = (totalMarksObtained / totalMarks) * 100 * examWeight;
		}
		if (totalMarksObtained == 0) {
			return 0;
		} else {
			return totalGradeA + totalGradeP + totalGradeE;
		}
	}

	public void trackAttendance(String status, boolean present) {
		attendance.put(status, present);
	}

	public Map<String, Boolean> getAttendance() {
		return attendance;
	}

	public void removeAttendance(String status) {
		if (getAttendance().containsKey(status)) {
			attendance.remove(status);
		}
	}

	public void addNotes(String note) {
		notes.add(note);
	}

	public String getName() {
		return name;
	}

	public void addWeights(double weightA, double weightP, double weightE) {
		assignmentWeight = weightA;
		projectWeight = weightP;
		examWeight = weightE;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void removeAssignmentGrade(String assignmentName) {
		assignmentGrade.removeIf(grade -> grade.getName().equals(assignmentName));
	}

	public void removeProjectGrade(String projectName) {
		assignmentGrade.removeIf(grade -> grade.getName().equals(projectName));
	}

	public void removeExamGrade(String examName) {
		assignmentGrade.removeIf(grade -> grade.getName().equals(examName));
	}

	public List<Grade> getAssignmentGrades() {
		List<Grade> assignmentGrades = new ArrayList<>();
		for (Grade grade : assignmentGrade) {
			assignmentGrades.add(new Grade(grade.getName(), grade.getMarksObtained(), grade.getTotalMarks()));
		}
		return assignmentGrades;
	}

	public List<Grade> getProjectGrades() {
		List<Grade> projectGrades = new ArrayList<>();
		for (Grade grade : projectGrades) {
			projectGrades.add(new Grade(grade.getName(), grade.getMarksObtained(), grade.getTotalMarks()));
		}
		return projectGrades;
	}

	public List<Grade> getExamGrades() {
		List<Grade> examGrades = new ArrayList<>();
		for (Grade grade : examGrades) {
			examGrades.add(new Grade(grade.getName(), grade.getMarksObtained(), grade.getTotalMarks()));
		}
		return examGrades;
	}

	public void removeNotes() {
		notes.clear();
	}
}
