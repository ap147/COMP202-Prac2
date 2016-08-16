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
            String parts[] = FirstLine.split(" ");

            if(parts.length == 3)
            {
                if(parts[0].compareTo("GET") == 0)
                {
                    String filename = parts[1].substring(1);
                    System.out.println(filename);
                }
            }

            //Going though all the other lines until empty
            while(true)
            {
                String line = reader.readLine();
                if(line == null)
                {
                    //DONT KNOW WHAT TO DO HERE
                }
                if(line.compareTo("") == 0)
                {
                    //Line is empty
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error :" + e);
        }
    }
}
