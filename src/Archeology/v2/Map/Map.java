package Archeology.v2.Map;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;
import java.io.File;

import Archeology.v2.Artifact.*;
import Archeology.v2.Helpers.*;

/**
 * @author Kurt
 *
 */
public class Map {
	
	private static final Integer MaximumGold = 1;
	private ArrayList<ArrayList<MapNode>> MapNodesArray = null;
	private Integer ColumnSize;
	private Integer RowSize;
	private Integer MapSize;
	private String FileName;
	private static Map ThisMap;
	
	/**
	 * @return the Instance of the Map that's created from the first loaded file
	 */
	static public Map getMap() {
		return ThisMap;
	}

	public Map(String _fileName)
	{
		//Load Map returns a string to show output of goldCheck()
		LoadMap(_fileName);
		//System.out.printf("%s",LoadMap(_fileName) );
	}
	
	public Map() 
	{
		ThisMap = this;
	}
	
	/**
	 * Given double coordinates, and integer option, this method does: AppendFinds, SetExcavations,
	 * setHeritage, ChangeFeatures
	 * @param _coordinate1 first coordinate from popupmenu
	 * @param _coordinate2 second Coordinate from popupmenu
	 * @param _option 1:Append Finds, 2. Dig, 3. Set Heritage, 4. Change Features
	 * @param _boolean true or false for Heritage or Change Features
	 * @param _additionList arraylist of the  type of artifact
	 * @param _surfaceType 'S', 'P','N'
	 * @return String output
	 */
	public String doStuff(Coordinate _coordinate1, Coordinate _coordinate2, Integer _option, Boolean _boolean, ArrayList<Artifact> _additionList, Character _surfaceType)
	{

		StringBuffer out = new StringBuffer();
		Coordinate tempCoordinate = null;
		Integer temp1= 0, temp2 = 0;
		Integer x1 =0, y1 = 0, x4 = 0, y4 =0;

		x1 = _coordinate1.getIntegerColumn();
		y1 = _coordinate1.getRow();
		
		x4 = _coordinate2.getIntegerColumn();
		y4 = _coordinate2.getRow();
				
		if (x1 > x4)
		{
			temp1 = x4; x4 = x1; x1 = temp1;
		}
		if (y1 > y4)
		{
			temp2 = y4; y4 = y1; y1 = temp2;
		}
		
		for(Integer col = x1; col <= x4;col++)
		{
			for (Integer row = y1; row <= y4; row++)
			{
				tempCoordinate = new Coordinate(col, row);
				switch(_option)
				{
				case 1:
					appendFinds(tempCoordinate, 3, _additionList, _boolean);
					break;
				case 2:
					out.append(setExcavations(tempCoordinate,3,_boolean));
					break;
				case 3:
					setHeritage(tempCoordinate, 3, _boolean);
					break;
				case 4:
					changeFeatures(tempCoordinate,3,_surfaceType);
					break;
				case 5:
					getMapNodesArray().get(row).get(col).setExcavated(true);
					break;
			
					
				}// close switch	
				
			}// close inner for
		}// close outer for
		return out.toString();
	}
	
	/**
	 * This overloaded method, given two coordinates, uses a temp arraylist of map data and scans the area between the coordinates using
	 * the appropriate tool. 
	 * @param _coordinate1 : mouse clicked square [row and column]
	 * @param _coordinate2 : mouse released square [row and column]
	 * @param _scanTool: 1 for metal detector, 2 for magnetometer
	 * @return : an arraylist of temp mapdata
	 */
	public ArrayList<ArrayList<String>> scanner( Coordinate _coordinate1, Coordinate _coordinate2, Integer _scanTool)
	{
		Integer x1 = 0, y1 = 0;
		Integer x4 = 0, y4 = 0;
		Integer temp1 = 0, temp2 = 0;
		Coordinate tempCoordinate = null;
		ArrayList<ArrayList<String>> _tempMapDataList = getInitialized2DStringArrayList();

		x1 = _coordinate1.getIntegerColumn();
		y1 = _coordinate1.getRow();
		
		x4 = _coordinate2.getIntegerColumn();
		y4 = _coordinate2.getRow();
		
		if (x1 > x4)
		{
			temp1 = x4; x4 = x1; x1 = temp1;
		}
		if (y1 > y4)
		{
			temp2 = y4; y4 = y1; y1 = temp2;
		}
		
		//get whole blank array list
		tempCoordinate = new Coordinate(x1, y1);
		_tempMapDataList = scanner(_scanTool, tempCoordinate, 3);
		
		for(Integer col = x1; col <= x4;col++)
		{
			for (Integer row =y1; row <= y4; row++)
			{
				//edit each individual piece of the array list
				tempCoordinate = new Coordinate(col, row);
				_tempMapDataList.get(row).set( col, scanner(_scanTool, tempCoordinate, 3).get(row).get(col) );
			}
		}
	
	 return _tempMapDataList;
	}
	
	
	/**
	 * changes the Heritage of any site
	 * @param _coordinate GRID or row or column of site(s) to change
	 * @param _changeOption 1: row, 2: column, 3: GRID
	 * @param _newValue True or False
	 */
	public void setHeritage(Coordinate _coordinate, Integer _changeOption, Boolean _newValue)
	{
		Integer row=0, column=0;
		switch(_changeOption)
		{
		case 1:
			//By Row
			row = _coordinate.getRow();
			for(int _column=0; _column < getColumnSize(); _column++)
			{
				getMapNodesArray().get(row).get(_column).setHeritage(_newValue);
			}
			break;
		case 2:
			//By Column
			column = _coordinate.getIntegerColumn();
			for(int _row=0; _row < getRowSize(); _row++)
			{
				getMapNodesArray().get(_row).get(column).setHeritage(_newValue);
			}
			break;
		case 3:
			//By GRID
			getMapNodesArray().get(_coordinate.getRow()).get(_coordinate.getIntegerColumn()).setHeritage(_newValue);
			break;
		}
	}
	
	/**
	 * Call this whenever a NonFerrous Metal is added to the Map.
	 * Already inside MapLoad
	 */
	public String goldCheck()
	{	
		
		StringBuffer out = new StringBuffer();
		ArrayList<Coordinate> goldCheckArray = new ArrayList<Coordinate>();
		Integer currentRow = 0;
		Integer currentColumn = 0;
	
		
		/*
		 * PSUEDO:
		 * count all gold items
		 * 		save reference to each
		 * choose last saved item
		 * delete gold from nodes
		 */
		for(ArrayList<MapNode> firstArray: getMapNodesArray())
		{
			for(MapNode node: firstArray)
			{
				if( node.getArtifactBag().goldCount() > 0 )
				{
					goldCheckArray.add(node.getCoordinate());
					out.append("\n" + node.getCoordinate().toString() + " HAS THE GOLD\n" );
				}
			}
		}
		//goldCheckArray changes after for loop
		Integer iteration =  goldCheckArray.size() - MaximumGold;
		Integer i = iteration;
		if(goldCheckArray.size() >= MaximumGold)
		{
			//for(int i=iteration; i >= 1; i-- ) BOTTOM TO TOP
			for(int j = iteration; j >= 1; j--)
			{
				i = iteration - j;
				currentRow = goldCheckArray.get(i).getRow();
				currentColumn = goldCheckArray.get(i).getIntegerColumn();
				getMapNodesArray().get(currentRow).get(currentColumn).getArtifactBag().removeGold();
				goldCheckArray.remove(i);
				out.append("\n" + goldCheckArray.get(i).toString() + " LOST THE GOLD\n" );
				
			}
			currentRow = goldCheckArray.get(iteration).getRow();
			currentColumn = goldCheckArray.get(iteration).getIntegerColumn();
			MapNode  goldHolder = getMapNodesArray().get(currentRow).get(currentColumn);
			out.append("\n" + goldHolder.getCoordinate().toString() + " is Ya' KEEPER OF YE GOLD!" );
			out.append("\n" + goldHolder.getArtifactBag().toString() + "\n"  );
		}
		
		if(goldCheckArray.size() == 0)
		{
			out.append("\nSail ho! Thar is no swag in this here map");
		}
			
		//out.append( showWhereGoldIs(goldCheckArray) );
		return out.toString();
	}
	
	/**
	 * @param _coordinate change ground of this node or nodes
	 * @param _changeOption	1: by row, 2: by column, 3: by GRID (row & column)
	 * @param _surfaceType	character that references hard-coded value from file (Natural.getDefault() returns a character)
	 */
	public void changeFeatures(Coordinate _coordinate, Integer _changeOption, Character _surfaceType)
	{
		Integer row=0, column=0;
		switch(_changeOption)
		{
		case 1:
			//By Row
			row = _coordinate.getRow();
			for(int _column=0; _column < getColumnSize(); _column++)
			{
				getMapNodesArray().get(row).get(_column).setGround(_surfaceType);
			}
			break;
		case 2:
			//By Column
			column = _coordinate.getIntegerColumn();
			for(int _row=0; _row < getRowSize(); _row++)
			{
				getMapNodesArray().get(_row).get(column).setGround(_surfaceType);
			}
			break;
		case 3:
			//By GRID
			getMapNodesArray().get(_coordinate.getRow()).get(_coordinate.getIntegerColumn()).setGround(_surfaceType);
			break;
		}
	}
	
	/**
	 * This is basically the Dig Tool
	 * Always include a print statement when using this function to handle the exception of Heritage
	 * @param _coordinate dig this node or row or column
	 * @param _changeOption	1: dig by row, 2: dig by column, 3: dig by GRID (row & column)
	 * @param _value Boolean to change Excavation value to
	 * @return String handling error print out if Digging is allowed from Heritage or Not
	 * 
	 */
	public String setExcavations(Coordinate _coordinate, Integer _changeOption, Boolean _value)
	{
		String _error = "Heritage Site, No Digging Allowed!";
		StringBuffer out = new StringBuffer();
		Integer row=0, column=0;
		Coordinate gridHeritage = null;
		
		switch(_changeOption)
		{
		case 1:
			//By Row
			row = _coordinate.getRow();
			for(Integer _column=0; _column < getColumnSize(); _column++)
			{
				if(getMapNodesArray().get(row).get(_column).setExcavated(_value) == false )
				{
					gridHeritage = new Coordinate(_column,row);
					out.append("\n" + gridHeritage.toString() + " " + _error);
					gridHeritage = null;
				}
				else{
					getMapNodesArray().get(row).get(_column).setExcavated(_value);
				}
				
			}
			break;
		case 2:
			//By Column
			column = _coordinate.getIntegerColumn();
			for(int _row=0; _row < getRowSize(); _row++)
			{
				if(getMapNodesArray().get(_row).get(column).setExcavated(_value) == false )
				{
					gridHeritage = new Coordinate(column,_row);
					out.append("\n" + gridHeritage.toString() + " " + _error);
					gridHeritage = null;
				}
				else{
					getMapNodesArray().get(_row).get(column).setExcavated(_value);
				}
			}
			break;
		case 3:
			//By GRID
			if( getMapNodesArray().get(_coordinate.getRow()).get(_coordinate.getIntegerColumn()).setExcavated(_value) == false )
			{
				out.append("\n" + _coordinate.toString() + " " + _error);
			} 
			else{
				getMapNodesArray().get(_coordinate.getRow()).get(_coordinate.getIntegerColumn()).setExcavated(_value);
			}
			break;
		}	
		return out.toString();
	}
	
	/**
	 * @param _coordinate	contains either row or column or both for element to change or add
	 * @param _changeOption	1-row, 2-column, 3-row&column
	 * @param _additionList	artifact's to add, one type at a time list, just one still add in ArrayList<Artifact>
	 * @param _setExcavation true: set excavation to true, false: leave alone
	 */
	public void appendFinds(Coordinate _coordinate, Integer _changeOption, ArrayList<Artifact> _additionList, Boolean _setExcavation)
	{	// generalizing an artifact change
		/* PSUEDO
		 * iterate through rows
		 * 	for node in each row
		 * 		iterate through each item in addition list
		 * 			append tempList with addition
		 * 		setList in node to tempList
		 * 	clear tempList--Kills it, no good to do (pass by reference)
		 */
		Integer row=0, column=0;

		switch(_changeOption)
		{
		case 1:	
			// By Row
			row = _coordinate.getRow();
			for(int _column=0; _column < getColumnSize(); _column++)
			{
				addArtifactListandExcavatedValue(_additionList, _setExcavation,	row, _column);
			}// for column
			break; //End By Row
		case 2: 
			//By Column
			column = _coordinate.getIntegerColumn();
			for(int _row=0; _row < getRowSize(); _row++)
			{
				addArtifactListandExcavatedValue(_additionList, _setExcavation, _row, column);
			}// for row
			break; //End by Column
		case 3:
			// By GRID (row & column)
			row = _coordinate.getRow();
			column = _coordinate.getIntegerColumn();
			
			addArtifactListandExcavatedValue(_additionList, _setExcavation,	row, column);
			break;
		}//Switch(_changeType)
	}//setFinds
	
	/**
	 * @param _additionList	list of Artifacts to add, type is determined inside ArtifactBag.addFinds() method
	 * @param _setExcavation if true, then will change to TRUE, false will leave value alone
	 * @param _row of node or Nodes
	 * @param _column of node or Nodes
	 */
	private void addArtifactListandExcavatedValue(
			ArrayList<Artifact> _additionList, Boolean _setExcavation,
			Integer _row, Integer _column) 
	{
		getMapNodesArray().get(_row).get(_column).addFinds(_additionList);
		
		if(_setExcavation)
		{	// change excavation if instructed to do so, to TRUE
			getMapNodesArray().get(_row).get(_column).setExcavated(true);
		}
	}
	
	private String getLetters(int _row, int _column)
	{	// for ShowMap
		Character _characterReturn;
		if(this.getTrueOrFalse(_row, _column) == true)
		{
			_characterReturn = this.MapNodesArray.get(_row).get(_column).getGround().getExcavated();
		}
		else
		{
			_characterReturn = this.MapNodesArray.get(_row).get(_column).getGround().getUnExcavated();
		}
		
		return _characterReturn.toString();
	}
	
	private String getSymbols(int _row, int _column)
	{	// for ShowMap
		Character _characterReturn;
		if(this.getTrueOrFalse(_row, _column) == true)
		{
			_characterReturn = this.MapNodesArray.get(_row).get(_column).getGround().getSymbExcavated();
		}
		else
		{
			_characterReturn = this.MapNodesArray.get(_row).get(_column).getGround().getSymbUnExcavated();
		}
		
		return _characterReturn.toString();
	}
	
	private Boolean getTrueOrFalse(int _row, int _column)
	{	// for ShowMap
		return this.MapNodesArray.get(_row).get(_column).getExcavated();
	}
	
	private Boolean getHeritage(int _row, int _column)
	{	// for Scanner
		return this.MapNodesArray.get(_row).get(_column).getHeritage();
	}
	
	private Integer metalDetector(int _row, int _column)
	{	// for Scanner
		return MapNodesArray.get(_row).get(_column).metalDetector();
	}
	
	private String magnetometerScan(int _row, int _column)
	{	// for Scanner
		if(this.MapNodesArray.get(_row).get(_column).magnetometerScanner() == true)
		{
			return "T";
		}
		else
		{
			return "F";
		}
	}
	
	private Integer getArtifactListCount(int _row, int _column, String _artifactListType)
	{	// for ShowMap
		Integer _integerReturn;
		if(this.MapNodesArray.get(_row).get(_column).getExcavated() == true)
		{
			_integerReturn = this.MapNodesArray.get(_row).get(_column).getArtifactListCount(_artifactListType);
		}
		else
		{
			_integerReturn = 0;
		}
		
		return _integerReturn;
	}
	
	/**
	 * Magnetometer & Metal Detector, not to be called outside of MapPrintController
	 * @param _scanTool 1-> Metal Detector; 2-> Magnetometer
	 * @param _searchBy	1: ROW, 2: COLUMN, 3: GRID
	 * @return a 2D Array to send to MapPrintController for Display
	 */
	public ArrayList<ArrayList<String>> scanner(Integer _scanTool, Coordinate _coordinate, Integer _searchBy)
	{
		/*
		 * PSUEDO:
		 * Fill Blank Array
		 * Based on Search Type
		 * 		Insert into Array The Info
		 */
		ArrayList<ArrayList<String>> _tempMapDataList = getInitialized2DStringArrayList();
		Integer row=0, column = 0;
		String heritageException = "F";		// Was H but for GUI tiles needs to be F for false
		
		for(int _row = 0; _row < getRowSize(); _row++)
		{ 	// Coordinate print for Row

			for(int _col=0; _col < getColumnSize(); _col++)
			{
				_tempMapDataList.get(_row).set(_col, " ");
			}
		}
		
		switch(_searchBy)
		{
		case 1:
			//By Row
			row = _coordinate.getRow();
			for(int _column=0; _column < getColumnSize(); _column++)
			{
				switch(_scanTool)
				{
				case 1:    // METAL DETECTOR
					if(getHeritage(row,_column) == true)
					{
						_tempMapDataList.get(row).set(_column, heritageException);
					}
					else
					{
						_tempMapDataList.get(row).set( _column, metalDetector(row,_column).toString() );
					}
					break;
				case 2:    // MAGNETOMETER
					if(getHeritage(row,_column) == true)
					{
						_tempMapDataList.get(row).set(_column, heritageException);
					}
					else
					{
						_tempMapDataList.get(row).set( _column, magnetometerScan(row, _column) );
					}
					break;
				}
			}
			break;
		case 2:
			//By Column
			column = _coordinate.getIntegerColumn();
			for(int _row=0; _row < getRowSize(); _row++)
			{
				switch(_scanTool)
				{
				case 1:    // METAL DETECTOR
					if(getHeritage(_row,column) == true)
					{
						_tempMapDataList.get(_row).set(column, heritageException);
					}
					else
					{
						_tempMapDataList.get(_row).set( column, metalDetector(_row,column).toString() );
					}
					break;
				case 2:    // MAGNETOMETER
					if(getHeritage(_row,column) == true)
					{
						_tempMapDataList.get(_row).set(column, heritageException);
					}
					else
					{
						_tempMapDataList.get(_row).set( column, magnetometerScan(_row, column) );
					}
					break;
				}
			}
			break;
		case 3:
			//By GRID
			row = _coordinate.getRow();
			column = _coordinate.getIntegerColumn();
			switch(_scanTool)
			{
			case 1:    // METAL DETECTOR
				if(getHeritage(row,column) == true)
				{
					_tempMapDataList.get(row).set(column, heritageException);
				}
				else
				{
					_tempMapDataList.get(row).set( column, metalDetector(row,column).toString() );
				}
				break;
			case 2:    // MAGNETOMETER
				if(getHeritage(row,column) == true)
				{
					_tempMapDataList.get(row).set(column, heritageException);
				}
				else
				{
					_tempMapDataList.get(row).set( column, magnetometerScan(row, column) );
				}
				break;
			}
			break;
		}	
		
		return _tempMapDataList;
	}
	
	/**
	 * @param _displayType based on display type a 2D array is returned of strings to print as characters
	 * @return 0:Default Letters, 1:Symbols, 2:Excavation value, 3:Pottery Counts, 4:Charcoal Counts, 5:Metal Counts
	 */
	public ArrayList<ArrayList<String>> getMapData(Integer _displayType) 
	{
		ArrayList<ArrayList<String>> _mapDataList = getInitialized2DStringArrayList();
		
		for(int row = 0; row < getRowSize(); row++)
		{ 	// Coordinate print for Row

			for(int col=0; col < getColumnSize(); col++)
			{
				switch(_displayType)
				{
				case 0:	// default Letters
					_mapDataList.get(row).set(col,getLetters(row, col));
					break;
				case 1:	// SYMBOLS @#$!,=+ 
					_mapDataList.get(row).set(col,getSymbols(row, col));
					break;
				case 2:	// True or False  
					if(getTrueOrFalse(row, col) == true)
					{
						_mapDataList.get(row).set(col,"T");
					}
					else
					{
						_mapDataList.get(row).set(col,"F");
					}
					break;
				case 3: // Pottery Amount 
					if(getArtifactListCount(row, col, "pottery") > 0)
					{
						_mapDataList.get(row).set(col,getArtifactListCount(row, col, "pottery").toString());
					}
					else
					{	// Square is unExcavated
						_mapDataList.get(row).set(col," ");
					}
					break;
					
				case 4: // Charcoal Amount 
					if(getArtifactListCount(row, col, "charcoal") > 0)
					{
						_mapDataList.get(row).set(col,getArtifactListCount(row, col, "charcoal").toString());
					}
					else
					{	// Square is unExcavated
						_mapDataList.get(row).set(col," ");
					}
					break;
					
				case 5: // Metal Amount 
					if(getArtifactListCount(row, col, "metal") > 0)
					{
						_mapDataList.get(row).set(col,getArtifactListCount(row, col, "metal").toString());
					}
					else
					{	// Square is unExcavated
						_mapDataList.get(row).set(col," ");
					}
					break;
				case 6: // Metal Detector Map (T or F ) map. It detects any metal count
					if(getTrueOrFalse(row, col))
					{
						_mapDataList.get(row).set(col, this.getMapNodesArray().get(row).get(col).metalDetector().toString() );
					}
					else
					{	// Square is unExcavated
						_mapDataList.get(row).set(col," ");
					}
					break;
				case 7: // Magnetometer Map (T or F ) map. This detects any charcoal count
					if(getTrueOrFalse(row, col))
					{
						if(this.getMapNodesArray().get(row).get(col).magnetometerScanner())
						{	// T there is charcoals
							_mapDataList.get(row).set(col,"T");
						}
						else
						{	// F there are NOT charcoals
							_mapDataList.get(row).set(col,"F");
						}
					}
					else
					{	// Square is unExcavated
						_mapDataList.get(row).set(col," ");
					}
					break;
				}
			}// end of for column
		}// end of row
		return _mapDataList;
	}
	
	/**
	 * @param _title Title with .:: TITLE ::. format
	 * @return nothing, just prints the entire contents of the MapNodes Array(s) for reporting or debugging
	 */
	public String PrintDualArray(String _title)
	{
		StringBuffer out = new StringBuffer();

		out.append("\n.:: " + _title + " ::.");
		for(ArrayList<MapNode> mapNodeFirstArray: this.getMapNodesArray())
		{
			for(MapNode node: mapNodeFirstArray)
			{
				out.append("\n\n"+ node.toString());
				out.append( node.getArtifactBag().toString() );
			}
		} 
		return out.toString();
	}
	
	/**
	 * @param _title Title with .:: TITLE ::. format
	 * @return nothing, just prints the entire contents of the MapNodes Array(s) for reporting or debugging
	 */
	public String PrintReport(String _title)
	{
		StringBuffer out = new StringBuffer();

		out.append("\n.:: " + _title + " ::.");
		for(ArrayList<MapNode> mapNodeFirstArray: this.getMapNodesArray())
		{
			for(MapNode node: mapNodeFirstArray)
			{
				//RESTRICT TO ONLY IF EXCAVATED
				if(node.getExcavated())
				{
					//RESTRICT FROM PRINTING EMPTY NODES OPTION
					if(node.getArtifactBag().wholeBagCount() > 0)
					{
						out.append("\n\n"+ node.toString());
						out.append( node.getArtifactBag().toReport() );
					}
				}
			}
		} 
		return out.toString();
	}
	
	private void preFillMapNodesArray()
	{	// to create 'holders' for the 2D Array
		// used in LoadMap
		// Because....java
		MapNode _dummy = new MapNode();
		MapNodesArray = new ArrayList<ArrayList<MapNode>>();
		
		for(int i=0; i < getRowSize(); i++)
		{
			ArrayList<MapNode> _dummyList = new ArrayList<MapNode>();
			for(int j=0; j < getColumnSize();j++)
			{
				_dummyList.add(_dummy);
			}
			MapNodesArray.add(_dummyList);
		}
	}
	
	private ArrayList<ArrayList<String>> getInitialized2DStringArrayList()
	{	// to create 'holders' for the 2D Array used for MapController
		// used in LoadMap
		// Because....java
		String _dummy = null;
		ArrayList<ArrayList<String>> _stringArray = new ArrayList<ArrayList<String>>();
		
		for(int i=0; i < getRowSize(); i++)
		{
			ArrayList<String> _dummyList = new ArrayList<String>();
			for(int j=0; j < getColumnSize();j++)
			{
				_dummyList.add(_dummy);
			}
			_stringArray.add(_dummyList);
		}
		
		return _stringArray;
	}
	
	//FILE HANDLERS
	public void CreateFile(Coordinate _passCoordinate, String _fileName)
	{
		String defaultLine = "0,0,0";
		Character defaultFeature = 'N';
		//REMOVE PREVIOUS MAP
		ThisMap = null;
		setMapNodesArray(null);
		this.setFileName(_fileName);
		
		setColumnSize(_passCoordinate.getIntegerColumn());	// Column
		setRowSize(_passCoordinate.getRow());	// Row
		setMapSize( getColumnSize()*getRowSize() );
		
		preFillMapNodesArray();		//Because Java is stupid like that
		
		for(Integer _row = 0; _row < getRowSize(); _row++)
		{
			for(Integer _col = 0; _col < getColumnSize(); _col++)
			{
				MapNode OneNode = new MapNode(_col,_row,defaultFeature
						,false,false,defaultLine);	
				MapNodesArray.get(_row).set(_col,OneNode);
			}
			
		}	//For _row
		
		ThisMap = this;
		PersistMap(_fileName);
	}
	
	//WRITE File
	/**
	 * @param _fileName file name to save to (doesn't have to be the same as load, for debugging)
	 */
	public void PersistMap(String _fileName)
	{	// creating file instance, deleting used file, 
		// opening formattter for writing
		// toPersist() in csv format
		File Persist = new File(_fileName);
		Persist.delete();
		Formatter Save = writeFile(_fileName);
		
		//First Line of File
		try{ // write map size to file. ex: 16,16 on first line
			Save.format("%d,%d",getColumnSize(),getRowSize());
			//Save.format("\n");
		}
		catch (FormatterClosedException formatterClosedException)
		{ // file disappeared
			System.err.println("Can't write to file anymore");
			formatterClosedException.printStackTrace();
			System.exit(1);
		}
		catch (Exception e)	
		{ // Bucket catch
			System.err.println("1.Error with file i/o");
			e.printStackTrace();
			System.exit(1);
		}
		
		//Rest of Data to file
		try{
			for(ArrayList<MapNode> firstArray: getMapNodesArray())
			{
				for(MapNode node: firstArray)
				{
					//Save Coordinate, Ground, Booleans
					Save.format("\n%s", node.toPersist() );
					// Save ArtifactBag
					Save.format(",%s", node.getArtifactBag().toPersist() );
				}
			} 
		}//try
		catch (FormatterClosedException formatterClosedException)
		{ // file disappeared
			System.err.println("Can't write to file anymore");
			formatterClosedException.printStackTrace();
			System.exit(1);
		}
		catch (Exception e)	
		{ // Bucket catch
			System.err.println("1.Error with file i/o");
			e.printStackTrace();
			System.exit(1);
		}
		
		closeFile(Save);	//Close Formatter
	}
	
	//READ FILE
	/**
	 * Returns a string from goldCheck()
	 * @param _fileName filename to enter, can always change anywhere, will change all data
	 * @return String from goldCheck() showing changes in gold added & corrections
	 */
	public String LoadMap(String _fileName)
	{
		Integer currentRow = 0, currentColumn = 0;
		StringBuffer out = new StringBuffer();
		
		//REMOVE PREVIOUS MAP
		ThisMap = null;
		setMapNodesArray(null);
		this.setFileName(_fileName);
		
		// CSV positions
		int ColumnPosition = 0;
		int RowPosition = 1;
		int SurfacePosition = 2;
		int ExcavatedPosition = 3;
		int HeritagePosition = 4;
	
		// Open File
		Scanner FileLines = openFile(_fileName);
		
		String Line = FileLines.nextLine();
		String [] Tokens = Line.split(",");
		setColumnSize(Integer.valueOf(Tokens[0]));	// Column
		setRowSize(Integer.valueOf(Tokens[1]));	// Row
		setMapSize( getColumnSize()*getRowSize() );
		
		preFillMapNodesArray();		//Because Java is stupid like that
		
		for(int i = 0; i < getMapSize(); i++)
		{
			if(FileLines.hasNext())
			{
				Line = FileLines.nextLine();		//getting line from file
				Tokens = Line.split(",");
			}
			else
			{
				System.out.printf("\nSHOULDN'T BE IN HERE! LoadMap(); for loop; hasNext()=FALSE");
				Line = null;
				Tokens = null;
			}
			currentRow = Integer.valueOf(Tokens[RowPosition]);
			currentColumn = ConvertFromBase26ToInteger.Convert(Tokens[ColumnPosition]);
			MapNode OneNode = new MapNode(Tokens[ColumnPosition],currentRow,
					(Tokens[SurfacePosition]).charAt(0), Boolean.valueOf(Tokens[ExcavatedPosition]),
					Boolean.valueOf(Tokens[HeritagePosition]),
					// 7 breaks, 8&9 works, since update trying with 5 added
					 Line.substring( (Line.indexOf(',',14) +1) ) );	
			MapNodesArray.get(currentRow).set(currentColumn,OneNode);
		}	//For i
		
		closeFile(FileLines);			// Close Scanner
		ThisMap = this;
		// goldCheck here needs to be in console unless MAP
		out.append( goldCheck() );
		return out.toString();
	}

	//FILE STREAMS
	private Scanner openFile(String _fileName)
	{	//Reading file with Scanner
		Scanner _fileLines = null;
		try{
			_fileLines = new Scanner(Paths.get(_fileName));
		}
		
		catch (SecurityException securityException)	// not good permissions
		{
			System.err.println("Cannot WRITE to file. Invalid permissions");
			securityException.printStackTrace();
			System.exit(1);
		}
		catch (FileNotFoundException fileNotFoundException) // file disappears
		{
			System.err.println("Cannot OPEN file.");
			fileNotFoundException.printStackTrace();
			System.exit(1);
		}
		catch (Exception e)	//Bucket catch
		{
			System.err.println("Some Error reading file!");
			e.printStackTrace();
		}
		
		return _fileLines;

	}
	
	private Formatter writeFile(String _fileName)
	{	//Writing file with Formatter
		Formatter _persist = null;
		try{
			_persist = new Formatter(_fileName);
		}
		
		catch (SecurityException securityException)	// not good permissions
		{
			System.err.println("Cannot WRITE to file. Invalid permissions");
			securityException.printStackTrace();
			System.exit(1);
		}
		catch (FileNotFoundException fileNotFoundException) // file disappears
		{
			System.err.println("Cannot OPEN file.");
			fileNotFoundException.printStackTrace();
			System.exit(1);
		}
		catch (Exception e)	//Bucket catch
		{
			System.err.println("Some Error writing file!");
			e.printStackTrace();
		}
		
		return _persist;

	}
	
	private void closeFile(Formatter _save) 
	{	// Close Formatter
		try{
			if (_save != null)
				_save.close();	
			}
			catch (Exception e)
			{
				System.out.println("Can't close?");
				e.printStackTrace();
				System.exit(1);
			}
		
	}
	
	private void closeFile(Scanner _fileLines)
	{	// Close Scanner
		try{
		if (_fileLines != null)
			_fileLines.close();	
		}
		catch (Exception e)
		{
			System.out.println("Can't close?");
			e.printStackTrace();
			System.exit(1);
		}
	}
		
	//Getters & Setters (trimmed down to ones used only)
	
	private void setMapNodesArray(ArrayList<ArrayList<MapNode>> _mapNodesArray)
	{
		this.MapNodesArray = _mapNodesArray;
	}
	
	/**
	 * @return the 2D mapNodesArray
	 */
	public ArrayList<ArrayList<MapNode>> getMapNodesArray() {
		return MapNodesArray;
	}
	
	/**
	 * @return the columnSize set from file
	 */
	public Integer getColumnSize() {
		return ColumnSize;
	}

	/**
	 * @param columnSize the columnSize to set
	 */
	private void setColumnSize(Integer _columnSize) {
		//private, column size set from Map.load no use for external
		ColumnSize = _columnSize;
	}

	/**
	 * @return the rowSize set from file
	 */
	public Integer getRowSize() {
		return RowSize;
	}

	/**
	 * @param rowSize the rowSize to set
	 */
	private void setRowSize(Integer _rowSize) {
		// private, row is set from Map.Load no use for external
		RowSize = _rowSize;
	}

	/**
	 * @return the mapSize set from Row Size * Column Size, from file
	 */
	public Integer getMapSize() {
		return MapSize;
	}

	/**
	 * @param mapSize the mapSize to set
	 */
	private void setMapSize(Integer _mapSize) {
		//private, map size set from Map.Load, no use for external
		MapSize = _mapSize;
	}

	/**
	 * @return the fileName that is used from Map.Load()
	 */
	public String getFileName() {
		return FileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		FileName = fileName;
	}

}
