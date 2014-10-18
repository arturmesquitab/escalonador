import java.util.Queue;

public abstract class EscalonadorAlgo {
	protected Queue<Processo> fila;
	public Queue<Processo> getFila()
	{
		return fila;
	}
	public EscalonadorAlgo(){};
	public abstract String toString();
	public abstract boolean restricao(Processo p);
	//public abstract  Queue<Processo> addProcesso(Queue<Processo> queue, Processo processo);
	//public abstract void removeProcesso();
	//public abstract Processo getCurrent();
}
