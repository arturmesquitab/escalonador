import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class Priority extends EscalonadorAlgo {
	
	Queue<Processo> queue;
	Processo current;
	
	public Priority(){
		//queue = new PriorityQueue<Processo>(10, Processo.getPriorityComparator());
		queue = new PriorityQueue<Processo>(10, getComparator());
	}
	
	@Override
	public void addProcesso(Processo processo) {
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
	
	public Comparator<Processo> getComparator() {
        return new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				if(p1.getP() > p2.getP()){
		        	return -1;
		        } else{
		        	return 1;
		        }
			}
        };
    }
	
	/** So para Testar
	Simulando a Execucao**/
	public void start(){
		removeProcesso();
		if(current!=null){
			executeProcesso();
		}
	}
		
	public void executeProcesso(){
		try {
			Thread.sleep((long)current.getBurstTime()*100);
			System.out.println(current.getID()+" - "+current.getP());
			start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
