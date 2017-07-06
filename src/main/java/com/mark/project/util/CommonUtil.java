package com.mark.project.util;

/**
 * Created by Administrator on 2017/6/16.
 */
public class CommonUtil {

	private CommonUtil() {}

	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str);
	}

	public int byteToInt(byte b) {
		//Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
		return b & 0xFF;
	}

	/**
	 * Unicode转字符串
	 * @param asciicode
	 * @return
	 */
	public static String unicode2String(String asciicode) {

		String[] asciis = asciicode.split ("\\\\u");
		StringBuffer nativeValue = new StringBuffer();
		try
		{
			for ( int i = 1; i < asciis.length; i++ )
			{
				String code = asciis[i];
				nativeValue.append((char) Integer.parseInt (code.substring (0, 4), 16));
				if (code.length () > 4)
				{
					nativeValue.append(code.substring (4, code.length ()));
				}
			}
		}
		catch (NumberFormatException e)
		{
			return asciicode;
		}
		return nativeValue.toString();
	}

}
