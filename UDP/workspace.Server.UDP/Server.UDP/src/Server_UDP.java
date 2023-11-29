import java.net.*;
import java.io.*;

public class Server_UDP {
	
	
	public Medida rodarServidor (Medida medida) throws SocketException
	
	{
		byte[] reciveData = new byte [256];
		DatagramSocket socketServidor;
		socketServidor = new DatagramSocket(9000);
		
		while(true)
		{
			DatagramPacket recivePack = new DatagramPacket (reciveData, reciveData.length);
			
			try 
			{
				socketServidor.receive(recivePack);
				
				ByteArrayInputStream arrayInptStrm = new ByteArrayInputStream(reciveData);
				ObjectInputStream objInptStrm = new ObjectInputStream(arrayInptStrm);
				
				InetAddress IP = recivePack.getAddress();
				int port = recivePack.getPort();
				
				System.out.println("==========================");
				System.out.println("--Dados recebidos");
				
				medida = (Medida) objInptStrm.readObject();
				medida.setValorConvertido(converterMedida(medida));
				
				System.out.println("--Dados convertidos");
				
				byte[] answerData = new byte[256];
				ByteArrayOutputStream arrayOtptStrm = new ByteArrayOutputStream();
				ObjectOutputStream objOtptStrm = new ObjectOutputStream(arrayOtptStrm);
				
				objOtptStrm.writeObject(medida);
				answerData = arrayOtptStrm.toByteArray();
				
				DatagramPacket answerPack = new DatagramPacket(answerData, answerData.length, IP, port);
				
				socketServidor.send(answerPack);
				
				System.out.println("--Dados enviados");
			} 
			
			catch (IOException  |  ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("erro " + e.getMessage());
			}
		}
	}
	
	public static double converterMedida (Medida medida)
	{
		medida.setValorConvertido(medida.getMph() * medida.getKmh());
		return medida.getValorConvertido();
	}
	
	
}
