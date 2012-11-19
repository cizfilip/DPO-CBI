package cvut.fit.dpo.arithmetic.parsers;

import java.util.ArrayList;

import cvut.fit.dpo.arithmetic.ArithmeticExpression;
import cvut.fit.dpo.arithmetic.builders.ArithmeticExpressionBuilder;


public class ArithmeticExpressionParser {
	private enum ExpressionTokenType {
		WHITESPACE, NUMBER, ADD_OPERATION, SUBSTRACT_OPERATION, LEFT_BRACKET, RIGHT_BRACKET
	}

	private char[] text;
	private ArithmeticExpressionBuilder builder;

	private int characterIndex;
	private char currentCharacter;
	private boolean isLastCharacter;
	private boolean isRegularCharacter;

	private ExpressionTokenType currentTokenType;
	private ExpressionTokenType lastTokenType = ExpressionTokenType.WHITESPACE;
	private boolean hasTokenTypeChanged;

	private StringBuilder currentToken = new StringBuilder();

	private ArrayList<String> tokenItems = new ArrayList<String>();
	private ArrayList<ExpressionTokenType> tokenTypes = new ArrayList<ExpressionTokenType>();

	public ArithmeticExpressionParser(String text,
			ArithmeticExpressionBuilder builder) {
		this.text = text.toCharArray();
		this.builder = builder;
	}

	public ArithmeticExpression parse() {
		TokenizeText();
		ProcessTokens();
		return builder.getExpression();
	}

	
	private IllegalArgumentException prepareInvalidCharacterException(
			char currentCharacter) {
		throw new IllegalArgumentException(String.format(
				"Invalid character \"%s\".", currentCharacter));
	}

	private ExpressionTokenType getTokenType() {
		if (Character.isWhitespace(currentCharacter))
			return ExpressionTokenType.WHITESPACE;

		if (Character.isDigit(currentCharacter))
			return ExpressionTokenType.NUMBER;

		if (currentCharacter == '(')
			return ExpressionTokenType.LEFT_BRACKET;

		if (currentCharacter == ')')
			return ExpressionTokenType.RIGHT_BRACKET;

		if (currentCharacter == '+')
			return ExpressionTokenType.ADD_OPERATION;

		if (currentCharacter == '-')
			return ExpressionTokenType.SUBSTRACT_OPERATION;

		throw prepareInvalidCharacterException(currentCharacter);
	}

	private void lookupCurrentCharacter() {
		currentCharacter = text[characterIndex];
		currentTokenType = getTokenType();

		if (lastTokenType != ExpressionTokenType.WHITESPACE) {
			if ((lastTokenType == ExpressionTokenType.LEFT_BRACKET)
					|| (lastTokenType == ExpressionTokenType.RIGHT_BRACKET))
				hasTokenTypeChanged = true;
			else
				hasTokenTypeChanged = (currentTokenType != lastTokenType);
		}

		isLastCharacter = ((characterIndex + 1) == text.length);
		isRegularCharacter = (currentTokenType != ExpressionTokenType.WHITESPACE);
	}

	private void storeCurrentCharacter() {
		if (currentTokenType != ExpressionTokenType.WHITESPACE)
			currentToken.append(currentCharacter);

		lastTokenType = currentTokenType;
	}

	private void storeCompleteToken() {
		tokenItems.add(currentToken.toString());
		tokenTypes.add(lastTokenType);

		currentToken.setLength(0);

		hasTokenTypeChanged = false;
	}

	private void TokenizeText() {
		for (characterIndex = 0; characterIndex < text.length; characterIndex++) {
			lookupCurrentCharacter();

			if (hasTokenTypeChanged)
				storeCompleteToken();

			storeCurrentCharacter();

			if (isLastCharacter && isRegularCharacter)
				storeCompleteToken();
		}
	}

	private void ProcessTokens() {
		for (int tokenIndex = 0; tokenIndex < tokenItems.size(); tokenIndex++) {
			String tokenText = tokenItems.get(tokenIndex);
			ExpressionTokenType tokenType = tokenTypes.get(tokenIndex);

			switch (tokenType) {
			case NUMBER:
				Integer value = Integer.parseInt(tokenText);
				builder.buildNumber(value);
				break;
			case LEFT_BRACKET:
				builder.buildLeftBracket();
				break;
			case RIGHT_BRACKET:
				builder.buildRightBracket();
				break;
			case ADD_OPERATION:
				builder.buildAddOperation();
				break;
			case SUBSTRACT_OPERATION:
				builder.buildSubstractOperation();
				break;
			case WHITESPACE:
				break;
			}
		}
	}

}
