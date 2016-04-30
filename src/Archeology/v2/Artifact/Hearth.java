package Archeology.v2.Artifact;

public class Hearth extends CharcoalArtifact
{
	private Double length;
	private Double height;
	private String hearth = "Hearth"; 	// correct spelling for reporting and persisting
	
	/**
	 * @param _date
	 * @param length
	 * @param height
	 */
	public Hearth(Double _length, Double _height, Integer _date) {
		super(_date);
		this.setLength(_length);
		this.setHeight(_height);
	}
	/**
	 * @return the length
	 */
	public Double getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Double length) {
		this.length = length;
	}
	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	
	/**
	 * @override toString to Print contents for MapPrintDualArray/Report; date meters
	 */
	public String toString()
	{
		return hearth + "> " + "Date: " + getDate().toString() + " Length: " + getLength().toString() + " Height: "  + getHeight().toString();
	}
	
	/*
	 * @return String to persist file in csv format
	 */
	public String toPersist()
	{
		return hearth + "," + getLength().toString() + "," + getHeight().toString() + "," + getDate().toString();
	}
}
