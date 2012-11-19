package cvut.fit.dpo.arithmetic.builders;

import java.util.Stack;

import cvut.fit.dpo.arithmetic.AddOperator;
import cvut.fit.dpo.arithmetic.ArithmeticExpression;
import cvut.fit.dpo.arithmetic.BinaryOperator;
import cvut.fit.dpo.arithmetic.NumberOperand;
import cvut.fit.dpo.arithmetic.SubstractOperator;

public class InOrderExpressionBuilder implements ArithmeticExpressionBuilder {
	
	public InOrderExpressionBuilder() {
		this.expressionStack = new Stack<ArithmeticExpression>();
	}
	
	private Stack<ArithmeticExpression> expressionStack;
    private BinaryOperator pendingOperator;
    private int bracketLevel;
	
	@Override
	public void buildNumber(Integer value) {
		ArithmeticExpression expression = new NumberOperand(value);
        appendExpression(expression);
	}  
	
	@Override
	public void buildAddOperation() {
		if (hasPendingOperator())
            throw new IllegalArgumentException("Can't build Add operation, no first operand.");

		pendingOperator = new AddOperator();
	}

	@Override
	public void buildSubstractOperation() {
		if (hasPendingOperator())
            throw new IllegalArgumentException("Can't build Substract operation, no first operand.");

		pendingOperator = new SubstractOperator();
	}

	@Override
	public void buildLeftBracket() {
		if (hasPendingOperator())
            appendBinaryExpression(null);
        bracketLevel++;
	}

	@Override
	public void buildRightBracket() {
		if (bracketLevel == 0)
            throw new IllegalArgumentException("Can't buil right bracket, no left bracket specified.");

        bracketLevel--;

        if (expressionStack.size() > 1)
            mergeBinaryExpression();
	}

	@Override
	public ArithmeticExpression getExpression() {
		if (bracketLevel > 0)
            throw new IllegalArgumentException("Right bracket missing.");

        if (expressionStack.empty())
            throw new IllegalArgumentException("Expression is empty.");

        if (expressionStack.size() > 1)
            throw new IllegalArgumentException("Expression is not complete.");

        return expressionStack.pop();
	}
	


    private void appendExpression(ArithmeticExpression expression) {
        if (hasPendingOperator())
            appendBinaryExpression(expression);
        else
            expressionStack.push(expression);
    }

    private void appendBinaryExpression(ArithmeticExpression secondExpression) {
    	ArithmeticExpression firstExpression = expressionStack.pop();
    	pendingOperator.setFirstOperand(firstExpression);
    	pendingOperator.setSecondOperand(secondExpression);
    	    	
        expressionStack.push(pendingOperator);
        pendingOperator = null;
    }
    
    private void mergeBinaryExpression() {
    	ArithmeticExpression pendingExpression = expressionStack.pop();
    	ArithmeticExpression rootExpression = expressionStack.pop();

        if (!(rootExpression instanceof BinaryOperator))
        	throw new IllegalArgumentException("Temporary expression must be binary.");
    	
        BinaryOperator binaryRootExpression = (BinaryOperator)rootExpression;

        if (binaryRootExpression.getSecondOperand() != null)
            throw new IllegalArgumentException("Temporary expression must have seconf operand empty.");

        expressionStack.push(binaryRootExpression.getFirstOperand());
        pendingOperator = binaryRootExpression;
        appendBinaryExpression(pendingExpression);
    }
    
    
    private boolean hasPendingOperator()
    {
    	return (pendingOperator != null); 
    }

    

}
