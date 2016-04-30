package gui;

public class MPT_Program 
{
	public static void main(String[] args) 
	{
		String programOption = "MPT";
		String programType = "image";	// "image" or "text"
		MainFrame mptProgram = new MainFrame(programOption,programType);
		mptProgram.run(programType);
	}
}
