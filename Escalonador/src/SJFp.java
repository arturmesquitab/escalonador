import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class SJFp extends EscalonadorAlgo {
	
	public SJFp()
	{
		super();
		fila = new PriorityQueue<Processo>(10, getComparator());
	}
	
	@Override
	public String toString() {
		return "SJFp";
	}
	
	public Comparator<Processo> getComparator() {
        return new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				if(p1.getBurstTime() >= p2.getBurstTime()){
		        	return 1;
		        } else{
		        	return -1;
		        }
			}
        };
    }
	
	/*
	Queue<Processo> queue;
	Processo current;
	long count;
	
	public SJFp(){
		queue = new PriorityQueue<Processo>(10, getComparator());
	}
	
	@Override
	public void addProcesso(Processo processo) {
		if(current!=null){
			long mili = System.currentTimeMillis() - count;
			mili = Math.round(mili/100.0);
			
			if(processo.getBurstTime()<mili){
				current.setBurstTime(mili);
				queue.add(current);
			}
		}
		queue.add(processo);
	}

	@Override
	public void removeProcesso() {
		current = queue.poll();
	}
	
	@Override
	public Processo getCurrent() {
		return current;
	}

	@Override
	public void run(ArrayList<Processo> lista) {
		long inicio = System.currentTimeMillis();
		while(!lista.isEmpty()){
			long mili = System.currentTimeMillis() - inicio;
			mili = Math.round(mili/100.0);
			for(int i=0; i<lista.size();i++){
				if(lista.get(i).getTA()<=mili){
					System.out.println(mili+" - "+lista.get(i).getTA());
					queue.add(lista.get(i));
					lista.remove(i);
				}
			}
		}
	}
	
	//Simulando a Execucao
	public void start(){
		removeProcesso();
		if(current!=null){
			System.out.println(current.getID()+" - "+current.getBurstTime());
			executeProcesso();
		}
	}
	
	public void executeProcesso() {
		count = System.currentTimeMillis();
		try {
			Thread.sleep((long)current.getBurstTime()*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		start();
	}
	*/
}