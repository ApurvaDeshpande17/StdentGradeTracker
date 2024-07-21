import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentGradeTracker {

	// storing students by name, hence using MAP data structure.
	private static Map<String, Student> students = new HashMap<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		System.out.println("Welcome to the Student Grade Tracker!");
		while (!exit) {
			System.out.println("Please choose an option:");
			System.out.println("1. Add a student");
			System.out.println("2. Add a grade");
			System.out.println("3. Add attendance");
			System.out.println("4. Add notes");
			System.out.println("5. Show student list");
			System.out.println("6. Show grades");
			System.out.println("7. Show attendance for a student");
			System.out.println("8. Show notes");
			System.out.println("9. Remove a student");
			System.out.println("10. Remove grades");
			System.out.println("11. Remove attendance");
			System.out.println("12. Remove notes");
			System.out.println("13. Calculate overall grades for a student");
			System.out.println("14. Exit");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				addStudent(scanner);
				break;
			case 2:
				addGrades(scanner);
				break;
			case 3:
				addAttendance(scanner);
				break;
			case 4:
				addNotes(scanner);
				break;
			case 5:
				showStudentList();
				break;
			case 6:
				showAllGrades(scanner);
				break;
			case 7:
				showAttendance(scanner);
				continue;
			case 8:
				getNotes(scanner);
				break;
			case 9:
				removeStudent(scanner);
				break;
			case 10:
				removeGrades(scanner);
				break;
			case 11:
				removeAttendance(scanner);
				break;
			case 12:
				removeNotes(scanner);
				break;
			case 13:
				calculateOverallGrade(scanner);
				break;
			case 14:
				exit = true;
				System.out.println("Exiting the Student Grade Tracker. Goodbye!");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
			System.out.println();
		}
	}

	private static void addStudent(Scanner scanner) {
		boolean continueAdding = true;
		while (continueAdding) {
			System.out.println("Enter the student's name:");
			String name = scanner.nextLine();
			if (students.containsKey(name)) {
				System.out.println("Error: student already exists.");
				return;
			}
			students.put(name, new Student(name));
			System.out.println("Student added successfully.");
			System.out.println("Do you want to add more grades? Type 'Y' for Yes or 'N' for No.");
			String input = scanner.nextLine();
			if (!input.equalsIgnoreCase("Y")) {
				continueAdding = false;
			}
		}
	}

	private static void addGrades(Scanner scanner) {
		Student student = validateStudent(scanner);
		boolean continueAdding = true;
		while (continueAdding) {
			System.out.println("To enter the grade, type 'A' for assignments, 'P' for projects, or 'E' for exams:");
			String gradeType = scanner.nextLine();
			System.out.println("Enter a name:");
			String gradeName = scanner.nextLine();
			System.out.println("Enter the marks obtained:");
			double grade = scanner.nextDouble();
			System.out.println("Enter total marks:");
			double totalMarks = scanner.nextDouble();
			scanner.nextLine();
			if (gradeType.equalsIgnoreCase("A")) {
				student.addAssignmentGrade(gradeName, grade, totalMarks);
			} else if (gradeType.equalsIgnoreCase("P")) {

				student.addProjectGrade(gradeName, grade, totalMarks);
			} else if (gradeType.equalsIgnoreCase("E")) {

				student.addExamGrade(gradeName, grade, totalMarks);
			} else {
				System.out.println("Error: invalid grade type.");
				return;
			}
			System.out.println("Grade added successfully.");
			System.out.println("Do you want to add more grades? Type 'Y' for Yes or 'N' for No.");
			String input = scanner.nextLine();
			if (!input.equalsIgnoreCase("Y")) {
				continueAdding = false;
			}
		}
	}

	private static void removeGrades(Scanner scanner) {
		Student student = validateStudent(scanner);
		System.out.println("To remove a grade, type 'A' for assignments, 'P' for projects, or 'E' for exams:");
		String gradeType = scanner.nextLine();
		System.out.println("Enter a name:");
		String gradeName = scanner.nextLine();
		if (gradeType.equalsIgnoreCase("A")) {
			student.removeAssignmentGrade(gradeName);
			System.out.println("Assignment " + gradeName + " removed successfully.");
		} else if (gradeType.equalsIgnoreCase("P")) {
			student.removeProjectGrade(gradeName);
			System.out.println("Project " + gradeName + " removed successfully.");

		} else if (gradeType.equalsIgnoreCase("E")) {
			student.removeExamGrade(gradeName);
			System.out.println("Exam " + gradeName + " removed successfully.");

		} else {
			System.out.println("Error: invalid grade type.");
		}
	}

	private static void showAllGrades(Scanner scanner) {
		Student student = validateStudent(scanner);
		System.out.println("Assignment Grades:");
		List<Grade> assignmentGrades = student.getAssignmentGrades();
		for (Grade grade : assignmentGrades) {
			System.out.println("Name: " + grade.getName() + " | Marks Obtained: " + grade.getMarksObtained()
					+ " | Total Marks: " + grade.getTotalMarks());
		}
		System.out.println("Project Grades:");
		List<Grade> projectGrades = student.getProjectGrades();
		for (Grade grade : projectGrades) {
			System.out.println("Name: " + grade.getName() + " | Marks Obtained: " + grade.getMarksObtained()
					+ " | Total Marks: " + grade.getTotalMarks());
		}
		System.out.println("Exam Grades:");
		List<Grade> examGrades = student.getExamGrades();
		for (Grade grade : examGrades) {
			System.out.println("Name: " + grade.getName() + " | Marks Obtained: " + grade.getMarksObtained()
					+ " | Total Marks: " + grade.getTotalMarks());
		}
	}

	private static void calculateOverallGrade(Scanner scanner) {
		System.out.println("Enter the student's name:");
		String name = scanner.nextLine();
		Student student = students.get(name);
		if (student == null) {
			System.out.println("Error: student not found.");
			return;
		}
		System.out.println("Enter the total grade weight for assignments (as a decimal, e.g. 0.5 for 50%):");
		double weightA = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter the total grade weight for projects (as a decimal, e.g. 0.2 for 20%):");
		double weightP = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter the total grade weight for exams (as a decimal, e.g. 0.3 for 30%):");
		double weightE = scanner.nextDouble();
		student.addWeights(weightA, weightP, weightE);
		double overallGrade = student.calculateOverallGrade();
		System.out.println("Overall grade for " + name + " is " + overallGrade);
	}

	private static void addAttendance(Scanner scanner) {
		Student student = validateStudent(scanner);
		System.out.println("Enter the date (MM/DD/YYYY):");
		String date = scanner.nextLine();
		System.out.println("Was the student present? (Y/N):");
		boolean present = scanner.nextLine().equalsIgnoreCase("Y");
		student.trackAttendance(date, present);
		System.out.println("Attendance tracked successfully.");
	}

	private static void removeAttendance(Scanner scanner) {
		Student student = validateStudent(scanner);
		System.out.println("Enter the date (MM/DD/YYYY):");
		String date = scanner.nextLine();
		student.removeAttendance(date);
		System.out.println("Attendance removed successfully");
	}

	private static Student validateStudent(Scanner scanner) {
		System.out.println("Enter the student's name:");
		String name = scanner.nextLine();
		Student student = students.get(name);
		if (student == null) {
			System.out.println("Error: student not found.");
			return student;
		}
		return student;
	}

	private static void addNotes(Scanner scanner) {
		Student student = validateStudent(scanner);
		System.out.println("Enter the note:");
		String note = scanner.nextLine();
		student.addNotes(note);
		System.out.println("Note added successfully.");
	}

	private static void removeNotes(Scanner scanner) {
		Student student = validateStudent(scanner);
		student.removeNotes();
		System.out.println("Note added successfully.");
	}

	private static void getNotes(Scanner scanner) {
		System.out.println("Enter the student's name:");
		String name = scanner.nextLine();
		Student student = students.get(name);
		if (student == null) {
			System.out.println("Error: student not found.");
			return;
		}
		System.out.println("Note for " + name + " is:");
		System.out.println(student.getNotes());
	}

	private static void showStudentList() {
		System.out.println("\nList of students:");
		for (String name : students.keySet()) {
			System.out.println("- " + name);
		}
		System.out.println();
	}

	private static void showAttendance(Scanner scanner) {
		System.out.println("Enter the student's name:");
		String name = scanner.nextLine();
		Student student = students.get(name);
		if (student == null) {
			System.out.println("Error: student not found.");
			return;
		}
		Map<String, Boolean> attendance = student.getAttendance();
		if (attendance.isEmpty()) {
			System.out.println("No attendance data found for this student.");
			return;
		}
		System.out.println("Attendance for " + name + ":");
		for (String date : attendance.keySet()) {
			System.out.println(date + " - " + (attendance.get(date) ? "Present" : "Absent"));
		}
	}

	private static void removeStudent(Scanner scanner) {
		System.out.println("Enter the name of the student you want to remove:");
		String name = scanner.nextLine();
		if (!students.containsKey(name)) {
			System.out.println("Error: student not found.");
			return;
		}
		students.remove(name);
		System.out.println("Student removed successfully.");
	}
}