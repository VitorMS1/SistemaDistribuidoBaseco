import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer 
{
	
	ServerSocket serverSocket;
	List<Sockets> usuarios = new LinkedList<>();
	int porta = 4000;
	
	
//====================================================================================  

	public void iniciar() throws IOException
	{
		serverSocket = new ServerSocket(porta);
		
		System.out.println("-- Servidor em execução | Porta: " + porta + " --");
		System.out.println("********************************************\n");
		conexãoLoop();
	}
	
//--------------------------------------------------------------------------------

	
	private void conexãoLoop() throws IOException
	{
		while(true)
		{
			Sockets cliente = new Sockets(serverSocket.accept());
			usuarios.add(cliente);
						
			new Thread(() -> receberMensagemLoop(cliente)).start();
		}
	}
	
//--------------------------------------------------------------------------------

	
	private void receberMensagemLoop(Sockets cliente)
	{
		String mensagem;
		
		try
		{
			while ((mensagem = cliente.ReceberMensagem()) != null) 
			{
				if ("sair".equalsIgnoreCase(mensagem)) 
				{  return; }
				
				System.out.printf("%s - %s\n", cliente.getRemoteSocketAddress(), mensagem);
				DevolverMensagem(cliente, mensagem);
			}
		} 
		
		finally
		{ cliente.fechar();}
	}
	
	
//--------------------------------------------------------------------------------
	
	private void DevolverMensagem(Sockets emissor, String mensagem)
	{
		
		Iterator<Sockets> iterator = usuarios.iterator();
		while (iterator.hasNext())
		{
			Sockets cliente = iterator.next();
			if (!emissor.equals(cliente))
			{
				if (!cliente.EniviarMensagem(mensagem))
				{
					iterator.remove();
				}				
			}
		}
	}
	
	
//====================================================================================  

	
	public static void main(String[] args)
	{
		ChatServer servidor = new ChatServer();
		try 
		{
			servidor.iniciar();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("-- Servidor encerrado --");
	}
	
}
