package gui;

public class ADT_Program 
{
	public static void main(String[] args) 
	{
		String programOption = "ADT";
		String programType = "image";	// or "text"
		MainFrame adtProgram = new MainFrame(programOption,programType);
		adtProgram.run(programType);
	}
}
