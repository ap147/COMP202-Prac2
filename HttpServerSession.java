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
            BufferedOutputStream writter = new BufferedOutputStream(AcceptedSocket.getOutputStream());

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
                    //return;
                    //DONT KNOW WHAT TO DO HERE
                }
                if(line.compareTo("") == 0)
                {
                    //Line is empty
                    break;
                }
            }

            // Use this when sending text

            println(writter, "HTTP/1.1 200 OK");
            println(writter, "");
            println(writter, "Hello World");
            writter.flush();
            AcceptedSocket.close();

        }
        catch(Exception e)
        {
            System.out.println("Error :" + e);
        }
    }

    private void println(BufferedOutputStream bos, String s) throws IOException
    {
        String news = s + "\r\n";
        byte[] array = news.getBytes();
        for(int i =0; i < array.length; i++)
        {
            bos.write(array[i]);
        }
        return;
    }





}
