import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;


class HttpServerSession extends Thread
{
    //Socket that was accepted by the Server and passed through constructor is saved here
    private Socket AcceptedSocket;
    public HttpServerSession(Socket _AcceptedSocket)
    {
        AcceptedSocket = _AcceptedSocket;
    }

    public void run()
    {
        try
        {
            //reader which is used to read what webpage/socket status
            BufferedReader reader = new BufferedReader(new InputStreamReader(AcceptedSocket.getInputStream()));
            //writter used to send data to socket/webpage
            BufferedOutputStream writter = new BufferedOutputStream(AcceptedSocket.getOutputStream());
            //getting what socket said and spliting it
            String FirstLine = reader.readLine();
            String parts[] = FirstLine.split(" ");
            //checking if correct message
            if(parts.length == 3)
            {
                if(parts[0].compareTo("GET") == 0)
                {
                    String filename = parts[1].substring(1);
                    System.out.println("File Requested : " + filename);
                    if(filename.compareTo("HttpServer.java") == 0)
                    {

                    }
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
            /*
            println(writter, "HTTP/1.1 200 OK");
            println(writter, "");
            println(writter, "Hello World");
            writter.flush();
            AcceptedSocket.close();
            */

            byte[] array = new byte[1024];
            FileInputStream file = new FileInputStream("page.html");
            int rc;
            println(writter, "HTTP/1.1 200 OK");
            println(writter, "");
            System.out.println(1);
            //Reading file until end of file

            while(true)
            {
                rc = file.read(array);
                System.out.println("Sleepy: 1 seconds");

                if(rc == - 1)
                {
                    System.out.println(2 + "looping out");
                    break;
                }
                sleep(1000);
                writter.write(array);

            }

            writter.flush();
            //Sending, Closing Connection
            System.out.println(3);
            //writter.flush();
            AcceptedSocket.close();
            System.out.println(4);
        }
        catch(Exception e)
        {
            System.out.println("Error :" + e);
            System.out.println("Http Server Session");
            int x = getStackTrace()[0].getLineNumber();
            System.out.println("Line Number : " + x);
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
