package gui.phase3;

import gui.Dialog;
import gui.phase2.SubController;
import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import Archeology.v2.Map.Coordinate;

public class PopUpMenu extends JPopupMenu implements ActionListener 
{
	private static final boolean DEBUG = false;
	private static final long serialVersionUID = 1L; 
	private SubController subController = new SubController();
	private MainFrame parent = new MainFrame();
	
	//private JMenu edit,view;
	private static final String askDate = "Enter Date: ";
	// The Edit ADT Menu
	private static final String commandScanWithMagnetometer = "Scan With Magnetometer";
	private static final String commandScanWithMetalDetector = "Scan With Metal Detector";
	private static final String commandDigMap = "Dig The Map";
	// The Edit MPT Menu
	private static final String commandChangeFeatures="Change Feature(s)";
	private static final String commandUpdateStoragePottery="Update Storage Pottery";
	private static final String commandUpdateDecoratedPottery="Update Decorated Pottery";
	private static final String commandUpdateSubmergedPottery="Update Submerged Pottery";
	private static final String commandUpdateKilnsCharcoal="Update Kilns Charcoal";
	private static final String commandUpdateHearthsCharcoal="Update Hearths Charcoal";
	private static final String commandUpdateFerrousMetal="Update Ferrous Metal";
	private static final String commandUpdateNonFerrousMetal="Update Non-FerrousMetal";
	private static final String commandMPTDigMap = "Set Excavation(s)";
	private static final String commandMarkHeritage="Mark Heritage(s)";
	
	// Edit ADT Menu
	private JMenuItem menuItemScanWithMagnetometer;
	private JMenuItem menuItemScanWithMetalDetector;
	private JMenuItem menuItemDigMap;
	//Edit MPT Menu
	private JMenuItem menuItemChangeFeatures;
	private JMenuItem menuItemUpdateStoragePottery;
	private JMenuItem menuItemUpdateDecoratedPottery;
	private JMenuItem menuItemUpdateSubmergedPottery;
	private JMenuItem menuItemUpdateKilnsCharcoal;
	private JMenuItem menuItemUpdateHearthsCharcoal;
	private JMenuItem menuItemUpdateFerrousMetal;
	private JMenuItem menuItemUpdateNonFerrousMetal;
	private JMenuItem menuItemSetExcavation;
	private JMenuItem menuItemMarkHeritage;
	//BEGIN EDIT DIALOGS
	// Dialogues for ADT Edit Menu
	private Dialog tempDialogMagnetometer;
	private Dialog tempDialogMetalDetector;
	private Dialog tempDialogDigMap;
	// Dialogues for MPT Edit Menu
	// Dialogues for Edit Menu
	private Dialog tempDialogChangeFeatures;
	private Dialog tempDialogStoragePottery;
	private Dialog tempDialogDecoratedPottery;
	private Dialog tempDialogSubmergedPottery;
	private Dialog tempDialogKilnsCharcoal;
	private Dialog tempDialogHearthsCharcoal;
	private Dialog tempDialogFerrousMetal;
	private Dialog tempDialogNonFerrousMetal;
	private Dialog tempDialogMarkHeritage;
	//END EDIT DIALOGS
	
	//private String menuType; 
	
	private JMenuItem makeMenuItem(String sLabel)
	{
		JMenuItem miTemp=null;
		miTemp=new JMenuItem(sLabel);
		miTemp.addActionListener(this);
		return miTemp;
	}
	
	public void makeDialogs(String _menuType,Coordinate _coordinate1, Coordinate _coordinate2)
	{
		if(_menuType.equals("ADT"))
		{
			tempDialogMagnetometer = new Dialog(_coordinate1, _coordinate2,commandScanWithMagnetometer);
			tempDialogMetalDetector = new Dialog(_coordinate1, _coordinate2,commandScanWithMetalDetector);
			tempDialogDigMap = new Dialog(_coordinate1, _coordinate2,commandDigMap);
		}
		else if(_menuType.equals("MPT"))
		{
			tempDialogChangeFeatures = new Dialog(_coordinate1, _coordinate2,commandChangeFeatures,"Ground Type");
			tempDialogStoragePottery = new Dialog(_coordinate1, _coordinate2,commandUpdateStoragePottery,"Storage [Double Volume]",askDate);
			tempDialogDecoratedPottery = new Dialog(_coordinate1, _coordinate2,commandUpdateDecoratedPottery,"Decorated [String Description]",askDate);
			tempDialogSubmergedPottery = new Dialog(_coordinate1, _coordinate2,commandUpdateSubmergedPottery,"Submerged [Integer Depth]",askDate);
			tempDialogKilnsCharcoal = new Dialog(_coordinate1, _coordinate2,commandUpdateKilnsCharcoal,"Kilns [Integer Radius]",askDate);
			tempDialogHearthsCharcoal = new Dialog(_coordinate1, _coordinate2,commandUpdateHearthsCharcoal,"Hearths [Integer Length]","Hearths [Integer Width]",askDate);
			tempDialogFerrousMetal = new Dialog(_coordinate1, _coordinate2,commandUpdateFerrousMetal,"Ferrous [Integer Strength]",askDate);
			tempDialogNonFerrousMetal = new Dialog(_coordinate1, _coordinate2,commandUpdateNonFerrousMetal,"NonFerrous [String Type]",askDate);
			tempDialogDigMap = new Dialog(_coordinate1, _coordinate2,commandMPTDigMap);
			tempDialogMarkHeritage = new Dialog(_coordinate1, _coordinate2,commandMarkHeritage);
		}
	}
	
	public PopUpMenu(String _menuType, String _guiType)
	{
		//setMenuType(_menuType);
		
		if(_menuType.equals("ADT"))
		{
			//Make ADT Menu items
			menuItemScanWithMagnetometer = makeMenuItem(commandScanWithMagnetometer);
			menuItemScanWithMetalDetector = makeMenuItem(commandScanWithMetalDetector);
			menuItemDigMap = makeMenuItem(commandDigMap);
			//Add the menus items that we created to its menu
			this.add(menuItemScanWithMagnetometer);
			this.add(menuItemScanWithMetalDetector);
			this.add(menuItemDigMap);
			menuItemScanWithMagnetometer.addActionListener(this);
			menuItemScanWithMetalDetector.addActionListener(this);
			menuItemDigMap.addActionListener(this);
		}
		else if(_menuType.equals("MPT"))
		{
			//Make MPT Menu items
			menuItemChangeFeatures=makeMenuItem(commandChangeFeatures);
			menuItemUpdateStoragePottery = makeMenuItem(commandUpdateStoragePottery);
			menuItemUpdateDecoratedPottery = makeMenuItem(commandUpdateDecoratedPottery);
			menuItemUpdateSubmergedPottery = makeMenuItem(commandUpdateSubmergedPottery);
			menuItemUpdateKilnsCharcoal = makeMenuItem(commandUpdateKilnsCharcoal);
			menuItemUpdateHearthsCharcoal = makeMenuItem(commandUpdateHearthsCharcoal);
			menuItemUpdateFerrousMetal = makeMenuItem(commandUpdateFerrousMetal);
			menuItemUpdateNonFerrousMetal = makeMenuItem(commandUpdateNonFerrousMetal);
			menuItemSetExcavation=makeMenuItem(commandMPTDigMap);
			menuItemMarkHeritage=makeMenuItem(commandMarkHeritage);
			//Add the menus items that we created to its menu
			this.add(menuItemChangeFeatures);
			this.addSeparator();
			this.addSeparator();
			this.add(menuItemUpdateStoragePottery);
			this.add(menuItemUpdateDecoratedPottery);
			this.add(menuItemUpdateSubmergedPottery);
			this.addSeparator();
			this.addSeparator();
			this.add(menuItemUpdateKilnsCharcoal);
			this.add(menuItemUpdateHearthsCharcoal);
			this.addSeparator();
			this.addSeparator();
			this.add(menuItemUpdateFerrousMetal);
			this.add(menuItemUpdateNonFerrousMetal);
			this.addSeparator();
			this.addSeparator();
			this.add(menuItemSetExcavation);
			this.add(menuItemMarkHeritage);
			
			menuItemChangeFeatures.addActionListener(this);
			menuItemUpdateStoragePottery.addActionListener(this);
			menuItemUpdateDecoratedPottery.addActionListener(this);
			menuItemUpdateSubmergedPottery.addActionListener(this);
			menuItemUpdateKilnsCharcoal.addActionListener(this);
			menuItemUpdateHearthsCharcoal.addActionListener(this);
			menuItemUpdateFerrousMetal.addActionListener(this);
			menuItemUpdateNonFerrousMetal.addActionListener(this);
			menuItemSetExcavation.addActionListener(this);
			menuItemMarkHeritage.addActionListener(this);
		}
		
	}
	
	/*private void setMenuType(String _menuType) {
		// TODO Auto-generated method stub
		menuType = _menuType;
	}
	
	private String getMenuType()
	{
		return menuType;
	} */

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		
		makeDialogs(parent.getMenuOption(), getGridClicked(),getGridReleased());
		
		switchMenuItems(e.getActionCommand(), getGridClicked(),getGridReleased());
	}
	
	/**
	 * All commands tied into subController go here.
	 * @param _command commands from GUI
	 */
	public void switchMenuItems(String _command, Coordinate _coordinate1, Coordinate _coordinate2)
	{
		switch(_command)
		{
		//BEGIN EDIT MENU
		//EDIT ADT MENU
		case commandScanWithMagnetometer:
			showDialog(tempDialogMagnetometer);
			if(tempDialogMagnetometer.getCancel() == false)
			{
				parent.switchMenuItems(commandScanWithMagnetometer,_coordinate1, _coordinate2);
			}
			break;
		case commandScanWithMetalDetector:
			showDialog(tempDialogMetalDetector);
			if(tempDialogMetalDetector.getCancel() == false)
			{
				parent.switchMenuItems(commandScanWithMetalDetector,_coordinate1, _coordinate2);
			}
			break;
		case commandDigMap:	
			showDialog(tempDialogDigMap);
			if(tempDialogDigMap.getCancel() == false)
			{
				subController.digMap(_coordinate1, _coordinate2);
				subController.save("PopUp");
				if(DEBUG)
				{
					parent.paintTileMap(2, null, null, "normal");
				}
				else
				{
					parent.paintTileMap(0, null, null, "normal");
				}
			}
			hideDialog(tempDialogDigMap);
			break;
		//EDIT MPT MENU
		case commandChangeFeatures:
			showDialog(tempDialogChangeFeatures);
			if(tempDialogChangeFeatures.getCancel() == false)
			{
				subController.changeFeatures(_coordinate1, _coordinate2,tempDialogChangeFeatures.getGroundType());
			}
			hideDialog(tempDialogChangeFeatures);
			break;
		case commandUpdateStoragePottery:
			showDialog(tempDialogStoragePottery);
			if(tempDialogStoragePottery.getCancel() == false)
			{
				subController.updateStoragePottery(_coordinate1, _coordinate2,tempDialogStoragePottery.getTextFieldString(),tempDialogStoragePottery.getDate());
			}
			hideDialog(tempDialogStoragePottery);
			break;
		case commandUpdateDecoratedPottery:
			showDialog(tempDialogDecoratedPottery);
			if(tempDialogDecoratedPottery.getCancel() == false)
			{
				subController.updateDecoratedPottery(_coordinate1, _coordinate2,tempDialogDecoratedPottery.getTextFieldString(),tempDialogDecoratedPottery.getDate());
			}
			hideDialog(tempDialogDecoratedPottery);
			break;
		case commandUpdateSubmergedPottery:
			showDialog(tempDialogSubmergedPottery);
			if(tempDialogSubmergedPottery.getCancel() == false)
			{
				subController.updateSubmergedPottery(_coordinate1, _coordinate2,tempDialogSubmergedPottery.getTextFieldString(),tempDialogSubmergedPottery.getDate());
			}
			hideDialog(tempDialogSubmergedPottery);
			break;
		case commandUpdateKilnsCharcoal:
			showDialog(tempDialogKilnsCharcoal);
			if(tempDialogKilnsCharcoal.getCancel() == false)
			{
				subController.updateKilnsCharcoal(_coordinate1, _coordinate2,tempDialogKilnsCharcoal.getTextFieldString(), tempDialogKilnsCharcoal.getDate());
			}
			hideDialog(tempDialogKilnsCharcoal);
			break;
		case commandUpdateHearthsCharcoal:
			showDialog(tempDialogHearthsCharcoal);
			if(tempDialogHearthsCharcoal.getCancel() == false)
			{
				subController.updateHearthsCharcoal(_coordinate1, _coordinate2,tempDialogHearthsCharcoal.getTextFieldString(),tempDialogHearthsCharcoal.getTextFieldString2(), tempDialogHearthsCharcoal.getTextFieldString3());
			}
			hideDialog(tempDialogHearthsCharcoal);
			break;
		case commandUpdateFerrousMetal:
			showDialog(tempDialogFerrousMetal);
			if(tempDialogFerrousMetal.getCancel() == false)
			{
				subController.updateFerrousMetal(_coordinate1, _coordinate2,tempDialogFerrousMetal.getTextFieldString(),tempDialogFerrousMetal.getDate());
			}
			hideDialog(tempDialogFerrousMetal);
			break;
		case commandUpdateNonFerrousMetal:
			showDialog(tempDialogNonFerrousMetal);
			if(tempDialogNonFerrousMetal.getCancel() == false)
			{
				subController.updateNonFerrousMetal(_coordinate1, _coordinate2,tempDialogNonFerrousMetal.getTextFieldString(),tempDialogNonFerrousMetal.getDate());
			}
			hideDialog(tempDialogNonFerrousMetal);
			break;
		case commandMPTDigMap:	
			showDialog(tempDialogDigMap);
			
			subController.digMap(_coordinate1, _coordinate2, "MPT");
				
			hideDialog(tempDialogDigMap);
			break;
		case commandMarkHeritage:
			showDialog(tempDialogMarkHeritage);
			if(tempDialogMarkHeritage.getCancel() == false)
			{
				subController.markHeritage(_coordinate1, _coordinate2);
				subController.save("PopUp");
			}
			hideDialog(tempDialogMarkHeritage);
			break;
		//END EDIT MENU
		}// end Switch
	}
	
	/**
	 * Set this dialog visibility and modal to true.
	 * This tells the GUI to display the dialog
	 * @param _dialog Dialog to display to user
	 */
	private void showDialog(Dialog _dialog)
	{
		_dialog.setModal(true);
		_dialog.setVisible(true);
	}
	
	/**
	 * Try to hide the dialog after display
	 * @param _dialog
	 */
	private void hideDialog(Dialog _dialog)
	{
		//_dialog.setModal(false);
	//	_dialog.setVisible(false);
	}
	
	public void setMainFrame(MainFrame _pointer)
	{
		parent = _pointer;
	}
	
	/**
	 * get Pressed from Main Frame
	 * @return Coordinate pressed on tile in Main Frame
	 */
	public Coordinate getGridClicked()
	{
		return parent.getPressedCoordinate();
	}
	
	/**
	 * get Released from Main Frame
	 * @return Coordinate pressed on tile Main Frame
	 */
	public Coordinate getGridReleased()
	{
		return parent.getReleasedCoordinate();
	}
}
