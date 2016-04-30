/**
 * 
 */
package Archeology.v2.Artifact;


/**
 * @author ronin
 *  Metalwork deposits will have two subclasses, either ferrous (iron based) or non‐ferrous.
 *  	Ferrous objects will have an integer signal strength indicator, the stronger the indicator, the larger the iron object.
 *  	Non‐ferrous objects will have a String data field to indicate what the different metals are: Copper, Bronze, or Gold.
 *
 */
public class MetalArtifact extends Artifact{

	/**
	 * @param _date
	 */
	public MetalArtifact(Integer _date) {
		super(_date);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @override printing for reporting or debugging
	 */
	public String toString()
	{
		return this.toString();
	}
	
	/*
	 * @returns String for persisting file in csv format
	 */
	public String toPersist()
	{
		return this.toPersist();
	}
}
