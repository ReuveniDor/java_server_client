import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

public class TriviaServerThread extends Thread{

	private Socket socket;
	private ObjectOutputStream out;
	private ArrayList<Question> questions;
	private final int NUMOFANSWERS = 4;

	public TriviaServerThread(Socket socket) {
		super();
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.questions = new ArrayList<Question>();
		run();
		
	}
	
	@Override
	public void run() {
		makeQuestions();
		for (Question question : questions) {
			try {
				out.writeObject(question);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Make a list of questions and shuffle them.
	private void makeQuestions() {
		try {
			File source = new File("TriviaQuestions.txt");
			Scanner input = new Scanner(source);
			
			while(input.hasNext()) {
				ArrayList<String> answers = new ArrayList<String>();
				String question = input.nextLine();
				for(int i = 0; i<NUMOFANSWERS; i++) {
					if(input.hasNext()) {
						answers.add(input.nextLine());
					}
				}
				Question questionObjectToAdd = new Question(question, answers, 0);
				questions.add(questionObjectToAdd);
			}
			
			input.close();
			
			Collections.shuffle(questions);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
