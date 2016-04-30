package Archeology.v2.Map;

import Archeology.v2.Helpers.ConvertFromBase26ToInteger;
import Archeology.v2.Helpers.ConvertIntegerToBase26;

/**
 * Coordinate class always conforms to Column First then Row.
 * Coordinate( Column, Row).
 * All logic to decode a coordinate, passed with any string or Integer combination is decoded here.
 * @author ronin
 */
public class Coordinate {
	private Integer Column;
	private Integer Row;
	
	/**
	 * If column is a string, row is an Integer
	 * @param _col entered as a string, will convert in setColumn()
	 * @param _row entered as an Integer
	 */
	public Coordinate(String _col, Integer _row) {

		setColumn(_col);
		this.Row = _row;
	}
	
	/**
	 * Both are integers, no specialty needed
	 * @param _col	an Integer
	 * @param _row an Integer
	 */
	public Coordinate(Integer _col, Integer _row) {

		setColumn(_col);
		this.Row = _row;
	}
	
	/**
	 * Method for overloading for use with GUI dialogs
	 * @param _col
	 * @param _row
	 */
	public Coordinate(String _col, String _row) {
		if(_col.equals(""))
		{
			this.Column = -1;
		}
		else
		{
			setGUIColumn(_col);
		}
		
		if(_row.equals(""))
		{
			this.Row = -1;
		}
		else
		{
			this.Row = Integer.valueOf(_row);
		}
		
	}
	
	/**
	 * Decode GUI Column parameters (only strings come from GUI dialogs).
	 * @param _col a string of an Integer Column or letter Column
	 */
	public void setGUIColumn(String _col)
	{
		if( _col.toLowerCase().charAt(0) >= 'a' && _col.toLowerCase().charAt(0) <= 'z' )
		{
			setColumn(_col);
		}
		else
		{
			setColumn(Integer.valueOf(_col));
		}
	}
	/**
	 * @return the column
	 */
	public Integer getIntegerColumn() {
		return Column;
	}
	/**
	 * @return the column
	 */
	public String getStringColumn() {
		return ConvertIntegerToBase26.Convert(Column);
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(Integer _column) {
		Column = _column;
	}
	/**
	 * @param column the column to set
	 * @overload setColumn with string
	 */
	public void setColumn(String _column) {
		Column = ConvertFromBase26ToInteger.Convert(_column);
	}
	/**
	 * @return the row
	 */
	public Integer getRow() {
		return Row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(Integer row) {
		Row = row;
	}

	/**
	 * for consistency a 0 is added to row if <= 9
	 * "Row: " + getRow() + " Column: " + getStringColumn();
	 * @override toString() used for printing Report
	 */
	public String toString()
	{
		//Print Row with two digits just like the Map Grid
		if(getRow() <= 9)
		{
			return "Row: 0" + getRow() + " Column: " + getStringColumn();
		}
		else
		{
			return "Row: " + getRow() + " Column: " + getStringColumn();
		}
	}
	
	/**
	 * getStringColumn() + "," + getRow();
	 * @return string, used for saving to persist file
	 */
	public String toPersist()
	{
		return getStringColumn() + "," + getRow();
	}
}
