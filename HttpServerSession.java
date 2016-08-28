import com.sun.org.apache.regexp.internal.RE;

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
            String RequestedFile = "";
            //checking if correct message
            if(parts.length == 3)
            {
                if(parts[0].compareTo("GET") == 0)
                {
                    String filename = parts[1].substring(1);
                    RequestedFile = filename;
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
            try
            {
                if (RequestedFile.equals(""))
                {
                    RequestedFile = "index.html";
                }

                //Opening Requested file (all files in Website Folder)
                FileInputStream file = new FileInputStream("Website/" + RequestedFile);

                //Used to check if end of file
                int rc;
                //Letting Browser know requested file is coming
                println(writter, "HTTP/1.1 200 OK");
                println(writter, "");

                //Reading file until end of file
                while (true) {
                    //Reading file
                    rc = file.read(array);
                    //Checking if file has ended
                    if (rc == -1) {
                        break;
                    }
                    //Pauses for 1 Secound
                    // sleep(1000);
                    //Loading it into a pipe
                    writter.write(array);
                }
                //Sending, Closing Connection
                writter.flush();
                AcceptedSocket.close();
            }
            catch(Exception e)
            {
                //End up here if file requested doesnt exist in "Website" Folder
                System.out.println("File Requested Not Found");
                println(writter, "HTTP/1.1 404 Not Found");
                println(writter, "");
                //Sending Message, Closing Connection
                writter.flush();
                AcceptedSocket.close();
                return;
            }
        }
        catch(Exception e)
        {
            //System.out.println("Error :" + e);
            //int x = getStackTrace()[0].getLineNumber();
            //System.out.println("Line Number : " + x);
        }
    }
    //A Simple Print method that mimcs
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
