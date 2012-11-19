package cvut.fit.dpo.arithmetic;

import cvut.fit.dpo.arithmetic.elements.BinaryExpressionElement;
import cvut.fit.dpo.arithmetic.iterator.InOrderIterator;
import cvut.fit.dpo.arithmetic.iterator.PostOrderIterator;


/**
 * Represents the Binary operations like + - etc.
 * 
 *
 */
public abstract class BinaryOperator extends ArithmeticExpression
{
	protected ArithmeticExpression firstOperand;
	protected ArithmeticExpression secondOperand;
	
	public BinaryOperator() {	}
	
	public BinaryOperator(ArithmeticExpression firstOperand, ArithmeticExpression secondOperand)
	{
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
	}

	public ArithmeticExpression getFirstOperand() {
		return firstOperand;
	}

	public void setFirstOperand(ArithmeticExpression firstOperand) {
		this.firstOperand = firstOperand;
	}

	public ArithmeticExpression getSecondOperand() {
		return secondOperand;
	}

	public void setSecondOperand(ArithmeticExpression secondOperand) {
		this.secondOperand = secondOperand;
	}
	
	
	@Override
	public InOrderIterator getInOrderIterator() {
		return new InOrderIterator(	firstOperand.getInOrderIterator(),
									secondOperand.getInOrderIterator(),
									getElement());
	}

	@Override
	public PostOrderIterator getPostOrderIterator() {
		 return new PostOrderIterator(	firstOperand.getPostOrderIterator(),
				 						secondOperand.getPostOrderIterator(),
				 						getElement());
	}
	
	protected abstract BinaryExpressionElement getElement();
		
}
