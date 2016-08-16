import java.net.*;
import java.io.*;
import java.util.*;
//http://localhost:18080/HttpServer.java
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
			ServerSocket server = new ServerSocket(18080);
			System.out.println("Server Socket Opened at port : "+ server.getLocalPort());

			while(true)
			{
				Socket client = server.accept();
				HttpServerSession newHttpServer = new HttpServerSession(client);
				newHttpServer.run();
				System.out.println("Connection Made With : "+ client.getInetAddress());
				client.close();
			}

		}
		catch(Exception e)
		{
			System.out.println("Error : " + e);
		}	
	}
	
}







