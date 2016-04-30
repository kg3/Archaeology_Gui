package Archeology.v2.Artifact;

public class NonFerrous extends MetalArtifact
{
	private String MetalType;
	private String nonFerrous = "Non-Ferrous"; 		//spelling correctly according to file format
	
	public NonFerrous(String _metalType, Integer _date)
	{
		super(_date);
		this.setMetalType(_metalType);
	}

	/**
	 * @return the metalType in string format
	 */
	public String getMetalType() {
		return MetalType;
	}

	/**
	 * @param metalType the metalType to set
	 */
	public void setMetalType(String _metalType) {
		MetalType = _metalType;
	}
	
	/*
	 * @override toString to Print contents for MapPrintDualArray/Report; date metal type
	 */
	public String toString()
	{
		return nonFerrous + "> " + "Date: " + getDate().toString() + " Type: " + getMetalType();
	}
	
	/**
	 * @return String for persisting file in csv format
	 */
	public String toPersist()
	{
		return nonFerrous + "," + getMetalType() + "," + getDate();
	}
}