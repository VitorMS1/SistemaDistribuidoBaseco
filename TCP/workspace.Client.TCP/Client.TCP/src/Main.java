import java.text.DecimalFormat;
import java.util.Scanner;


public class Main
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Medida medida = new Medida();
		Client_TCP client_TCP = new Client_TCP();
		
		medida.setKmh(1.609);
		System.out.println("Digite o valor em Milhas por hora para ser convertido:");
		medida.setMph(scan.nextDouble());
		
		medida = client_TCP.sendData(medida);
		
		System.out.println(medida.getMph() + "Mph convertidas, s�o: ~" + 
		new DecimalFormat(".#").format(medida.getValorConvertido())  + " Km/h");
	}
}
