
/**
 * Client class serves
 * @author Sivalee Intachit
 *
 */
public class Client {
	private int id;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private Gender gender;
	private Request[] requests;
	private int arrivalTime;
	private int waitingTime;
	private int timeInQueue;
	private int serviceTime;
	private int departureTime;
	private int patience;
	/**
	 * uniqueId serves as a tracker to produce a unique ID for each Client.
	 */
	private static int uniqueId = 1;
	
	// Getters and setters
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = uniqueId;
		uniqueId++;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if (gender.equals("MALE")) {
			this.gender = Gender.MALE;
		}
		else if (gender.equals("FEMALE")) {
			this.gender = Gender.FEMALE;
		}
	}
	
	public Request[] getRequests() {
		return requests;
	}
	public void setRequests(Request[] requests) {
		this.requests = requests;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		if (arrivalTime >= 1) {
			this.arrivalTime = arrivalTime;
		}
		else {
			this.arrivalTime = 0;
		}
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		if (waitingTime >= 0) {
			this.waitingTime = waitingTime;
		}
	}
	
	public int getTimeInQueue() {
		return timeInQueue;
	}
	public void setTimeInQueue(int timeInQueue) {
		if (timeInQueue >= 0) {
			this.timeInQueue = timeInQueue;
		}
	}
	
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	
	public int getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(int departureTime) {
		if (departureTime == 0 || departureTime > arrivalTime + waitingTime + timeInQueue) {
			this.departureTime = QueueSystem.getClock();
		}
		this.departureTime = departureTime;
	}
	
	public int getPatience() {
		return patience;
	}
	public void setPatience(int patience) {
		this.patience = patience;
		// If there is a TV in the waiting area, increase client's patience by 20
		if (QueueSystem.getTvInWaitingArea() == true) {
			this.patience += 20;
		}
		// If there is a coffee machine in the waiting area and the client is an adult, increase client's patience by 15
		if (QueueSystem.getCoffeeInWaitingArea() == true && 2023 - yearOfBirth >= 18) {
			this.patience += 20;
		}
	}
	
	public Client(String firstName, String lastName, int yearOfBirth, String gender, int arrivalTime, int patience, Request[] requests) {
		setId();
		setFirstName(firstName);
		setLastName(lastName);
		setYearOfBirth(yearOfBirth);
		setGender(gender);
		setArrivalTime(arrivalTime);
		setPatience(patience);
		setRequests(requests);
		serviceTime();
		waitingTime = 0;
		timeInQueue = 0;
		departureTime = 0;
	}
	public Client(String firstName, String lastName, int yearOfBirth, String gender, int patience, Request[] requests) {
		setId();
		setFirstName(firstName);
		setLastName(lastName);
		setYearOfBirth(yearOfBirth);
		setGender(gender);
		setPatience(patience);
		setRequests(requests);
		serviceTime();
		arrivalTime = QueueSystem.getClock();
		waitingTime = 0;
		timeInQueue = 0;
		departureTime = 0;
	}
	public Client (String firstName, String lastName, int yearOfBirth, String gender, int patience) {
		setId();
		setFirstName(firstName);
		setLastName(lastName);
		setYearOfBirth(yearOfBirth);
		setGender(gender);
		setPatience(patience);
		serviceTime();
		arrivalTime = QueueSystem.getClock();
		waitingTime = 0;
		timeInQueue = 0;
		departureTime = 0;
	}
	
	public int serviceTime() {
		int calcTime = 0;
		if (requests != null) {
			for (int i = 0; i < requests.length; i++) {
				calcTime += requests[i].calculateProcessingTime();
			}
		}
		serviceTime = calcTime;
		return calcTime;
	}
	
	public int estimateServiceLevel() {
		boolean clientInSystem = true;
		for (int i = 0; i < QueueSystem.getClientsWorld().length; i++) {
			if (QueueSystem.getClientsWorld()[i] != null && id == QueueSystem.getClientsWorld()[i].getId()) {
				clientInSystem = false;
				break;
			}
		}
		if (departureTime == 0 && clientInSystem == true) {
			return -1;
		}
		int serviceLevel = 10;
		
		if (waitingTime > 4) serviceLevel--;
		if (waitingTime > 6) serviceLevel--;
		if (waitingTime > 8) serviceLevel--;
		if (waitingTime > 10) serviceLevel--;
		if (waitingTime > 12) serviceLevel--;
		
		if (timeInQueue > 4) serviceLevel--;
		if (timeInQueue > 6) serviceLevel--;
		if (timeInQueue > 8) serviceLevel--;
		if (timeInQueue > 10) serviceLevel--;
		if (timeInQueue > 12) serviceLevel--;
		
		return serviceLevel;
	}
	
	// HELPER METHOD
	public String getServerName() {
		String serverName = "";
		for (int i = 0; i < QueueSystem.getQueues().length; i++) {
			Queue queue = QueueSystem.getQueues()[i];
			if (queue.getClientBeingServed() != null && queue.getClientBeingServed().getId() == id) {
				serverName = queue.getServerName();
			}
		}
		if (serverName == "") {
			for (int i = 0; i < QueueSystem.getQueues().length; i++) {
				Queue queue = QueueSystem.getQueues()[i];
				for (int j = 0; j < queue.getClientsHistory().length; j++) {
					if (queue.getClientsHistory()[j].getId() == id) {
						serverName = queue.getServerName();
					}
				}
			}
		}
		return serverName;
	}
	
	public String toString() {
		return "Client: " + lastName + ", " + firstName + "\n" +
				"** Arrival Time    : " + arrivalTime + "\n" +
				"** Waiting Time    : " + waitingTime + "\n" +
				"** Time In Queue   : " + timeInQueue + "\n" +
				"** Service Time    : " + serviceTime + "\n" +
				"** Departure Time  : " + departureTime + "\n" +
				"** Served By Server: " + getServerName() + "\n" +
				"** Service Level   : " + estimateServiceLevel() + "\n";
		
	}
}
