package gui.phase2;

import gui.phase3.ArtificialIntelligence;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Archeology.v2.Artifact.*;
import Archeology.v2.Helpers.*;
import Archeology.v2.Map.*;

public class SubController 
{
	private JTextArea outToGui;
	private Map TheMap = new Map("Tikal2.csv"); //new Map();//= new Map("Tikal2b.csv"); //= new Map(); // ;// = new Map("TEST.csv");
	private MapPrintController PrintMap = new MapPrintController();
	private ArrayList<Artifact> passNew = null;
	private StringBuffer text = new StringBuffer();
	
	/*public static SubController ThisSubController;
	
	public SubController()
	{
		ThisSubController = this;
		//TheMap = TheMap.getMap();
	}
		*/
	/* ####################### SET TEXT AREA ####################### */
	public void setTextArea(JTextArea textArea)
	{
		outToGui=textArea;
	}
	/* ####################### END of SETTINGS ################# */
	
	
	/* ####################### FILE MENU ####################### */
	
	/**
	 * If "ADT" then threads will start. Load file from file menu
	 * @param _menuOption
	 */
	public void load(String _menuOption)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open a comma seperated file...");
		Component parent = null;
		int returnVal = fileChooser.showOpenDialog(parent);
		
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("You are trying to open the file called: "+fileChooser.getSelectedFile().getAbsolutePath() );
		}
		
		//TheMap = new Map( fileChooser.getSelectedFile().getAbsolutePath() );
		goldDialog( TheMap.LoadMap(fileChooser.getSelectedFile().getAbsolutePath()) );
		//TheMap = Map.getMap();
		//// Create Thread Here
		//If ADT
			// Declare Coordinates
				// Start X# of Threads out.append( thread(name,coordinateStart) )
			//dialogReport( out )
		
		if(_menuOption == "ADT")
		{	
			ArtificialIntelligence ai1 = new ArtificialIntelligence("Schliemann", new Coordinate(0,0) );
			ArtificialIntelligence ai2 = new ArtificialIntelligence("Carter", new Coordinate(5,0) );
			ArtificialIntelligence ai3 = new ArtificialIntelligence("Bingham", new Coordinate(10,0) );
			ArtificialIntelligence ai4 = new ArtificialIntelligence("Thompson", new Coordinate(15,0) );
			ArtificialIntelligence ai5 = new ArtificialIntelligence("Robinson", new Coordinate(20,0) );
			
			ai1.start();
			ai2.start();
			ai3.start();
			ai4.start();
			ai5.start();
		}
	}
	
	public void save()
	{
		// parent component of the dialog
		JFrame parentFrame = new JFrame();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Menu");
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		
		if (userSelection == JFileChooser.APPROVE_OPTION)
		{
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			TheMap.PersistMap(fileToSave.getAbsoluteFile().getAbsolutePath());
		}
	}
	
	/**
	 * Overloading for popup Menu automatically save itself
	 * @param _popUp must equal "PopUp" ...
	 */
	public void save(String _popUp)
	{
		//Save to itself
		if(_popUp.equals("PopUp"))
		{
			TheMap.PersistMap(TheMap.getFileName());
		}
	}
	
	public void exit() {
		System.exit(0);
	}
	/* ####################### END of FILE MENU ####################### */
	
	public void heritageDialog(String _temp)
	{
		if(_temp.isEmpty() == false)
		{
			popupDialog(_temp, "Heritage Issues", "heritage");
		}
		else
		{
			
		}
	}
	
	public void goldDialog(String _temp)
	{
		if(_temp.isEmpty() == false)
		{
			popupDialog(_temp, "Gold Checking", "gold");
		}
		else
		{
			
		}
	}
	

	/* ####################### BEGIN ADT_EDIT MENU ####################### */
	public void scanWithMagnetometer(Coordinate passCoordinate,Integer addby)
	{
		if(checkRowAndColumnInput(passCoordinate) == true)
		{
			PrintMap = new MapPrintController();
			text.append("Scanning with Magnetometer\n");
			text.append( PrintMap.scanner(2,passCoordinate,addby) );
			printToGui(text);
		}
	}
	
	public void scanWithMetalDetector(Coordinate passCoordinate,Integer addby)
	{
		if(checkRowAndColumnInput(passCoordinate) == true)
		{
			PrintMap = new MapPrintController();
			text.append("Scanning with Metal Detector\n");
			text.append(PrintMap.scanner(1,passCoordinate,addby));
			printToGui(text);
		}
	}
	
	public void digMap(Coordinate passCoordinate,Integer addby)
	{
		if(checkRowAndColumnInput(passCoordinate) == true)
		{
			PrintMap = new MapPrintController();
			text.append("Digging\n");
			// set excavation
			heritageDialog( TheMap.setExcavations(passCoordinate, addby, true) );
			// show changes
			text.append( PrintMap.getMapDisplay(2) );
			printToGui(text);
		}
	}
	
	/**
	 * Overloading for Artificial Intelligence. PassBack report to thread
	 * @param passCoordinate grid from thread
	 */
	public StringBuffer digMap(Coordinate _passCoordinate)
	{
		StringBuffer out = new StringBuffer();
		heritageDialog( TheMap.setExcavations(_passCoordinate, 3, true) );
		Integer row = _passCoordinate.getRow();
		Integer column = _passCoordinate.getIntegerColumn();
		if(TheMap.getMapNodesArray().get(row).get(column).getHeritage() == false)
		{
			out.append( "\n\n"+ TheMap.getMapNodesArray().get(row).get(column).toString() );
			out.append( TheMap.getMapNodesArray().get(row).get(column).getArtifactBag().toReport() );
		}
		return out;
	}
	
	/**
	 * Dig the map from PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void digMap(Coordinate _one, Coordinate _two)
	{
		heritageDialog( TheMap.doStuff(_one, _two, 2, true, null, null) );
		save("PopUp");
	}
	
	/**
	 * Dig the map from PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void digMap(Coordinate _one, Coordinate _two, String _mpt)
	{
		heritageDialog( TheMap.doStuff(_one, _two, 5, true, null, null) );
		save("PopUp");
	}
	/* ####################### END ADT_EDIT MENU ####################### */
	
	
	/* ####################### BEGIN MPT_EDIT MENU ####################### */
	public void createMap(Coordinate _passCoordinate, String _fileName)
	{
		if(_fileName.equals(null) == false)
		{
			text.append("\n" + _passCoordinate.toString());
			text.append("\nFile name = " + _fileName);
			text.append("\nCreating a Map\n");
			TheMap.CreateFile(_passCoordinate, _fileName);
			PrintMap = new MapPrintController();
			text.append("\nNew Map\n");
			text.append(PrintMap.getMapDisplay(0));
			printToGui(text);
		}
		
	}
	
	public void changeFeatures(Coordinate _passCoordinate, Integer _addBy, Character _groundType)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			PrintMap = new MapPrintController();
			text.append("Changing Features\n");
			TheMap.changeFeatures(_passCoordinate, _addBy, _groundType);
			text.append(PrintMap.getMapDisplay(0));
			printToGui(text);
		}		
	}
	
	/**
	 * Change feature from PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 * @param _groundType type of Feature to change
	 */
	public void changeFeatures(Coordinate _one, Coordinate _two, Character _groundType)
	{
		TheMap.doStuff(_one, _two, 4, true, null, _groundType);
		save("PopUp");
	}
	
	public void updateStoragePottery(Coordinate _passCoordinate,Integer _addBy, String volume,String date)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			passNew = new ArrayList<Artifact>();
			Storage temp = new Storage(Double.valueOf(volume),Integer.valueOf(date));
			text.append("\nAdding" + temp.toString() + "\n");
			printToGui(text);	
			passNew.add(temp);
			TheMap.appendFinds(_passCoordinate, _addBy, passNew, false);
		}
	}
	
	/**
	 * Update Storage Pottery PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void updateStoragePottery(Coordinate _one, Coordinate _two, String volume,String date)
	{
		passNew = new ArrayList<Artifact>();
		Storage temp = new Storage(Double.valueOf(volume),Integer.valueOf(date));
		passNew.add(temp);
		
		TheMap.doStuff(_one, _two, 1, false, passNew, null);
		save("PopUp");
	}
	
	public void updateDecoratedPottery(Coordinate _passCoordinate,Integer _addBy, String description, String date)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			passNew = new ArrayList<Artifact>();
			Decorated temp = new Decorated(description,Integer.valueOf(date));
			text.append("\nAdding" + temp.toString() + "\n");
			printToGui(text);
			passNew.add(temp);
			TheMap.appendFinds(_passCoordinate, _addBy, passNew, false);
			
		}
	}
	
	/**
	 * Update Decorate Pottery PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void updateDecoratedPottery(Coordinate _one, Coordinate _two, String description, String date)
	{
		passNew = new ArrayList<Artifact>();
		Decorated temp = new Decorated(description,Integer.valueOf(date));
		passNew.add(temp);
		
		TheMap.doStuff(_one, _two, 1, false, passNew, null);
		save("PopUp");
	}
	
	public void updateSubmergedPottery(Coordinate _passCoordinate,Integer _addBy, String depth, String date)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			passNew = new ArrayList<Artifact>();
			Submerged temp = new Submerged(Integer.valueOf(depth),Integer.valueOf(date));
			text.append("\nAdding" + temp.toString() + "\n");
			printToGui(text);
			
			passNew.add(temp);
			TheMap.appendFinds(_passCoordinate, _addBy, passNew, false);
		}
	}
	
	/**
	 * Update Submerged Pottery PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void updateSubmergedPottery(Coordinate _one, Coordinate _two, String depth, String date)
	{
		passNew = new ArrayList<Artifact>();
		Submerged temp = new Submerged(Integer.valueOf(depth),Integer.valueOf(date));
		passNew.add(temp);
		
		TheMap.doStuff(_one, _two, 1, false, passNew, null);
		save("PopUp");
	}
	
	public void updateKilnsCharcoal(Coordinate _passCoordinate,Integer _addBy,String radius, String date)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			passNew = new ArrayList<Artifact>();
			Kiln temp = new Kiln(Double.valueOf(radius),Integer.valueOf(date));
			text.append("\nAdding" + temp.toString() + "\n");
			printToGui(text);
			
			passNew.add(temp);
			TheMap.appendFinds(_passCoordinate, _addBy, passNew, false);
		}
	}
	
	/**
	 * Update Kiln Charcoal PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void updateKilnsCharcoal(Coordinate _one, Coordinate _two, String radius, String date)
	{
		passNew = new ArrayList<Artifact>();
		Kiln temp = new Kiln(Double.valueOf(radius),Integer.valueOf(date));
		passNew.add(temp);
		
		TheMap.doStuff(_one, _two, 1, false, passNew, null);
	}
	
	public void updateHearthsCharcoal(Coordinate _passCoordinate,Integer _addBy,String length, String height, String date)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			passNew = new ArrayList<Artifact>();
			Hearth temp = new Hearth(Double.valueOf(length),Double.valueOf(height),Integer.valueOf(date));
			text.append("\nAdding" + temp.toString() + "\n");
			printToGui(text);
			
			passNew.add(temp);
			TheMap.appendFinds(_passCoordinate, _addBy, passNew, false);
		}

	}
	
	/**
	 * Update Hearths Charcoal PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void updateHearthsCharcoal(Coordinate _one, Coordinate _two, String length, String height, String date)
	{
		passNew = new ArrayList<Artifact>();
		Hearth temp = new Hearth(Double.valueOf(length),Double.valueOf(height),Integer.valueOf(date));
		passNew.add(temp);
		
		TheMap.doStuff(_one, _two, 1, false, passNew, null);
		save("PopUp");
	}
	
	public void updateFerrousMetal(Coordinate _passCoordinate,Integer _addBy, String strength, String date)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			passNew = new ArrayList<Artifact>();
			Ferrous temp = new Ferrous(Integer.valueOf(strength),Integer.valueOf(date));
			text.append("\nAdding" + temp.toString() + "\n");
			printToGui(text);
			
			passNew.add(temp);
			TheMap.appendFinds(_passCoordinate, _addBy, passNew, false);
		}
		
	}
	
	/**
	 * Update Ferrous Metal PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void updateFerrousMetal(Coordinate _one, Coordinate _two, String strength, String date)
	{
		passNew = new ArrayList<Artifact>();
		Ferrous temp = new Ferrous(Integer.valueOf(strength),Integer.valueOf(date));
		passNew.add(temp);
		
		TheMap.doStuff(_one, _two, 1, false, passNew, null);
	}
	
	public void updateNonFerrousMetal(Coordinate _passCoordinate,Integer _addBy, String type, String date)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			passNew = new ArrayList<Artifact>();
			NonFerrous temp = new NonFerrous(type,Integer.valueOf(date));

			passNew.add(temp);
			TheMap.appendFinds(_passCoordinate, _addBy, passNew, false);
			goldDialog( TheMap.goldCheck() );
		}

	}
	
	/**
	 * Update NonFerrous Metal PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void updateNonFerrousMetal(Coordinate _one, Coordinate _two, String type, String date)
	{
		passNew = new ArrayList<Artifact>();
		NonFerrous temp = new NonFerrous(type,Integer.valueOf(date));
		passNew.add(temp);
		
		TheMap.doStuff(_one, _two, 1,false, passNew, null);
		save("PopUp");
		goldDialog( TheMap.goldCheck() );
	}
	
	public void markHeritage(Coordinate _passCoordinate,Integer _addBy)
	{
		if(checkRowAndColumnInput(_passCoordinate))
		{
			text.append("Changing Heritage\n");
			TheMap.setHeritage(_passCoordinate, _addBy, true);
			printToGui(text);
		}
	}
	
	/**
	 * Mark Heritage PopUpMenu
	 * @param _one first Coordinate
	 * @param _two second Coordinate
	 */
	public void markHeritage(Coordinate _one, Coordinate _two)
	{
		TheMap.doStuff(_one, _two, 3, true, null, null);
		save("PopUp");
	}
	
	/**
	 * Base 26 conversion for subController. GUI only inserts strings.
	 * Must enter both parameters
	 * @param letter enter a letter for conversion
	 * @param number enter a number for conversion
	 */
	public void convertBase(String letter, String number )
	{
		if(letter != "")
		{
			text.append("\n"+  number + " = " + ConvertIntegerToBase26.Convert( Integer.valueOf(number) ) );
		}
		if(number != "")
		{
			text.append("\n"+ letter + " = " + ConvertFromBase26ToInteger.Convert( letter ) );
		}
		printToGui(text);
	}
	/* ####################### END MPT_EDIT MENU ####################### */

	/* ####################### START OF VIEW MENU ####################### */
	public void updateMapSymbols(String oldString, String newString)
	{
		System.out.printf("\n Replacing oldString = [%s] with newString = [%s]\n", oldString,newString);
		text.append("Updating Map Symbols\n");
		printToGui(text);
		
	}

	public void viewDefaultMap()
	{
		PrintMap = new MapPrintController();
		text.append( PrintMap.getMapDisplay(0) );
		printToGui(text);
	}
	
	public void viewSymbolMap() 
	{
		PrintMap = new MapPrintController();
		text.append(PrintMap.getMapDisplay(1));
		printToGui(text);
	}
	
	public void viewExcavationMap()
	{
		PrintMap = new MapPrintController();
		text.append(PrintMap.getMapDisplay(2));
		printToGui(text);				// gui
	}
	
	public void viewMagnetometerMap()
	{
		PrintMap = new MapPrintController();
		text.append(PrintMap.getMapDisplay(7));
		printToGui(text);	// gui
	}
	
	public void viewMetalDetectorMap()
	{
		PrintMap = new MapPrintController();
		text.append(PrintMap.getMapDisplay(6));
		printToGui(text);		// gui
		
	}
	
	public void viewPotteryFindsMap()
	{
		PrintMap = new MapPrintController();
		text.append(PrintMap.getMapDisplay(3));
		printToGui(text);		// gui
	}
	
	public void viewCharcoalFindsMap()
	{
		PrintMap =  new MapPrintController();
		text.append(PrintMap.getMapDisplay(4));
		printToGui(text);		// gui
	}
	
	public void viewMetalworkMap()
	{
		PrintMap = new MapPrintController();
		text.append(PrintMap.getMapDisplay(5));
		printToGui(text);		// gui
	}

	public void viewReport()
	{
		PrintMap = new MapPrintController();
		text.append(TheMap.PrintReport("Excavated Report"));
		//printToGui(text);
		popupDialog(text.toString(),"Excavated Report", "information");
	}
	/* ####################### END of VIEW MENU ####################### */
	
	
	/* ####################### ABOUT MENU ####################### */
	
	public void about()
	{
			popupDialog("Team Hare Krishna.\n"+"Kurt Gibbons & Krishna Bhattarai\n"+"Archeology v3\n",
			"About", "about");
	}
	
	/**
	 * PopUpDialog, make any type of JOptionPane Message
	 * @param _string Message text
	 * @param _title Title of Dialog
	 * @param _option "plain", "warning", "information" (report type), "about", "gold", "heritage"
	 */
	public void popupDialog(String _string, String _title, String _option)
	{
		Component frame = new Frame();
		JTextArea listBox = new JTextArea(_string);
	    JScrollPane scroll = new JScrollPane(listBox);
	    Integer mediumWidth = 600;
	    Integer mediumHeight = 400;
	    Integer smallWidth = 300;
	    Integer smallHeight = 200;
	    
	    listBox.setLineWrap(true);  
	    listBox.setWrapStyleWord(true);
	    listBox.setEditable(false);
		try{
			if( _option.equals("plain") )
			{
				scroll.setPreferredSize(new Dimension(mediumWidth, mediumHeight));
				JOptionPane.showMessageDialog(frame, scroll, _title, JOptionPane.PLAIN_MESSAGE);
			}
			
			else if( _option.equals("warning") )
			{
				JOptionPane.showMessageDialog(frame, _string, _title, JOptionPane.WARNING_MESSAGE);
			}
			
			else if( _option.equals("information") )
			{  
				scroll.setPreferredSize(new Dimension(mediumWidth, mediumHeight));
			    JOptionPane.showMessageDialog(frame, scroll, _title, JOptionPane.INFORMATION_MESSAGE);
			}
			else if(_option.equals("about"))
			{
				JOptionPane.showMessageDialog(frame, _string, _title, JOptionPane.PLAIN_MESSAGE);
	
			}
			else if( _option.equals("gold") )
			{
				scroll.setPreferredSize(new Dimension(smallWidth, smallHeight));
				JOptionPane.showMessageDialog(frame, scroll, _title, JOptionPane.INFORMATION_MESSAGE);
			}
			else if( _option.equals("heritage") )
			{
				frame.setVisible(false);
				scroll.setPreferredSize(new Dimension(smallWidth, smallHeight));
				JOptionPane.showMessageDialog(frame, scroll, _title, JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception HeadlessException)
		{
			System.out.printf("\nHEADLESS CAUGHT");
		}
		
	}
	
	/* ####################### END of ABOUT MENU ####################### */
	
	/**
	 * Print the String BUffer to the GUI
	 * @param _temp A -String Buffer- that is ready to be sent to the GUI
	 */
	public void printToGui(StringBuffer _temp)
	{
		String outString = "";
		// gui needs string for getText
		outString = outToGui.getText();
		// add StringBuffer to string
		outString += _temp.toString();
		//print string w/ string buffer in gui
		outToGui.setText(outString);
		//clear textBuffer to prevent reprint
		_temp.delete(0, _temp.length());
	}
	
	/**
	 * Check if input from GUI is within map size
	 * @param _coordinate check if either one is greater than row
	 * @return true
	 */
	public Boolean checkRowAndColumnInput(Coordinate _coordinate)
	{
		Boolean _check = true;
		Integer maxRow = TheMap.getRowSize() - 1;
		Integer maxColumn =  TheMap.getColumnSize() - 1;
		if(_coordinate.getRow() >= maxRow )
		{
			text.append( "\n" + _coordinate.getRow().toString() +" Is greater than " + maxRow );
			text.append( "\nEnter a number within " + maxRow  );
			popupDialog(text.toString(), "Giant Row Number", "warning");
			_check = false;
		}
		
		if(_coordinate.getIntegerColumn() >= maxColumn )
		{
			text.append( "\n" + _coordinate.getStringColumn() +" Is greater than " + maxColumn );
			text.append( "\nEnter a number within " + maxColumn );
			popupDialog(text.toString(), "Giant Row Number", "warning");
			_check = false;
		}
		
		return _check;
	}
}