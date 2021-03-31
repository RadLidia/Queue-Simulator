
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SimulationManager extends Thread{
    
	private Client nextClient;
	private static DataInput data = new DataInput();
	private CashRegisters cashReg[];
	private int waitingTime;
	
	public SimulationManager() {
		
		//--create and start nr of queues threads
		waitingTime = 0;
		cashReg = new CashRegisters[data.getNrCashRegisters()];
		for(int i = 0; i < data.getNrCashRegisters(); i++) {
			cashReg[i] = new CashRegisters();
			Thread t = new Thread(cashReg[i]);
			t.start();
		}
		
		data.generate();
		data.display();
	}
	
	@Override
	public void run() {
		
		System.out.println();
		int time = 0, i, index, nextTime;
		nextClient = data.getNextClient();
		nextTime = nextClient.getArrivalTime();
		while(time < data.getTMaxSimulation()) {
			//iterate generatedClients and pick clients that have the arrival time = with the curr time
			//-send client to queue
			time++;
			System.out.println("Time " + time);
			while (nextTime == time) {		
				
				//shortest queue
				index = 0;
				int min = cashReg[0].getCashRegQueueLength();

				for (i = 0; i < data.getNrCashRegisters(); i++) {
					if (cashReg[i].getCashRegQueueLength() < min) {
						min = cashReg[i].getCashRegQueueLength();
						index = i;
					}
				}
				
				cashReg[index].insertClient(nextClient);
				System.out.println("INSERT: Client " + nextClient.getClientID() + " Was Added to the queue " + (index+1) + ".\n");	
				
				waitingTime += -(time - nextClient.getFinishTime());
				nextClient = data.getNextClient();
				if (nextClient != null){
					nextTime = nextClient.getArrivalTime();
				}
				else{
					nextTime = 0;
				}
			}

			//wait an interval of 1 sec
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Average waiting time is: " + (waitingTime / (4 * 1.0)));
		
	}
	
	public static void main(String[] args) throws IOException {


		data.readFromFile(args[0]);
		File file = new File(args[1]);

		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
	
		SimulationManager gen = new SimulationManager();
		Thread t = new Thread(gen);
		t.start();
	}


}
