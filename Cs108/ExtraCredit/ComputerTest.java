/**
 * Program Extra Credit, Anas Khafagi, Masc1916. 
 * a driver class to test the ComputerPart and Computerkit class
 * CS108-2 04-15-15
 * @author Anas khafagi
 */
public class ComputerTest {

	public static void main(String args[]) {
		ComputerPart MB = new ComputerPart("MotherBoard", 150.00); // creates a ComputerPart object called MB with name MotherBoard and price 150.00
		ComputerPart CPU = new ComputerPart("Central Processing Unit", 200.00);
		ComputerPart GPU = new ComputerPart("Graphics Processing Unit", 400.00);
		ComputerPart RAM = new ComputerPart("Random Access Memory", 50.00);
		ComputerPart HD = new ComputerPart("Hard Disk", 80.00);
		ComputerPart M = new ComputerPart("Monitor", 40.00);
		ComputerPart Mo = new ComputerPart("Mouse", 20.00);
		ComputerPart K = new ComputerPart("Keyboard", 30.00);
		ComputerPart Disk = new ComputerPart("Disk Drive", 80.00);
		ComputerPart Case = new ComputerPart("Computer Case", 100.00);
		ComputerPart PS = new ComputerPart("Power Supply", 50.00);
		ComputerPart NC = new ComputerPart("NetWork Card", 80.00);
		ComputerPart Fan = new ComputerPart("Fans", 40.00);
		ComputerPart CPUFan = new ComputerPart("CPU Fan", 50.00);
		ComputerPart L = new ComputerPart("CPU Fan", 50.00);
		ComputerKit computer1 = new ComputerKit(); // Creates an empty ComputerPart Object.
		computer1.add(CPU); // adds the ComputerPart CPU to the ComputerPart Arraylist Computer1
		computer1.add(Case);
		computer1.add(MB);
		computer1.add(CPU);
		computer1.add(GPU);
		computer1.add(PS);
		computer1.add(RAM);
		computer1.add(RAM);
		computer1.add(RAM);
		computer1.add(HD);
		computer1.add(NC);
		computer1.add(Fan);
		computer1.add(Fan);
		computer1.add(CPUFan);
		computer1.add(M);
		computer1.add(Mo);
		computer1.add(K);
		System.out.println(computer1.comparePrice(computer1)); // checks the price of computer1 and returns expensive
		System.out.println(computer1.instanceOf(computer1, MB)); // checks if the ComputerPart MB is in Computer1 and returns true
		System.out.println(computer1.numFound(computer1, RAM)); // checks how many times ComputerPart RAM is found in computer1 returns 3
		System.out.println(computer1.instanceOf(computer1, Disk)); // checks if the ComputerPart Disk is found in computer1 and returns false
		System.out.println(computer1.getComputer().indexOf(L));

	}

}
