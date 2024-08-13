import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Question implements Serializable{

	private String question;
	private ArrayList<String> answers;
	private int indexOfRightAnswer;
	
	//Makes a question. The question's answers will be randomized and the right index of answer will be saved
	public Question(String question, ArrayList<String> answers, int indexOfRightAnswer) {
		super();
		this.question = question;
		String rightAnswer = answers.get(indexOfRightAnswer);
		this.answers = new ArrayList<String>(answers);
		Collections.shuffle(this.answers);
		this.indexOfRightAnswer = this.answers.indexOf(rightAnswer);
	}
	
	@Override
	public String toString() {
		String newRow = "\n";
		String ans = ""+ question + newRow;
		for (String string : answers) {
			ans += string + newRow;
		}
		return ans;
	}

	public String getQuestion() {
		return question;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public int getIndexOfRightAnswer() {
		return indexOfRightAnswer;
	}
	
}
