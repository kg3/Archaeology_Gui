package Archeology.v2.Helpers;

public class ConvertIntegerToBase26 
{
	/**
	 * @param number
	 * @return the base26 of 0 to A or 26 as AA
	 */
	public static String Convert(Integer number)
	{
        String converted = "";
        // Repeatedly divide the number by 26 and convert the
        // remainder into the appropriate letter.
        while (number >= 0)
        {
            int remainder = number % 26;
            converted = (char)(remainder + 'A') + converted;
            number = (number / 26) - 1;
        }

        return converted;
    }

}
