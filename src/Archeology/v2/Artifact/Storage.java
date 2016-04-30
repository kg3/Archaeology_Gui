package Archeology.v2.Artifact;

public class Storage extends PotteryArtifact
{
	private Double Volume;
	private String storage = "Storage"; 	// correct spelling from file for persisting & reporting
	
	/**
	 * @param _date the date to be entered (Integer), goes to super
	 * @param volume the volume of the vessel (Double)
	 */
	public Storage(Double _volume, Integer _date) {
		super(_date);
		this.setVolume(_volume);
	}

	public Double getVolume()
	{
		return this.Volume;
	}
	
	public void setVolume(Double Volume)
	{
		this.Volume = Volume;
	}
	
	/*
	 * @override toString to Print contents for MapPrintDualArray/Report; date volume
	 */
	public String toString()
	{
		// two spaces to align with Decorated> & Submerged>
		return storage + ">   " + "Date: " + getDate().toString() + " Volume: " + getVolume().toString();
	}
	
	/*
	 * @return String to persist file in csv format
	 */
	public String toPersist()
	{
		return storage + "," + getVolume() + "," + getDate().toString();
	}
}