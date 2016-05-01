Course Project - Phase 3 - Archaeology Game
===========

A map based game where a user clicks to dig, or excavate certain tiles of the map to find gold. The coding started the game
inside the console, then as the class progressed we moved the output into a GUI using Java's Swing package.

  1. The goal of this third phase is to cover the graphical components of the Java language, including
    1. Swing
    2. Mouse control
    3. Image Drawing
    4. Special Menus
    5. Special Dialogs
    6. UML Sequence Diagrams

2. Previous Objectives:
  1. Phase 1 and Phase 2 of the project included this material:
    1. Use of basic data types
    2. Controls
    3. Simple concrete classes
    4. Files
    5. User Input
    6. Screen Output
    7. Exception handling
    8. Collection types
    9. Class Properties
    10. Inheritance
    11. Interfaces
    12. Polymorphism
    13. Static Members
    14. Exception Handling
    15. Beginning Graphical User Interface (GUI)
    16. Menus and Menu Items
    17. Basic Dialog
    18. Text Fields and Scrollbars
    19. The Subcontroller Concept
    20. UML Class Diagram

1. Problem: 
  1. Create two programs: one will be a map creator program MPT (Map Population Tool), and the other will be a map reader program ADT(Archaeological Dig Tool). These programs are to be built in an Object‐Oriented manner. In addition, two sequence diagrams must be created, one for each tool.

2. Diagram Instructions:
  1. For the Map Population Tool, create a sequence diagram for the adding of a second gold item. For the Archaeological Dig Tool, create a sequence diagram for digging in an area marked “Heritage”

3. The Graphical Update:
  1. Enough of both tools have been created with Phase 1 and Phase 2. Instead of adding more menu items, it is time to have more graphical components.
  2. Instead of having text characters printed to the screen, a GridLayout of Components will be used instead. For example, a TileComponent can be created that is a part of the GUI, and the Tile Component can be assigned a graphic file to represent a symbol. As a result, the three types of maps (Feature, Boolean, and Count) have changed.

4. The Feature Map:
  1. The Feature Map has the characters for the types of finds replaced with a colored tile to represent the various types. As before, each feature can be unexcavated or excavated.

5. The Boolean Map:
  1. The Boolean Map has the characters for true, false, and unscanned replaced with a black tile or a white tile to represent the various types excavated. A gray tile marks an area as being unscanned with a Geophysical instrument. As before, each square can be scanned, and have a true or false response. In addition, a unknown symbol must exist to replace the whitespace on the character map.

6. The Count Map:
  1. The Count Map has the characters for the number of finds replaced with a colored tile to represent the number of the type(pottery, charcoal, metal) found in an area. As before, each square can be dug, and have a colored tile to represent the number of finds. In addition, a unknown symbol must exist to replace the whitespace on the character map.

7. The Mouse Update:
  1. Punching in rows and columns is hard to use, hard to see, and worse, boring. Instead of putting in rows and columns, the same sequence can be done by using the mouse. This time, your program can select an area on the map, and then a right‐click will open a pop‐up menu. This can be done by working out co‐ordinate systems, or by having each Tile component be a GUI component that can listen for a mouse‐click. The row and column for the activity should come from the calculations from the mouse instead of from a dialog box.

8. Update Previous features
  1. All previous features must be active in the tools. For the Map Excavation Tool, this will include the following components.
    1. On MPT
      1. Load File: The Load must use the Swing JFileDialog
      2. Save File: The Save must use the Swing JFileDialog
      3. Single Item of Gold: A message dialog must report that the gold item has been replaced.
      4. The About button: The About button must open a message dialog with the same information.
    2. On ADT:
      1. Load File: The Load must use the Swing JFileDialog
      2. Save File: The Save must use the Swing JFileDialog
      3. Heritage Exception: A message Dialog must report the attempt to dig a heritage area
      4. Age‐of‐Site Report: A dialog box with a text area should now show the Finds report.
      5. The About button: The About button must open a message dialog with the same information.
