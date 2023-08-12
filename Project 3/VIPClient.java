
public class VIPClient extends Client implements Prioritizable {
	private int memberSince;
	private int priority;
	
	// Getters and Setters
	public int getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(int memberSince) {
		this.memberSince = memberSince;
	}
	public void setPriority(int a) {
		priority = a;
	}
	public int getPriority() {
		return priority;
	}
	
	public VIPClient(String firstName, String lastName, int birthYear, String gender, int arrivalTime, int patience, Request[] requests, int memberSince, int priority) {
		super(firstName, lastName, birthYear, gender, arrivalTime, patience, requests);
		setPriority(priority);
	}
	public String toString() {
		return super.toString() + "** VIP Since       : " + memberSince + "\n" +
			   "** priority        : " + priority;
	}
}
