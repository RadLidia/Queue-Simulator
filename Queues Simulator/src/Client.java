public class Client {

	private int clientID;
	private int arrivalTime; //the client finished shopping(insert into the queue)
	private int serviceTime; //serving time (then remove from queue) 
	
	public Client(int newClientID, int newArrivalTime, int newServiceTime) {
		
		this.clientID = newClientID;
		this.arrivalTime = newArrivalTime;
		this.serviceTime = newServiceTime;
	}

	public int getArrivalTime() {
		return this.arrivalTime;	
	}
	
	public int getFinishTime() {
		return arrivalTime + serviceTime;	
	}

	public int getServiceTime() {
		return this.serviceTime;	
	}

	public int getClientID() {
		return this.clientID;	
	}

	public void setArrivalTime(int newArrivalTime) {
		this.arrivalTime = newArrivalTime;	
	}

	public void setServiceTime(int newServiceTime) {
		this.serviceTime = newServiceTime;	
	}

}
