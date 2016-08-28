import com.sun.org.apache.regexp.internal.RE;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

//Inststances are created when a new connection is made, sends any files browser requests except if its not in "Website"
//folder, then sends a
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
                }
            }

            //Going though all the other lines until empty
            while(true)
            {
                String line = reader.readLine();
                if(line == null)
                {
                    break;
                }
                if(line.compareTo("") == 0)
                {
                    //Line is empty
                    break;
                }
            }

            byte[] array = new byte[1024];

            try
            {
                if (RequestedFile.equals(""))
                {
                    RequestedFile = "informational.html";
                }
                /*
                println(writter, "HTTP/1.1 200 OK");
                println(writter, "");
                println(writter, "Hello World");
                */

                //Opening Requested file (all files in Website Folder)
                FileInputStream file = new FileInputStream("Website/" + RequestedFile);

                //Used to check if end of file
                int fileOutput;
                //Letting Browser know requested file is coming
                println(writter, "HTTP/1.1 200 OK");
                println(writter, "");

                //Reading file until end of file
                while (true)  {
                    //Reading file
                    fileOutput = file.read(array);
                    //Checking if file has ended
                    if (fileOutput == -1) {
                        break;
                    }
                    //Pauses for 1 Secound
                    sleep(500);
                    //Loading it into a pipe, No jumk
                    writter.write(array,0,fileOutput);
                    //Sending, Closing Connection
                    writter.flush();
                }

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
        catch(Exception e) {}
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
