import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class Priorityp extends EscalonadorAlgo {

	@Override
	public Queue<Processo> apply() {
		return new PriorityQueue<Processo>(10, getComparator());	
	}
	
	@Override
	public String toString() {
		return "Priorityp";
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
}
