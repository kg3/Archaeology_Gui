package Archeology.v2.Artifact;

public class Ferrous extends MetalArtifact
{
	private Integer Strength;
	private String ferrous = "Ferrous"; 	// correct spelling for reporting and persisting
	
	public Ferrous(Integer _strength, Integer _date)
	{
		super(_date);
		this.setStrength(_strength);
	}
	
	/**
	 * @return the strength
	 */
	public Integer getStrength() {
		return Strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(Integer _strength) {
		Strength = _strength;
	}
	
	/*
	 * @override toString to Print contents for MapPrintDualArray/Report; date strength
	 */
	public String toString()
	{
		// 4-spaces to align with Non-Ferrous>
		return ferrous + ">     " + "Date: " + getDate().toString() + " Strength: " + getStrength().toString();
	}
	
	/*
	 * @return String to persist file in csv format
	 */
	public String toPersist()
	{
		return ferrous + "," + getStrength().toString() + "," + getDate().toString();
	}
}