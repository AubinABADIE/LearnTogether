package common;

public interface ClientIF {
    void handleMessageFromServer(Object msg);

    void connectionClosed();

    void connectionException(Exception exception);

    void connectionEstablished();
}
