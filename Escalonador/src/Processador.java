
public class Processador {
	public enum Status
	{
		RUN,SLEEP
	}
	private Status s;
	private Processo p;
	private boolean run;
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
		int time = 0;
		//Forgot to throw exception when processor is running!
		s = Status.RUN;
		run = true;
		while (run && p.getBurstTime() > 0)
		{
			p.run();
			time++;
		}
		s = Status.SLEEP;
		return time;
	}
	public void interruption()
	{
		run = false;
	}
}
