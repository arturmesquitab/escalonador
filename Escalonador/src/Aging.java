
public class Aging extends QueueManager{

	@Override
	public boolean checkProblem(Processo p, int time) {
		if ((time - p.getTA() > 30) && p.getStatus() == StatusProcesso.QUEUE)
		{
			System.out.println(time+" "+p.getTA());
			return true;
		}
		return false;
	}

}
