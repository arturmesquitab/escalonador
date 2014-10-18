import java.util.HashMap;


public class Escalonador {
	int nProcessor;
	int nQueue;
	HashMap<Integer,Fila> queuetype;
	String migration;
	ProcessadorCollection processadores;
	public ProcessadorCollection getProcessadores() {
		return processadores;
	}
	public int getnProcessor() {
		return nProcessor;
	}
	public int getnQueue() {
		return nQueue;
	}
	public HashMap<Integer, Fila> getQueuetype() {
		return queuetype;
	}
	public String getMigration() {
		return migration;
	}
	public int getTime() {
		return time;
	}
	public boolean isRun() {
		return run;
	}
	public Despacho getD() {
		return d;
	}
	int time;
	boolean run;
	Despacho d;
	void start() throws InterruptedException
	{
		run = true;
		runSchedule();
	}
	void runSchedule() throws InterruptedException
	{
		d.run();
		while (run)
		{
			Thread.sleep(1);
			time++;
		}
	}
	void stop()
	{
		run = false;
		d.stop();
	}
	public boolean freeProcessador()
	{
		return processadores.freeProcessador();
	}
}
