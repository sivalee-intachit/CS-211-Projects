
public class VIPQueue extends Queue {
	private boolean acceptingAnyClients;

	public boolean getAcceptingAnyClients() {
		return acceptingAnyClients;
	}
	public void setAcceptingAnyClients(boolean acceptingAnyClients) {
		this.acceptingAnyClients = acceptingAnyClients;
	}
	
	public VIPQueue (String serverName, int queueSize, boolean acceptAnyClients) {
		super(serverName, queueSize);
		setAcceptingAnyClients(acceptAnyClients);
	}
	public VIPQueue (String serverName, int queueSize) {
		super(serverName, queueSize);
		setAcceptingAnyClients(false);
	}
}
