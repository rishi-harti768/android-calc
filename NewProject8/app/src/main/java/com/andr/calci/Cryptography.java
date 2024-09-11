package com.andr.calci;

import android.util.Base64;
import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography
{
	private static final IvParameterSpec DEFAULT_IV = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private Key key;
	private IvParameterSpec iv;
	private Cipher cipher;
	
	public Cryptography()
	{
		this.iv = new IvParameterSpec(getHash("MD5", "8421"));
		init();
	}
	private static byte[] getHash(final String algorithm, final String text) {
		try
		{
			return getHash(algorithm, text.getBytes("UTF-8"));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	private static byte[] getHash(final String algorithm, final byte[] data) {
		try {
			final MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.update(data);
			return digest.digest();
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	private void init()
	{
		try
		{
			cipher = Cipher.getInstance(TRANSFORMATION);
		}
		catch(final Exception ex)
		{
			throw new RuntimeException(ex.getMessage());
		}
	}
	public String encrypt(final String str,final String inpkey)
	{
		key = new SecretKeySpec(getHash("SHA-256", inpkey), ALGORITHM);
		try
		{
			String result=encrypt(str.getBytes("UTF-8"));
			return result.trim();
		}
		catch(final Exception ex)
		{
			throw new RuntimeException(ex.getMessage());
		}
	}
	public String encrypt(final byte[] data)
	{
		try
		{
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			final byte[] encryptData = cipher.doFinal(data);
			return new String(Base64.encode(encryptData, Base64.DEFAULT), "UTF-8");
		}
		catch(final Exception ex)
		{
			throw new RuntimeException(ex.getMessage());
		}
	}
	public String decrypt(final String str,final String inpkey)
	{
		key= new SecretKeySpec(getHash("SHA-256", inpkey), ALGORITHM);
		try {
			String result=decrypt(Base64.decode(str, Base64.DEFAULT));
			return result.trim();
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	public String decrypt(final byte[] data) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			final byte[] decryptData = cipher.doFinal(data);
			return new String(decryptData, "UTF-8");
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	public String keyGen(String noKey)
	{
		String result="";
		for(int i=0;i<noKey.length();i++)
		{
			int ch= (int)noKey.charAt(i);
			String app=String.valueOf(ch);
			result=result.concat(app);
		}
		return result.trim();
	}
}