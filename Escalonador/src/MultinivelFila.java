import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Semaphore;


public class MultinivelFila implements Runnable {
	private Escalonador e;
	private HashMap<Integer,Fila> filas;
	private boolean run;
	private Semaphore s;
	public MultinivelFila (Escalonador e, HashMap<Integer,Fila> f)
	{
		this.e = e;
		this.filas = f;
		this.p1 = 0;
		this.p2 = 0;
		this.s = new Semaphore(1);
	}
	public void arrive(Processo p) throws InterruptedException
	{
		System.out.println("Process "+p.getID()+" arrived");
		s.acquire();
		p.setStatus(StatusProcesso.QUEUE);
		filas.get(1).add(p); //Colocar na primeira fila
		s.release();
	}
	public void RRajust() throws InterruptedException
	{
		s.acquire();
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
							p.setStatus(StatusProcesso.QUEUE);
						}
					}
				}
			}
		s.release();
	}
	public void FinishAjust() throws InterruptedException //Ajeitar metodo
	{
		s.acquire();
		for (int i = 1;i <= e.getnQueue();i++)
		{
			Iterator<Processo> it = filas.get(i).get();
			while (it.hasNext())
			{
				if ((it.next().getStatus() == StatusProcesso.FINISH))
					it.remove();
			}
		}
		s.release();
	}
	public void SetStatusProcess(int id, StatusProcesso sp) throws InterruptedException 
	{
		s.acquire();
		for (int i = 1;i <= e.getnQueue();i++)
		{
			Iterator<Processo> it = filas.get(i).get();
			while (it.hasNext())
			{
				Processo p = it.next();
				if (p.getID() == id)
					p.setStatus(sp);
			}
		}
		s.release();
	}
	public void start() throws InterruptedException
	{
		run();
	}
	public void run()
	{
		run = true;
		System.out.println("Schedule Queue running");
		while(run)
		{
			try {
				procurarPrioridades();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			/*try {
				procurarPrioridadesFila();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}*/
			if (e.freeProcessador())
			{
				int next = -1;
				try {
					next = next();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (next > 0)
				{
					try {
						s.acquire();
						filas.get(next).enviarProcesso(e.getD());
						s.release();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			try {
				RRajust();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				FinishAjust();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void stop()
	{
		run = false;
	}
	public int next() throws InterruptedException
	{
		s.acquire();
		for (int i = 1;i <= e.getnQueue();i++)
		{
			if (!filas.get(i).isEmpty())
			{
				s.release();
				return i;
			}
		}
		s.release();
		return -1;
	}
	private int p1;
	public void procurarPrioridades() throws InterruptedException
	{
		if (p1 == 0)
		{
			System.out.println("Search priority between queues");
			p1++;
		}
		s.acquire();
		for (int i = 2;i <= e.getnQueue();i++)
		{
			for (Iterator<Processo> it = filas.get(i).get(); it.hasNext();)
			{
				Processo p = it.next();
				if ((p.getStatus() == StatusProcesso.RUN) && (!filas.get(i).isEmpty()) && (!e.freeProcessador()))
				{
					p1 = 0;
					System.out.println("Priority between queues: Process "+p.getID()+": stop it!");
					e.getD().stopProcesso(p.getID());
				}
			}
		}
		s.release();
	}
	private int p2;
	public void procurarPrioridadesFila() throws InterruptedException
	{
		if (p2 == 0)
		{
			p2++;
			System.out.println("Search priority inside preemptive queues");
		}
		s.acquire();
		for (int i = 1;i <= e.getnQueue();i++)
		{
			for (Iterator<Processo> it = filas.get(i).get(); it.hasNext();)
			{
				Processo p = it.next();
				if ((p.getStatus() == StatusProcesso.RUN) && (filas.get(i).restricao(p)))
				{
					p2 = 0;
					System.out.println("Priority inside preemptive queues: Process "+p.getID()+": stop it!");
					e.getD().stopProcesso(p.getID());
				}
			}
		}
		s.release();
	}
}
