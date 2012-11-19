package cvut.fit.dpo.arithmetic;

import cvut.fit.dpo.arithmetic.elements.BinaryExpressionElement;
import cvut.fit.dpo.arithmetic.elements.SubstractOperation;

/**
 * Represents - operation
 * 
 */
public class SubstractOperator extends BinaryOperator
{
	public SubstractOperator() { super(); }
	
	public SubstractOperator(ArithmeticExpression firstOperand, ArithmeticExpression secondOperand)
	{
		super(firstOperand, secondOperand);
	}

	@Override
	public Integer evaluate() {
		return firstOperand.evaluate() - secondOperand.evaluate();
	}

	@Override
	protected BinaryExpressionElement getElement() {
		return new SubstractOperation();
	}

}
