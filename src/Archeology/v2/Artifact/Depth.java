package Archeology.v2.Artifact;

public class Depth extends PotteryArtifact
{
	private Integer Depth;
	/**
	 * @param _date
	 * @param depth
	 */
	public Depth(Integer _date, Integer _depth) {
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
	 * @param depth the depth to set In many cultures, pottery 
	 * and other artifacts are an offering in water‐filled environments 
	 * (Fens, Cenotes, etc)
	 */
	public void setDepth(Integer depth) {
		this.Depth = depth;
	}
	
}