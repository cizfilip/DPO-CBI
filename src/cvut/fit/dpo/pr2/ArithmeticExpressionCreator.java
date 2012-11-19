package cvut.fit.dpo.pr2;

import cvut.fit.dpo.arithmetic.ArithmeticExpression;
import cvut.fit.dpo.arithmetic.builders.InOrderExpressionBuilder;
import cvut.fit.dpo.arithmetic.builders.PostOrderExpressionBuilder;
import cvut.fit.dpo.arithmetic.parsers.ArithmeticExpressionParser;


/**
 * Stupid class which can create some {@link ArithmeticExpression}s.
 * 
 *
 */
public class ArithmeticExpressionCreator
{
	/**
	 * Creates 3 - (1 + 2)
	 * 
	 * This is ugly. I don't like creating expressions in this
	 * 	form. I never know, what expression I have created...
	 */
	public ArithmeticExpression createExpression1()
	{
		String exp = "3 - (1 + 2)";
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser(exp, new InOrderExpressionBuilder());
		
		return parser.parse();
		
	}

	/**
	 * Creates (3 - 1) + 2
	 *
	 * This is ugly. I don't like creating expressions in this
	 * 	form. I never know, what expression I have created...
	 */
	public ArithmeticExpression createExpression2()
	{
		String exp = "(3 - 1) + 2";
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser(exp, new InOrderExpressionBuilder());
		
		return parser.parse();
		
	}
	
	/**
	 * Creates any expression from the RPN input. This is nice and
	 * 	universal. 
	 * 
	 * @see http://en.wikipedia.org/wiki/Reverse_Polish_notation
	 * 	
	 * @param input in Reverse Polish Notation
	 * @return {@link ArithmeticExpression} equivalent to the RPN input.
	 */
	public ArithmeticExpression createExpressionFromRPN(String input)
	{
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser(input, new PostOrderExpressionBuilder());
		
		return parser.parse();
	}
}
