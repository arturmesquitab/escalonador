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
	private int nProcessor;
	private int nQueue;
	private HashMap<Integer,Fila> queuetype;
	private String migration;
	private ProcessadorCollection processadores;
	private ArrayList<Processo> processos;
	private MultinivelFila m;
	private Relatorio r;
	/**
	 * Metodo construtor, usado pra forcar que as configuracoes iniciais do escalonador sejam
	 * inicializadas junto com o escalonador
	 * @param processos: String, representado os processos do sistema
	 * @param configuracoes: String, com as configuracoes inicias do sistema
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public Escalonador (String processos, String configuracoes) throws IOException, InterruptedException
	{
		loadProcesses(processos);
		Configuration c = new Configuration(configuracoes);
		setInitialParameters(c);
		System.out.println("All entries on system");
		this.d = new Despacho(processadores);
		this.r = new Relatorio(c);
		reportProcessos();
	}
	public void reportProcessos()
	{
		for (Processo p : processos)
		{
			Processo aux = new Processo(p);
			r.addProcesso(aux);
		}
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
		m = new MultinivelFila(this, queuetype,migration);
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
			Finish();
		}
		System.out.println("--------------ALL STOP--------------");
		r.setTempoTotal(time);
		m.reportFilas();
		System.exit(0);
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
	public boolean runProcessador() throws InterruptedException
	{
		return processadores.runProcessador();
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
	public void ChangeStatus (int id, StatusProcesso s)
	{
		synchronized(processos)
		{
			for (Processo p : processos)
			{
				if (p.getID() == id)
					p.setStatus(s);
			}
		}
	}
	public void Finish () throws InterruptedException
	{
		synchronized(processos)
		{
			boolean b = true;
			for (Processo p : processos)
			{
				if (p.getTA() >= time)
					b = false;
			}
			if (b && (m.next() == -1) && !(this.runProcessador()))
				stop();
		}
	}
	public Relatorio getR() {
		return r;
	}
}
