package cvut.fit.dpo.arithmetic.builders;

import java.util.Stack;

import cvut.fit.dpo.arithmetic.AddOperator;
import cvut.fit.dpo.arithmetic.ArithmeticExpression;
import cvut.fit.dpo.arithmetic.BinaryOperator;
import cvut.fit.dpo.arithmetic.NumberOperand;
import cvut.fit.dpo.arithmetic.SubstractOperator;

public class PostOrderExpressionBuilder implements ArithmeticExpressionBuilder {

	public PostOrderExpressionBuilder() {
		this.expressionStack = new Stack<ArithmeticExpression>();
	}
	
	private Stack<ArithmeticExpression> expressionStack;

	
	@Override
	public void buildNumber(Integer value) {
		expressionStack.push(new NumberOperand(value));
	}

	@Override
	public void buildAddOperation() {
		appendBinaryExpression(new AddOperator());
	}

	@Override
	public void buildSubstractOperation() {
		appendBinaryExpression(new SubstractOperator());
	}

	@Override
	public void buildLeftBracket() {
		throwBracketException();
	}

	@Override
	public void buildRightBracket() {
		throwBracketException();
	}

	@Override
	public ArithmeticExpression getExpression() {
        if (expressionStack.empty())
            throw new IllegalArgumentException("Expression is empty.");

        if (expressionStack.size() > 1)
            throw new IllegalArgumentException("Expression is not complete.");

        return expressionStack.pop();
	}

	
	private void appendBinaryExpression(BinaryOperator expression) {
		throwIfNoOperands();
		
		ArithmeticExpression secondOperand = expressionStack.pop();
		ArithmeticExpression firstOperand = expressionStack.pop();
		
		expression.setFirstOperand(firstOperand);
		expression.setSecondOperand(secondOperand);
		
		expressionStack.push(expression);
	}
	
	private void throwIfNoOperands() {
		if (expressionStack.size() < 2)
			throw new IllegalStateException("No operands for operation");
	}
	
	private void throwBracketException() {
		throw new UnsupportedOperationException("Cannot build brackets in RPN notation");
	}
	
}
