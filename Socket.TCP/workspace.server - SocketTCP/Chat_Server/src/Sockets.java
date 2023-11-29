import java.net.*;
import java.io.*;

public class Sockets
{
	
	private Socket socket;
	private BufferedReader receber;
	private PrintWriter enviar;
	
//====================================================================================  
	
	public Sockets(Socket socket) throws IOException
	{
		this.socket = socket;
		this.receber = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		this.enviar = new PrintWriter(socket.getOutputStream(), true);
		System.out.println("==========================\n" + socket.getRemoteSocketAddress() + " conectado \n==========================");
	}
	
//====================================================================================  

	public String ReceberMensagem()
	{
		try { return receber.readLine();}
		catch (Exception e) {return null;}
	}
	
//--------------------------------------------------------------------------------
		
	public Boolean EniviarMensagem(String mensagem)
	{
		enviar.println(mensagem);
		return !enviar.checkError();
	}
	
//--------------------------------------------------------------------------------
	
	public SocketAddress getRemoteSocketAddress()
	{
		return socket.getRemoteSocketAddress();
	}
	
//--------------------------------------------------------------------------------
	public void fechar()
	{
		try 
		{
			receber.close();
			enviar.close();
			socket.close();
		} 
		catch (IOException e) 
		{ System.out.println("** ERRO NO ENCERRAMENTO DOS SOCKETS: " + e.getMessage() + " **"); }
	}
}