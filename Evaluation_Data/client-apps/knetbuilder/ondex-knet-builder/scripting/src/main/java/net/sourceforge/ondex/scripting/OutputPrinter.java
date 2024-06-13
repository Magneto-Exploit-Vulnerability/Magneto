package net.sourceforge.ondex.scripting;

/**
 * 
 * @author lysenkoa
 * All classes that can deal with output implement this interface
 */
public interface OutputPrinter {

	
	/**
	 * Takes output
	 * @param output output to process
	 */
	public abstract String print(Object ... output);
	public abstract void printAndPrompt(String output);
	public abstract void prompt();
    public abstract void setDefaultCommnadCompletenessStrategy();
    public abstract void setCommandCompletenessStrategy(CommandCompletenessStrategy ccs);
}
