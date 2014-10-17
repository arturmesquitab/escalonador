import java.util.ArrayList;


public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RR rr = new RR();
		SJF sjf = new SJF();
		final SJFp sjfp = new SJFp();
		FCFS fcfs = new FCFS();
		Priority priority = new Priority();
		final ArrayList<Processo> l = new ArrayList<Processo>();
		//rr.addProcesso(new Processo(new Date(), 1, 20, 1));
		//rr.addProcesso(new Processo(new Date(), 2, 10, 1));
		//rr.addProcesso(new Processo(new Date(), 3, 2, 1));
		//rr.addProcesso(new Processo(new Date(), 4, 15, 1));
		//rr.addProcesso(new Processo(new Date(), 5, 8, 1));
		//rr.addProcesso(new Processo(new Date(), 6, 12, 1));
		//rr.addProcesso(new Processo(new Date(), 7, 22, 1));
		//rr.addProcesso(new Processo(new Date(), 8, 4, 1));
		
		new Thread() {
			
			@Override
			public void run() {

				l.add(new Processo(1, 1, 20, 1));
				l.add(new Processo(2, 2, 20, 5));
				l.add(new Processo(3, 3, 20, 8));
		//l.add(new Processo(1, 2, 10, 1));
		//l.add(new Processo(2, 3, 2, 3));
		//l.add(new Processo(3, 4, 15, 1));
		//l.add(new Processo(3, 5, 8, 7));
		//l.add(new Processo(4, 6, 12, 3));
		//l.add(new Processo(6, 7, 12, 2));
		//l.add(new Processo(6, 8, 4, 10));
		
		//rr.run(l);
		//rr.start();
		//sjf.run(l);
		//sjf.start();
		//fcfs.run(l);
		//fcfs.start();
		//priority.run(l);
		//priority.start();
				sjfp.run(l);
				sjfp.start();
		}}.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sjfp.addProcesso(new Processo(4, 4, 2, 3));
	}

}
