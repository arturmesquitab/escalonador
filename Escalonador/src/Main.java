import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(args[0]);
		System.out.println(args[1]);
		Escalonador e = new Escalonador(args[0],args[1]);
		Thread t = new Thread(e);
		t.start();
	}

}
