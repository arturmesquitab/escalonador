import java.util.Iterator;


public class Fila {
	private EscalonadorAlgo esc;
	public Fila (String s)
	{
		switch (s)
		{
			case "RR":
				esc = new RR();
				break;
			case "SJF":
				esc = new SJF();
			case "SJFp":
				esc = new SJFp();
			case "FCFS":
				esc = new FCFS();
			case "Priorityp":
				esc = new Priorityp();
		}
	}
	public void add(Processo p)
	{
		esc.getFila().add(p);
	}
	public int size()
	{
		return esc.getFila().size();
	}
	public Iterator<Processo> get()
	{
		return esc.getFila().iterator();
	}
	public void remove(Processo p)
	{
		esc.getFila().remove(p);
	}
	public EscalonadorAlgo getEscalonamento()
	{
		return esc;
	}
	public Processo next()
	{
		for (Iterator<Processo> it = esc.getFila().iterator();it.hasNext();)
		{
			Processo p = it.next();
			if (p.getStatus() == StatusProcesso.QUEUE)
				return p;
		}
		return null;
	}
	public boolean isEmpty()
	{
		return esc.getFila().isEmpty();
	}
	public void enviarProcesso(Despacho d) throws InterruptedException
	{
		Processo p = next();
		if (p != null)
		{
			p.setStatus(StatusProcesso.READY);
			d.addProcessor(p);
		}
	}
	public boolean restricao(Processo p)
	{
		return esc.restricao(p);
	}
}
