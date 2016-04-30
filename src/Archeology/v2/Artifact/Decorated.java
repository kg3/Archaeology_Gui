package Archeology.v2.Artifact;

public class Decorated extends PotteryArtifact
{
	private String Description;
	private String decorated = "Decorated"; 	// correct spelling for saving and reporting

	/**
	 * @param _date the date to be entered (Integer), goes to super
	 * @param description the decoration of the pottery
	 */
	public Decorated(String _description, Integer _date) {
		super(_date);
		this.setDescription(_description);
	}

	/**
	 * @return the description "Sacrifice","Moon","Sun", etc.
	 */
	public String getDescription() {
		return this.Description;
	}

	/**
	 * @param desctiption the description to set
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	/*
	 * @override toString to Print contents for MapPrintDualArray/Report; date description
	 */
	public String toString()
	{
		return decorated + "> " + "Date: " + getDate().toString() + " Description: " + getDescription();
	}
	
	/*
	 * @return String to persist file in csv format
	 */
	public String toPersist()
	{
		return decorated + "," + getDescription() + "," + getDate().toString();
	}
}
