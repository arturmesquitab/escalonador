import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class Configuration {
	private int numProcessors;
	private int numQueues;
	private ArrayList<String> alAlgorithms;
	private HashMap<Integer, Integer> alQuantum;
	private String migration;

	public Configuration(String s) throws IOException
	{
		setInitialParameters(s);
	}


	public void setInitialParameters(String path) throws IOException {
		System.out.println("Reading file "+path);
		System.out.println("Starting reading configurations");
		alAlgorithms = new ArrayList<String>();
		alQuantum = new HashMap<Integer,Integer>();
		
		BufferedReader bf = new BufferedReader(new FileReader(path));
			
		while (bf.ready()) {
			String line[] = bf.readLine().split("=");
			
			if (line[0].contains("#proc"))
				numProcessors = Integer.parseInt(line[1].trim());
			
			else if (line[0].contains("#queue"))
				numQueues = Integer.parseInt(line[1].trim());
			
			else if (line[0].contains("q")) {
				
				if (line[0].contains("quantum"))
				{
					int i;
					for (i = 0;i < line[0].length();i++)
					{
						if (line[0].charAt(i) == '.')
							break;
					}
					alQuantum.put(Integer.parseInt((String) line[0].subSequence(1, i)),Integer.parseInt(line[1].trim()));
				}
				else
					alAlgorithms.add(line[1].trim());
				
			} else if (line[0].contains("migration"))
				migration = line[1].trim();
		}
		System.out.println("Done");
		System.out.println("Configurations:");
		System.out.println("N processors: "+numProcessors);
		System.out.println("N queue: "+numQueues);
		System.out.println("Queues:");
		for (String s : alAlgorithms)
			System.out.println(s);
		System.out.println("Entries:");
		System.out.println(alQuantum);
		System.out.println(migration);
		bf.close();
		
	}




	public int getNumProcessors() {
		return numProcessors;
	}




	public int getNumQueues() {
		return numQueues;
	}




	public ArrayList<String> getAlAlgorithms() {
		return alAlgorithms;
	}




	public HashMap<Integer, Integer> getAlQuantum() {
		return alQuantum;
	}




	public String getMigration() {
		return migration;
	}
}
