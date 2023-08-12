
public class QueueSystem {
	private static int clock;
	private static int totalWaitingTime;
	private static Client[] clientsWorld;
	private static int totalClientsInSystem;
	private static int waitingLineSize;
	private static Client[] waitingLine;
	private static boolean tvInWaitingArea;
	private static boolean coffeeInWaitingArea;
	private static Queue[] queues;
	
	// Getters and Setters
	public static int getClock() {
		return clock;
	}
	public static void setClock(int clock) {
		QueueSystem.clock = clock;
	}
	
	public static int getTotalWaitingTime() {
		int totalWaitingTime = 0;
		// Get total waiting time of clients being served
		for (int i = 0; i < queues.length; i++) {
			totalWaitingTime += queues[i].getClientBeingServed().getWaitingTime();
		}
		// Get total waiting time of clients in queues
		for (int i = 0; i < queues.length; i++) {
			for (int j = 0; j < queues[i].getClientsInQueue().length; j++) {
				totalWaitingTime += queues[i].getClientsInQueue()[j].getWaitingTime();
			}
		}
		// Get total waiting time of clients in waiting line
		for (int i = 0; i < waitingLine.length; i++) {
			totalWaitingTime += waitingLine[i].getWaitingTime();
		}
		return totalWaitingTime;
	}
	public static void setTotalWaitingTime(int totalWaitingTime) {
		QueueSystem.totalWaitingTime = totalWaitingTime;
	}
	
	public static Client[] getClientsWorld() {
		return clientsWorld;
	}
	public static void setClientsWorld(Client[] clientsWorld) {
		QueueSystem.clientsWorld = clientsWorld;
	}
	
	public static int getTotalClientsInSystem() {
		return totalClientsInSystem;
	}
	public static void setTotalClientsInSystem(int totalClientsInSystem) {
		QueueSystem.totalClientsInSystem = totalClientsInSystem;
	}
	
	public static int getWaitingLineSize() {
		return waitingLineSize;
	}
	public static void setWaitingLineSize(int waitingLineSize) {
		QueueSystem.waitingLineSize = waitingLineSize;
	}
	
	public static Client[] getWaitingLine() {
		return waitingLine;
	}
	public static void setWaitingLine(Client[] waitingLine) {
		QueueSystem.waitingLine = waitingLine;
	}
	
	public static boolean getTvInWaitingArea() {
		return tvInWaitingArea;
	}
	public static void setTvInWaitingArea(boolean tvInWaitingArea) {
		QueueSystem.tvInWaitingArea = tvInWaitingArea;
	}

	public static boolean getCoffeeInWaitingArea() {
		return coffeeInWaitingArea;
	}
	public static void setCoffeeInWaitingArea(boolean coffeeInWaitingArea) {
		QueueSystem.coffeeInWaitingArea = coffeeInWaitingArea;
	}
	
	public static Queue[] getQueues() {
		return queues;
	}
	public static void setQueues(Queue[] queues) {
		QueueSystem.queues = queues;
	}
	
	public QueueSystem (int waitingLineSize, boolean tvInWaitingArea, boolean coffeeInWaitingArea) {
		QueueSystem.clock = 0;
		QueueSystem.waitingLineSize = waitingLineSize;
		QueueSystem.tvInWaitingArea = tvInWaitingArea;
		QueueSystem.coffeeInWaitingArea = coffeeInWaitingArea;
		
		// Initialize waitingLine based on waitingLineSize
		waitingLine = new Client[waitingLineSize];
	}
	
	public static void increaseTime() {
		// Increase time
		clock++;
		
		// Retrieve incoming clients
		int numNewClients = 0;
		int numVIPClients = 0;
		for (int i = 0; i < clientsWorld.length; i++) {
			if (clientsWorld[i] != null && clientsWorld[i].getArrivalTime() == clock) {
				numNewClients++;
				// Calculate number of new VIP clients
				if (clientsWorld[i] instanceof VIPClient) {
					numVIPClients++;
				}
			}
		}
		Client[] newClients = new Client[numNewClients];
		int index = 0;
		
		// Check if there is a VIP queue in the system
		boolean hasVIPQueue = false;
		boolean vipSort = false;
		int vipIndex = 0;
		for (int i = 0; i < queues.length; i++) {
			if (queues[i] instanceof VIPQueue) {					
				hasVIPQueue = true;
				vipIndex = i;
				break;
			}
		}
		// If there is a VIP queue in the system, check if there are any new VIP clients incoming
		if (hasVIPQueue == true && numVIPClients > 0) {
			for (int i = 0; i < clientsWorld.length; i++) {
				if (clientsWorld[i] != null && clientsWorld[i] instanceof VIPClient && clientsWorld[i].getArrivalTime() == clock) {
					newClients[index] = clientsWorld[i];
					clientsWorld[i] = null;
					index++;
				}
			}
			// Order VIP clients according to business rules
			for (int i = 0; i < numVIPClients; i++) {
				for (int j = i + 1; j < numVIPClients; j++) {
					// Order by priority
					if (((VIPClient) newClients[i]).getPriority() > ((VIPClient)newClients[j]).getPriority()) {
						Client client = newClients[i];
						newClients[i] = newClients[j];
						newClients[j] = client;
						vipSort = true;
					}
					// Order by membership
					else if (((VIPClient) newClients[i]).getPriority() == ((VIPClient)newClients[j]).getPriority()) {
						if (((VIPClient) newClients[i]).getMemberSince() < ((VIPClient) newClients[j]).getMemberSince()) {
							Client client = newClients[i];
							newClients[i] = newClients[j];
							newClients[j] = client;
							vipSort = true;
						}
					}
				}
			}
		}
		
		// Add remaining incoming clients to newClients
		for (int i = 0; i < clientsWorld.length; i++) {
			if (clientsWorld[i] != null && clientsWorld[i].getArrivalTime() == clock) {
				newClients[index] = clientsWorld[i];
				clientsWorld[i] = null;
				index++;
			}
		}
		
		// If neither VIP clients were sorted according to priority or membership, order the clients like normal
		if (hasVIPQueue == true && vipSort == true) {
			index = numVIPClients;
		}
		else {
			index = 0;
		}
		// For all clients, order them according to business rules
		if (numNewClients > 0) {
			for (int i = 0; i < numNewClients; i++) {
				for (int j = i + 1; j < numNewClients; j++) {
					// Order by age
					if (newClients[i].getYearOfBirth() > newClients[j].getYearOfBirth()) {
						Client client = newClients[i];
						newClients[i] = newClients[j];
						newClients[j] = client;
					}
					// Order by processing time
					else if (newClients[i].getYearOfBirth() == newClients[j].getYearOfBirth()) {
						if (newClients[i].serviceTime() > newClients[j].getServiceTime()) {
							Client client = newClients[i];
							newClients[i] = newClients[j];
							newClients[j] = client;
						}
						// Order by service time
						else if (newClients[i].serviceTime() == newClients[j].getServiceTime()) {
							if (newClients[i].getLastName().compareTo(newClients[j].getLastName()) > 0) {
								Client client = newClients[i];
								newClients[i] = newClients[j];
								newClients[j] = client;
							}
							// Order by last name
							else if (newClients[i].getLastName().compareTo(newClients[j].getLastName()) == 0) {
								if (newClients[i].getFirstName().compareTo(newClients[i].getFirstName()) > 0) {
									Client client = newClients[i];
									newClients[i] = newClients[j];
									newClients[j] = client;
								}
								// Order by first name
								else if (newClients[i].getFirstName().compareTo(newClients[i].getFirstName()) == 0) {
									if (newClients[i].getId() < newClients[j].getId()) {
										Client client = newClients[i];
										newClients[i] = newClients[j];
										newClients[j] = client;
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Calculate the total number of VIP clients in the system
		for (int i = 0; i < queues.length; i++) {
			if (queues[i].getClientBeingServed() instanceof VIPClient) {
				numVIPClients++;
			}
			for (int j = 0; j < queues[i].getClientsInQueue().length; j++) {
				if (queues[i].getClientsInQueue()[j] instanceof VIPClient) {
					numVIPClients++;
				}
			}
		}
		for (int i = 0; i < waitingLine.length; i++) {
			if (waitingLine[i] != null && waitingLine[i] instanceof VIPClient) {
				numVIPClients++;
			}
		}
		
		// If there are VIP clients in the system, then acceptingAnyClients is false. Otherwise, acceptingAnyClients is true.
		if (hasVIPQueue == true && numVIPClients > 0) {
			((VIPQueue) queues[vipIndex]).setAcceptingAnyClients(false);
		}
		else if (hasVIPQueue == true && numVIPClients == 0) {
			((VIPQueue) queues[vipIndex]).setAcceptingAnyClients(true);
		}
		 	
		// Update patience and waitingTime for each client who still remains in the waiting line
		int numLeavingWaiting = 0;
		for (int i = 0; i < waitingLine.length; i++) {
			if (waitingLine[i] == null) {
				break;
			}
			waitingLine[i].setWaitingTime(waitingLine[i].getWaitingTime() + 1);
			waitingLine[i].setPatience(waitingLine[i].getPatience() - 1);
			// If a client runs out of patience, remove them from the waiting line
		 	if (waitingLine[i].getPatience() < 0) {
		 		numLeavingWaiting++;
		 		waitingLine[i].setDepartureTime(clock);
		 		waitingLine[i] = null;
		 	}
		}
		// If a client leaves the waiting line early, update the array accordingly
		if (numLeavingWaiting > 0) {
		 	Client[] tempArray = new Client[waitingLine.length];
		 	int waitingIndex = 0;
		 	for (int i = 0; i < waitingLine.length; i++) {
		 		if (waitingLine[i] != null) {
		 			tempArray[waitingIndex] = waitingLine[i];
		 			waitingIndex++;
		 		}
		 	}
		 	waitingLine = tempArray;
		}
		
		int newClientTracker = 0;
		
		// Before adding to the queue/waitingLine, check if all requests of clientsBeingServed have been processed
 		for (int i = 0; i < queues.length; i++) {
 			if (queues[i].getClientBeingServed() != null) {
				Request[] requests = queues[i].getClientBeingServed().getRequests();
				int numProcessed = 0;
				for (int j = 0; j < requests.length; j++) {
					if (requests[j].getStatus() == Status.PROCESSED) {
						numProcessed++;
						continue;
					}
					else if (requests[j].getStatus() == Status.NEW) {
						requests[j].setStartTime(clock);
						requests[j].setStatus(Status.IN_PROGRESS);
						queues[i].setProcessingStartTime(clock);
						queues[i].setRequestInProgress(requests[j]);
						break;
					}
					else if (requests[j].getStatus() == Status.IN_PROGRESS) {
						// Check if request is complete after increasing time
						if (clock - requests[j].getStartTime() == requests[j].calculateProcessingTime()) {
							// If request is complete after increasing time, set endTime and update status
							numProcessed++;
							requests[j].setEndTime(clock);
							requests[j].setCompletionLevel(100);
							requests[j].setStatus(Status.PROCESSED);
							// If there are more requests, start on the next request on the list
							if (j != requests.length - 1) {
								requests[j+1].setStartTime(clock);
								requests[j+1].setStatus(Status.IN_PROGRESS);
								queues[i].setRequestInProgress(requests[j+1]);
							}
						}
						else {
							// If request is not complete after increasing time, update completion level
							requests[j].setCompletionLevel((clock - requests[j].getStartTime() / requests[j].calculateProcessingTime()) * 100);
						}
						break;
					}
				}
				// If all requests have been completed, complete the following:
				if (numProcessed == requests.length) {
					// Update client history
					Client[] tempClientHistory = new Client[queues[i].getClientsHistory().length + 1];
					if (tempClientHistory.length == 1) {
						tempClientHistory[0] = queues[i].getClientBeingServed();
					}
					else {
						for (int j = 0; j < queues[i].getClientsHistory().length; j++) {
							tempClientHistory[i] = queues[i].getClientsHistory()[j];
						}
						tempClientHistory[tempClientHistory.length-1] = queues[i].getClientBeingServed();
					}
					queues[i].setClientsHistory(tempClientHistory);
					// If client is a VIP member, update numVIPClients
					if (queues[i].getClientBeingServed() instanceof VIPClient) {
						numVIPClients--;
						if (numVIPClients == 0) {
							((VIPQueue) queues[vipIndex]).setAcceptingAnyClients(true);
						}
					}
					
					// Remove client from the system
					queues[i].getClientBeingServed().setDepartureTime(clock);
					queues[i].setClientBeingServed(null);
					// Start serving the next client in queue and update queue accordingly
					Client[] clientsInQueue = queues[i].getClientsInQueue();
					if (clientsInQueue[0] != null) {
						clientsInQueue[0].setTimeInQueue(clientsInQueue[0].getTimeInQueue() + 1);
						queues[i].setClientBeingServed(clientsInQueue[0]);
						Request[] nextRequest = queues[i].getClientBeingServed().getRequests();
						nextRequest[0].setStartTime(clock);
						nextRequest[0].setStartTime(clock);
						nextRequest[0].setStatus(Status.IN_PROGRESS);
						queues[i].setProcessingStartTime(clock);
						queues[i].setRequestInProgress(nextRequest[0]);
						for (int j = 0; j < clientsInQueue.length; j++) {
							if (j == clientsInQueue.length - 1) {
								clientsInQueue[j] = null;
								break;
							}
							clientsInQueue[j] = clientsInQueue[j + 1];
						}
					}

					// Shift clients up one slot in the queue and update the waiting line accordingly
					if (waitingLine[0] != null) {
						// If no VIP members are remaining and no client is being served in the VIP queue, assign the next
						// person in waiting line to clientBeingServed
						if (hasVIPQueue == true && ((VIPQueue) queues[vipIndex]).getAcceptingAnyClients() == true &&
								((VIPQueue) queues[vipIndex]).getClientBeingServed() == null && numVIPClients == 0) {
							queues[vipIndex].setClientBeingServed(waitingLine[0]);
						}
						else {
							clientsInQueue[clientsInQueue.length - 1] = waitingLine[0];
							// Add one to patience to prevent over calculation in the following steps
							clientsInQueue[clientsInQueue.length - 1].setPatience(clientsInQueue[clientsInQueue.length - 1].getPatience() + 1);
						}
						
						waitingLine[0] = null;
						for (int j = 0; j < waitingLine.length; j++) {
							if (j == waitingLine.length - 1) {
								waitingLine[j] = null;
								break;
							}
							waitingLine[j] = waitingLine[j+1];
						}
						waitingLine[waitingLine.length - 1] = null;
					}
				}
			}
 			// If no client is being served
 			else if (queues[i].getClientBeingServed() == null) {
				// Serve first incoming client(s)
 				if (newClients.length != 0) {
 					// If the queue is not a VIP queue OR is a VIP queue that is accepting any clients, set the next available client in
 					// newClients as the client being served.
 					if (queues[i] instanceof VIPQueue == false ||
 							(queues[i] instanceof VIPQueue && ((VIPQueue) queues[i]).getAcceptingAnyClients() == true && numVIPClients == 0)) {
 						for (int k = 0; k < newClients.length; k++) {
 							if (newClients[k] != null) {
 								queues[i].setClientBeingServed(newClients[k]);
 		 	 					newClients[k] = null;
 		 	 					newClientTracker++;
 		 	 					Request[] requests = queues[i].getClientBeingServed().getRequests();
 		 	 					requests[0].setStartTime(clock);
 		 	 					requests[0].setStatus(Status.IN_PROGRESS);
 		 	 					queues[i].setProcessingStartTime(clock);
 		 	 					queues[i].setRequestInProgress(requests[0]);
 		 	 					break;
 							}
 						}
 					}
 					// If the queue is a VIP queue and is not accepting any clients, assign the next available VIP client in newClients
 					// as the client being served.
 					else if (queues[i] instanceof VIPQueue && ((VIPQueue) queues[i]).getAcceptingAnyClients() == false && numVIPClients > 0) {
 						for (int k = 0; k < newClients.length; k++) {
 							if (newClients[k] instanceof VIPClient) {
 								queues[i].setClientBeingServed(newClients[k]);
 		 	 					newClients[k] = null;
 		 	 					newClientTracker++;
 		 	 					Request[] requests = queues[i].getClientBeingServed().getRequests();
 		 	 					requests[0].setStartTime(clock);
 		 	 					requests[0].setStatus(Status.IN_PROGRESS);
 		 	 					queues[i].setProcessingStartTime(clock);
 		 	 					queues[i].setRequestInProgress(requests[0]);
 		 	 					break;
 							}
 						}
 					}
 				}
			}
		}
 		// Update patience and timeInQueue for each client who still remains in the queue
 		int numLeavingQueue = 0;
 		for (int i = 0; i < queues.length; i++) {
 				Client[] clientsInQueue = queues[i].getClientsInQueue();
 				for (int j = 0; j < clientsInQueue.length; j++) {
 					if (clientsInQueue[j] == null) {
 						break;
 					}
 	 			clientsInQueue[j].setTimeInQueue(clientsInQueue[j].getTimeInQueue() + 1);
 	 			clientsInQueue[j].setPatience(clientsInQueue[j].getPatience() - 1);
 	 			// If a client runs out of patience, remove them from the queue
 	 			if (clientsInQueue[j].getPatience() < 0) {
 	 				numLeavingQueue++;
 	 				clientsInQueue[j].setDepartureTime(clock);
 	 				clientsInQueue[j] = null;
 	 			}
 	 		}
 	 		// If a client leaves the queue early, update the array accordingly
 	 	 	if (numLeavingQueue > 0) {
 	 	 		Client[] tempArray = new Client[clientsInQueue.length];
 	 	 		int counter = 0;
 	 	 		for (int j = 0; j < clientsInQueue.length; j++) {
 	 	 			if (clientsInQueue[j] != null) {
 	 	 				tempArray[counter] = clientsInQueue[j];
 	 	 				counter++;
 	 	 			}
 	 	 		}
 	 	 		clientsInQueue = tempArray;
 	 	 	}
 	 	}
 		
 		// Assign remaining incoming clients to queue(s) and waiting line accordingly
 		// If there is an available spot in queue(s), add client to the queue
 		int maxQueueSize = 0;
 		for (int i = 0; i < queues.length; i++) {
 			if (queues[i].getQueueSize() > maxQueueSize) {
 				maxQueueSize = queues[i].getQueueSize();
 			}
 		}
 		for (int i = 0; i < maxQueueSize; i++) {
 			for (int k = 0; k < queues.length; k++) {
 				// If the queue is not a VIP queue OR is a VIP queue that is accepting any clients, then add the next available client
 				// in newClients to the queue
 				if (i >= queues[k].getQueueSize()) {
 					continue;
 				}
 				if (queues[k] instanceof VIPQueue == false ||
							(queues[k] instanceof VIPQueue && ((VIPQueue) queues[k]).getAcceptingAnyClients() == true && numVIPClients == 0)) {
 					if (newClientTracker == numNewClients) {
 	 	 				break;
 	 				}
 	 				if (queues[k].getClientsInQueue()[i] == null) {
 	 					for (int j = 0; j < newClients.length; j++) {
 	 						if (newClients[j] != null) {
 	 							queues[k].getClientsInQueue()[i] = newClients[j];
 	 							newClients[j] = null;
 	 							newClientTracker++;
 	 							break;
 	 						}
 	 					}
 	 				}
 				}
 				// If the queue is a VIP queue and is not accepting any clients, then add the next available VIP client
 				// in newClients to the queue
 				else if (queues[k] instanceof VIPQueue == true &&
 						((VIPQueue) queues[k]).getAcceptingAnyClients() == false && numVIPClients > 0) {
 					int vipTracker = 0;
 					if (queues[k].getClientsInQueue()[i] == null) {
 	 					for (int j = 0; j < newClients.length; j++) {
 	 						if (vipTracker == numVIPClients) {
 	 							break;
 	 						}
 	 						if (newClients[j] != null && newClients[j] instanceof VIPClient) {
 	 							queues[k].getClientsInQueue()[i] = newClients[j];
 	 							newClients[j] = null;
 	 							vipTracker++;
 	 							newClientTracker++;
 	 							break;
 	 						}
 	 					}
 					}
 				}
 			}
 			if (newClientTracker == numNewClients) {
 				break;
 			}
 		}
 		// If there are no more spots in the queue, add client to waiting line accordingly
 		if (newClientTracker < numNewClients) {
 			for (int i = 0; i < waitingLine.length; i++) {
 				if (newClientTracker == numNewClients) {
 					break;
 				}
 				else if (waitingLine[i] == null) {
 					for (int j = 0; j < newClients.length; j++) {
 						if (newClients[j] != null) {
 							waitingLine[i] = newClients[j];
 							newClients[j] = null;
 							newClientTracker++;
 							break;
 						}
 					}
 				}
 			}
 		}
	}
	
	public static void increaseTime(int time) {
		for (int i = 0; i < time; i++) {
			increaseTime();
		}
	}
	
	public static Client[] getClientsBeingServed() {
		Client[] clientsBeingServed = new Client[queues.length];
		int nullCount = 0;
		for (int i = 0; i < queues.length; i++) {
			if (queues[i].getClientBeingServed() != null) {
				clientsBeingServed[i] = queues[i].getClientBeingServed();
			}
			else {
				nullCount++;
			}
		}
		if (nullCount > 0) {
			nullCount = 0;
			Client[] tempArray = new Client[clientsBeingServed.length - nullCount];
			for (int i = 0; i < clientsBeingServed.length; i++) {
				if (clientsBeingServed[i] == null) {
					nullCount++;
				}
				tempArray[i - nullCount] = clientsBeingServed[i];
			}
			clientsBeingServed = tempArray;
		}
		return clientsBeingServed;
	}
	
	public String toString() {
		String currentState = "";
		
		for (int i = 0; i < queues.length; i++) {
			currentState += queues[i].toString(false) + "\n";
		}
		
		currentState += "---\n[WaitingLine]-";
		for (int i = 0; i < waitingLine.length; i++) {
			if (waitingLine[i] == null) {
				currentState += "[  ]";
			}
			else if (waitingLine[i].serviceTime() >= 10) {
				currentState += "[" + waitingLine[i].serviceTime() + "]";
			}
			else if (waitingLine[i].serviceTime() < 10) {
				currentState += "[0" + waitingLine[i].serviceTime() + "]";
			}
		}
		
		return currentState;
	}
	public String toString(boolean showID) {
		if (showID == false) {
			return toString();
		}
		
		String currentState = "";
		for (int i = 0; i < queues.length; i++) {
			currentState += queues[i].toString() + "\n";
		}
		
		currentState += "---\n[WaitingLine]-";
		if (waitingLine == null) {
			for (int i = 0; i < waitingLineSize; i++) {
				currentState += "[  ]";
			}
		}
		else {
			for (int i = 0; i < waitingLineSize; i++) {
				if (waitingLine[i] == null) {
					currentState += "[  ]";
				}
				else if (waitingLine[i].getId() >= 10) {
					currentState += "[" + waitingLine[i].getId() + "]";
				}
				else if (waitingLine[i].getId() < 10) {
					currentState += "[0" + waitingLine[i].getId() + "]";
				}
			}
		}
		return currentState;
	}
}
