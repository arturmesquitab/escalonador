import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class ProcessadorCollection {
	private ArrayList<Processador> listaPC;
	private Semaphore sp;
	public ProcessadorCollection(int n, MultinivelFila m)
	{
		listaPC = new ArrayList<Processador>();
		for (int i = 0;i < n;i++)
		{
			Processador p = new Processador(m,i+1);
			listaPC.add(p);
		}
		sp = new Semaphore(1);
	}
	public void add(Processador p)
	{
		listaPC.add(p);
	}
	public boolean freeProcessador() throws InterruptedException
	{
		sp.acquire();
		for (Processador it : listaPC)
		{
			if (!it.isRun())
			{
				sp.release();
				return true;
			}
		}
		sp.release();
		return false;
	}
	public void executarProcesso(Processo p) throws InterruptedException
	{
		sp.acquire();
		for (Processador it : listaPC)
		{
			if (!it.isRun())
			{
				it.setS(StatusProcessador.RUN);
				System.out.println(p.getID()+" sending to processor "+it.getId());
				it.setP(p);
				Thread t = new Thread(it);
				t.start();
				break;
			}
		}
		sp.release();
	}
	public void pararProcesso(int ID) throws InterruptedException
	{
		sp.acquire();
		for (Processador it : listaPC)
		{
			if (it.getP().getID() == ID)
				it.interruption();
		}
		sp.release();
	}
}
