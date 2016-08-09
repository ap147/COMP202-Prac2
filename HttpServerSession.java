import java.net.*;
import java.io.*;
import java.util.*;

class HttpServerSession extends Thread
{
	private Socket AcceptedSocket;
	public HttpServerSession(Socket _ASocket)
	{
		AcceptedSocket = _ASocket;
	}
}
