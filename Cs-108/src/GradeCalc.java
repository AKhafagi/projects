
/**
 * Simple Program to calculate Current Course Grade Percentage
 * @author Anas Khafagi 
 * Friday, January 22nd, 2016
 */
import java.util.Scanner;

public class GradeCalc {
	public static void calcGrade() {
		double homeworkScore = 0.0;
		double midtermScore = 0.0;
		double finalScore = 0.0;
		double coursePercentage = 0.0;
		double quizScore = 0.0;
		double quizPart = 0.0;
		double midtermPart = 0.0;
		double finalPart = 0.0;
		double homeworkPart = 0.0;
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter homework score, max score, and weight:");
		homeworkScore = scnr.nextDouble();
		final double HOMEWORK_MAX = scnr.nextDouble();
		final double HOMEWORK_WEIGHT = scnr.nextDouble();
		System.out.println("Calculating Weighted Homework Grade");
		homeworkPart = ((homeworkScore / HOMEWORK_MAX) * HOMEWORK_WEIGHT) * 100;
		System.out.printf("Weighted Homework grade is : %.2f%%\n", homeworkPart);

		System.out.println();

		System.out.println("Enter quiz score, max score, and weight:");
		quizScore = scnr.nextDouble();
		final double QUIZ_MAX = scnr.nextDouble();
		final double QUIZ_WEIGHT = scnr.nextDouble();
		System.out.println("Calculating Weighted Quiz Grade");
		quizPart = ((quizScore / QUIZ_MAX) * QUIZ_WEIGHT) * 100;
		System.out.printf("Weighted Quiz grade is : %.2f%%\n", quizPart);

		System.out.println();

		System.out.println("Enter midterm exam score, max score, and weight:");
		midtermScore = scnr.nextDouble();
		final double MIDTERM_MAX = scnr.nextDouble();
		final double MIDTERM_WEIGHT = scnr.nextDouble();
		System.out.println("Calculating Weighted Midterm Grade");
		midtermPart = ((midtermScore / MIDTERM_MAX) * MIDTERM_WEIGHT) * 100;
		System.out.printf("Midterm grade is : %.2f%%\n", midtermPart);

		System.out.println();

		System.out.println("Enter final exam score, max score, and weight:");
		finalScore = scnr.nextDouble();
		final double FINAL_MAX = scnr.nextDouble();
		final double FINAL_WEIGHT = scnr.nextDouble();
		System.out.println("Calculating Weighted Final Grade");
		finalPart = ((finalScore / FINAL_MAX) * FINAL_WEIGHT) * 100;
		System.out.printf("Weighted Final grade is : %.2f%%\n", finalPart);

		System.out.println();

		System.out.println("Calculating Weighted Course Grade Please Wait");
		System.out.println(".");
		System.out.println(".");
		System.out.println(".");
		coursePercentage = midtermPart + finalPart + quizPart + homeworkPart;
		System.out.print("Final Weighted Course Grade is: ");
		System.out.printf("%.2f%%\n", coursePercentage);
		scnr.close();
	}

	public static void calcFinal() {
		double currentClassGrade = 0.0;
		double finalScore = 0.0;
		double finalWeight = 0.0;
		double wantedGrade = 0.0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input Current class grade: ");
		currentClassGrade = scan.nextDouble();
		System.out.println("Please input final class grade wanted: ");
		wantedGrade = scan.nextDouble();
		System.out.println("Please input final exam precentage");
		finalWeight = scan.nextDouble() / 100;
		finalScore = (wantedGrade - (currentClassGrade - (finalWeight * currentClassGrade))) / (finalWeight);
		System.out.print("you will need ");
		System.out.printf("%.1f%%", finalScore);
		System.out.println(" on your final to get " + wantedGrade + "% in the class");
		if (finalScore >= 76 && finalScore <= 100) {
			System.out.println("its gonna be a long shot but you can do it !!");
		} else if (finalScore > 100) {
			System.out.println("It's impossible, better luck next time !!");
		} else if (finalScore < 55) {
			System.out.println("You got this for sure !!!");
		} else if (finalScore == 0) {
			System.out.println("No need to take the final !!! ");
		}
		scan.close();
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String choice;

		System.out.println("Please choose calc grade, calc final, or help");
		while (true) {
			choice = scnr.nextLine();
			if (choice.equals("calc grade")) {
				calcGrade();
				break;
			} else if (choice.equals("calc final")) {
				calcFinal();
				break;
			} else if (choice.equals("help")) {
				System.out.println("you can either calculate your final class grade by choosing calc grade or \n"
						+ "calculating the required final grade to get the grade you want by choosing calc final .\n ");
				System.out.println("Please make a selection.");
			} else {
				System.out.println("Invalid Selction please try again\n");
				System.out.println("Please choose calc grade, calc final, or help");
			}

		}
		scnr.close();
	}
}
