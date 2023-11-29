import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		
		Scanner scan = new Scanner(System.in);
		Medida medida = new Medida();
		Client_UDP cliente_UDP = new Client_UDP();
		
		medida.setKmh(1.609);
		System.out.println("Digite o valor em Milhas por hora para ser convertido:");
		medida.setMph(scan.nextDouble());
		
		medida = cliente_UDP.sendData(medida);
		medida = cliente_UDP.reciveData(medida);
		
		System.out.println(medida.getMph() + "Mph convertidas, são: ~" + 
		new DecimalFormat(".#").format(medida.getValorConvertido())  + " Km/h");
		
	}

}
