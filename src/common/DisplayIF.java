package common;
public interface DisplayIF {
	
	/**
	   * Method that when overriden is used to display objects onto
	   * a UI.
	   */
	  public abstract void display(String message);

	  public abstract void displayCommand(String cmd);
	  public abstract void showLogin(boolean isConnected, int id, String role);
}
