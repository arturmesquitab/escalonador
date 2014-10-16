import java.util.Date;
import java.util.Comparator;


public class Processo 
{
	private Date TA; //TimeArrived
	private int ID;
	private float BurstTime;
	private int p;
	public Processo(Date tA, int iD, float burstTime, int p) {
		this.TA = tA;
		this.ID = iD;
		this.BurstTime = burstTime;
		this.p = p;
	}
	public Date getTA() {
		return TA;
	}
	public int getID() {
		return ID;
	}
	public float getBurstTime() {
		return BurstTime;
	}
	public int getP() {
		return p;
	}
	public void run() throws InterruptedException
	{
			Thread.sleep(1);
			BurstTime--;
	}
	
	static Comparator<Processo> getFCFSComparator() {
        return new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				if(p1.getTA().before(p2.getTA())){
		        	return -1;
		        } else{
		        	return 1;
		        }
			}
        };
    }
	
	static Comparator<Processo> getSJFComparator() {
        return new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				if(p1.getBurstTime() < p2.getBurstTime()){
		        	return -1;
		        } else{
		        	return 1;
		        }
			}
        };
    }
}
