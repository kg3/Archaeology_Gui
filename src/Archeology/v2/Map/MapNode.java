/**
 * 
 */
package Archeology.v2.Map;

import java.util.ArrayList;

import Archeology.v2.Artifact.*;
import Archeology.v2.Surface.*;

public class MapNode{
	private Coordinate coordinate;
	
	private Boolean excavated = false;
	private Boolean heritage = false;
	
	private Surface ground;
	private ArtifactBag artifactBag;
	
	/*
	 * @override
	 * Constructor to be used in MapData during read or loading from file
	 */
	public MapNode()
	{
		// EMPTY FOR A REASON, that reason is in Map.java.load!
	}
	
	/**
	 * 
	 * @param _col goes into Coordinate()
	 * @param _row goes into Coordinate()
	 * @param _surface builds surface from Surface.getSurface()
	 * @param _excavated setNewExcavated from load do not send to setExcavated: checks heritage
	 * @param _heritage	boolean
	 * @param _line	After set indexi's the whole line is sent to ArtifactBag
	 */
	public MapNode(String _col, Integer _row, Character _surface, 
			Boolean _excavated, Boolean _heritage, String _line) 
	{	
		setCoordinate(_col,_row);
		setNewExcavated(_excavated);
		setHeritage(_heritage);
	    ground = Surface.getSurface(_surface);
	   /* if(_line == "false")
	    { // from argument Line.substring( (Line.indexOf(',',8) +1)
	    	System.out.printf("\ncol: %s row: %d %s",_col,_row,_line);
	    }*/
		setArtifactBag(new ArtifactBag(_line));
	}
	
	public MapNode(Integer _col, Integer _row, Character _surface, 
			Boolean _excavated, Boolean _heritage, String _line) 
	{	
		setCoordinate(_col,_row);
		setNewExcavated(_excavated);
		setHeritage(_heritage);
	    ground = Surface.getSurface(_surface);
	   /* if(_line == "false")
	    { // from argument Line.substring( (Line.indexOf(',',8) +1)
	    	System.out.printf("\ncol: %s row: %d %s",_col,_row,_line);
	    }*/
		setArtifactBag(new ArtifactBag(_line));
	}
	
	/**
	 * @param _artifactList to pass to ArtifactBag from MAP
	 */
	public void addFinds(ArrayList<Artifact> _artifactList)
	{
		this.artifactBag.addFinds(_artifactList);
	}

	/**
	 * @param _countType 'pottery', 'charcoal', 'metal', returns the count of right list
	 * @return Integer of the size of Artifact List
	 */
	public Integer getArtifactListCount(String _countType)
	{
		return this.artifactBag.getArtifactListCount(_countType);
	}
	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(String _col, Integer _row) {
			this.coordinate = new Coordinate(_col,_row);
	}
	public void setCoordinate(Integer _col, Integer _row) {
		this.coordinate = new Coordinate(_col,_row);
}

	/**
	 * @return the excavated True (dug) False (not dug)
	 */
	public Boolean getExcavated() {
		return excavated;
	}
	
	public void setNewExcavated(Boolean _excavated)
	{
		this.excavated = _excavated;
	}

	/**
	 * @param excavated the boolean to set
	 * @return If Heritage is true, setExcavated will return (false=can't change)
	 */
	public Boolean setExcavated(Boolean _excavated) 
	{
		Boolean _ifNotHeritage = null;
		// The Grid is a heritage, getHeritage() == true; 
		if(this.getHeritage() == true)
		{
			_ifNotHeritage = false;	// not able to change
		}
		// The Grid IS NOT a heritage, getHeritage() == false;
		if(this.getHeritage() == false)
		{
			_ifNotHeritage = true;
			setNewExcavated(_excavated); // able to change & changed!
		}
		return _ifNotHeritage;
	}
	
	/**
	 * @return the heritage
	 */
	public Boolean getHeritage() {
		return heritage;
	}
	/**
	 * @param heritage the heritage to set
	 */
	public void setHeritage(Boolean heritage) {
		this.heritage = heritage;
	}
	/**
	 * @return the ground
	 */
	public Surface getGround() {
		return ground;
	}

	/**
	 * @param ground the ground to set of Surface
	 */
	public void setGround(Surface _ground) {
		this.ground = _ground;
	}
	
	/**
	 * @param _defaultType a character reference to surface type (found from file or established as 'hard-coded')
	 */
	public void setGround(Character _defaultType) {
		this.ground = Surface.getSurface(_defaultType);
	}

	/**
	 * @return the artifactCollection
	 */
	public ArtifactBag getArtifactBag() {
		return artifactBag;
	}

	/**
	 * @param artifactBag the artifactCollection to set
	 */
	public void setArtifactBag(ArtifactBag _artifactCollection) {
		this.artifactBag = _artifactCollection;
	}
	
	/**
	 * @return 2 if Ferr >= 1, 4 if nonFerr >= 1, 6 if nonFerr & Ferr >= 2, 0 no metal
	 */
	public Integer metalDetector()
	{
		return this.artifactBag.metalDetector();
	}
	
	/**
	 * Charcoal Scanner
	 * @return True or False if Charcoal in CharcoalList
	 */
	public Boolean magnetometerScanner()
	{
		return this.artifactBag.magnetometerScanner();
	}
	
	/**
	 * @override toString() used for printing report
	 */
	public String toString()
	{
		return coordinate.toString() + " \t" + getGround().toString() + " \tExcavated: " + getExcavated() + " Heritage: " + getHeritage();
	}

	/**
	 * @return String to be written to file in csv format
	 */
	public String toPersist()
	{
		return coordinate.toPersist() + "," + getGround().toPersist() + "," + getExcavated() + "," + getHeritage();
	}
}
