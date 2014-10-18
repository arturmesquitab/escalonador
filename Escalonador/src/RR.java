import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class RR extends EscalonadorAlgo {
		
	int quantum;
	public RR(int quantum)
	{
		super();
		fila = new LinkedList<Processo>();
		this.quantum = quantum;
	}
	
	@Override
	public String toString() {
		return "RR";
	}
	public boolean restricao(int time)
	{
		if (time > quantum)
			return false;
		return true;
	}
	
	/*
	Queue<Processo> queue;
	Processo current;
	int Quantum = 4;
	
	public RR(){
		queue = new LinkedList<Processo>();
	}
	
	@Override
	public void addProcesso(Processo processo) {
		queue.add(processo);
	}
	
	@Override
	public void removeProcesso() {
		current = queue.poll();;
	}
	
	@Override
	public Processo getCurrent(){
		return current;
	}
	
	@Override
	public void run(ArrayList<Processo> lista) {
		long inicio = System.currentTimeMillis();
		while(!lista.isEmpty()){
			long mili = System.currentTimeMillis() - inicio;
			mili = Math.round(mili/1000.0);
			for(int i=0; i<lista.size();i++){
				if(lista.get(i).getTA()<=mili){
					System.out.println(mili+" - "+lista.get(i).getTA());
					queue.add(lista.get(i));
					lista.remove(i);
				}
			}
		}
	}
	
	//So para Testar
	public void start(){
		removeProcesso();
		if(current!=null){
			executeProcesso();
		}
	}
	
	public void executeProcesso(){
		try {
			Thread.sleep(100);
			current.subBurstTime(Quantum);
			System.out.println(current.getID()+" - "+current.getBurstTime());
			if(current.getBurstTime()>0){
				queue.add(current);
			}
			start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
}
