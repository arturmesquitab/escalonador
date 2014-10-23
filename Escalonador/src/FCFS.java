import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Classe repesentando o metodo de escalonamento FCFS(First Come First Served)
 *
 */
public class FCFS extends EscalonadorAlgo {
	/**
	 * Construtor do metodo FCFS
	 */
	public FCFS()
	{
		super();
		fila = new PriorityQueue<Processo>(10, getComparator());
	}
	
	@Override
	public String toString() {
		return "FCFS";	
	}
	/**
	 * Metodo comparador, serve pra decidir qual processo tem mais prioridade(pra priorityQueue)
	 * @return 1 ou -1
	 */
	public Comparator<Processo> getComparator() {
        return new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				if(p1.getTA() >= p2.getTA()){
		        	return 1;
		        } else{
		        	return -1;
		        }
			}
        };
    }
	public boolean restricao(Processo p)
	{
		return true;
	}

	@Override
	public void add(Processo p) {
		fila.add(p);
		
	}


	
	/*
	Queue<Processo> queue;
	Processo current;
	
	public FCFS(){
		queue = new PriorityQueue<Processo>(10, getComparator());
	}
	
	@Override
	public void addProcesso(Queue<Processo> queue, Processo processo) {
		queue.add(processo);
	}
	
	@Override
	public void removeProcesso() {
		current = queue.poll();
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
			Thread.sleep((long)current.getBurstTime()*100);
			System.out.println(current.getID()+" - "+current.getTA());
			start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
}
