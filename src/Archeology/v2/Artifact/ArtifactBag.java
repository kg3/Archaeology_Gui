package Archeology.v2.Artifact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * @author ronin
 *
 */
public class ArtifactBag {

	private ArrayList<Artifact> PotteryList = new ArrayList<Artifact>();
	private ArrayList<Artifact> CharcoalList = new ArrayList<Artifact>();
	private ArrayList<Artifact> MetalList = new ArrayList<Artifact>();

	/**
	 * The Logic to automatically create a pottery, charcoal,
	 * or metal list. Using For loops for infinite values in file
	 * @param _line from Map.Load() method; after heritage value
	 */
	public ArtifactBag(String _line)
	{	// previous index, where last for loop left
		Integer indexPosition=0,ti=0;	// ti = tempIndex, smaller for ease of use
		Integer intPotteryCount=0, intCharcoalCount=0, intMetalCount=0;
		Integer twoFields=3, threeFields=4;
		String type;
		String [] tokens = _line.split(",");
		
		// Pottery index is always 0
		intPotteryCount = Integer.valueOf(tokens[indexPosition]);
		// Pottery Handler
		if(intPotteryCount > 0)
		{	//Ignoring list if total is 0
			// 1 will always be the next index after Pottery Count
			indexPosition += 1;
			for(int i=0; i < intPotteryCount; i++)
			{	// create new artifact instance (doesn't work as an abstract)
				// append new instance to array
				PotteryArtifact tempPottery = null;
				//indexPosition += i+1;
				ti = indexPosition;
				type = tokens[indexPosition];
				switch(type)
				{
				case "Storage":
					tempPottery = new Storage( Double.valueOf(tokens[ti+1]),
							Integer.valueOf(tokens[ti+2]) );
					indexPosition += twoFields;
					break;
				case "Decorated":
					tempPottery = new Decorated(tokens[ti+1],
							Integer.valueOf(tokens[ti+2]));
					indexPosition += twoFields;
					break;
					
				case "Submerged":
					tempPottery = new Submerged(Integer.valueOf(tokens[ti+1]),
							Integer.valueOf(tokens[ti+2]));
					indexPosition += twoFields;
					break;
				}
				// append new instance to list
				PotteryList.add(tempPottery);
			}
		}
		else
		{
			indexPosition += 1;
		}
		
		intCharcoalCount = Integer.valueOf(tokens[indexPosition]);
		//Charcoal Handler 
		if(intCharcoalCount > 0)
		{
			indexPosition += 1;
			for(int i=0; i < intCharcoalCount; i++)
			{
				CharcoalArtifact tempCharcoal = null;
				ti = indexPosition;
				type = tokens[indexPosition];
				switch(type)
				{
				case "Kiln":
					tempCharcoal = new Kiln(Double.valueOf(tokens[ti+1]), 
							Integer.valueOf(tokens[ti+2])); 
					indexPosition += twoFields;
					break;
				case "Hearth":
					tempCharcoal = new Hearth(Double.valueOf(tokens[ti+1]),
							Double.valueOf(tokens[ti+2]), Integer.valueOf(tokens[ti+3]));
					indexPosition += threeFields;
					break;
				}
				CharcoalList.add(tempCharcoal);
			}
		}
		else 
		{
			indexPosition += 1;
		}
	
		intMetalCount = Integer.valueOf(tokens[indexPosition]);
		//Metal Handler
		if(intMetalCount > 0)
		{
			indexPosition += 1;
			for(int i=0; i < intMetalCount; i++)
			{
				MetalArtifact tempMetal = null;
				ti = indexPosition;
				type = tokens[indexPosition];
				switch(type)
				{
				case "Non-Ferrous":
					tempMetal = new NonFerrous(tokens[ti+1],Integer.valueOf(tokens[ti+2]));
					indexPosition += twoFields;
					break;
				case "Ferrous":
					tempMetal = new Ferrous(Integer.valueOf(tokens[ti+1]),
							Integer.valueOf(tokens[ti+2]));
					indexPosition += twoFields;
					break;
				}
				MetalList.add(tempMetal);
			}
		}
	}
	
	/**
	 * Sort every ArtifactList by their Date.
	 * Useful to call before printing report.
	 */
	public void sortWholeBagByDate()
	{
		Collections.sort(getPotteryList(), new DateComparator() );
		Collections.sort(getCharcoalList(), new DateComparator() );
		Collections.sort(getMetalList(), new DateComparator() );
	}
	
	/**
	 * Custom Date Comparator.  Sort a class List by Date.
	 * Copy code and replace with any element from any type of class
	 * to sort by a different element.
	 *
	 */
	private class DateComparator implements Comparator<Artifact> 
	{
		@Override
		public int compare(Artifact artifact1, Artifact artifact2) 
		{
			Integer date1 = artifact1.getDate();
			Integer date2 = artifact2.getDate();
			//DESCENDING ORDER
			return date2.compareTo(date1);
			//ASCENDING ORDER
			//return date1.compareTo(date2);
		}
	}
	
	/**
	 * get list count which is actually the size of the list
	 * @param _artifactName : 'pottery', 'charcoal', 'metal'
	 * @return the list size of selected ArtifactList
	 */
	public Integer getArtifactListCount(String _artifactName)
	{
		Integer _count = 0;
		if (_artifactName == "pottery")
		{
			_count = PotteryList.size();
		}
		else
		{
			if(_artifactName == "charcoal")
			{
				_count = CharcoalList.size();
			}
			
			else
			{
				if (_artifactName == "metal")
				{
					_count = MetalList.size();
				}
				else
				{
					System.out.printf("\nSHOULDN't BE HERE From getArtifactListCount; last else\n");
				}
			}
		}
		return _count;
	}
	
	public Double averageDateOfBag()
	{
		Integer _dateTotal = 0;
		Double _averageDate = 0.0;
		
		_dateTotal += dateSummation("pottery");
		_dateTotal += dateSummation("charcoal");
		_dateTotal += dateSummation("metal");
		
		if(this.wholeBagCount() != 0)
		{
			_averageDate =  (double) (_dateTotal / this.wholeBagCount());
		}
		
		return _averageDate;
		
	}
	
	/**
	 * Get the summation of the dates from a certain type of list.
	 * Setup to be altered for any other summation of Artifact
	 * @param _artifactName "pottery", "charcoal", "metal"
	 * @return the summation of all dates in the list
	 */
	public Integer dateSummation(String _artifactName)
	{
		Integer _summation = 0;
		if (_artifactName == "pottery")
		{
			for(Artifact pottery: getPotteryList())
			{
				_summation += ((PotteryArtifact)pottery).getDate();
			}
		}
		else
		{
			if(_artifactName == "charcoal")
			{
				for(Artifact charcoal: getCharcoalList())
				{
					_summation += ((CharcoalArtifact)charcoal).getDate();
				}
			}
			
			else
			{
				if (_artifactName == "metal")
				{
					for(Artifact metal: getMetalList())
					{
						_summation += ((MetalArtifact)metal).getDate();
					}
				}
				else
				{
					System.out.printf("\nSHOULDN't BE HERE From ArtifactBag averageDate; last else\n");
				}
			}
		}
		return _summation;
	}
	
	/**
	 * The whole count of every find. For reporting only whole finds, not zeros.
	 * @return the amount of all finds in bag
	 */
	public Integer wholeBagCount()
	{
		return this.getArtifactListCount("pottery") + this.getArtifactListCount("charcoal") + this.getArtifactListCount("metal");
	}
	/**
	 * Intelligence for Appending list of Finds
	 * @param _artifactList appends the correct list based on instance of Artifact in _artifactList
	 */
	public void addFinds(ArrayList<Artifact> _artifactList)
	{
		ArrayList<Artifact> tempArtifactList = null;
		//Determine the correct class of item from _artifactList
		// to add to the correct type of list
		if (_artifactList.get(0) instanceof PotteryArtifact)
		{
			tempArtifactList = getPotteryList();
		}
		else
		{
			if(_artifactList.get(0) instanceof CharcoalArtifact)
			{
				tempArtifactList = getCharcoalList();
			}
			
			else
			{
				if (_artifactList.get(0) instanceof MetalArtifact)
				{
					tempArtifactList = getMetalList();
				}
				else
				{
					System.out.printf("\nSHOULDN't BE HERE From addFinds; last else\n");
				}
			}
		}
		
		for(Artifact addMe: _artifactList)
		{/*	Appending and adding tempList to potteryList or charcoalList etc.
		  * Clear() is not needed, because of pass-by-reference
		  * 
		  * Secondly, setCount is not needed, getCount of any type returns
		  * the size of that particular arrayList,
		  * hence no need to update the count
		  */
			tempArtifactList.add(addMe);
		}

	}
	
	/**
	 * For Magnetometer Scanning
	 * (leave here in-case of expansion)
	 * @return size of Charcoal list 
	 */
	private Integer charcoalCounter()
	{
		return this.getArtifactListCount("charcoal");
	}
	
	/**
	 * @return 2 if FerrCount >=1, 4 if NFerr >= 1, 6 if NonFerr & Ferr >= 2, 0 no metal
	 */
	private Integer respondToMetalDetector()
	{
		/*
		 * Ferrous.count >= 1 		:: return 2
		 * Non-Ferrous.count >= 1 	:: return 4
		 * Ferrous + NonFerrous		:: return 6
		 */
		Integer response = 0;
		Integer ferrousCount = ferrousCounter();
		Integer nonFerrousCount = nonFerrousCounter();
		if(this.getArtifactListCount("metal") == 0)
		{
			response = 0;
		}
		else
		{
			if(ferrousCount >= 1 && nonFerrousCount == 0)
			{
				response = 2;
			}
			else
			{
				if(nonFerrousCount >= 1 && ferrousCount == 0)
				{
					response = 4;
				}
				else
				{
					if (nonFerrousCount >=1 && ferrousCount >= 1)
					{
						response = 6;
					}
				}
			}
		}
		return response;
	}
	
	/**
	 * For the Scanning Tools
	 * @return the amount of Metal Type FERROUS in MetalList
	 */
	private Integer ferrousCounter()
	{
		Integer counter = 0;
		for(Artifact metal: this.MetalList)
		{
			if(metal instanceof Ferrous)
			{
				counter += 1;
			}
		}
		return counter;
	}
	
	/**
	 * For the Scanning Tools
	 * @return the amount of Metal Type NONFERROUS in MetalList
	 */
	private Integer nonFerrousCounter()
	{
		Integer counter = 0;
		for(Artifact metal: this.MetalList)
		{
			if(metal instanceof NonFerrous)
			{
				counter += 1;
			}
		}
		return counter;
	}
	
	/**
	 * @return 2 if Ferr >= 1, 4 if nonFerr >= 1, 6 if nonFerr & Ferr >= 2, 0 no metal
	 */
	public Integer metalDetector()
	{
		return respondToMetalDetector();
	}
	
	/**
	 * @return True or False if Charcoal in CharcoalList (from charcoalCounter)
	 */
	public Boolean magnetometerScanner()
	{
		if(charcoalCounter() > 0)
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Part of the Gold checking method, return the amount of gold in Bag
	 * @return Integer count of gold
	 */
	public Integer goldCount()
	{
		Integer goldCount = 0;
		for(Artifact metal: MetalList)
		{
			if(metal instanceof NonFerrous)
			{
				if( ((NonFerrous)metal).getMetalType().equals("Gold") )
				{
					goldCount += 1;
				}
			}
		}
		return goldCount;
	}
	
	/**
	 * Part of the Gold checking Method, deletes the gold metal type from the list
	 * yes, Java moves the indicies to the left
	 */
	public void removeGold()
	{
		for(int i=0; i < getMetalList().size(); i++)
		{
			if(getMetalList().get(i) instanceof NonFerrous )
			{
				if( ((NonFerrous)getMetalList().get(i)).getMetalType().equals("Gold") )
				{
					getMetalList().remove(i);
				}
			}
		}
	}

	/**
	 * @return the potteryList
	 */
	public ArrayList<Artifact> getPotteryList() {
		return PotteryList;
	}

	/**
	 * @param potteryList the potteryList to set
	 */
	public void setPotteryList(ArrayList<Artifact> _potteryList) {
		PotteryList = _potteryList;
	}

	/**
	 * @return the charcoalList
	 */
	public ArrayList<Artifact> getCharcoalList() {
		return CharcoalList;
	}

	/**
	 * @param charcoalList the charcoalList to set
	 */
	public void setCharcoalList(ArrayList<Artifact> charcoalList) {
		CharcoalList = charcoalList;
	}

	/**
	 * @return the metalList
	 */
	public ArrayList<Artifact> getMetalList() {
		return MetalList;
	}

	/**
	 * @param metalList the metalList to set
	 */
	public void setMetalList(ArrayList<Artifact> metalList) {
		MetalList = metalList;
	}
	
	/**
	 * Use this one for Reporting, includes averageDates
	 * @override toString used for debugging
	 */
	public String toReport()
	{
		StringBuffer _out = new StringBuffer();
		//Sort all Dates
		this.sortWholeBagByDate();
		
		//Print Sorted Contents
		_out.append("\n\t" + "Pottery(s): "+ this.getArtifactListCount("pottery") );
		for(Artifact _pottery: this.PotteryList)
		{	// two tabs in
			_out.append( "\n\t\t" + ( (PotteryArtifact) _pottery).toString() );
		}
		
		_out.append("\n\t" + "Charcoal(s): "+ this.getArtifactListCount("charcoal") );
		for(Artifact _charcoal: this.CharcoalList)
		{	// two tabs in
			_out.append( "\n\t\t" + ( (CharcoalArtifact) _charcoal).toString() );
		}
		
		_out.append("\n\t" + "Metal(s): "+ this.getArtifactListCount("metal") );
		for(Artifact _metal: this.MetalList)
		{	// two tabs in
			_out.append( "\n\t\t" + ( (MetalArtifact) _metal).toString() );
		}
		
		_out.append("\n\t" + "Average Date: " + this.averageDateOfBag());
		return _out.toString();
	}
	
	/*
	 * @override toString used for debugging
	 */
	public String toString()
	{
		StringBuffer _out = new StringBuffer();
		
		_out.append("\n\t" + "Potterys: "+ this.getArtifactListCount("pottery") );
		for(Artifact _pottery: this.PotteryList)
		{	// two tabs in
			_out.append( "\n\t\t" + ( (PotteryArtifact) _pottery).toString() );
		}
		
		_out.append("\n\t" + "Charcoals: "+ this.getArtifactListCount("charcoal") );
		for(Artifact _charcoal: this.CharcoalList)
		{	// two tabs in
			_out.append( "\n\t\t" + ( (CharcoalArtifact) _charcoal).toString() );
		}
		
		_out.append("\n\t" + "Metals: "+ this.getArtifactListCount("metal") );
		for(Artifact _metal: this.MetalList)
		{	// two tabs in
			_out.append( "\n\t\t" + ( (MetalArtifact) _metal).toString() );
		}
		
		return _out.toString();
	}
	
	/*
	 * @return String to persist file in csv format
	 */
	public String toPersist()
	{
		StringBuffer _out = new StringBuffer();
		
		// Pottery has trailing comma
		if( this.getArtifactListCount("pottery") == 0 )
		{
			_out.append( this.getArtifactListCount("pottery") + ",");
		}
		else
			{
				_out.append( this.getArtifactListCount("pottery") );
				for(Artifact _pottery: this.PotteryList)
				{	// two tabs in
					_out.append( "," + ( (PotteryArtifact) _pottery).toPersist() );
				}
				_out.append(",");
			}
		
		// Charcoal has trailing comma
		if( this.getArtifactListCount("charcoal") == 0 )
		{
			_out.append( this.getArtifactListCount("charcoal") + ",");
		}
		else
			{
				_out.append( this.getArtifactListCount("charcoal") );
				for(Artifact _charcoal: this.CharcoalList)
				{	// two tabs in
					_out.append( "," + ( (CharcoalArtifact) _charcoal).toPersist() );
				}
				_out.append(",");
			}
		
		// Metal DOES NOT have a trailing comma
		if( this.getArtifactListCount("metal") == 0 )
		{
			_out.append( this.getArtifactListCount("metal") );
		}
		else
			{
				_out.append( this.getArtifactListCount("metal") );
				for(Artifact _metal: this.MetalList)
				{	// two tabs in
					_out.append( "," + ( (MetalArtifact) _metal).toPersist() );
				}
			}
		
		return _out.toString();
	}
}
