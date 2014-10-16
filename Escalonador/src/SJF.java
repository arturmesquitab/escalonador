import java.util.PriorityQueue;
import java.util.Queue;


public class SJF extends EscalonadorAlgo {

	Queue<Processo> queue;
	
	public SJF() {
		queue = new PriorityQueue<Processo>(10, Processo.getSJFComparator());
	}
	
	@Override
	public Queue<Processo> getQueue() {
		return queue;
	}

	@Override
	public void addProcesso(Processo processo) {
		queue.add(processo);
	}

	@Override
	public Processo removeProcesso() {
		Processo processo = queue.remove();
		return processo;
	}

}
