import java.util.LinkedList;


/**
 * Classe repesentando o metodo de escalonamento RR(Round Robin)
 *
 */
public class RR extends EscalonadorAlgo {
		
	int quantum;
	/**
	 * Construtor da classe RR, inicializada com o quantum
	 * @param quantum, int valor do quantum da classe
	 */
	public RR(int quantum)
	{
		super();
		fila = new LinkedList<Processo>();
		this.quantum = quantum;
	}
	/**
	 * Construtor da classe, inicializada sem o quantum
	 */
	public RR()
	{
		super();
		fila = new LinkedList<Processo>();
	}
	public void setQuantum(int quantum)
	{
		this.quantum = quantum;
	}
	
	@Override
	public String toString() {
		return "RR";
	}

	public int getQuantum() {
		return quantum;
	}

	public boolean restricao(Processo p)
	{
		/*if (p.getTime() >= quantum)
			return false;*/
		return true;
	}
	@Override
	public void add(Processo p) {
		if (p.getBurstTime() > quantum)
		{
			Processo pq = new Processo(p.getTA(),p.getID(),quantum,p.getP());
			fila.add(pq);
			p.setBurstTime(p.getBurstTime()-quantum);
			p.setStatus(StatusProcesso.SLEEP);
		}
		fila.add(p);
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
