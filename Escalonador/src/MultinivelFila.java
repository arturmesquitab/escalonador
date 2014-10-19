import java.util.HashMap;
import java.util.Iterator;


public class MultinivelFila {
	private Escalonador e;
	private HashMap<Integer,Fila> filas;
	private boolean run;
	public MultinivelFila (Escalonador e, HashMap<Integer,Fila> f)
	{
		this.e = e;
		this.filas = f;
	}
	public void arrive(Processo p)
	{
		System.out.println("Process "+p.getID()+" arrived");
		filas.get(1).add(p); //Colocar na primeira fila
	}
	public void RRajust()
	{
		for (int i = 1;i <= e.getnQueue();i++)
		{
			if (filas.get(i).toString().equals("RR"))
			{
				for (Iterator<Processo> it = filas.get(i).get(); it.hasNext();)
				{
					Processo p = it.next();
					if ((p.getStatus() == StatusProcesso.SLEEP) && (p.getTime()%((RR)filas.get(i).getEscalonamento()).getQuantum() == 0) && (i < e.getnQueue()))
					{
						filas.get(i).remove(p);
						filas.get(i+1).add(p);
						p.setStatus(StatusProcesso.READY);
					}
				}
			}
		}
	}
	public void FinishAjust()
	{
		for (int i = 1;i <= e.getnQueue();i++)
		{
			for (Iterator<Processo> it = filas.get(i).get(); it.hasNext();)
			{
				Processo p = it.next();
				if ((p.getStatus() == StatusProcesso.FINISH))
				{
					filas.get(i).remove(p);
				}
			}
		}
	}
	public void run() throws InterruptedException
	{
		while(run)
		{
			procurarPrioridades();
			procurarPrioridadesFila();
			if (e.freeProcessador())
			{
				int next = next();
				if (next > 0)
					filas.get(next).enviarProcesso(e.getD());
			}
			RRajust();
			FinishAjust();
		}
	}
	public void stop()
	{
		run = false;
	}
	public int next()
	{
		for (int i = 1;i <= e.getnQueue();i++)
		{
			if (!filas.get(i).isEmpty())
				return i;
		}
		return -1;
	}
	public void procurarPrioridades()
	{
		for (int i = 2;i <= e.getnQueue();i++)
		{
			for (Iterator<Processo> it = filas.get(i).get(); it.hasNext();)
			{
				Processo p = it.next();
				if ((p.getStatus() == StatusProcesso.RUN) && (!filas.get(i).isEmpty()) && (!e.freeProcessador()))
				{
					e.getD().stopProcesso(p.getID());
				}
			}
		}
	}
	public void procurarPrioridadesFila()
	{
		for (int i = 1;i <= e.getnQueue();i++)
		{
			for (Iterator<Processo> it = filas.get(i).get(); it.hasNext();)
			{
				Processo p = it.next();
				if ((p.getStatus() == StatusProcesso.RUN) && (filas.get(i).restricao(p)))
				{
					e.getD().stopProcesso(p.getID());
				}
			}
		}
	}
}
