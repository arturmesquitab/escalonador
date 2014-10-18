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
	public boolean freeProcessador()
	{
		for (Processador it : listaPC)
		{
			if (!it.isRun())
				return true;
		}
		return false;
	}
	public void executarProcesso(Processo p) throws InterruptedException
	{
		for (Processador it : listaPC)
		{
			if (!it.isRun())
				it.run(p);
		}
	}
	public void pararProcesso(int ID)
	{
		for (Processador it : listaPC)
		{
			if (it.getP().getID() == ID)
				it.interruption();
		}
	}
}
