import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientInterface extends Application{ 

	public static long timeToAnswer = 1000;
	public static String ip = "127.0.0.1";

	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent)
				FXMLLoader.load(getClass().getResource("ClientInterface.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("Client Interface"); 
		stage.setScene(scene); 
		stage.show(); 
	} 

	//The first two elements in args are expected to be 'String'-IP address and 'long' type numbers
	//If they are not or  does not exist - we provide default numbers instead
	public static void main(String[] args) { 
		try {
			if(checkIPadd(args[0]))
				ip=args[0];
			else {
				JOptionPane.showMessageDialog(null, "This ip is not legal - ill set it to a default one!");
			}
			timeToAnswer = Long.parseLong(args[1]);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "This time is not legal - ill set it to a default one!");
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Please provide ip and numbers in args next time... \n"
					+ "I am giving you the default selections.");
		}
		launch(args);
		System.out.println();
	} 

	public static boolean checkIPadd(String ipAdd) {
		String[] parts = ipAdd.split("\\.");
		boolean answer = true;
		//4 parts exactly
		if (parts.length != 4) {
			answer = false;
		}
		//Every part is numbers with values 0-255
		for (String part : parts) {
			try {
				int number = Integer.parseInt(part);
				if (number < 0 || number > 255) {
					answer = false;
				}
			}catch(NumberFormatException e) {
				answer = false;
			}
		}
		return answer;
	}

}