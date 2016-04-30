/**
 * 
 */
package Archeology.v2.Artifact;

/**
 * @author ronin
 *
 */
public abstract class Artifact {
	
	private Integer date;		
	
	/*
	 * @override Artifact
	 */
	public Artifact()
	{
		super();
		this.getDate();
	}
	
	/**
	 * @param date in phase1 the date set the whole artifact
	 */
	public Artifact(Integer _date) {
		this.setDate(_date);
	}
	
	public Integer getDate()
	{
		return this.date;
	}
	
	public void setDate(Integer _date)
	{
		this.date = _date;
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
