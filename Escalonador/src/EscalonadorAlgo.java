import java.util.ArrayList;

public abstract class EscalonadorAlgo {
	public abstract  void addProcesso(Processo processo);
	public abstract void removeProcesso();
	public abstract Processo getCurrent();
	public abstract  void run(ArrayList<Processo> lista);
}
