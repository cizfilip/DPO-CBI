package cvut.fit.dpo.arithmetic;

import cvut.fit.dpo.arithmetic.iterator.InOrderIterator;
import cvut.fit.dpo.arithmetic.iterator.PostOrderIterator;

public abstract class ArithmeticExpression
{
	
	public abstract Integer evaluate();
	
	/**
	 * {@link #root} field getter.
	 * 
	 * @deprecated Do not provide access to the inner representation
	 */
	public BinaryOperator getRoot()
	{
		throw new UnsupportedOperationException("Not yet implemented...");
	}
	
	
	public abstract InOrderIterator getInOrderIterator();
	

	public abstract PostOrderIterator getPostOrderIterator();
	

}
