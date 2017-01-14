package calculatorT;

import java.util.StringTokenizer;

import data_structures.Queue;
import data_structures.Stack;

public class ExpressionEvaluator {
    private String infixExpression, postfixExpression;
    private Stack<String> stack;
    private Queue<String> queue;

    
    public ExpressionEvaluator() {
        stack = new Stack<String>();
        queue = new Queue<String>();
        }

    public String processInput(String s) {
        infixExpression = s;
        getPostfixExpression();
        if(postfixExpression.equals("ERROR")) return "ERROR";
        return evaluatePostfixExpression();        
        }
                
        
    private String evaluatePostfixExpression() {
    try {
        StringTokenizer t = new StringTokenizer(postfixExpression);
        stack.makeEmpty();
        while(t.hasMoreElements()) {
            String token = ((String) t.nextElement()).trim();         
            if(isNumber(token)) {            
                stack.push(token);
                }
            else if(isOperator(token)) {            
                double num1 = Double.parseDouble((String) stack.pop());
                double num2 = Double.parseDouble((String) stack.pop());
                String answer = process(num2, num1, token);
                stack.push(answer);
                }
            }
         return ""+ stack.pop();
        }
    catch(Exception e) {
        return "ERROR";
        }
    }
        
    private void getPostfixExpression() {
    try {
        String token;
        StringTokenizer t = new StringTokenizer(infixExpression);
        stack.makeEmpty();
        queue.makeEmpty();
        while(t.hasMoreElements()) {
            token = ((String) t.nextElement()).trim();           
            if(token.equals("("))
                stack.push(token);
            else if(token.equals(")")) {
                token = ((String) stack.pop());

                while(!token.equals("(")) {
                    queue.enqueue(token);
                    token = ((String) stack.pop());
                    }
                }
            else if(isOperator(token)) {
                while(!stack.isEmpty() &&
                      !((String)stack.peek()).equals("(") &&
                      greaterThanOrEqual(((String)stack.peek()),token))                     
                        queue.enqueue(stack.pop());                       
                stack.push(token);
                }                                
            else
                queue.enqueue(token);
        } // end while
        
        while(!stack.isEmpty())
            queue.enqueue(stack.pop());
        StringBuffer buffer = new StringBuffer();
        while(!queue.isEmpty())
            buffer.append((String)queue.dequeue()+" ");
        postfixExpression = buffer.toString().trim();
               
        }
    catch(Exception e) {
      postfixExpression = "ERROR";
        }
    }
    
    private boolean greaterThanOrEqual(String a, String b) {

        if(a.equals("^")) 
            return true;
            
        if(b.equals("^"))
            return false;
            
        if(a.equals("*") || a.equals("/")) 
            return true;

        if(b.equals("*") || b.equals("/"))
            return false;
            
        return true;
        }
        
    private String process(double a, double b, String s) {
        String answer;
        if(s.trim().equals("+"))
            answer = ""+ (a+b);
        else if(s.trim().equals("-"))
            answer = ""+ (a-b);
        else if(s.trim().equals("*"))
            answer = ""+ (a*b);
        else if(s.trim().equals("/"))
            answer = ""+ (a/b);
        else
            answer = "" + (Math.pow(a,b));
        return answer;
        
    }
           
        
    private boolean isOperator(String s) {
        if(s.trim().length() != 1)
            return false;
        if(s.equals("+") ||
           s.equals("-") ||
           s.equals("*") ||
           s.equals("/") ||
           s.equals("^"))
            return true;
        return false;
        }
           
    private boolean isNumber(String s) {
        try {
            double n = Double.parseDouble(s);
            return true;
            }
        catch(NumberFormatException e) {
            return false;
            }
        }
           
    
}