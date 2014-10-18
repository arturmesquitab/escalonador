

public class Processo 
{
	private int TA; //TimeArrived
	private int ID;
	private int BurstTime;
	private int p;
	public Processo(int tA, int iD, int burstTime, int p) {
		this.TA = tA;
		this.ID = iD;
		this.BurstTime = burstTime;
		this.p = p;
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
	public int getP() {
		return p;
	}
	public void run() throws InterruptedException
	{
			Thread.sleep(1);
			BurstTime--;
	}
}