import java.util.ArrayList;


public class ProcessadorCollection {
	private ArrayList<Processador> listaPC;
	public ProcessadorCollection()
	{
		listaPC = new ArrayList<Processador>();
	}
	public void add(Processador p)
	{
		listaPC.add(p);
	}
}
