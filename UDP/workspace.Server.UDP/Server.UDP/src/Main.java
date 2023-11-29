import java.net.SocketException;

public class Main 
{
	public static void main(String[] args) throws SocketException 
	{
		Server_UDP server_UDP = new Server_UDP();
		Medida medida = new Medida();
		
		System.out.println("SERVIDOR ONLINE");
		server_UDP.rodarServidor(medida);
	}
}
