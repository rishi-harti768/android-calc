package com.andr.calci;

import java.math.*;

public class arith
{
	public String quo(String s1,String s2)
	{
		BigDecimal n1= new BigDecimal(s1);
		BigDecimal n2= new BigDecimal(s2);
		return n1.divide(n2, MathContext.DECIMAL128).toString();
	}
	public String pro(String s1,String s2)
	{
		BigDecimal n1= new BigDecimal(s1);
		BigDecimal n2= new BigDecimal(s2);
		return n1.multiply(n2, MathContext.DECIMAL128).toString();
	}
	public String add(String s1,String s2)
	{
		BigDecimal n1= new BigDecimal(s1);
		BigDecimal n2= new BigDecimal(s2);
		return n1.add(n2, MathContext.DECIMAL64).toString();
	}
	public String sub(String s1,String s2)
	{
		BigDecimal n1= new BigDecimal(s1);
		BigDecimal n2= new BigDecimal(s2);
		return n1.subtract(n2, MathContext.DECIMAL64).toString();
	}
}