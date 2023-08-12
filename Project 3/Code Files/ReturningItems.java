
public class ReturningItems extends Request {
	private String[] itemsToReturn;

	// Setters and Getters
	public String[] getItemsToReturn() {
		return itemsToReturn;
	}
	public void setItemsToReturn(String[] itemsToReturn) {
		this.itemsToReturn = itemsToReturn;
	}
	
	public ReturningItems(String description, int priority, int difficulty, String[] itemsToReturn) {
		super.setDescription(description);
		super.setPriority(priority);
		super.setDifficulty(difficulty);
		super.setFactor(3);
		super.setStatus(Status.NEW);
		super.setStartTime(0);
		super.setEndTime(0);
		super.setCompletionLevel(0);
		setItemsToReturn(itemsToReturn);
	}
	
	public int calculateProcessingTime() {
		return super.getDifficulty() * super.getFactor() * itemsToReturn.length;
	}
}
