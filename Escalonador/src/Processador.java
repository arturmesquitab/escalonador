
public class Processador {
	public enum Status
	{
		RUN,SLEEP
	}
	private Status s;
	private Processo p;
	public Status getS() {
		return s;
	}
	public void setS(Status s) {
		this.s = s;
	}
	public Processo getP() {
		return p;
	}
	public void setP(Processo p) {
		this.p = p;
	}
	public float run() throws InterruptedException
	{
		//Forgot to throw exception when processor is running!
		s = Status.RUN;
		p.run();
		s = Status.SLEEP;
		return p.getBurstTime();
	}
}
