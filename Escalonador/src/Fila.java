import java.util.Iterator;


public class Fila {
	private EscalonadorAlgo esc;
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
		return esc.getFila().element();
	}
	public boolean isEmpty()
	{
		return esc.getFila().isEmpty();
	}
	public void enviarProcesso(Despacho d) throws InterruptedException
	{
		d.addProcessor(next());
	}
	public boolean restricao(Processo p)
	{
		return esc.restricao(p);
	}
}
