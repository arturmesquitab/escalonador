/**
 * Classe que representa um processador
 *
 */
public class Processador implements Runnable {
	private int time;
	private MultinivelFila m;
	private int id;
	private int TempoTotal;
	public boolean isRun() {
		return s == StatusProcessador.RUN;
	}
	private StatusProcessador s;
	private Processo p;
	private boolean run;
	/**
	 * Construtor da classe processador
	 * @param m MultinivelFila: uma fila
	 * @param id int: id do processador
	 */
	public Processador(MultinivelFila m, int id)
	{
		s = StatusProcessador.SLEEP;
		run = false;
		this.m = m;
		this.id = id;
		this.TempoTotal = 0;
	}
	public StatusProcessador getS() {
		return s;
	}
	public void setS(StatusProcessador s) {
		System.out.println("Processor "+id+" changed status to "+s.toString());
		this.s = s;
	}
	public Processo getP() {
		return p;
	}
	public void setP(Processo p) {
		this.p = p;
	}
	/**
	 * Roda um processo no processador
	 * @return time int: o tempo que demorou pra rodar o precoesso
	 * @throws InterruptedException
	 */
	public int runProcess() throws InterruptedException
	{
		setP(p);
		time = 0;
		p.setStatus(StatusProcesso.RUN);
		m.SetStatusProcess(p.getID(),p.getBurstTime(), StatusProcesso.RUN);
		s = StatusProcessador.RUN;
		run = true;
		System.out.println("Process "+p.getID()+" running in processor "+id);
		while (run && p.getBurstTime() != p.getTime())
		{
			p.run();
			time++;
			TempoTotal++;
		}
		if (p.getBurstTime() != p.getTime())
		{
			System.out.println("Process "+p.getID()+" "+p.getBurstTime()+" stopped! Time executed: "+p.getTime()+" on processor "+id);
			p.setStatus(StatusProcesso.QUEUE);
			m.SetStatusProcess(p.getID(),p.getBurstTime(), StatusProcesso.QUEUE);
		}
		else
		{
			System.out.println("Process "+p.getID()+" "+p.getBurstTime()+" finish on processor "+id);
			p.setStatus(StatusProcesso.FINISH);
			m.SetStatusProcess(p.getID(),p.getBurstTime(), StatusProcesso.FINISH);
		}
		s = StatusProcessador.SLEEP;
		return time;
	}
	/**
	 * Interrompe o processador
	 */
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
	public int getId() {
		return id;
	}
	public int getTempoTotal() {
		return TempoTotal;
	}
}
