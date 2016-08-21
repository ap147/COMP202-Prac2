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
            /*
            println(writter, "HTTP/1.1 200 OK");
            println(writter, "");
            println(writter, "Hello World");
            writter.flush();
            AcceptedSocket.close();
            */

            /*
             - Declare a byte array of a fixed size,
             - open the file with a FileInputStream,
             - read from the file with the FileInputStream::read method,
             - and with each read send the byte array to the client using the BufferedOutputStream::write method.
             - Be sure to catch end of file (when FileInputStream::read returns -1).
             - At the end of the le, ensure that you then flush the output before you return.
            */


            //FileInputStream file = new FileInputStream("helloworld.txt");

            byte[] array = new byte[1024];

            FileInputStream is = new FileInputStream("hi.png");

            int rc = 0;

            while(true)
            {
                if(rc == -1)
                {
                    break;
                }
                rc = is.read(array);
                writter.write(array);
            }
           /*
            while(true)
            {
                System.out.println("Inside While loop");
                int x = file.read();
                System.out.println("file read : " + x);
                if(x == -1)
                {
                    break;
                }

                array =ByteBuffer.allocate(4).putInt(x).array();
                writter.write(array);
            }
            System.out.println("While loop completed");
            */
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
