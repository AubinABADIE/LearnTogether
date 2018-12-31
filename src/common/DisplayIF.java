package common;
public interface DisplayIF {

    /**
     * Method that when overriden is used to display objects onto
     * a UI.
     */
    void display(String message);
    void setState(String cmd);
    void showLogin(boolean isConnected, int id, String role);
}
