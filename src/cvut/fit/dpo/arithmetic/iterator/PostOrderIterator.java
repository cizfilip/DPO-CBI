package cvut.fit.dpo.arithmetic.iterator;

import java.util.Iterator;
import java.util.LinkedList;

import cvut.fit.dpo.arithmetic.elements.ExpressionElement;

public class PostOrderIterator implements Iterator<ExpressionElement>
{
	private LinkedList<ExpressionElement> elements;
    
    public PostOrderIterator(ExpressionElement element) {
    	elements = new LinkedList<ExpressionElement>();
    	elements.add(element);
    }
    
    public PostOrderIterator(PostOrderIterator a, PostOrderIterator b, ExpressionElement element) {
    	elements = a.elements;
    	elements.addAll(b.elements);
    	elements.add(element);  
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
