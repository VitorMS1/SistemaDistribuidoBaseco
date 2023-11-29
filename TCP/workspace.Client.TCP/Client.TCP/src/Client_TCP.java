import java.io.*;
import java.net.Socket;

public class Client_TCP {

	public Medida sendData (Medida medida)
	{
		ObjectInputStream objInput;
		ObjectOutputStream objOutput;
        
        Socket conectar;
        
        try 
        {
        	System.out.println("==========================");
        	System.out.println("Informações do Sistema:");
			
        	conectar = new Socket("localhost", 700);
			System.out.println("-- Conectado" );
			
			objOutput = new ObjectOutputStream(conectar.getOutputStream());
			objInput = new ObjectInputStream(conectar.getInputStream());
									
			objOutput.writeObject(medida);
			System.out.println("--Dados enviados");			
			
			medida = (Medida) objInput.readObject();
			System.out.println("--Dados recebidos");
			System.out.println("==========================\n");	
			
			
			objInput.close();
			objOutput.close();
			conectar.close();
			
			return medida;
			
		} 
        catch (Exception e) 
        { 
        	System.out.println("ERRO: " + e.getMessage());
        	return null; 
        	
        }
	}
}

