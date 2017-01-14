/*  BaseConverter.java
    Java solution to program #3, CS237, SP 2015
    Code by Alan Riggins
*/    

import java.io.*;

public class BaseConverter {
    private String input, base;
    private int inputNumber, outputBase;

    public BaseConverter() throws IOException {
        processInput();
        }
        
    private void processInput() {
        boolean ok = false;
        printHeader();
        // read input number to convert, and validate it.
        do {        
            getInput();        
            ok = isValidInteger(input);  // contains only digits 0..9
            if(!ok) {
                System.out.println("ERROR, invalid number " + input);
                continue;
                }
            if(ok) {
                ok = isRangeValid();  // is in the range 0 .. 65535
                if(!ok)
                    System.out.println("ERROR, number out of range: " + input);            
                }
            }
        while(!ok);
        
        // read input base to convert to, and validate it.        
        do {        
            getBase();        
            ok = isValidInteger(base);  // contains only digits 0..9
            if(!ok) {
                System.out.println("ERROR, invalid base " + base);
                continue;
                }
            if(ok) {
                ok = isValidBase();  // is in the range 2 .. 16
                if(!ok)
                    System.out.println("ERROR, number out of range: " + base);            
                }
            }
        while(!ok);        

       System.out.println("The answer is " + convertNumber());
       }
        
    private void printHeader() {
        System.out.println("Program #3, Alan Riggins, mascxxxx\n");
        }
        
    private void getInput()  {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
        try {
            System.out.println("Enter a base 10 number:");
            input = in.readLine();
            }
        catch(IOException e) {
            System.out.println("Sorry, an IO error has occurred " +e);
            }
        }
        
    private void getBase()  {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
        try {
            System.out.println("\nEnter a base to convert to:");
            base = in.readLine();
            }
        catch(IOException e) {
            System.out.println("Sorry, an IO error has occurred " +e);
            }
        }        
        
    private boolean isValidInteger(String number) {
        byte [] inputArray = number.getBytes();
        for(int i=0; i < inputArray.length; i++)
            if(inputArray[i] < 0x30 || inputArray[i] > 0x39)
               return false;
        return true;
        }
        
    private boolean isRangeValid() {
        inputNumber = Integer.parseInt(input);
        if(inputNumber < 0 || inputNumber > 65535)
            return false;        
        return true;
        }
        
    private boolean isValidBase() {
        outputBase = Integer.parseInt(base);
        if(outputBase < 2 || outputBase > 16)
            return false;
        return true;
        }
        
    @SuppressWarnings("unused")
	private String convertNumber() {
        int quotient, remainder, digitCounter = 0;
        char tmp;        
        char [] remainders = new char[16];
        String answer = "";
        while(inputNumber != 0) {
            remainder = inputNumber % outputBase;        
            inputNumber /= outputBase;
            if(remainder < 10)
                tmp = (char) (remainder+0x30);
            else
                tmp = (char) (remainder+0x37);
            remainders[digitCounter++] = tmp;
            }
        // since the remainders are read from the bottom up, you
        // must reverse the answer string.
        for(int i=digitCounter-1; i >= 0; i--)
            answer += remainders[i];
        return answer;
    }
        
    public static void main(String [] args) {
        try {
            new BaseConverter();
            }
        catch(IOException e) {
            System.out.println("Sorry, an IO error has occurred " +e);
            }
        }
    }