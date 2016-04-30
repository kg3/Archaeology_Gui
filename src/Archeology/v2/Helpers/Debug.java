package Archeology.v2.Helpers;



import java.security.SecureRandom;
import java.util.ArrayList;
import Archeology.v2.Map.*;
import Archeology.v2.Artifact.*;



public class Debug
{

	 // ####### SIMPLEY REMOVE THE /*  */ PARTS TO TEST EACH THING 
	 // ####### SIMPLEY REMOVE THE /*  */ PARTS TO TEST EACH THING 
	 // ####### SIMPLEY REMOVE THE /*  */ PARTS TO TEST EACH THING 
	 // ####### SIMPLEY REMOVE THE /*  */ PARTS TO TEST EACH THING 
	 // ####### SIMPLEY REMOVE THE /*  */ PARTS TO TEST EACH THING 
	 // ####### SIMPLEY REMOVE THE /*  */ PARTS TO TEST EACH THING 
	 // ####### SIMPLEY REMOVE THE /*  */ PARTS TO TEST EACH THING 
	
	public static void main(String[] args)
	{
		Boolean DEBUG1 = true;
		Boolean DEBUG2 = true;
		Boolean DEBUG3 = true;
		Boolean DEBUG4 = true;
		Boolean DEBUG5 = true;
		
		 Map TheMap = new Map("Tikal2.csv");
		 ArrayList<Artifact> passNew = null;
		 MapPrintController PrintMap = new MapPrintController();
		
		// ########################## NEW NEW NEW NEW NEW  DEBUG STUFF #################################
	
		 /* THIS IS A NOTE ABOUT HOW THE COORDINATE SYSTEM WORKS
		   
			 THIS IS WHAT ALL KINDS OF DRAGS WILL LOOK LIKE
			 
			 CASE1: TOP LEFT TO BOTTOM RIGHT 
			 GIVE COORDINATES FIRST SQUARE (3,10) , SECOND SQUARE (20,50)
			 
			 CASE 2: BOTTOM RIGHT TO TOP LEFT
			 GIVE COORDINATES FIRST SQUARE (20,50) , SECOND SQUARE (3,10)
			 
			 CASE 3: BOTTOM LEFT TO TOP RIGHT 
			 GIVE COORDINATES FIRST SQUARE (20,10) , SECOND SQUARE (3,50)
			 
			 CASE 3: TOP RIGHT TO BOTTOM LEFT
			 GIVE COORDINATES FIRST SQUARE (3,50) , SECOND SQUARE (20,10)
			 */
		 
		 Coordinate firstSquare = new Coordinate(3,15);
		 Coordinate secondSquare = new Coordinate(1,40);
		// DoubleCoordinate tempDoubleCoordinate = new DoubleCoordinate(firstSquare, secondSquare);
		 
		 			// This is the test FOR CHANGING FEATURES
		if (!DEBUG1)
		{
			Character _surfaceType = 'S';
			Coordinate firstSquare0 = new Coordinate(3,0);
			Coordinate secondSquare0 = new Coordinate(13,20);
			TheMap.doStuff(firstSquare0,secondSquare0, 4, true, null, _surfaceType);
			System.out.printf("%s", PrintMap.getMapDisplay(1) );
			
		}
		 			// This is the test of the heritage tool
		if (!DEBUG2)
		{
		 	Coordinate firstSquare1 = new Coordinate(1,1);
			Coordinate secondSquare1 = new Coordinate(10,20);
		    TheMap.doStuff(firstSquare1, secondSquare1, 3, true, null,null);
		}
		 
		 			// This is the test the for Dig tool
		if (DEBUG3)
		{
		    Coordinate firstSquare2 = new Coordinate("A",3);
			Coordinate secondSquare2 = new Coordinate("D",9);
			System.out.printf("%s", TheMap.doStuff(firstSquare2, secondSquare2, 2, true, null, null));
		//	System.out.printf("%s", TheMap.setExcavations(firstSquare2, secondSquare2, true));
			System.out.printf("%s", PrintMap.getMapDisplay(2) );
		}	
					// This is the test for the scan tool
		if (!DEBUG4)
		{
		 	Coordinate firstSquare3 = new Coordinate(0,0);
			Coordinate secondSquare3 = new Coordinate("K",10);
			
	//	    System.out.printf("%s", PrintMap.scanner(firstSquare3, secondSquare3, 1) );
	//	    System.out.printf("%s", PrintMap.scanner(firstSquare3, secondSquare3, 2) );
		}
		 
		 			// This is the test for appending finds
		if (!DEBUG5)
		{  
		    passNew = new ArrayList<Artifact>();
		    String date = "400";
		    // For Pottery
		    String volume = "32.21";
		    String description = "Superb";
		    String depth = "23";
		    
		    String length = "12.00";
		    String height = "11.12";
		    
		   
		    
			Storage temp = new Storage(Double.valueOf(volume),Integer.valueOf(date));
			Decorated temp1 = new Decorated(description,Integer.valueOf(date));
			Submerged temp2 = new Submerged(Integer.valueOf(depth),Integer.valueOf(date));
			
			Hearth temp3 = new Hearth(Double.valueOf(length),Double.valueOf(height),Integer.valueOf(date));
		
			passNew.add(temp);
			TheMap.doStuff(firstSquare, secondSquare, 1, true, passNew, null);
			System.out.printf("%s", PrintMap.getMapDisplay(3) );
			
			passNew.add(temp1);
			TheMap.doStuff(firstSquare, secondSquare, 1, true, passNew, null);
			System.out.printf("%s", PrintMap.getMapDisplay(3) );
		   
			passNew.add(temp2);
			TheMap.doStuff(firstSquare, secondSquare, 1, true, passNew, null);
			System.out.printf("%s", PrintMap.getMapDisplay(3) );
		    
			passNew.add(temp3);
			TheMap.doStuff(firstSquare, secondSquare, 1, true, passNew, null);
			System.out.printf("%s", PrintMap.getMapDisplay(4));
		    }
		    
		// ########################## END OF NEW NEW NEW NEW NEW DEBUG STUFF #################################
		// ########################## END OF NEW NEW NEW NEW NEW DEBUG STUFF #################################
		// ########################## END OF NEW NEW NEW NEW NEW DEBUG STUFF #################################
		// ########################## END OF NEW NEW NEW NEW NEW DEBUG STUFF #################################
		// ########################## END OF NEW NEW NEW NEW NEW DEBUG STUFF #################################
		 
		 
		 
		 
		// ########################## OLD DEBUG STUFF #################################
		 //	Print Whole Array for debugging
		 //	System.out.printf("%s", TheMap.PrintDualArray("Report") );
		
		
		//Change Map WORKS!
		//TheMap.LoadMap("Tikal2.csv");
		//System.out.printf("%s", TheMap.PrintDualArray() );
		
		//Persist Map
	//	TheMap.PersistMap("persistFile.csv");
		
	//	System.out.printf("%s", TheMap.LoadMap("persistFile.csv") );
		
		//Persist to Same filename WORKS!
		//TheMap.PersistMap( TheMap.getFileName() );
		
		//Display all Types WORKS!
		
	//	System.out.printf("%s", PrintMap.getMapDisplay(2) );

		
		//BEGIN DIG TOOL WORKS!
	//	Coordinate digThisGrid = new Coordinate(1,1);	// for testing, pass a heritage grid
	//	System.out.printf("%s", TheMap.setExcavations(digThisGrid, 1, true) );
	//	System.out.printf("%s", TheMap.setExcavations(digThisGrid, 2, true) );
	//	System.out.printf("%s", TheMap.setExcavations(digThisGrid, 3, true) );
	
	//	System.out.printf("%s", PrintMap.getMapDisplay(2) );
		//END DIG TOOL
		/**
		
		//BEGIN SCAN TOOL WORKS!
		/**
		Coordinate scanThisGrid = new Coordinate("B",1);  // for testing, pass a heritage grid
		System.out.printf("%s", PrintMap.scanner(1, scanThisGrid, 1) );
		
		System.out.printf("%s", PrintMap.scanner(1, scanThisGrid, 2) );
		
		System.out.printf("%s", PrintMap.scanner(1, scanThisGrid, 3) );
		
		System.out.printf("%s", PrintMap.scanner(2, scanThisGrid, 1) );
		
		System.out.printf("%s", PrintMap.scanner(2, scanThisGrid, 2) );
		
		System.out.printf("%s", PrintMap.scanner(2, scanThisGrid, 3) );
		//END SCAN TOOL
		*/
		//GOLD CHECK WORKS!
		//Call this everytime a non-ferrous metal is added
//		System.out.printf("%s",TheMap.goldCheck() );
		
		//ADD/CHANGE Heritage
//		Coordinate changeThisHeritage = new Coordinate("A",0);
//		TheMap.setHeritage(changeThisHeritage, 3, true);
		
		//Print Whole Array for debugging
//		System.out.printf("%s", TheMap.PrintReport("Report") );

//		System.out.printf("%s",PrintMap.getMapDisplay(1));
		
		// Append New Finds to Coordinate
		
//		Integer addSize = 4;
//		Integer randomDate = randomNumbers(500,1000);
		// Generate a list to pass based on size
//		ArrayList<Artifact> passNew = new ArrayList<Artifact>(addSize);
	
		/*
		for(int i=0; i < addSize; i++)
		{	// For just a date, can be any artifact
			// when adding different things, we'll need to
			// set the correct type with the right constructors
			PotteryArtifact temp = new PotteryArtifact(randomDate);
			passNew.add(temp);
		}
		Coordinate setThisOrTheseNodesOrNode = new Coordinate(0,1); //col,row
		// magical line to set any array of the 3 types
		TheMap.appendFinds(setThisOrTheseNodesOrNode,1,passNew,true);
		TheMap.setExcavations(setThisOrTheseNodesOrNode, 1, true);
		//Display Changes
		System.out.printf("\n\nAll the Changes made would added from addSize: %d",addSize);
		//Persist Map After Any Changes we want to keep
		TheMap.PersistMap("persistFile.csv");	//rename to same file as load, this is just a debug to show difference
		//Change Ground Type (Set Feature)
		TheMap.getMapNodesArray().get(0).get(0).setGround(Natural.getDefault());
		*/
				
			
		//Change Symbols
		/* doesn't work right now
		Natural newSymbols = new Natural('&','^');
		
		newSymbols.setSymbExcavated('&');
		newSymbols.setSymbUnExcavated('^');
		*/

		//TheMap.PrintDualArray();
		
	}
	
	public static Integer randomNumbers(Integer min, Integer max)
	{	// formula to generate #'s within a given range
		// default java behavior is not dynamic
		SecureRandom randomNumbers = new SecureRandom();	// a very random number
		Integer generatedNumber = randomNumbers.nextInt( max - min + 1) + min;
		return generatedNumber;
	}
}