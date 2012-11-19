package cvut.fit.dpo.arithmetic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AddOperatorTest
{
	NumberOperand o1;
	NumberOperand o2;
	
	@Before
	public void setUp(){
		o1 = new NumberOperand(1);
		o2 = new NumberOperand(2);
	}
	
	@Test
	public void testGetFirstOperand()
	{
		AddOperator o = new AddOperator(o1, o2);
		assertEquals(o1, o.getFirstOperand());
	}

	@Test
	public void testGetSecondOperand()
	{
		AddOperator o = new AddOperator(o1, o2);
		assertEquals(o2, o.getSecondOperand());
	}

}
