package cvut.fit.dpo.arithmetic.iterator;

import java.util.Iterator;
import java.util.LinkedList;

import cvut.fit.dpo.arithmetic.elements.BinaryExpressionElement;
import cvut.fit.dpo.arithmetic.elements.CloseBracketOperation;
import cvut.fit.dpo.arithmetic.elements.ExpressionElement;
import cvut.fit.dpo.arithmetic.elements.OpenBracketOperation;

public class InOrderIterator implements Iterator<ExpressionElement>
{
	private LinkedList<ExpressionElement> elements;
    
    public InOrderIterator(ExpressionElement element) {
    	elements = new LinkedList<ExpressionElement>();
    	elements.add(element);
    }
    
    public InOrderIterator(InOrderIterator firstIterator, InOrderIterator secondIterator, ExpressionElement element) {
    	addElement(firstIterator, secondIterator, element);
    }
    
    public InOrderIterator(InOrderIterator a, InOrderIterator b, BinaryExpressionElement element) {
    	addElement(a, b, element);
    	elements.addFirst(new OpenBracketOperation());
    	elements.addLast(new CloseBracketOperation());
    }
    
    private void addElement(InOrderIterator a, InOrderIterator b, ExpressionElement element) {
    	elements = a.elements;
    	elements.add(element);  
    	elements.addAll(b.elements);
    }
    
	@Override
	public boolean hasNext()
	{
		return !elements.isEmpty();
	}

	@Override
	public ExpressionElement next()
	{
		return elements.removeFirst();
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("Remove is not suported");
	}


}
