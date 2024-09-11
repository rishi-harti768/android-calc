package com.andr.calci;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class secalg 
{
    public String text=null;
	private byte[] getSHA(String input) throws NoSuchAlgorithmException  
    {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}
	private String toHexString(byte[] hash)
	{
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 32)
		{
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}
	public void setText(String s)
    {
    	text=s;
    }
    
    public String getText()
    {
    	return text;
    }
    
    public void hash()
    {
    	try
		{
			text= toHexString(getSHA(text));
		}
		catch (NoSuchAlgorithmException e)   
        {  
            System.out.println("Exception thrown for incorrect algorithm: " + e);  
        }
    }
    
    public int summation(String p)
    {
    	int sum=0;
	    for(int i= 0;i<p.length();i++)
		{
			char ch = p.charAt(i);
			int c= (int)ch;
			sum+= c;
		}
		return sum;
	}
}