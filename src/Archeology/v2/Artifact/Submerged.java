package Archeology.v2.Artifact;

public class Submerged extends PotteryArtifact
{
	private Integer Depth;
	private String submerged = "Submerged"; 	// correct spelling for persisting and reporting
	
	/**
	 * @param _date the date to be entered (Integer), goes to super
	 * @param depth in meters (Integer)
	 */
	public Submerged(Integer _depth, Integer _date) {
		super(_date);
		this.setDepth(_depth);
	}

	/**
	 * @return the depth
	 */
	public Integer getDepth() {
		return this.Depth;
	}

	/**
	 * @param depth the depth to set 
	 */
	public void setDepth(Integer depth) {
		this.Depth = depth;
	}
	
	/*
	 * @override toString to Print contents for MapPrintDualArray/Report; date depth
	 */
	public String toString()
	{
		return submerged + "> " + "Date: " + getDate().toString() + " Depth: " + getDepth().toString();
	}
	
	/*
	 * @return String to persist file in csv format
	 */
	public String toPersist()
	{
		return submerged + "," + getDepth() + "," + getDate().toString();
	}
}