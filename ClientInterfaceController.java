import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import javafx.scene.control.Button;

public class ClientInterfaceController {

	@FXML
	private Text score;

	@FXML
	private VBox vBox;

	@FXML
	private Text question;

	@FXML
	private Text time;

	@FXML
	private RadioButton rb1;

	@FXML
	private ToggleGroup radioButtons;

	@FXML
	private RadioButton rb2;

	@FXML
	private RadioButton rb3;

	@FXML
	private RadioButton rb4;

	@FXML
	private Pane pane;

	private ArrayList<RadioButton> radioButtonsList;

	private Socket socket;
	private int scoreValue;
	private double timeValue;
	private ObjectInputStream in;
	private Question currentQuest;

	@FXML
	void initialize() {
		try {
			this.socket = new Socket(ClientInterface.ip, 3333);
			scoreValue = 0;
			timeValue = ClientInterface.timeToAnswer/1000;
			this.in = new ObjectInputStream(socket.getInputStream());
			radioButtonsList = new ArrayList<RadioButton>();
			radioButtonsList.add(rb1);
			radioButtonsList.add(rb2);
			radioButtonsList.add(rb3);
			radioButtonsList.add(rb4);
			nextQuestion();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nextQuestion() {

		//Used to set JavaFX shown variables from a thread
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					//Reset timer
					timeValue = ClientInterface.timeToAnswer/1000;
					//Get the next question from the stream
					currentQuest = (Question) in.readObject();

					//Set the texts
					score.setText(""+scoreValue);
					question.setText(currentQuest.getQuestion());	    		
					ArrayList<String> answers = currentQuest.getAnswers();
					for(int i=0; i < 4; i++)
						radioButtonsList.get(i).setText(answers.get(i));
					//Make a thread that does the waiting and tell us when to get the next question
					new HelpingThread().start();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					gameOver();
				}
			}
		});

	}

	private void gameOver() {
		pane.getChildren().clear();
		Text txt = new Text(" Your score is: " + scoreValue);
		txt.setFont(Font.font(Font.getDefault().getSize() + 50));
		Button btn = new Button("Play again?");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				pane.getChildren().clear();
				pane.getChildren().add(vBox);
				initialize();
			}

		});
		VBox vb = new VBox();
		vb.getChildren().add(txt);
		vb.getChildren().add(btn);
		vb.setAlignment(Pos.CENTER);
		pane.getChildren().add(vb);
	}

	private void updateScore() {
		//Index that does not exist. if the number 4 will remain we know the user haven't marked anything 
		int indexOfToggeledAnswer = 4; 
		for(int i=0; i<4; i++)
			if(radioButtonsList.get(i).isSelected())
				indexOfToggeledAnswer=i;
		if(indexOfToggeledAnswer == currentQuest.getIndexOfRightAnswer())
			scoreValue +=10;
		else
			scoreValue -=5;
	}

	private void sleepingTime() {
		long timeWeSlept = 0;
		while(timeWeSlept < ClientInterface.timeToAnswer) {
			long timeToSleep = 1000;
			if(timeWeSlept+timeToSleep >= ClientInterface.timeToAnswer)
				timeToSleep = ClientInterface.timeToAnswer - timeWeSlept;
			Platform.runLater(new Runnable() {
				public void run() {
					time.setText(""+timeValue);
				}
			});
			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeValue -=timeToSleep/1000;
			timeWeSlept +=timeToSleep;
		}
	}

	private class HelpingThread extends Thread{

		@Override
		public void run() {
			sleepingTime();
			updateScore();
			nextQuestion();
		}

	}

}
