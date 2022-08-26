package entities;

import java.util.Stack;

public class Calculator {
	public double evaluate(String exp) {
		
		Stack<Double> operands = new Stack<>(); // Operand stack
		Stack<Character> operations = new Stack<>(); // Operator stack
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			
			if (Character.isDigit(c)) // check if it is number
			{	
				// Entry is Digit, and it could be greater than a one-digit number
				double num = 0;
				while (Character.isDigit(c)) {
					num = num * 10 + (c - '0');
//					System.out.println("indice: "+i);
					System.out.println("C - 0: "+ (c - '0'));
					System.out.println("Num: " + num);
					i++;
					if (i < exp.length()) {
						c = exp.charAt(i);
					} else {
						break;
					}
				}
				i--;
				operands.push(num);
			} else if (c == '(') {
				operations.push(c); // push character to operators stack
			}
			// Closed brace, evaluate the entire brace
			else if (c == ')') {
				while (operations.peek() != '(') {
					double output = performOperation(operands, operations);
					operands.push(output); // push result back to stack
				}
				operations.pop();
			}

			// current character is operator
			else if (isOperator(c)) {
				while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
					double output = performOperation(operands, operations);
					operands.push(output); // push result back to stack
				}
				operations.push(c); // push the current operator to stack
			}
		}

		while (!operations.isEmpty()) {
			double output = performOperation(operands, operations);
			operands.push(output); // push final result back to stack
		}
		
		return operands.pop();		
	}

	static int precedence(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}

	public double performOperation(Stack<Double> operands, Stack<Character> operations) {
		double a = operands.pop();
		double b = operands.pop();		
		char operation = operations.pop();
		
		System.out.println("A: " + a + "\nB: "+ b + "\nOperation: " + operation);
		
		switch (operation) {
		case '+':
			return a + b;
		case '-':
			return b - a;
		case '*':
			return a * b;
		case '/':
			if (a == 0) {
				System.out.println("Cannot divide by zero");
				return 0;
			}
			return b / a;
		}
		return 0;
	}

	public boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '/' || c == '*' || c == '^');
	}
}
