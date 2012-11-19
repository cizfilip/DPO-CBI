package cvut.fit.dpo.arithmetic.builders;

import cvut.fit.dpo.arithmetic.ArithmeticExpression;

public interface ArithmeticExpressionBuilder {
	void buildNumber(Integer value);
    
    void buildAddOperation();
    void buildSubstractOperation();

    void buildLeftBracket();
    void buildRightBracket();

    ArithmeticExpression getExpression();
}
