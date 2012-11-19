package cvut.fit.dpo.pr2;

import java.util.Iterator;

import cvut.fit.dpo.arithmetic.ArithmeticExpression;
import cvut.fit.dpo.arithmetic.elements.ExpressionElement;

/**
 * Printer for {@link ArithmeticExpression}s. It can print inOrder notation (3 +
 * 1) or postOrder notation (3 1 +).
 * 
 * PostOrder is RPN (Reverse Polish Notation) in fact. See wiki for more
 * information.
 * 
 */
public class ArithmeticExpressionPrinter {

	private ArithmeticExpression expression;

	public ArithmeticExpressionPrinter(ArithmeticExpression expression) {
		setExpression(expression);
	}

	private void setExpression(ArithmeticExpression expression) {
		this.expression = expression;
	}

	/**
	 * Print an expression in classical notation, e.g. (3+1).
	 * 
	 * The "(" and ")" is used to preserve priorities.
	 * 
	 * @return String in classical "inOrder" format.
	 */
	public String printInOrder() {
		return print(expression.getInOrderIterator(), null);
	}


	/**
	 * Print an expression in RPN notation, e.g. 3 1 +.
	 * 
	 * Please note, the "(" and ")" is no longer necessary, because RPN does not
	 * need them :)
	 * 
	 * @return string in "postOrder" (RPN) format.
	 */
	public String printPostOrder() {
		return print(expression.getPostOrderIterator(), " ");
	}
	
	private String print(Iterator<ExpressionElement> it, String separator) {
		StringBuilder sb = new StringBuilder();		
		
		boolean firstRun = true;
		
		while(it.hasNext()) {
			if(!firstRun && separator != null)
				sb.append(separator);
			sb.append(it.next().stringValue());
			firstRun = false;
		}
				
		return sb.toString();
	}

	
}
