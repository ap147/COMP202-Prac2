import java.net.*;
import java.io.*;
import java.util.*;


class HttpServerSession extends Thread
{
    private Socket AcceptedSocket;
    public HttpServerSession(Socket _AcceptedSocket)
    {
        AcceptedSocket = _AcceptedSocket;
    }

    public void run()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(AcceptedSocket.getInputStream()));
            String FirstLine = reader.readLine();
            System.out.println(FirstLine);
        }
        catch(Exception e)
        {
            System.out.println("Error :" + e);
        }
    }
}
