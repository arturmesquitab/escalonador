import java.util.ArrayList;

/*------------PARA IMPRIMIR OS DADOS NECESSÁRIOS!!!---------------------
 * i) Lista dos parametros: get dos parametros do configuration
 * ii) Tempo total do processamento: atributo TempoTotal da classe Relatorio
 * iii) Percentual de utilização da cpu: Seja p um dos processadores de listProcessadores(classe Relatorio). O percentual de
 * utilização é dado por: (p.getTempoTotal/TempoTotal)*100
 * iv) Media Throughput dos processos: (listProcessos.size()/TempoTotal)*100
 * v) Media turnaround dos processos: Seja p um dos processos de listProcessos (classe Relatorio). Seu turnaround é 
 * (p.getTimeFinish). Depois é só fazer a média
 * vi) Media tempo de espera: Seja p um dos processos de listProcessos (classe Relatorio). Seu turnaround é 
 * (p.getTimeFinish - p.getTA). Depois é só fazer a média
 * vii) Media tempo de resposta: ???
 * viii) Numero de processos executados: listProcessos.size()
 * ix) Numero de processos em cada fila de execução: processosFila.get(i), para a fila i.
 */
public class Relatorio {
	private Configuration c;
	private ArrayList<Processo> listProcessos;
	private int TempoTotal;
	private ArrayList<Processador> listProcessadores;
	private ArrayList<Integer> processosFila;
	public Relatorio (Configuration c)
	{
		this.c = c;
		listProcessos = new ArrayList<Processo>();
		listProcessadores = new ArrayList<Processador>();
		processosFila = new ArrayList<Integer>();
	}
	public void addProcesso (Processo p)
	{
		listProcessos.add(p);
	}
	public void ProcessoAjustTime(int id, int time, int realTime)
	{
		for (Processo p : listProcessos)
		{
			if (p.getID() == id)
			{
				p.setTime(p.getTime()+time);
				if (p.getTime() == p.getBurstTime())
				{
					p.setStatus(StatusProcesso.FINISH);
					p.setTimeFinish(realTime);
				}
			}
		}
	}
	public int getTempoTotal() {
		return TempoTotal;
	}
	public void setTempoTotal(int tempoTotal) {
		TempoTotal = tempoTotal;
	}
	public void addProcessador(Processador p)
	{
		listProcessadores.add(p);
	}
	public void addnProcessoFila(int n)
	{
		processosFila.add(n);
	}
}
