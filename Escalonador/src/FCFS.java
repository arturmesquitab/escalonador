import java.util.PriorityQueue;
import java.util.Queue;


public class FCFS extends EscalonadorAlgo {

	Queue<Processo> queue;
	
	public FCFS() {
		queue = new PriorityQueue<Processo>(10, Processo.getFCFSComparator());
	}
	@Override
	public Queue<Processo> getQueue() {
		// TODO Auto-generated method stub
		//Collections.sort(l, Processo.getFCFSComparator());
		//Queue<Processo> queue = new LinkedList<Processo>(l);
		//return queue;
		return null;
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
