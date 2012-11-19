package cvut.fit.dpo.arithmetic;

import cvut.fit.dpo.arithmetic.iterator.InOrderIterator;
import cvut.fit.dpo.arithmetic.iterator.PostOrderIterator;
import cvut.fit.dpo.arithmetic.elements.Number;


/**
 * Represents number in the arithmetic expression

 */
public class NumberOperand extends ArithmeticExpression {
	private Integer value;
	
	public NumberOperand(Integer value)
	{
		this.value = value;
	}
	
	@Override
	public Integer evaluate() {
		return value;
	}

	@Override
	public InOrderIterator getInOrderIterator() {
		return new InOrderIterator(new Number(value));
	}

	@Override
	public PostOrderIterator getPostOrderIterator() {
		return new PostOrderIterator(new Number(value));
	}
	
	
}
