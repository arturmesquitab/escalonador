import java.util.Queue;
/**
 * Classe abstrata, serve para dar uma "interface" que todos os tipos de escalonamento devem seguir
 *
 */
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
