
package Archeology.v2.Map;

import java.util.ArrayList;

/**
 * @author ronin
 *
 */
public class MapPrintController 
{
	private Map retrievedMap;
	private Integer columnSize = 0;
	private Integer rowSize = 0;
	public static MapPrintController ThisMapPrintController;
	
	public MapPrintController()
	{
		retrievedMap = Map.getMap();
		setColumnSize( retrievedMap.getColumnSize() );
		setRowSize( retrievedMap.getRowSize() );
		ThisMapPrintController = this;
	}
	
	public MapPrintController getMapPrintController()
	{
		return ThisMapPrintController;
	}
	
	/**
	 * For GUI, retrieve 2Dimensional Array of strings of all display types.
	 * Use for Scanners needs a different method.
	 * @param _displayType not 1, 0-7, 1 is symbols, not used in phase3
	 * @return ArrayList<ArrayList<String>> of display type
	 */
	public ArrayList<ArrayList<String>> get2DArray(Integer _displayType)
	{
		return retrievedMap.getMapData(_displayType);
	}
	
	/**
	 * This overloaded method, given two coordinates, uses a temp arraylist of map data and scans the area between the coordinates using
	 * the appropriate tool. 
	 * @param _coordinate1 : mouse clicked square [row and column]
	 * @param _coordinate2 : mouse released square [row and column]
	 * @param _scanTool: 1 for metal detector, 2 for magnetometer
	 * @return the 2D arrayList to use for GUI Tiles Map Display
	 */
	public ArrayList<ArrayList<String>> getScannerArray(Coordinate _coordinate1, Coordinate _coordinate2, Integer _scanTool)
	{
		return retrievedMap.scanner(_coordinate1, _coordinate2, _scanTool);
	}
	
	/**
	 * This Scanner is both the Magnetometer & Metal Detector, can search by 
	 * GRID, COLUMN or ROW like all other tools.
	 * @param _scanTool	1: Metal Detector, 2: Magnetometer
	 * @param _coordinate Grid of place, for only row or column enter 0 for other
	 * @param _searchBy 1: ROW, 2: COLUMN, 3: GRID
	 * @return String of out text for printing to either javaTerm or GUI
	 */
	public String scanner(Integer _scanTool, Coordinate _coordinate, Integer _searchBy)
	{
		ArrayList<ArrayList<String>> printCharacterArray = retrievedMap.scanner(_scanTool, _coordinate, _searchBy);
		StringBuffer outText = new StringBuffer();
		
		// add 5 to scanTool to call buildTitle w/o recoding
		
		outText.append( buildTitle(_scanTool + 5) );
		
		outText.append( buildTopGridCoordinates() );
		
		outText.append( buildMapGrid(printCharacterArray) );
		
		outText.append( buildBottomBorder() );	

		return outText.toString();
	}
	
	/**
	 * @param _displayType 0, default letters, 1, symbols, 2, T/F 3, Pottery Amount, 4, Charcoal Amount, 5, Metal Amount
	 * @return a long string of the map, for gui text area (as a string)
	 */
	public String getMapDisplay(Integer _displayType)
	{	
		ArrayList<ArrayList<String>> printCharacterArray = retrievedMap.getMapData(_displayType);
		StringBuffer outText = new StringBuffer();
		
		outText.append( buildTitle(_displayType) );
		
		outText.append( buildTopGridCoordinates() );
		
		outText.append( buildMapGrid(printCharacterArray) );
		
		outText.append( buildBottomBorder() );	
		
		return outText.toString();
	}
	
	
	private StringBuffer buildMapGrid(ArrayList<ArrayList<String>> _printCharacterArray)
	{	// MOVED ARRAY LIST OUT:
		// because Scanner has different needs
		//Printing Map 
		StringBuffer tempOut = new StringBuffer();
		
		for(int row = 0; row < rowSize; row++)
		{
			if(row < 10)
			{
				tempOut.append("0"+row+"|");
			}
			else
			{
				tempOut.append(row+"|"); // Coordinate print for Row
			}
			
			for(int col=0; col < columnSize; col++)
			{
				tempOut.append( _printCharacterArray.get(row).get(col) );
			} // columns
			// New Line after end of Column
			tempOut.append("\n");
		} // rows
		return tempOut;
	}

	private StringBuffer buildBottomBorder() {
		StringBuffer tempOut = new StringBuffer();
		
		// BEGIN BOTTOM BORDER
		tempOut.append("--+"); // Spacer
		for(int i=0; i < columnSize; i++)
		{
			tempOut.append("-");
		}
		//END BOTTOM BORDER
		tempOut.append("\n");
		
		return tempOut;
	}

	/**
	 * @param _displayType
	 */
	private StringBuffer buildTitle(Integer _displayType) {
		StringBuffer tempOut = new StringBuffer("\n\t.:: ");	//Beginning of Title
		String titleEnd = " Map ::.";
		
		// Title Print
		switch(_displayType)
		{
		case 0:
			tempOut.append("Default Letters" + titleEnd);
			break;
		case 1:
			tempOut.append("Symbols" + titleEnd);
			break;
		case 2:
			tempOut.append("Excavated" + titleEnd);
			break;
		case 3:
			tempOut.append("Pottery Count" + titleEnd);
			break;
		case 4:
			tempOut.append("Charcoal Count" + titleEnd);
			break;
		case 5:
			tempOut.append("Metal Count" + titleEnd);
			break;
		case 6:
			tempOut.append("Metal Detector" + titleEnd);
			break;
		case 7:
			tempOut.append("Magnetometer" + titleEnd);
			break;
		}
		return tempOut;
	}

	private StringBuffer buildTopGridCoordinates() 
	{
		Integer temp=0;
		StringBuffer tempOut = new StringBuffer();
		//BEGIN TOP COLUMN COORDINATES
		tempOut.append("\n");
		tempOut.append("  |"); //spacer
		
		for (int i=0;i< columnSize;i++)
		{	// TOP COLUMN "AA..BB.."
			if(i < 26)
			{
				tempOut.append(" ");			//print a space
			}
			else
			{ //using modulus to increment through alphabet
				if(i%26 == 0)
					temp++;
				tempOut.append((char)('A'+temp-1));	// Starting with spaces
				
			}
		}
		tempOut.append("\n  |"); //spacer
		
		temp=0;
		for (int i=0;i<columnSize;i++)
		{	// BOTTOM COLUMN ABCDEF...
			if(i < 26)
				tempOut.append((char)('A'+i));
			else
			{	if(temp%26 ==0)
					temp=0;
				tempOut.append((char)('A'+temp));
				temp++;
			}
		}
		tempOut.append("\n");
		tempOut.append("--+"); 	// Border
		for(int i=0; i < columnSize; i++)
		{	
			tempOut.append("-");
		} 	
		tempOut.append("\n");
		//END TOP COLUMN COORDINATE
		return tempOut;
	}

	/**
	 * Use with grid layout for GUI
	 * @return the columnSize
	 */
	public Integer getColumnSize() {
		return columnSize;
	}

	/**
	 * Use with grid layout for GUI
	 * @return the rowSize
	 */
	public Integer getRowSize() {
		return rowSize;
	}

	/**
	 * @param columnSize the columnSize to set
	 */
	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}

	/**
	 * @param rowSize the rowSize to set
	 */
	public void setRowSize(Integer rowSize) {
		this.rowSize = rowSize;
	}
	
}	
