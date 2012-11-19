package cvut.fit.dpo.arithmetic;


import cvut.fit.dpo.arithmetic.elements.AddOperation;
import cvut.fit.dpo.arithmetic.elements.BinaryExpressionElement;


/**
 * Represents + operation
 * 
 * 
 */
public class AddOperator extends BinaryOperator
{
	public AddOperator() { super();	}

	public AddOperator(ArithmeticExpression firstOperand, ArithmeticExpression secondOperand)
	{
		super(firstOperand, secondOperand);
	}

	@Override
	public Integer evaluate() {
		return firstOperand.evaluate() + secondOperand.evaluate(); 
	}

	@Override
	protected BinaryExpressionElement getElement() {
		return new AddOperation();
	}

	
}
