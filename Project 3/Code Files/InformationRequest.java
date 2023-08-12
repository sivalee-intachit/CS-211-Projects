
public class InformationRequest extends Request {
	private String[] questions;

	// Getters and Setters
	public String[] getQuestions() {
		return questions;
	}
	public void setQuestions(String[] questions) {
		this.questions = questions;
	}
	
	public InformationRequest(String description, int priority, int difficulty, String[] questions) {
		super.setDescription(description);
		super.setPriority(priority);
		super.setDifficulty(difficulty);
		super.setFactor(1);
		super.setStatus(Status.NEW);
		super.setStartTime(0);
		super.setEndTime(0);
		super.setCompletionLevel(0);
		setQuestions(questions);
	}
	
	public int calculateProcessingTime() {
		return super.getDifficulty() * super.getFactor() * questions.length;
	}
}
