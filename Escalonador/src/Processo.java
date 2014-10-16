import java.util.Date;
import java.util.Comparator;


public class Processo 
{
	private Date TA; //TimeArrived
	private int ID;
	private float BurstTime;
	private int p;
	private float QuantumTime;
	public Processo(Date tA, int iD, float burstTime, int p) {
		this.TA = tA;
		this.ID = iD;
		this.BurstTime = burstTime;
		this.p = p;
		this.QuantumTime = 0;
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
		float time;
		if (QuantumTime > 0)
			time = QuantumTime;
		else
			time = BurstTime;
		Thread.sleep((long) time);
	}
	public float getQuantumTime() {
		return QuantumTime;
	}
	public void setQuantumTime(float quantumTime) {
		QuantumTime = quantumTime;
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
