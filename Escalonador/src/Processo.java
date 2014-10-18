

public class Processo 
{
	private int TA; //TimeArrived
	private int ID;
	private int BurstTime;
	private int time;
	public int getTime() {
		return time;
	}
	private int p;
	private StatusProcesso status;
	public Processo(int tA, int iD, int burstTime, int p) {
		this.TA = tA;
		this.ID = iD;
		this.BurstTime = burstTime;
		this.p = p;
		this.time = 0;
		this.status = StatusProcesso.READY;
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
			time++;
	}
	public StatusProcesso getStatus() {
		return status;
	}
	public void setStatus(StatusProcesso status) {
		this.status = status;
	}
}