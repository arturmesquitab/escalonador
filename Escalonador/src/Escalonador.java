import java.util.HashMap;


public class Escalonador {
	int nProcessor;
	int nQueue;
	HashMap<Integer,Fila> queuetype;
	String migration;
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
}
