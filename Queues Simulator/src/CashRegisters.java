import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//Queue = FIFO
public class CashRegisters implements Runnable {
	
	private BlockingQueue<Client> queueClients;
	private int cashRegisterId;
	private int currCashRegSize = 0;
		
    public CashRegisters() {
    	queueClients = new ArrayBlockingQueue<Client>(1001);
    }
	
	public void insertClient(Client newClient) {
		queueClients.add(newClient);
	}
	
	public void run() {
		
		while(true) {
			//take next client from the queue 'queueClients'
			//stop the thread for a time equal with the client's serviceTime
			try {		
				Client servingClient;
		
				servingClient = queueClients.take();
				currCashRegSize++;
				Thread.sleep(servingClient.getServiceTime() * 1001);
				System.out.println("REMOVE: Client " + servingClient.getClientID() + " was removed from the queue " + (this.cashRegisterId+1) + ".\n");
				
			}catch(InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public BlockingQueue<Client> getQueueClients() {
		return queueClients;

	}
	
	public int getCashRegQueueLength() {
		return this.currCashRegSize;
	}
	
}
