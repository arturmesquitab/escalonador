import java.util.ArrayList;


public class ProcessadorCollection {
	private ArrayList<Processador> listaPC;
	public ProcessadorCollection(int n, MultinivelFila m)
	{
		listaPC = new ArrayList<Processador>();
		for (int i = 0;i < n;i++)
		{
			Processador p = new Processador(m,i+1);
			listaPC.add(p);
		}
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
			{
				it.setP(p);
				Thread t = new Thread(it);
				t.start();
				break;
			}
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
