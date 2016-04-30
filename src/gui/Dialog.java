package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Archeology.v2.Map.Coordinate;

public class Dialog extends JDialog implements ActionListener
{
	private static Integer gridWidth = 4;
	private static Integer gridHeight = 0;
	private static Integer dialogHeight = 200;
	private static Integer dialogWidth = 350;
	
	private static Integer grid1Width = 4;
	private static Integer grid1Height = 0;
	private static Integer dialog1Height = 250;
	private static Integer dialog1Width = 450;
	
	private static Integer grid2Width = 4;
	private static Integer grid2Height = 0;
	private static Integer dialog2Width = 400;
	private static Integer dialog2Height = 250;
	
	private static Integer grid3Width = 6;
	private static Integer grid3Height = 0;
	private static Integer dialog3Width = 400;
	private static Integer dialog3Height = 250;
	
	private static String commandOK = "OK";
	private static String commandCancel = "Cancel";
	private static String nullCase = "";
	
	private static final long serialVersionUID = 6542587319791401132L;
	
	private JButton buttonOK = new JButton(commandOK); 
	private JButton buttonCancel = new JButton(commandCancel);
	
	private JTextField textFieldRow=new JTextField();
	private JTextField textFieldColumn=new JTextField();
	private JTextField textFieldString;
	private JTextField textFieldString2;
	private JTextField textFieldString3;
	
	private JLabel labelName;
	private JLabel labelName2;
	private JLabel labelName3;
	private JLabel labelRow = new JLabel("Row");
	private JLabel labelColumn = new JLabel("Column");
	private Container content = this.getContentPane();
	private Boolean cancel = false;
	
	/**
	 * Add OK & Cancel to Dialog.
	 * Add Action Listeners
	 */
	private void makeOkCancel()
	{
		this.add(buttonOK);
		this.add(buttonCancel);
	
		buttonOK.addActionListener(this);
		buttonCancel.addActionListener(this);
	}
	
	/**
	 * Add row & column labels & fields to this.dialog.
	 */
	private void addRowColumn()
	{
		this.add(labelRow);
		this.add(textFieldRow);
		
		this.add(labelColumn);
		this.add(textFieldColumn);
	}
	
	/**
	 * Create a label that states from one to two grid
	 * @param _one one coordinate from popup menu
	 * @param _two second coordinate from popup menu
	 */
	public void labelRowsandColumns(Coordinate _one, Coordinate _two)
	{
		JLabel _temp1 = new JLabel("From: "+ _one.toString() );
		JLabel _temp2 = new JLabel(" To: " + _two.toString() );
		this.add(_temp1);
		this.add(_temp2);
	}
	
	/**
	 * Main setup of the dialog
	 * @param _title Title of the dialog bos
	 * @param _width width of GridLayout
	 * @param _height height of GridLayout
	 */
	private void makeDialogLayout(String _title, Integer _width, Integer _height)
	{
		this.setTitle(_title);
		//Container content = this.getContentPane();
		GridLayout layout = new GridLayout(_width,_height);
		content.setLayout(layout);
	}
	
	/**
	 * A dialog that asks for just the Row and Column
	 *  @param _title The title of the Dialog Box
	 */
	public Dialog(String _title)
	{
		makeDialogLayout(_title,gridWidth,gridHeight);
		
		addRowColumn();
		
		makeOkCancel();
		
		setSize(dialogWidth,dialogHeight);
	}
	
	/**
	 * IMAGE TILE dialog, prints from & to
	 * @param _one coordinate one
	 * @param _two coordinate two
	 * @param _title title of the dialog
	 */
	public Dialog(Coordinate _one, Coordinate _two,String _title)
	{
		makeDialogLayout(_title,gridWidth,gridHeight);
		
		labelRowsandColumns(_one,_two);
		
		makeOkCancel();
		
		setSize(dialogWidth,dialogHeight);
		
	}
	
	/**
	 * A generic dialog that asks for Row & Column & a String.
	 * @param _title The title of the Dialog Box
	 * @param _genericStringName The name displayed to user that instructs input variable
	 */
	public Dialog(String _title, String _genericStringName)
	{
		makeDialogLayout(_title,grid1Width,grid1Height);
		
		labelName = new JLabel(_genericStringName);
		textFieldString = new JTextField();
		
		addRowColumn();
		
		this.add(labelName);
		this.add(textFieldString);
		
		makeOkCancel();
		
		setSize(dialog1Width,dialog1Height);
	}
	
	/**
	 * IMAGE TILE dialog, prints from & to, with ONE generic String
	 * @param _one coordinate one
	 * @param _two coordinate two
	 * @param _title title of the dialog
	 * @param _genericStringName The name displayed to user that instructs input variable
	 */
	public Dialog(Coordinate _one, Coordinate _two,String _title, String _genericStringName)
	{
		makeDialogLayout(_title,grid1Width,grid1Height);
		
		labelRowsandColumns(_one,_two);
		
		labelName = new JLabel(_genericStringName);
		textFieldString = new JTextField();
		
		this.add(labelName);
		this.add(textFieldString);
		
		makeOkCancel();
		
		setSize(dialog1Width,dialog1Height);
		
	}
	
	/**
	 * A generic dialog that asks for Row & Column & two strings.
	 * First String is part of the feature. Second string is Date usually.
	 * To get The right Data use getTextFieldString... For Date there is getDate()
	 * @param _title The title of the Dialog Box
	 * @param _genericStringName The name displayed to user that instructs input variable
	 * @param _genericStringName2 The name displayed to user that instructs input variable
	 */
	public Dialog(String _title, String _genericStringName, String _genericStringName2)
	{
		
		makeDialogLayout(_title,grid2Width,grid2Height);
		
		labelName = new JLabel(_genericStringName);
		labelName2 = new JLabel(_genericStringName2);
	
		textFieldString = new JTextField();
		textFieldString2 = new JTextField();
		
		addRowColumn();
		
		this.add(labelName);
		this.add(textFieldString);
		
		this.add(labelName2);
		this.add(textFieldString2);
	
		makeOkCancel();
		
		setSize(dialog2Width,dialog2Height);
	}
	
	/**
	 * IMAGE TILE dialog, prints from & to, with TWO generic Strings
	 * @param _one coordinate one
	 * @param _two coordinate two
	 * @param _title title of the dialog
	 * @param _genericStringName The name displayed to user that instructs input variable
	 * @param _genericStringName2 The name displayed to user that instructs input variable
	 */
	public Dialog(Coordinate _one, Coordinate _two,String _title, String _genericStringName, String _genericStringName2)
	{
		makeDialogLayout(_title,grid2Width,grid2Height);
		
		labelRowsandColumns(_one,_two);
		
		labelName = new JLabel(_genericStringName);
		labelName2 = new JLabel(_genericStringName2);
	
		textFieldString = new JTextField();
		textFieldString2 = new JTextField();
		
		this.add(labelName);
		this.add(textFieldString);
		
		this.add(labelName2);
		this.add(textFieldString2);
		
		makeOkCancel();
		
		setSize(dialog2Width,dialog2Height);
	}
	
	/**
	 * A generic dialog that asks for Row & Column & two strings.
	 * First String is part of the feature. Second string is Date usually.
	 * To get The right Data use getTextFieldString... For Date there is getDate()
	 * @param _title The title of the Dialog Box
	 * @param _genericStringName The name displayed to user that instructs input variable
	 * @param _genericStringName2 The name displayed to user that instructs input variable
	 * @param _genericStringName3 The name displayed to user that instructs input variable
	 */
	public Dialog(String _title, String _genericStringName, String _genericStringName2, String _genericStringName3)
	{	
		makeDialogLayout(_title,grid3Width,grid3Height);
		
		labelName = new JLabel(_genericStringName);
		labelName2 = new JLabel(_genericStringName2);
		labelName3 = new JLabel(_genericStringName3);
		
		textFieldString = new JTextField();
		textFieldString2 = new JTextField();
		textFieldString3 = new JTextField();
		
		addRowColumn();
		
		this.add(labelName);
		this.add(textFieldString);
		
		this.add(labelName2);
		this.add(textFieldString2);
		
		this.add(labelName3);
		this.add(textFieldString3);
	
		makeOkCancel();
		
		setSize(dialog3Width,dialog3Height);
	}
	
	/**
	 * IMAGE TILE dialog, prints from & to, with TWO generic Strings
	 * @param _one coordinate one
	 * @param _two coordinate two
	 * @param _title title of the dialog
	 * @param _genericStringName The name displayed to user that instructs input variable
	 * @param _genericStringName2 The name displayed to user that instructs input variable
	 */
	public Dialog(Coordinate _one, Coordinate _two,String _title, String _genericStringName, String _genericStringName2, String _genericStringName3)
	{
		makeDialogLayout(_title,grid3Width,grid3Height);
		
		labelRowsandColumns(_one,_two);
		
		labelName = new JLabel(_genericStringName);
		labelName2 = new JLabel(_genericStringName2);
		labelName3 = new JLabel(_genericStringName3);
		
		textFieldString = new JTextField();
		textFieldString2 = new JTextField();
		textFieldString3 = new JTextField();
		
		this.add(labelName);
		this.add(textFieldString);
		
		this.add(labelName2);
		this.add(textFieldString2);
		
		this.add(labelName3);
		this.add(textFieldString3);
		
		makeOkCancel();
		
		setSize(dialog3Width,dialog3Height);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if  (e.getActionCommand()==commandOK)
		{
			setTextFieldRow(this.textFieldRow);
			setTextFieldColumn(this.textFieldColumn);
			setTextFieldString(this.textFieldString);
			setTextFieldString2(this.textFieldString2);
			setTextFieldString3(this.textFieldString3);

			this.setVisible(false);
			this.setModal(false);
			this.dispose();
		}
		else if (e.getActionCommand()==commandCancel)
		{
			this.setVisible(false);
			this.setModal(false);
			this.dispose();
			setCancel(true);
		}
		
	}
	
	/**
	 * Symbol Dialog. Uses overriding of an integer to know this is a symbol dialog.
	 * @param _integer enter any integer (not used) 
	 */
	public Dialog(Integer _integer, String _title, String _genericStringName, String _genericStringName2)
	{
		makeDialogLayout(_title,gridWidth,gridHeight);
		labelName = new JLabel(_genericStringName);
		labelName2 = new JLabel(_genericStringName2);
	
		textFieldString = new JTextField();
		textFieldString2 = new JTextField();
		
		this.add(labelName);
		this.add(textFieldString);
		
		this.add(labelName2);
		this.add(textFieldString2);
	
		makeOkCancel();
		
		setSize(dialogWidth,dialogHeight);
	}
	
	/**
	 *  If null return "" for subController calculations
	 * @return the textFieldRow
	 */
	public String getRow() 
	{
		if(textFieldRow.getText().equals(null))
		{
			return nullCase;
		}
		
		else
		{
			return textFieldRow.getText();
		}
	}

	/**
	 * @param textFieldRow the textFieldRow to set
	 */
	public void setTextFieldRow(JTextField textFieldRow) {
		this.textFieldRow = textFieldRow;
	}

	/**
	 * If null return "" for subController calculations
	 * @return the textFieldCol
	 */
	public String getColumn() 
	{
		if(textFieldColumn.getText().equals(null))
		{
			return nullCase;
		}
		
		else
		{
			return textFieldColumn.getText();
		}
	}

	/**
	 * @param textFieldCol the textFieldCol to set
	 */
	public void setTextFieldColumn(JTextField textFieldCol) {
		this.textFieldColumn = textFieldCol;
	}

	/**
	 * This returns the String from the first TextField
	 * @return the textFieldName as a string
	 */
	public String getTextFieldString() {
		return textFieldString.getText();
	}

	/**
	 * @param textFieldName the textFieldName to set
	 */
	public void setTextFieldString(JTextField textFieldName) {
		this.textFieldString = textFieldName;
	}
	
	/**
	 *  This returns the String from the second TextField.
	 *  Usually the Date.
	 * @return the textFieldString2 as a string
	 */
	public String getTextFieldString2() {
		return textFieldString2.getText();
	}

	/**
	 * @param textFieldString2 the textFieldString2 to set
	 */
	public void setTextFieldString2(JTextField textFieldString2) {
		this.textFieldString2 = textFieldString2;
	}
	
	/**
	 *  This returns the String from the third TextField.
	 *  This ends up as the date sometimes.
	 * @return the textFieldString3 as a string
	 */
	public String getTextFieldString3() {
		return textFieldString3.getText();
	}

	/**
	 * @param textFieldString3 the textFieldString3 to set
	 */
	public void setTextFieldString3(JTextField textFieldString3) {
		this.textFieldString3 = textFieldString3;
	}

	/**
	 * To lessen confusion, can use getDate() to retrieve the date.
	 * Will pull data from textFieldString2
	 * @return the date as a string
	 */
	public String getDate() {
		return textFieldString2.getText();
	}

	/**
	 * When the dialog is entering or asking for a ground type use this option.
	 * @return the groundType as an uppercase character from the stringField
	 */
	public Character getGroundType() {
		return getTextFieldString().toUpperCase().charAt(0);
	}

	/**
	 * @return the cancel
	 */
	public Boolean getCancel() {
		return cancel;
	}

	/**
	 * @param cancel the cancel to set
	 */
	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}

}
