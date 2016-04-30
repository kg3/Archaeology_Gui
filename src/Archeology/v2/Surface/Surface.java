package Archeology.v2.Surface;

public class Surface {
	private Character SurfaceType;
	private Character Excavated;
	private Character UnExcavated;
	private Character symbUnExcavated;
	private Character symbExcavated;
	
	private static Surface _surfaceInstance;
	
	public Surface(Character _surfaceType, Character _excavated, Character _unexcavated, 
				   Character _symbunexcavated, Character _symbexcavated)
    {
		this.setSurfaceType(_surfaceType);
		this.setExcavated(_excavated);
		this.setUnExcavated(_unexcavated);
		this.setSymbUnExcavated(_symbunexcavated);
		this.setSymbExcavated(_symbexcavated);
    }
	
	/*
	 * @param _surface a character referencing hard-coded value from file
	 * @return Surface returns a type of Surface Class based on Character
	 */
	public static Surface getSurface(Character _surface)
	{	// automatically check what goes inside ArrayList for MapNode
		// return the correct Surface
		if(Stone.isStone(_surface))
	    {
	    	_surfaceInstance = new Stone();
	    }
		if(Pit.isPit(_surface))
	    {
			_surfaceInstance = new Pit();
	    }
		if(Natural.isNatural(_surface))
	    {
			_surfaceInstance = new Natural();
	    }
		return _surfaceInstance;
	}
	
	public Surface getSurface()
	{ // may not need 
		return _surfaceInstance;
	}
	/**
	 * @return the _surfaceType
	 */
	public Character getSurfaceType() {
		return SurfaceType;
	}
	/**
	 * @param _surfaceType the default to set
	 */
	public void setSurfaceType(Character _surfaceType) {
		SurfaceType = _surfaceType;
	}
	/**
	 * @return the unExcavated
	 */
	public Character getUnExcavated() {
		return UnExcavated;
	}
	/**
	 * @return the excavated
	 */
	public Character getExcavated() {
		return Excavated;
	}
	/**
	 * @param excavated the excavated to set
	 */
	public void setExcavated(Character _excavated) {
		Excavated = _excavated;
	}
	/**
	 * @param unExcavated the unExcavated to set
	 */
	public void setUnExcavated(Character _unExcavated) {
		UnExcavated = _unExcavated;
	}
	/**
	 * @return the symbUnExcavated
	 */
	public Character getSymbUnExcavated() {
		return symbUnExcavated;
	}
	/**
	 * @param symbUnExcavated the symbUnExcavated to set
	 */
	public void setSymbUnExcavated(Character _symbUnExcavated) {
		this.symbUnExcavated = _symbUnExcavated;
	}
	/**
	 * @return the symbExcavated
	 */
	public Character getSymbExcavated() {
		return symbExcavated;
	}
	/**
	 * @param symbExcavated the symbExcavated to set
	 */
	public void setSymbExcavated(Character _symbExcavated) {
		this.symbExcavated = _symbExcavated;
	}
	
	/**
	 * @override toString()
	 */
	public String toString()
	{
		return this.toString();
	}
	
	/*
	 * @return String, for persist file in csv format
	 */
	public String toPersist()
	{
		return this.toPersist();
	}

}
