
public class Processador {
	int time;
	public boolean isRun() {
		return run;
	}
	private StatusProcessador s;
	private Processo p;
	private boolean run;
	public StatusProcessador getS() {
		return s;
	}
	public void setS(StatusProcessador s) {
		this.s = s;
	}
	public Processo getP() {
		return p;
	}
	public void setP(Processo p) {
		this.p = p;
	}
	public float run(Processo p) throws InterruptedException
	{
		setP(p);
		time = 0;
		//Forgot to throw exception when processor is running!
		p.setStatus(StatusProcesso.RUN);
		s = StatusProcessador.RUN;
		run = true;
		while (run && p.getBurstTime() == p.getTime())
		{
			p.run();
			time++;
		}
		if (p.getBurstTime() != p.getTime())
			p.setStatus(StatusProcesso.SLEEP);
		else
			p.setStatus(StatusProcesso.FINISH);
		s = StatusProcessador.SLEEP;
		return time;
	}
	public void interruption()
	{
		run = false;
	}
	public int getTime() {
		return time;
	}
}
