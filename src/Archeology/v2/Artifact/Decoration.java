package Archeology.v2.Artifact;

public class Decoration extends PotteryArtifact
{
	private String Description;
	

	/**
	 * @param _date
	 * @param description
	 */
	public Decoration(Integer _date, String _description) {
		super(_date);
		this.setDescription(_description);
	}

	/**
	 * @return the description "Sacrifice","Moon","Sun"
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
	
}
