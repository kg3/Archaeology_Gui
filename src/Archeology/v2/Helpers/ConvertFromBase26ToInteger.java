
package Archeology.v2.Helpers;

public class ConvertFromBase26ToInteger
{
	/**
	 * @param col_name
	 * @return the Integer of A or ZZ
	 */
	public static Integer Convert(String col_name) 
	{
	    col_name = col_name.trim();

	    StringBuffer buff = new StringBuffer(col_name);

	    char chars[] = buff.reverse().toString().toLowerCase().toCharArray();

	    Integer retVal=0, multiplier=0;

	    for(int i = 0; i < chars.length; i++)
	    {
	        multiplier = (int)chars[i]-96;
	        retVal += (int)(multiplier * Math.pow(26, i));
	    }
	 	    return retVal-1;
	}

}
