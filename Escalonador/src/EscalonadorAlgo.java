import java.util.Queue;


public abstract class EscalonadorAlgo {
	public abstract Queue<Processo> getQueue();
	public abstract void addProcesso(Processo processo);
	public abstract Processo removeProcesso();
}
