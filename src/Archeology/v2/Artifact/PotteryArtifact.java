/**
 * 
 */
package Archeology.v2.Artifact;

/**
 * @author ronin
 *Pottery will be able to have three subclasses: Storage, Decorated, or Submerged.
 *	Storage Pottery has a double field that indicates the volume of the vessel.
 *	Decorated pottery has a String data member that will contain one word to describe the pottery.
 *		Example: “Sacrifice”,”Moon”,”Sun”.
 *  Submerged pottery has an integer field to represent depth in meters. In many cultures, pottery and
 *  other artifacts are an offering in water‐filled environments (Fens, Cenotes, etc)
 */

public class PotteryArtifact extends Artifact{
	
	/**
	 * @param _date the date to be entered (Integer), goes to super
	 */
	public PotteryArtifact(Integer _date) {
		super(_date);
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
