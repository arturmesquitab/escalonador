import java.util.ArrayList;
import java.util.Queue;


public class Fila {
	private Queue<Processo> lista;
	private EscalonadorAlgo esc;
	public void enfileirar(ArrayList<Processo> l)
	{
		lista = esc.apply(l);
	}
	public Processo getNext()
	{
		return lista.poll();
	}
}
