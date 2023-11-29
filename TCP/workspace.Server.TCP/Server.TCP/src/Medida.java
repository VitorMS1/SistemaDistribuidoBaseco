import java.io.Serializable;

public class Medida implements Serializable {
	
	private double mph;
	private double kmh;
	private double valorConvertido;
	private boolean termino;
	
	//------------------------------
	
	public double getMph() {
		return mph;
	}
	public void setMph(double mph) {
		this.mph = mph;
	}
	public double getKmh() {
		return kmh;
	}
	public void setKmh(double kmh) {
		this.kmh = kmh;
	}
	public double getValorConvertido() {
		return valorConvertido;
	}
	public void setValorConvertido(double valorConvertido) {
		this.valorConvertido = valorConvertido;
	}
	public boolean isTermino() {
		return termino;
	}
	public void setTermino(boolean termino) {
		this.termino = termino;
	}
}
