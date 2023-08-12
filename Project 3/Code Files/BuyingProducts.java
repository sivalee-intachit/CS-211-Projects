
public class BuyingProducts extends Request {
	private String[] itemsToBuy;

	// Getters and Setters
	public String[] getItemsToBuy() {
		return itemsToBuy;
	}
	public void setItemsToBuy(String[] itemsToBuy) {
		this.itemsToBuy = itemsToBuy;
	}
	
	public BuyingProducts (String description, int priority, int difficulty, String[] itemsToBuy) {
		super.setDescription(description);
		super.setPriority(priority);
		super.setDifficulty(difficulty);
		super.setFactor(2);
		super.setStatus(Status.NEW);
		super.setStartTime(0);
		super.setEndTime(0);
		super.setCompletionLevel(0);
		setItemsToBuy(itemsToBuy);
	}
	
	public int calculateProcessingTime() {
		return super.getDifficulty() * super.getFactor() * itemsToBuy.length;
	}
}
