package gameFactory;

public class CommandState {
	private Command previousCommand;
	public CommandState() {}
	public void executeCommand(Command aCommand) {
		aCommand.execute();
		previousCommand = aCommand;
	}
	public boolean canUndo() {
		return previousCommand != null;
	}
	public void undo() {
			previousCommand.undo();
			previousCommand = null;
	}
	public boolean isUndoAvailable() {
		return true;
	}
}

