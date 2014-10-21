import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Essa e a classe que representa o escalonador.
 *
 */
public class Escalonador implements Runnable {
	int nProcessor;
	int nQueue;
	HashMap<Integer,Fila> queuetype;
	String migration;
	ProcessadorCollection processadores;
	ArrayList<Processo> processos;
	MultinivelFila m;
	/**
	 * Metodo construtor, usado pra forcar que as configuracoes iniciais do escalonador sejam
	 * inicializadas junto com o escalonador
	 * @param processos: String, representado os processos do sistema
	 * @param configuracoes: String, com as configuracoes inicias do sistema
	 * @throws IOException
	 */
	public Escalonador (String processos, String configuracoes) throws IOException
	{
		loadProcesses(processos);
		Configuration c = new Configuration(configuracoes);
		setInitialParameters(c);
		System.out.println("All entries on system");
		this.d = new Despacho(processadores);
	}
	/**Inicializa as variaveis do escalonador e outras configurações
	 * @param c: Configuration, classe com a configuracao inicial do sistema
	 */
	public void setInitialParameters(Configuration c)
	{
		this.nProcessor = c.getNumProcessors();
		this.nQueue = c.getNumQueues();
		int i = 1;
		queuetype = new HashMap<Integer,Fila>();
		for (String s : c.getAlAlgorithms())
		{
			queuetype.put(i, new Fila(s));
			i++;
		}
		migration = c.getMigration();
		if (!c.getAlQuantum().isEmpty())
		{
			for (Integer j : c.getAlQuantum().keySet())
			{
				Integer value = c.getAlQuantum().get(j);
				((RR)queuetype.get(j).getEscalonamento()).setQuantum(value);
			}
		}
		m = new MultinivelFila(this, queuetype);
		processadores = new ProcessadorCollection(nProcessor,m);
	}
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
	private int time;
	private boolean run;
	private Despacho d;
	public void start() throws InterruptedException
	{
		run = true;
		runSchedule();
	}
	public void run()
	{
		try {
			start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void runSchedule() throws InterruptedException
	{
		System.out.println("Running Schedule");
		System.out.println("Actual time: "+time);
		Thread t1 = new Thread(d);
		Thread t2 = new Thread(m);
		t1.start();
		t2.start();
		while (run)
		{
			CheckNewProcesses();
			Thread.sleep(1000);
			time++;
			System.out.println("Time: "+time);
		}
	}
	void stop()
	{
		run = false;
		d.stop();
	}
	public boolean freeProcessador() throws InterruptedException
	{
		return processadores.freeProcessador();
	}
	/**
	 * Le os processos do arquivo
	 * @param nomeArquivo
	 * @throws IOException
	 */
	public void loadProcesses(String nomeArquivo) throws IOException
	{
		System.out.println("Reading file "+nomeArquivo);
		System.out.println("Starting reading processes");
		processos = new ArrayList<Processo>();
		BufferedReader bf = new BufferedReader(new FileReader(nomeArquivo));
			
		while (bf.ready()) {
			String[] line = bf.readLine().split(",");
			processos.add(new Processo(Integer.parseInt(line[0].trim()), Integer.parseInt(line[1].trim()), Integer.parseInt(line[2].trim()), Integer.parseInt(line[3].trim())));
		}
			
		bf.close();
		System.out.println("Done");
		System.out.println("Processes:");
		System.out.println("ID\t BurstTime\t TimeArrive\t Priority");
		for (Processo p : processos)
			System.out.println(p.getID()+"\t"+p.getBurstTime()+"\t"+p.getTA()+"\t"+p.getP());
	}
	public void CheckNewProcesses() throws InterruptedException
	{
		synchronized(processos)
		{
			for (Processo p : processos)
			{
				if (time == p.getTA())
				{
					m.arrive(p);
					//processos.remove(p);
				}
			}
		}
	}
}
