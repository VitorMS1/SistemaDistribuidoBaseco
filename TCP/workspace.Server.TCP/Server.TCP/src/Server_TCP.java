import java.io.*;
import java.net.*;

public class Server_TCP 
{
		
	public static void main(String[] args)
	{
		rodarServidor(700);
	}

	public static void rodarServidor(int porta) 
	{
			
		try
		{
				
			ServerSocket socketServidor = new ServerSocket(porta);
				
			while (true) 
			{
					
				System.out.println("==========================");
				System.out.println("--Servidor ativo");
					
				Socket conectar = socketServidor.accept();
					
				System.out.println("--Conectado com: " + 
				conectar.getInetAddress().getHostAddress());
					
				ObjectInputStream objInput = new ObjectInputStream(conectar.getInputStream());
				ObjectOutputStream objOutput = new ObjectOutputStream(conectar.getOutputStream());
					
				Medida medida = (Medida) objInput.readObject();
				System.out.println("--Dados recebidos");
					
				medida.setValorConvertido(converterMedida(medida));
				System.out.println("--Dados convertidos");
					
				while (!medida.isTermino())
				{
					try 
					{
						objOutput.writeObject(medida);
							
					} 
					catch (Exception e) { }			
				}
					
				System.out.println("--Dados enviados");
				System.out.println("--Conexão encerrada");
				objOutput.close();
				objInput.close();
			}
				
		} 
			
		catch (Exception e)
		{          
			System.out.println("ERRO: " + e.getMessage()); 
		}
	}
		
	public static double converterMedida(Medida medida)
	{
		medida.setValorConvertido(medida.getMph() * medida.getKmh());
		return medida.getValorConvertido();
	}	
}
