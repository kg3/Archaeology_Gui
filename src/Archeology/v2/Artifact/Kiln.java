package Archeology.v2.Artifact;

public class Kiln extends CharcoalArtifact
{
	private Double meters;
	private String kiln = "Kiln"; 		// correct spelling for reporting and persisting

	/**
	 * @param meters radius of Kiln (Double)
	 */
	public Kiln(Double _meters, Integer _date) {
		super(_date);
		this.meters = _meters;
	}

	/**
	 * @return the meters
	 */
	public Double getMeters() {
		return meters;
	}

	/**
	 * @param meters the meters to set
	 */
	public void setMeters(Double meters) {
		this.meters = meters;
	}
	
	/**
	 * @override toString to Print contents for MapPrintDualArray/Report; date meters
	 */
	public String toString()
	{
		return kiln + "> \t" + "Date: " + getDate().toString() + " Radius: " + getMeters().toString();
	}
	
	/*
	 * @return String to persist file in csv format
	 */
	public String toPersist()
	{
		return kiln + "," + getMeters().toString() + "," + getDate().toString();
	}
}