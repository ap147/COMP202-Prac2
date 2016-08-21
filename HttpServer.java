import java.net.*;
import java.io.*;
import java.util.*;
//http://localhost:8080/HttpServer.java
class HttpServer
{
	public static void main(String args[])
	{
		System.out.println();
		System.out.println("--------------- Web Server Starting ---------------");
		System.out.println();
		try
		{
			//Server Port 18080
			ServerSocket server = new ServerSocket(8080);
			System.out.println("Server Socket Opened at port : "+ server.getLocalPort());

			while(true)
			{
				Socket client = server.accept();
				HttpServerSession newHttpServer = new HttpServerSession(client);
				newHttpServer.start();
				System.out.println("Connection Made With : "+ client.getInetAddress());

			}
//getStackTrace()[0].getLineNumber();
		}
		/*

Extend your web-server to catch that exception and
return the 404 message detailed earlier.

Extend your web-server to log a message that
says whether or not the requested le was found or not.

Test: Check that your web-server sends a 404 message by requesting a le that you
know does not exist.
5
		 */
		catch(Exception e)
		{
			System.out.println("Error : " + e);
		}
	}

}







