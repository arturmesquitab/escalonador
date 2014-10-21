import java.util.ArrayList;


public class Despacho implements Runnable{
	private boolean run;
	private ProcessadorCollection l;
	private ArrayList<Processo> p;
	private int i;
	public Despacho(ProcessadorCollection l)
	{
		i = 0;
		run = false;
		this.l = l;
		p = new ArrayList<Processo>();
	}
	public void start()
	{
		run();
	}
	public void run()
	{
		System.out.println("Running Despacher");
		run = true;
		while (run)
		{
			try {
				checkNewProcesses();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void stop()
	{
		run = false;
	}
	public void addProcessor(Processo p) throws InterruptedException
	{
		synchronized(this.p)
		{
			p.setStatus(StatusProcesso.READY);
			this.p.add(p);
		}
	}
	public void stopProcesso(int ID) throws InterruptedException
	{
		l.pararProcesso(ID);
	}
	public void checkNewProcesses() throws InterruptedException
	{
		synchronized(this.p)
		{
			if (!p.isEmpty())
			{
				i = 0;
				for (Processo x : p)
				{
					System.out.println("Sending "+x.getID()+" to run");
					l.executarProcesso(x);
				}
				p.clear();
			}
			else
			{
				if (i == 0)
				{
					i++;
					System.out.println("No processes to run");
				}
			}
		}
	}
}
