import java.util.Iterator;

/**
 * Classe representando uma fila de processos pra escalonamento 
 *
 */
public class Fila {
	private EscalonadorAlgo esc;
	private int tamMax;
	/**
	 * Constructor da classe Fila
	 * @param s String: o tipo de escalonamento a ser utilizado(Ex: RR,SJB ...)
	 */
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
		tamMax = 0;
	}
	/**
	 * Adiciona um processo a fila
	 * @param p Processo: o processo a ser adicionado a fila
	 */
	public void add(Processo p)
	{
		esc.add(p);
		tamMax++;
	}
	/**
	 * Retorna o tamanho da fila
	 * @return int: o tamanho da fila
	 */
	public int size()
	{
		return esc.getFila().size();
	}
	/**
	 * Retorna o iterador da fila
	 * @return Iterator: iterador da fila
	 */
	public Iterator<Processo> get()
	{
		return esc.getFila().iterator();
	}
	/**
	 * Remove um processo da fila
	 * @param p Processo: o processo a ser removido da fila
	 */
	public void remove(Processo p)
	{
		esc.getFila().remove(p);
	}
	public EscalonadorAlgo getEscalonamento()
	{
		return esc;
	}
	/**
	 * Retorna o proximo processo da fila(caso haja) a ser executado
	 * @return p Processo: o proximo processo da fila
	 */
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
	/**
	 * Envia um processo para o despacho
	 * @param d Despacho: um classe de despacho
	 * @throws InterruptedException
	 */
	public void enviarProcesso(Despacho d, MultinivelFila m) throws InterruptedException
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
	public int getTamMax() {
		return tamMax;
	}
	public boolean haveProcess()
	{
		for (Iterator<Processo> it = esc.getFila().iterator();it.hasNext();)
		{
			Processo p = it.next();
			if (p.getStatus() == StatusProcesso.QUEUE)
				return true;
		}
		return false;
	}
}
