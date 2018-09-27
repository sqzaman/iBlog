package iblog;

public class Calculator {
	private static int result = 0;

	static int calculateMe(Calculate calcObj) {
		switch (calcObj.getOperator()) {
		case '+':
			result = result + calcObj.getValue();
		case '-':
			result = result - calcObj.getValue();
		case '*':
			result = result * calcObj.getValue();
		case '/':
			result = result / calcObj.getValue();
		default:

		}

		return result;
	}

	public static int getResult() {
		return result;
	}

	
	
}
