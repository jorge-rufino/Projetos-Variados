package entities;

import java.util.Stack;

public class Calculator {
	public double evaluate(String expression) {
		
		Stack<Double> operands = new Stack<>(); // Operand stack
		Stack<Character> operations = new Stack<>(); // Operator stack
		
		String[] tokens = expression.split("(?<=[-+*/()^])|(?=[-+*/()^])");
		
		for (int i = 0; i < tokens.length; i++) {			
            String token = tokens[i];
            
            if (Character.isDigit(token.charAt(0))) {
                Double number = Double.valueOf(token);
                operands.push(number);
			} else if ("(".equals(token)) {
				operations.push(token.charAt(0)); // push character to operators stack
			}
			// Closed brace, evaluate the entire brace
			else if (")".equals(token)) {
				while (operations.peek() != '(') {
					double output = performOperation(operands, operations);
					operands.push(output); // push result back to stack
				}
				operations.pop();
			}

			// current character is operator
			else if (isOperator(token.charAt(0))) {
				while (!operations.isEmpty() && precedence(token.charAt(0)) <= precedence(operations.peek())) {
					double output = performOperation(operands, operations);
					operands.push(output); // push result back to stack
				}
				operations.push(token.charAt(0)); // push the current operator to stack
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
