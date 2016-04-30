package Archeology.v2.Surface;
/**
 * 
 * @author ronin
 *
File Symbol, Stone = S
File Symbol, Pit = P
File Symbol, Natural=N
Map Symbol, Stone, Excavated = R
Map Symbol, Pit, Excavated = H
Map Symbol, Natural, Excavated = D
Map Symbol, Stone, Unexcavated = Y
Map Symbol, Pit, Unexcavated = G
Map Symbol, Natural, Unexcavated = g
*/

public class Stone extends Surface {
	
	private static Character Default = 'S';
	
	public Stone()
	{
		super(Default,'R','Y','#','$');
	}
	
	public Stone(Character _symbUnExcavated, Character _symbExcavated)
	{
		super(Default,'R','Y',_symbUnExcavated,_symbExcavated);
	}
	
	public static Boolean isStone(Character _type) {
		return (_type == Default);
	}
	
	/**
	 * @return the default
	 */
	public static Character getDefault() {
		return Default;
	}

	/**
	 * @param default1 the default to set
	 */
	public static void setDefault(Character _default) {
		Default = _default;
	}
	
	/**
	 * @override
	 */
	public String toString()
	{
		return "Feature: " + "Stone " + "[" + getDefault().toString() + "]";
	}
	
	/*
	 * @return String, for persist file in csv format
	 */
	public String toPersist()
	{
		return getDefault().toString();
	}
}
