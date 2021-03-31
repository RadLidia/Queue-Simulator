import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DataInput {
	private static int nrClients;   
	private static int nrCashRegisters;
	private static int tMaxSimulation;
	private static int arrivalTimeMin;
	private static int arrivalTimeMax;
	private static int serviceTimeMin;
	private static int serviceTimeMax;
	
	private List<Client> generatedClients = new ArrayList<Client>();
	
	private Client nextClient;
	
	public void readFromFile( String path) {
		try {
			File myObj = new File(path);

//			File myObj;
//			if(nrFile == 1) {
//				myObj = new File("C:\\Users\\Lidia\\eclipse-workspace\\Queues Simulator\\src\\In-Test-1.txt");
//			}else if(nrFile == 2) {
//				myObj = new File("C:\\Users\\Lidia\\eclipse-workspace\\Queues Simulator\\src\\In-Test-2.txt");
//			}else {
//				myObj = new File("C:\\Users\\Lidia\\eclipse-workspace\\Queues Simulator\\src\\In-Test-3.txt");
//			}
			
			if (myObj.exists()){                               
				Scanner inputFile = new Scanner(myObj); 
				while(inputFile.hasNextLine()) {
				
					nrClients = Integer.parseInt(inputFile.nextLine());    
					nrCashRegisters = Integer.parseInt(inputFile.nextLine()); 
					tMaxSimulation = Integer.parseInt(inputFile.nextLine()); 
								
					String[] arr = inputFile.nextLine().split(",");
					int[] intArr = new int[arr.length];
					 
					for(int i = 0; i < arr.length; i++)
						intArr[i] = Integer.parseInt(arr[i]);
					arrivalTimeMin = intArr[0]; 
					arrivalTimeMax = intArr[1];
				
					String[] arr1 = inputFile.nextLine().split(",");
					int[] intArr1 = new int[arr1.length];
					 
					for(int i = 0; i < arr1.length; i++)
						intArr1[i] = Integer.parseInt(arr1[i]);
					serviceTimeMin = intArr1[0]; 
					serviceTimeMax = intArr1[1]; 
				}
			inputFile.close();                        
			}
			
		}catch(FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void generate() {
		for (int i = 1; i <= nrClients; i++) {
			Random rand = new Random();
			int randomArrivalTime = arrivalTimeMin + rand.nextInt((arrivalTimeMax - arrivalTimeMin) + 1);
			int randomServiceTime = serviceTimeMin + rand.nextInt((serviceTimeMax - serviceTimeMin) + 1);

			Client x = new Client(i, randomArrivalTime, randomServiceTime);
			generatedClients.add(x);
		}
		generatedClients.sort(Comparator.comparing(Client::getArrivalTime));
	
	}
	
	//Waiting clients: (1,2,2); (2,3,3); (3,4,3); (4,10,2)
	public void display() {
		System.out.print("Waiting clients: ");
			for(Client c : generatedClients)// {
				System.out.print("(" + c.getClientID() + "," + c.getArrivalTime() + "," + c.getServiceTime() + "); ");
	
	}
	
	public int getNrClients() {
		return nrClients;	
	}
	
	public int getNrCashRegisters() {
		return nrCashRegisters;	
	}
	
	public int getTMaxSimulation() {
		return tMaxSimulation;	
	}
	public int getArrivalTimeMin() {
		return arrivalTimeMin;	
	}
	
	public Client getNextClient() {
		//-get next client from generatedClients and delete client from list
		if (DataInput.nrClients > 0){
			nextClient = generatedClients.get(0);
			generatedClients.remove(0);
			nrClients--;
		}else{
			nextClient = null;
		}
		return nextClient;
	}
	
	public int getServiceTimeMin() {
		return serviceTimeMin;	
	}
	public int getServiceTimeMax() {
		return serviceTimeMax;	
	}
}