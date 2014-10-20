
public class Processador implements Runnable {
	private int time;
	private MultinivelFila m;
	private int id;
	public boolean isRun() {
		return run;
	}
	private StatusProcessador s;
	private Processo p;
	private boolean run;
	public Processador(MultinivelFila m, int id)
	{
		s = StatusProcessador.SLEEP;
		run = false;
		this.m = m;
		this.id = id;
	}
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
	public int runProcess() throws InterruptedException
	{
		setP(p);
		time = 0;
		p.setStatus(StatusProcesso.RUN);
		m.SetStatusProcess(p.getID(), StatusProcesso.RUN);
		s = StatusProcessador.RUN;
		run = true;
		System.out.println("Process "+p.getID()+" running in processor "+id);
		while (run && p.getBurstTime() != p.getTime())
		{
			p.run();
			time++;
		}
		if (p.getBurstTime() != p.getTime())
		{
			System.out.println("Process "+p.getID()+" stopped! Time executed: "+p.getTime());
			p.setStatus(StatusProcesso.SLEEP);
			m.SetStatusProcess(p.getID(), StatusProcesso.SLEEP);
		}
		else
		{
			System.out.println("Process "+p.getID()+" finish!");
			p.setStatus(StatusProcesso.FINISH);
			m.SetStatusProcess(p.getID(), StatusProcesso.FINISH);
		}
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
	@Override
	public void run() {
		try {
			runProcess();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
