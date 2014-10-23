
/**
 * Classe que representa um processo
 *
 */
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
	private int TimeFinish;
	/**
	 * Construtor da classe processo
	 * @param tA int: tempo de chegada do processo
	 * @param iD int:
	 * @param burstTime int:
	 * @param p int: 
	 */
	public Processo(int tA, int iD, int burstTime, int p) {
		this.TA = tA;
		this.ID = iD;
		this.BurstTime = burstTime;
		this.p = p;
		this.time = 0;
		this.status = StatusProcesso.QUEUE;
		TimeFinish = 0;
	}
	/**
	 * Construtor da classe processo(copiando os valores de outro processo)
	 * @param p2 processo: os valores de um processo a serem copiados
	 */
	public Processo(Processo p2) {
		this.TA = p2.getTA();
		this.ID = p2.getID();
		this.BurstTime = (int) p2.getBurstTime();
		this.p = p2.getP();
		this.time = 0;
		this.status = StatusProcesso.READY;
	}
	public int getTA() {
		return TA;
	}
	public int getID() {
		return ID;
	}
	public int getBurstTime() {
		return BurstTime;
	}
	public int getP() {
		return p;
	}
	public void run() throws InterruptedException
	{
		Thread.sleep(1000);
		time++;
	}
	public StatusProcesso getStatus() {
		return status;
	}
	public void setStatus(StatusProcesso status) {
		this.status = status;
	}
	public void setBurstTime(int burstTime) {
		BurstTime = burstTime;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTimeFinish() {
		return TimeFinish;
	}
	public void setTimeFinish(int timeFinish) {
		TimeFinish = timeFinish;
	}
}