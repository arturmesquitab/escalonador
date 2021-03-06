

public class Processo 
{
	private int TA; //TimeArrived
	private int ID;
	private float BurstTime;
	private int p;
	private float QuantumTime;
	public Processo(int tA, int iD, float burstTime, int p) {
		this.TA = tA;
		this.ID = iD;
		this.BurstTime = burstTime;
		this.p = p;
		this.QuantumTime = 0;
	}
	public int getTA() {
		return TA;
	}
	public int getID() {
		return ID;
	}
	public float getBurstTime() {
		return BurstTime;
	}
	public void setBurstTime(float b) {
		BurstTime = b;
	}
	public void subBurstTime(float b) {
		if(b<BurstTime){
			BurstTime = BurstTime - b;
		} else {
			BurstTime = 0;
		}
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
	
}
