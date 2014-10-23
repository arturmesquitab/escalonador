import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Classe repesentando o metodo de escalonamento Priorityp(Priority Pick)
 *
 */
public class Priorityp extends EscalonadorAlgo {
	/**
	 * Construtor da classe PriorityP
	 */
	public Priorityp()
	{
		super();
		fila = new PriorityQueue<Processo>(10, getComparator());
	}
	
	@Override
	public String toString() {
		return "Priorityp";
	}
	/**
	 * Metodo comparador, serve pra decidir qual processo tem mais prioridade(pra priorityQueue)
	 * @return 1 ou -1
	 */
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
	public boolean restricao(Processo p)
	{
		for (Iterator<Processo> it = fila.iterator(); it.hasNext();)
		{
			Processo a = it.next();
			if (a.getP() > p.getP())
				return false;
		}
		return true;
	}

	@Override
	public void add(Processo p) {
		fila.add(p);
		
	}

}
