package application;
import java.util.*;


public class MathExpressions {
	
	
	
	public static double evaluate(String exp) {
		
		Stack<Double> operands=new Stack();
		Stack<Character> operators=new Stack();
		
		for(int i=0;i<exp.length();i++) {
			char ch=exp.charAt(i);
			
			if(Character.isDigit(ch)) {
				operands.push(Double.parseDouble(ch+""));
			}
	
			else if(ch=='(') {
				operators.push(ch);
			}
	

			else if(ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='^' ) {
				
				//if operator comes then check
				if(operators.isEmpty()) {
					operators.push(ch);
				}
				else if(operators.peek()=='(') {
					operators.push(ch);	
				}
				else if(getPrecedence(operators.peek())>=getPrecedence(ch)) {
					char op=operators.pop();
					double num2= operands.pop();
					double num1= operands.pop();
					
					double value=Calculate( num1,num2,op);
					operands.push(value);
					operators.push(ch);
				}
				else {
					operators.push(ch);
				}
				
										
			}
			else if(ch==')') {
				
				while (operators.peek()!='(') {
					char op=operators.pop();
					double num2= operands.pop();
					double num1= operands.pop();
					double value=Calculate( num1,num2,op);
					operands.push(value);
				}
				
				operators.pop();
			}
		}
		

		
		// end of loop
		
		while(operators.size()!=0) {
			
			char op=operators.pop();
			double num2= operands.pop();
			double num1= operands.pop();
			
			double value=Calculate(num1,num2,op);
			operands.push(value);
			
		}
		
		return operands.pop();
	}
	
	

	
	private static int getPrecedence(char ch) {
		
		
		
		if(ch=='+' || ch=='-')
			return 1;
		
		else if(ch=='*' || ch=='/')
			return 2;
		
		else if(ch=='^')
			return 3;
		
		else if(ch=='(' || ch==')')
			return 4;
		
		else
			return 0;
		
	}
	
	
	
	private static double Calculate( double num1, double num2, char operator) {
		

             switch(operator) {
		
		                case'+': return num1+num2;
	                  	case'-': return num1-num2;
		                case'*': return num1*num2;
		                 case'/': 
		                        	if(num2==0)
				                            return 0;
			                        else
			                             	return num1/num2;
		case'^': return Math.pow(num1, num2);
	

		
			default: return 0;
		}
		
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	