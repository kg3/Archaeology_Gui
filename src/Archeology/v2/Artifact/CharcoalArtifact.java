/**
 * 
 */
package Archeology.v2.Artifact;

/**
 * @author ronin
 *Charcoal deposits have two subclasses, either a kiln for making goods, or a hearth for cooking food.
 *	 Kilns are a circular feature, will have a data member that is the radius in meters.
 *	Hearths are a rectangular feature, and will have two data members to describe its length and width in meters.
 */
public class CharcoalArtifact extends Artifact {

	/**
	 * @param _date
	 */
	public CharcoalArtifact(Integer _date) {
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
