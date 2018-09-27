package iblog;

public class Calculate {
	
	private char operator;

	private Integer value;
	
	public Calculate() {}

	public char getOperator() {
		return operator;
	}

	
	
	/**
	 * @param operator
	 * @param value
	 */
	public Calculate(char operator, Integer value) {
		this.operator = operator;
		this.value = value;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Calculate [operator=" + operator + ", value=" + value + "]";
	}
	
}
