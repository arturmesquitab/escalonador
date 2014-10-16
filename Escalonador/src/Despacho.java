
public class Despacho {
	private boolean run;
	private ProcessadorCollection l;
	public Despacho(ProcessadorCollection l)
	{
		run = false;
		this.l = l;
	}
	public void run()
	{
		run = true;
		while (run)
		{
			//Procurar processo para despachar
		}
	}
	public void stop()
	{
		run = false;
	}
}
