package server;

import common.ChatIF;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerConsole implements ChatIF {

    private static final int DEFAULT_PORT = 5555;
    private GeneralServer generalServer;

    public ServerConsole(int port){
        generalServer = new GeneralServer(port,this);
    }

    @Override
    public void display(String message) {
        System.out.println("> " + message);
    }

    public void accept(){
        try
        {
            BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true)
            {
                message = fromConsole.readLine();
                generalServer.handleMessageFromServerUI(message);
            }
        }
        catch (Exception ex)
        {
            System.out.println
                    ("Unexpected error while reading from console!");
        }
    }

    public static void main(String[] args){
        int port; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT; //Set port to 5555
        }

        ServerConsole serverConsole = new ServerConsole(port);
        serverConsole.accept();


    }
}
