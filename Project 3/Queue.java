
public class Queue {
	private String serverName;
	private int queueSize;
	private Client clientBeingServed;
	private Request requestInProgress;
	private int processingStartTime;
	private Client[] clientsHistory = new Client[0];
	private Client[] clientsInQueue;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	// Getters and Setters
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	public Client getClientBeingServed() {
		return clientBeingServed;
	}
	public void setClientBeingServed(Client clientBeingServed) {
		this.clientBeingServed = clientBeingServed;
	}

	public Request getRequestInProgress() {
		return requestInProgress;
	}
	public void setRequestInProgress(Request requestInProgress) {
		this.requestInProgress = requestInProgress;
	}

	public int getProcessingStartTime() {
		return processingStartTime;
	}
	public void setProcessingStartTime(int processingStartTime) {
		this.processingStartTime = processingStartTime;
	}

	public Client[] getClientsHistory() {
		return clientsHistory;
	}
	public void setClientsHistory(Client[] clientsHistory) {
		this.clientsHistory = clientsHistory;
	}
	
	public Client[] getClientsInQueue() {
		return clientsInQueue;
	}
	public void setClientsInQueue(Client[] clientsInQueue) {
		this.clientsInQueue = clientsInQueue;
	}
	
	public int getQueueNumber() {
		int queueNumber = 0;
		for (int i = 0; i < QueueSystem.getQueues().length; i++) {
			queueNumber++;
			if (QueueSystem.getQueues()[i] != null && QueueSystem.getQueues()[i].getServerName() == serverName) {
				break;
			}
		}
		return queueNumber;
	}
	
	public Queue(String serverName, int queueSize) {
		setServerName(serverName);
		setQueueSize(queueSize);
		setClientsInQueue(new Client[queueSize]);
	}
	
	public String toString() {
		String currentState = "[Queue:" + getQueueNumber() + "]";//
		if (clientBeingServed == null) {
			currentState += "[  ]";
		}
		else if (clientBeingServed.getId() >= 10) {
			currentState += "[" + clientBeingServed.getId() + "]";
		}
		else if (clientBeingServed.getId() < 10) {
			currentState += "[0" + clientBeingServed.getId() + "]";
		}
		currentState += "-----";
		for (int i = 0; i < clientsInQueue.length; i++) {
			if (clientsInQueue[i] == null) {
				currentState += "[  ]";
			}
			else if (clientsInQueue[i].getId() >= 10) {
				currentState += "[" + clientsInQueue[i].getId() + "]";
			}
			else if (clientsInQueue[i].getId() < 10) {
				currentState += "[0" + clientsInQueue[i].getId() + "]";
			}
		}
		return currentState;
	}
	public String toString(boolean showID) {
		if (showID == true) {
			return toString();
		}
		String currentState = "[Queue:" + getQueueNumber() + "]"; // fix
		if (clientBeingServed == null) {
			currentState += "[  ]-----";
		}
		else {
			int remainingProcessTime = clientBeingServed.serviceTime() - (QueueSystem.getClock() - processingStartTime);
			if (remainingProcessTime >= 10) {
				currentState += "[" + remainingProcessTime + "]-----";
			}
			else if (remainingProcessTime < 10) {
				currentState += "[0" + remainingProcessTime + "]-----";
			}
		}
		for (int i = 0; i < queueSize; i++) {
			if (clientsInQueue[i] == null) {
				currentState += "[  ]";
			}
			else if (clientsInQueue[i].getServiceTime() >= 10) {
				currentState += "[" + clientsInQueue[i].serviceTime() + "]";
			}
			else if (clientsInQueue[i].getServiceTime() < 10) {
				currentState += "[0" + clientsInQueue[i].serviceTime() + "]";
			}
		}
		return currentState;
	}
}
