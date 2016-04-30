package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Archeology.v2.Map.Coordinate;
import Archeology.v2.Map.MapPrintController;
import gui.phase2.SubController;
import gui.phase3.PopUpMenu;
import gui.phase3.Tile;

public class MainFrame extends JFrame implements ActionListener
{
	private static Integer windowWidth = 600;
	private static Integer windowHeight = 500;
	private static Integer textSize = 14;
	private static String askDate = "Enter Date";		// string for dialog
	//pack() refers to setMinimumSize, which is set by a Dimension
	private Dimension minimumSize = new Dimension(windowWidth,windowHeight);
	
	private static final long serialVersionUID = 1156392119668344557L;
	private SubController subController;

	// The File Menu
	private static final String commandLoad="Load";
	private static final String commandSave="Save";
	private static final String commandExit="Exit";
	
	// The Edit ADT Menu
	private static final String commandScanWithMagnetometer = "Scan With Magnetometer";
	private static final String commandScanWithMetalDetector = "Scan With Metal Detector";
	private static final String commandDigMap = "Dig The Map";
	// The Edit MPT Menu
	private static final String commandCreateMap="Create Map";
	private static final String commandChangeFeatures="Change Feature(s)";
	private static final String commandUpdateStoragePottery="Update Storage Pottery";
	private static final String commandUpdateDecoratedPottery="Update Decorated Pottery";
	private static final String commandUpdateSubmergedPottery="Update Submerged Pottery";
	private static final String commandUpdateKilnsCharcoal="Update Kilns Charcoal";
	private static final String commandUpdateHearthsCharcoal="Update Hearths Charcoal";
	private static final String commandUpdateFerrousMetal="Update Ferrous Metal";
	private static final String commandUpdateNonFerrousMetal="Update Non-FerrousMetal";
	private static final String commandMarkHeritage="Mark Heritage(s)";
	private static final String commandConvertBase="Convert Base";
	
	// The View Menu
	private static final String commandUpdateMapSymbol = "Update Map Symbol";
	private static final String commandViewSymbolMap = "View Symbol Map";
	private static final String commandViewDefaultMap = "View Default Map";
	private static final String commandViewExcavationMap="View Excavation Map"; 
	private static final String commandViewMagnetometerMap = "View Magnetometer Map";
	private static final String commandViewMetalDetectorMap = "View Metal Detector Map";
	private static final String commandViewPotteryFindsMap = "View Pottery Finds Map";
	private static final String commandViewCharcoalFindsMap = "View Charcoal Finds Map";
	private static final String commandViewMetalworkMap = "View Metalwork Map";
	private static final String commandViewReport = "View Report";
	
	// About Menu
	private static final String commandAbout="About";

	// Menu Bar
	private JMenuBar menuBar; 					
	
	// File Menu
	private JMenu menuFile;						
	private JMenuItem menuItemLoad;				
	private JMenuItem menuItemSave;
	private JMenuItem menuItemExit;
	
	// Edit Menu
	private JMenu menuEdit;	
	// Edit ADT Menu
	private JMenuItem menuItemScanWithMagnetometer;
	private JMenuItem menuItemScanWithMetalDetector;
	private JMenuItem menuItemDigMap;
	//Edit MPT Menu
	private JMenuItem menuItemCreateMap;
	private JMenuItem menuItemChangeFeatures;
	private JMenuItem menuItemUpdateStoragePottery;
	private JMenuItem menuItemUpdateDecoratedPottery;
	private JMenuItem menuItemUpdateSubmergedPottery;
	private JMenuItem menuItemUpdateKilnsCharcoal;
	private JMenuItem menuItemUpdateHearthsCharcoal;
	private JMenuItem menuItemUpdateFerrousMetal;
	private JMenuItem menuItemUpdateNonFerrousMetal;
	private JMenuItem menuItemMarkHeritage;
	private JMenuItem menuItemConvertBase;
	
	// View Menu
	private JMenu menuView;					
	private JMenuItem menuItemUpdateMapSymbols;
	private JMenuItem menuItemViewDefaultMap;
	private JMenuItem menuItemViewSymbolMap;
	private JMenuItem menuItemViewExcavationMap;
	private JMenuItem menuItemViewMagnetometerMap;
	private JMenuItem menuItemViewMetalDetectorMap;
	private JMenuItem menuItemViewPotteryFindsMap;
	private JMenuItem menuItemViewCharcoalFindsMap;
	private JMenuItem menuItemViewMetalworkMap;
	private JMenuItem menuItemViewReport;
	
	// About Menu
	private JMenuItem menuItemAbout;
	// END OF MENUS	

	// Dialogues for File Menu	
	//BEGIN EDIT MENU
	// Dialogues for ADT Edit Menu
	private Dialog tempDialogMagnetometer;
	private Dialog tempDialogMetalDetector;
	private Dialog tempDialogDigMap;
	// Dialogues for MPT Edit Menu
	// Dialogues for Edit Menu
	private Dialog tempDialogCreateMap;
	private Dialog tempDialogChangeFeatures; 
	private Dialog tempDialogStoragePottery;
	private Dialog tempDialogDecoratedPottery;
	private Dialog tempDialogSubmergedPottery;
	private Dialog tempDialogKilnsCharcoal;
	private Dialog tempDialogHearthsCharcoal;
	private Dialog tempDialogFerrousMetal;
	private Dialog tempDialogNonFerrousMetal;
	private Dialog tempDialogMarkHeritage;
	private Dialog tempDialogConvertBase;
	//END EDIT MENU

	// Dialogues for View Menu
	private Dialog tempDialogSymbols;
	
	// The Text Area
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private Container content;
	
	//The Image GUI
	private JPanel tilePanel;
	private String guiType = null;
	private String menuOption = null;
	private JLabel statusBar = new JLabel("");
	private MapPrintController mapPrintController = null;
	private Coordinate pressedCoordinate;
	private Coordinate releasedCoordinate;
	public static MainFrame ThisMainFrame;
	
	public MainFrame()
	{
		ThisMainFrame = this;
	}
	
	public MainFrame getMainFrame()
	{
		return ThisMainFrame;
	}
	
	/**
	 * Make either the Menu ADT() Program or the Menu MPT() Program.
	 * Make the text area or the Image/Tile panel
	 * @param _menuOption either "MPT" or "ADT"
	 * @param _type either "image" or "text"
	 */
	public MainFrame(String _menuOption, String _type)
	{
		super(_menuOption + " Main Frame");
		setMenuOption(_menuOption);		// global menuOption ADT or MPT
		setGuiType(_type);		// global guiType to control image or text gui.
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		subController=new SubController();
		
		MakeBarMenus();
		
		if(getGuiType().equals("text"))
		{
			MakeTextArea();
			MakeAllDialogs(_menuOption);
			this.setSize(windowWidth, windowHeight);
		}
		else if(getGuiType().equals("image"))
		{
			content = this.getContentPane();
			tilePanel = new JPanel();
			
			getStatusBar().setText(getMenuOption() + " Program");
			//tilePanel.addMouseListener((MouseListener) this);
			//tilePanel.addMouseMotionListener((MouseMotionListener) this);
			content.add(tilePanel,BorderLayout.CENTER);
			content.add(getStatusBar(),BorderLayout.SOUTH);
			//content.addMouseListener(this);
			//content.addMouseMotionListener(this);
			
			this.add(tilePanel);
					
			mapPrintController = new MapPrintController();
			Integer _columnSize = mapPrintController.getColumnSize();
			Integer _rowSize = mapPrintController.getRowSize();
			//Integer _columnSize = 70;
			//Integer _rowSize = 30;
			
			this.tilePanel.setBackground(new Color(10,90,90));
			this.tilePanel.setLayout(new GridLayout(_rowSize,_columnSize) );
			Tile _temp = new Tile("0", _columnSize, _rowSize);
			Integer tileDimensionX = _columnSize*_temp.getTileImage().getWidth();
			Integer tileDimensionY = _rowSize*_temp.getTileImage().getHeight();
			this.tilePanel.setPreferredSize( new Dimension(tileDimensionX,tileDimensionY) );
			this.setPreferredSize(new Dimension(tileDimensionX,tileDimensionY) );
			this.setSize(new Dimension(tileDimensionX,tileDimensionY) );
			
			//Print Default Map
			//switchMenuItems(commandViewDefaultMap, null, null);
			
			this.setVisible(true);
		}
	}
	
	//GUI Paint Tile

	/**
	 * Do not input 1: any number 0-7, print regular maps (i.e scanner(s) need different method
	 * @param _displayType not 2, but any other number: 0,1,2,3,4,5,6,7
	 * @param _displayType
	 * @param _first
	 * @param _second
	 * @param _scanType "normal" "metal" or "magento" to show popup choice
	 */
	public void paintTileMap(Integer _displayType, Coordinate _first, Coordinate _second, String _scanType)
	{
		mapPrintController = new MapPrintController();
		Integer _columnSize = mapPrintController.getColumnSize();
		Integer _rowSize = mapPrintController.getRowSize();
		
		PopUpMenu popUpMenu = new PopUpMenu( getMenuOption(), getGuiType() );
		popUpMenu.setMainFrame(this);
		
		//Display for Regular stuff
		if(_scanType.equals("normal"))
		{
			buildGUIMapTiles( popUpMenu, mapPrintController.get2DArray(_displayType), _columnSize, _rowSize );
		}
		else 
		{	// Magnetometer
			if(_scanType.equals("magneto"))
			{
				buildGUIMapTiles( popUpMenu, mapPrintController.getScannerArray(_first,_second,2), _columnSize, _rowSize );
			}
			else
			{	//Metal Detector
				if(_scanType.equals("metal"))
				{
					buildGUIMapTiles( popUpMenu, mapPrintController.getScannerArray(_first,_second,1), _columnSize, _rowSize );
				}
			}
		}
		
	}
	
	/**
	 * Print the Map according to the ArrayList from MapPrintController
	 * @param _popUpMenu PopMenu for all tiles to have
	 * @param _printCharacterArray 2D<String> list from MapPrintController
	 * @param _columnSize Column size of the map (taken from MapPrintController)
	 * @param _rowSize Row size of the map (taken from MapPrintController)
	 */
	private void buildGUIMapTiles( PopUpMenu _popUpMenu, ArrayList<ArrayList<String>> _printCharacterArray, Integer _columnSize, Integer _rowSize)
	{	
		tilePanel.removeAll();
		Tile _temp;
	
		for(int row = 0; row < _rowSize; row++)
		{
			for(int col=0; col < _columnSize; col++)
			{
				_temp = new Tile( _printCharacterArray.get(row).get(col), col, row );
				_temp.setComponentPopupMenu(_popUpMenu);
				//_temp.addMouseMotionListener(this);
				_temp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                    	Tile _tile = (Tile) e.getSource();
                		
                		if(e.getButton() == 1)
                		{
                			setPressedCoordinate( _tile.getTileCoordinate() );
                    		String message = "Mouse Pressed: ";
                    		getStatusBar().setText(message + getPressedCoordinate().toString());
                		}
                    }
                    
                   	@Override
                	public void mouseReleased(MouseEvent e) {
                		// TODO Auto-generated method stub
                   		String message = "";
                   		Tile _tile = (Tile) e.getSource();
                   		//BEGIN PopUpMenu
                   		Point position = e.getLocationOnScreen();
                		_tile.getComponentPopupMenu().setLocation(position.x,position.y);
                		_tile.getComponentPopupMenu().setVisible(true);
                   		//END PopUpMenu
                		
                		if( e.getClickCount() >= 1 )
                		{
                			setReleasedCoordinate(_tile.getTileCoordinate());
                       		message = "FROM: " + getPressedCoordinate().toString() + " TO: " + _tile.getTileCoordinate().toString();
                		}
                		else
                		{
                     		message = "FROM: " + getPressedCoordinate().toString() + " TO: " + getReleasedCoordinate().toString() ;
                		}
                		message = "FROM: " + getPressedCoordinate().toString() + " TO: " + getReleasedCoordinate().toString();
                     	getStatusBar().setText(message);
                	}
                   	@Override
                	public void mouseEntered(MouseEvent e) {
                		// TODO Auto-generated method stub
                		Tile _tile = (Tile) e.getSource();
                		//BEGIN PopUpMenu
                		_tile.getComponentPopupMenu().setVisible(false);
                		//END PopUpMenu
                		String message = ": ";
                   		getStatusBar().setText(message + _tile.getTileCoordinate().toString());
               			
                   		if(e.getClickCount() >= 1)
                   		{
                   			setReleasedCoordinate(_tile.getTileCoordinate());
                   		}
                   	}
                   	/*@Override
                   	public void mouseClicked(MouseEvent e){
                   		Tile _tile = (Tile) e.getSource();
                   		if(e.getClickCount() < 1)
                   		{
	                   		setPressedCoordinate( _tile.getTileCoordinate() );
	                   		setReleasedCoordinate(_tile.getTileCoordinate());
                   		}
                   	}*/
                });
				tilePanel.add(_temp);
			} 
		} 
	
		this.tilePanel.repaint();
		this.tilePanel.validate();
	}
	
	/**
	 * Implement packing with right sequence according to JavaDocs
	 */
	public void packMe()
	{
		this.setMinimumSize(minimumSize);
		this.pack();
	}
		
	private JMenuItem makeMenuItem(String sLabel)
	{
		JMenuItem miTemp=null;
		miTemp=new JMenuItem(sLabel);
		miTemp.addActionListener(this);
		return miTemp;
	}
	
	/**
	 * The edit menu option should equal "ADT" or "MPT".
	 * Then the JMenu Item will create the appropriate Edit menu.
	 * Since that is the only menu item that changes
	 * @param _editMenuOption
	 */
	private void MakeBarMenus()
	{
		menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		
		//File Menu
		menuFile=new JMenu("File");
		//Make Menu items
		menuItemLoad=makeMenuItem(commandLoad);
		menuItemSave=makeMenuItem(commandSave);
		menuItemExit=makeMenuItem(commandExit);
		//Add the menus items that we created to its menu
		menuFile.add(menuItemLoad);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemExit);
		menuBar.add(menuFile);
		//Edit Menu
		
		//menuEdit=new JMenu("Edit");
		menuEdit = makeEditMenu(getMenuOption());
		menuBar.add(menuEdit);
		
		//View Menu
	
		menuView = makeViewMenu(getGuiType());
		menuBar.add(menuView);
		
		//About Menu Item
		
		//Make Menu items
		menuItemAbout=makeMenuItem(commandAbout);
		//Add the menus items that we created to its menu
		menuBar.add(menuItemAbout);
		
	} // End of method make menu
	
	private void MakeTextArea()
	{
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", 0, textSize));
		textArea.setEditable(false);

		scrollPane = new JScrollPane(textArea);
		content = this.getContentPane();
		content.add(scrollPane, BorderLayout.CENTER);
		subController.setTextArea(textArea);  
	}
	
	/**
	 * Based on "MPT" or "ADT" instantiate the appropriate edit menu dialogs.
	 * @param _editMenuOption
	 */
	private void MakeAllDialogs(String _editMenuOption) 
	{
		// File Menu dialogs are JChooser in subController
		//EDIT
		makeEditDialogs(_editMenuOption);
		// View Menu
		//tempDialogSymbols = new Dialog(1,commandUpdateMapSymbol,"Old Symbol","New Symbol");
		// About Menu Does not need any Dialogues
	}
	
	/**
	 * Offer the view menu on the PopUp or Regular Menu
	 * @return JMenu view items
	 */
	public JMenu makeViewMenu(String _guiType)
	{
		JMenu _menuView=new JMenu("View");
		//Make Menu items
		menuItemUpdateMapSymbols = makeMenuItem(commandUpdateMapSymbol);
		menuItemViewDefaultMap = makeMenuItem(commandViewDefaultMap);
		menuItemViewSymbolMap = makeMenuItem(commandViewSymbolMap);
		menuItemViewExcavationMap = makeMenuItem(commandViewExcavationMap);
		menuItemViewPotteryFindsMap = makeMenuItem(commandViewPotteryFindsMap);
		menuItemViewCharcoalFindsMap = makeMenuItem(commandViewCharcoalFindsMap);
		menuItemViewMetalworkMap = makeMenuItem(commandViewMetalworkMap);
		menuItemViewMagnetometerMap = makeMenuItem(commandViewMagnetometerMap);
		menuItemViewMetalDetectorMap = makeMenuItem(commandViewMetalDetectorMap);
		menuItemViewReport= makeMenuItem(commandViewReport);
		
		//Add the menus items that we created to its menu
		if(_guiType.equals("text") == true)
		{
			_menuView.add(menuItemUpdateMapSymbols);
			_menuView.add(menuItemViewDefaultMap);
			_menuView.add(menuItemViewSymbolMap);
			_menuView.add(menuItemViewExcavationMap);
			_menuView.add(menuItemViewPotteryFindsMap);
			_menuView.add(menuItemViewCharcoalFindsMap);
			_menuView.add(menuItemViewMetalworkMap);
			_menuView.add(menuItemViewMagnetometerMap);
			_menuView.add(menuItemViewMetalDetectorMap);
			_menuView.add(menuItemViewReport);
		}
		else{
			_menuView.add(menuItemViewDefaultMap);
			_menuView.add(menuItemViewExcavationMap);
			_menuView.add(menuItemViewPotteryFindsMap);
			_menuView.add(menuItemViewCharcoalFindsMap);
			_menuView.add(menuItemViewMetalworkMap);
			_menuView.add(menuItemViewMagnetometerMap);
			_menuView.add(menuItemViewMetalDetectorMap);
			_menuView.add(menuItemViewReport);
		}
		
		return _menuView;
	}
	
	/**
	 * Create JMenuItems for Edit or Right-click PopUp.
	 * @return JMenu Edit Menu
	 */
	public JMenu makeEditMenu(String _menuOption)
	{
		JMenu _menuEdit = new JMenu("Edit");
		
		if(_menuOption.equals("ADT"))
		{
			//Make ADT Menu items
			menuItemScanWithMagnetometer = makeMenuItem(commandScanWithMagnetometer);
			menuItemScanWithMetalDetector = makeMenuItem(commandScanWithMetalDetector);
			menuItemDigMap = makeMenuItem(commandDigMap);
			//Add the menus items that we created to its menu
			_menuEdit.add(menuItemScanWithMagnetometer);
			_menuEdit.add(menuItemScanWithMetalDetector);
			_menuEdit.add(menuItemDigMap);
		}
		else if(_menuOption.equals("MPT"))
		{
			//Make MPT Menu items
			menuItemCreateMap=makeMenuItem(commandCreateMap);
			menuItemChangeFeatures=makeMenuItem(commandChangeFeatures);
			menuItemUpdateStoragePottery = makeMenuItem(commandUpdateStoragePottery);
			menuItemUpdateDecoratedPottery = makeMenuItem(commandUpdateDecoratedPottery);
			menuItemUpdateSubmergedPottery = makeMenuItem(commandUpdateSubmergedPottery);
			menuItemUpdateKilnsCharcoal = makeMenuItem(commandUpdateKilnsCharcoal);
			menuItemUpdateHearthsCharcoal = makeMenuItem(commandUpdateHearthsCharcoal);
			menuItemUpdateFerrousMetal = makeMenuItem(commandUpdateFerrousMetal);
			menuItemUpdateNonFerrousMetal = makeMenuItem(commandUpdateNonFerrousMetal);
			menuItemMarkHeritage=makeMenuItem(commandMarkHeritage);
			menuItemConvertBase = makeMenuItem(commandConvertBase);
			//Add the menus items that we created to its menu
			_menuEdit.add(menuItemCreateMap);
			_menuEdit.add(menuItemChangeFeatures);
			_menuEdit.addSeparator();
			_menuEdit.addSeparator();
			_menuEdit.add(menuItemUpdateStoragePottery);
			_menuEdit.add(menuItemUpdateDecoratedPottery);
			_menuEdit.add(menuItemUpdateSubmergedPottery);
			_menuEdit.addSeparator();
			_menuEdit.addSeparator();
			_menuEdit.add(menuItemUpdateKilnsCharcoal);
			_menuEdit.add(menuItemUpdateHearthsCharcoal);
			_menuEdit.addSeparator();
			_menuEdit.addSeparator();
			_menuEdit.add(menuItemUpdateFerrousMetal);
			_menuEdit.add(menuItemUpdateNonFerrousMetal);
			_menuEdit.addSeparator();
			_menuEdit.addSeparator();
			_menuEdit.add(menuItemMarkHeritage);
			_menuEdit.addSeparator();
			_menuEdit.addSeparator();
			_menuEdit.add(menuItemConvertBase);
		}
		return _menuEdit;
	}
	
	/**
	 * Based on "MPT" or "ADT" instantiate the appropriate edit menu
	 */
	public void makeEditDialogs(String _menuOption)
	{
		//Edit Menu
		if(_menuOption.equals("ADT"))
		{
			// ADT Edit Menu (using one generic dialog)
			tempDialogMagnetometer = new Dialog(commandViewMagnetometerMap);
			tempDialogMetalDetector = new Dialog(commandViewMetalDetectorMap);
			tempDialogDigMap = new Dialog(commandDigMap);
		}
		else if(_menuOption.equals("MPT"))
		{
			// MPT Edit Menu
			tempDialogCreateMap = new Dialog(commandCreateMap,"File Name"); 
			tempDialogChangeFeatures = new Dialog(commandChangeFeatures,"Ground Type");
			tempDialogStoragePottery = new Dialog(commandUpdateStoragePottery,"Storage [Double Volume]",askDate);
			tempDialogDecoratedPottery = new Dialog(commandUpdateDecoratedPottery,"Decorated [String Description]",askDate);
			tempDialogSubmergedPottery = new Dialog(commandUpdateSubmergedPottery,"Submerged [Integer Depth]",askDate);
			tempDialogKilnsCharcoal = new Dialog(commandUpdateKilnsCharcoal,"Kilns [Integer Radius]",askDate);
			tempDialogHearthsCharcoal = new Dialog(commandUpdateHearthsCharcoal,"Hearths [Integer Length]","Hearths [Integer Width]",askDate);
			tempDialogFerrousMetal = new Dialog(commandUpdateFerrousMetal,"Ferrous [Integer Strength]",askDate);
			tempDialogNonFerrousMetal = new Dialog(commandUpdateNonFerrousMetal,"NonFerrous [String Type]",askDate);
			tempDialogMarkHeritage = new Dialog(commandMarkHeritage);
			tempDialogConvertBase = new Dialog(commandConvertBase);
		}
	}
	
	/**
	 * Start the selected program by setting the GUI to visible.
	 * Based on either text or Images, set appropriate options
	 * @param _type "text" or "image" 
	 */
	public void run(String _type)
	{
		setGuiType(_type);
		if(getGuiType().endsWith("text"))
		{
			this.setVisible(true);
		}
		else if(getGuiType().equals("image"))
		{
			this.packMe();
			this.setVisible(true);
		}
	}
	
	/**
	 * Set this dialog visibility and modal to true.
	 * This tells the GUI to display the dialog
	 * @param _dialog
	 */
	private void showDialog(Dialog _dialog)
	{
		_dialog.setModal(true);
		_dialog.setVisible(true);
	}
	
	/**
	 * For other methods to know "ADT" or "MPT"
	 * Not just in MainFrame but in PopUpMenu as well.
	 * @return the menuOption "ADT" or "MPT"
	 */
	public String getMenuOption() {
		return menuOption;
	}

	/**
	 * Remember to set so other implementations of Main Frame will make the appropriate menus.
	 * @param menuOption the menuOption to set
	 */
	public void setMenuOption(String _menuOption) {
		this.menuOption = _menuOption;
	}

	/**
	 * sets the global parameter GuiType to control which GUI to make/manipulate
	 * @param _type "image" or "text
	 */
	public void setGuiType(String _type){
		guiType = _type;
	}
	/**
	 * return the guiType ("image" or "text") entered from MPT_program or ADT_program.
	 * To control the text or Image GUI Type
	 */
	public String getGuiType(){
		return guiType;
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String command="";
		command=event.getActionCommand();
		switchMenuItems(command, null, null);
	}
	
	/**
	 * All commands tied into subController go here.
	 * @param _command commands from GUI
	 */
	public void switchMenuItems(String _command, Coordinate _first, Coordinate _second)
	{
		Coordinate passCoordinate = null;
		Integer addBy = 0;
		
		switch(_command)
		{
		//FILE MENU
		case commandLoad:
			subController.load( getMenuOption() );
			paintTileMap(0,null,null,"normal");
			break;
		case commandSave:
			subController.save();
			break;
		case commandExit:
			subController.exit();
			break;	
		//VIEW MENU
		case commandViewReport:
			subController.viewReport();
			break;
		//ABOUT MENU
		case commandAbout:
			subController.about();
			break;
			
		}
		
		if(getGuiType().equals("image"))
		{
			switch(_command)
			{
			//VIEW MENU
			case commandViewDefaultMap:
				paintTileMap(0,null,null,"normal");
				break;
			case commandViewExcavationMap:
				paintTileMap(2,null,null,"normal");
				break;
			case commandViewPotteryFindsMap:
				paintTileMap(3,null,null,"normal");
				break;
			case commandViewCharcoalFindsMap:
				paintTileMap(4,null,null,"normal");
				break;
			case commandViewMetalworkMap:
				paintTileMap(5,null,null,"normal");
				break;
			case commandViewMetalDetectorMap:
				paintTileMap(6,_first,_second,"normal");
				break;
			case commandViewMagnetometerMap:
				paintTileMap(7,_first,_second,"normal");
				break;
			//ADT EDIT
			case commandScanWithMagnetometer:
				paintTileMap(6,_first,_second,"magneto");
				break;
			case commandScanWithMetalDetector:
				paintTileMap(7,_first,_second,"metal");
				break;
			}
		}
		
		else if(getGuiType().equals("text"))
		{
			switch(_command)
			{
			//BEGIN EDIT MENU
			//EDIT ADT MENU
			case commandScanWithMagnetometer:
				showDialog(tempDialogMagnetometer);
			    passCoordinate = new Coordinate(tempDialogMagnetometer.getColumn(),tempDialogMagnetometer.getRow());
			    addBy = checkCoordinateContents(passCoordinate);
			    subController.scanWithMagnetometer(passCoordinate,addBy);
				break;
			case commandScanWithMetalDetector:
				showDialog(tempDialogMetalDetector);
				passCoordinate = new Coordinate(tempDialogMetalDetector.getColumn(),tempDialogMetalDetector.getRow());
			    addBy = checkCoordinateContents(passCoordinate);
		 		subController.scanWithMetalDetector(passCoordinate,addBy);
				break;
			case commandDigMap:	
				showDialog(tempDialogDigMap);
				passCoordinate = new Coordinate(tempDialogDigMap.getColumn(),tempDialogDigMap.getRow());
			    addBy = checkCoordinateContents(passCoordinate);
				subController.digMap(passCoordinate,addBy);
				break;
			//EDIT MPT MENU
			case commandCreateMap:
				showDialog(tempDialogCreateMap);
				passCoordinate = new Coordinate(tempDialogCreateMap.getColumn(),tempDialogCreateMap.getRow());
				subController.createMap(passCoordinate,tempDialogCreateMap.getTextFieldString());
				break;
			case commandChangeFeatures:
				showDialog(tempDialogChangeFeatures);
				passCoordinate = new Coordinate(tempDialogChangeFeatures.getColumn(),tempDialogChangeFeatures.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.changeFeatures(passCoordinate,addBy,tempDialogChangeFeatures.getGroundType());
				break;
			case commandUpdateStoragePottery:
				showDialog(tempDialogStoragePottery);
				passCoordinate = new Coordinate(tempDialogStoragePottery.getColumn(),tempDialogStoragePottery.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.updateStoragePottery(passCoordinate,addBy,tempDialogStoragePottery.getTextFieldString(),tempDialogStoragePottery.getDate());
				break;
			case commandUpdateDecoratedPottery:
				showDialog(tempDialogDecoratedPottery);
				passCoordinate = new Coordinate(tempDialogDecoratedPottery.getColumn(),tempDialogDecoratedPottery.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.updateDecoratedPottery(passCoordinate,addBy,tempDialogDecoratedPottery.getTextFieldString(),tempDialogDecoratedPottery.getDate());
				break;
			case commandUpdateSubmergedPottery:
				showDialog(tempDialogSubmergedPottery);
				passCoordinate = new Coordinate(tempDialogSubmergedPottery.getColumn(),tempDialogSubmergedPottery.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.updateSubmergedPottery(passCoordinate,addBy,tempDialogSubmergedPottery.getTextFieldString(),tempDialogSubmergedPottery.getDate());	
				break;
			case commandUpdateKilnsCharcoal:
				showDialog(tempDialogKilnsCharcoal);
				passCoordinate = new Coordinate(tempDialogKilnsCharcoal.getColumn(),tempDialogKilnsCharcoal.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.updateKilnsCharcoal(passCoordinate,addBy,tempDialogKilnsCharcoal.getTextFieldString(), tempDialogKilnsCharcoal.getDate());
				break;
			case commandUpdateHearthsCharcoal:
				showDialog(tempDialogHearthsCharcoal);
				passCoordinate = new Coordinate(tempDialogHearthsCharcoal.getColumn(),tempDialogHearthsCharcoal.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.updateHearthsCharcoal(passCoordinate,addBy,tempDialogHearthsCharcoal.getTextFieldString(),tempDialogHearthsCharcoal.getTextFieldString2(), tempDialogHearthsCharcoal.getTextFieldString3());
				break;
			case commandUpdateFerrousMetal:
				showDialog(tempDialogFerrousMetal);
				passCoordinate = new Coordinate(tempDialogFerrousMetal.getColumn(),tempDialogFerrousMetal.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.updateFerrousMetal(passCoordinate,addBy,tempDialogFerrousMetal.getTextFieldString(),tempDialogFerrousMetal.getDate());
				break;
			case commandUpdateNonFerrousMetal:
				showDialog(tempDialogNonFerrousMetal);
				passCoordinate = new Coordinate(tempDialogNonFerrousMetal.getColumn(),tempDialogNonFerrousMetal.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.updateNonFerrousMetal(passCoordinate,addBy,tempDialogNonFerrousMetal.getTextFieldString(),tempDialogNonFerrousMetal.getDate());
				break;
			case commandMarkHeritage:
				showDialog(tempDialogMarkHeritage);
				passCoordinate = new Coordinate(tempDialogMarkHeritage.getColumn(),tempDialogMarkHeritage.getRow());
				addBy = checkCoordinateContents(passCoordinate);
				subController.markHeritage(passCoordinate,addBy);
				break;
			case commandConvertBase:
				showDialog(tempDialogConvertBase);
				subController.convertBase(tempDialogConvertBase.getColumn(), tempDialogConvertBase.getRow());
				break;
			//END EDIT MENU
			//VIEW MENU
			case commandUpdateMapSymbol:
				showDialog(tempDialogSymbols);
				subController.updateMapSymbols(tempDialogSymbols.getTextFieldString(),tempDialogSymbols.getTextFieldString2());
				break;
			case commandViewDefaultMap:
				subController.viewDefaultMap();
				break;
			case commandViewSymbolMap:
				subController.viewSymbolMap();
				break;
			case commandViewExcavationMap:
				subController.viewExcavationMap();
				break;
			case commandViewPotteryFindsMap:
			    subController.viewPotteryFindsMap();
				break;
			case commandViewCharcoalFindsMap:
				subController.viewCharcoalFindsMap();
				break;
			case commandViewMetalworkMap:
			    subController.viewMetalworkMap();
				break;
			case commandViewMagnetometerMap:
				subController.viewMagnetometerMap();
				break;
			case commandViewMetalDetectorMap:
				subController.viewMetalDetectorMap();
				break;
			}// End of Switch Case
		}// end IF.equals"image"
	}

	/**
	 * @return the pressedCoordinate
	 */
	public Coordinate getPressedCoordinate() {
		return pressedCoordinate;
	}

	/**
	 * @param pressedCoordinate the pressedCoordinate to set
	 */
	public void setPressedCoordinate(Coordinate pressedCoordinate) {
		this.pressedCoordinate = pressedCoordinate;
	}

	/**
	 * @return the releasedCoordinate
	 */
	public Coordinate getReleasedCoordinate() {
		return releasedCoordinate;
	}

	/**
	 * @param releasedCoordinate the releasedCoordinate to set
	 */
	public void setReleasedCoordinate(Coordinate releasedCoordinate) {
		this.releasedCoordinate = releasedCoordinate;
	}

	/**
	 * @return the statusBar
	 */
	public JLabel getStatusBar() {
		return statusBar;
	}

	/**
	 * @param statusBar the statusBar to set
	 */
	public void setStatusBar(JLabel statusBar) {
		this.statusBar = statusBar;
	}
	
	/**
	 * Decide based on row and/or column entered in dialog to make changes by row, column or row&column.
	 * @param _passCoordinate a coordinate generated by guiDialogs
	 * @return 1, 2, or 3
	 */
	public Integer checkCoordinateContents(Coordinate _passCoordinate)
	{
		Integer addBy;
		if ( (_passCoordinate.getIntegerColumn().equals(-1)) )
		{
			addBy = 1; // row
		}
		else 
		{
			if ( (_passCoordinate.getRow().equals(-1))  )
			{
				addBy = 2; // column
			}
			else 
			{
				addBy = 3; // grid
			}
		}
		return addBy;
	}

}
