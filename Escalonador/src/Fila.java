import java.util.ArrayList;


public class Fila {
	//private Queue<Processo> lista;
	private ArrayList<Processo> lista;
	private EscalonadorAlgo esc;
	
	public void enfileirar(ArrayList<Processo> l)
	{
		//lista = esc.apply();
		esc.run(lista);
	}
	public Processo getNext() {
		//return lista.poll();
		return esc.getCurrent();
	}
}
