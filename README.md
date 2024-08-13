# Java Trivia Game Client-Server Application
## Overview
This project is a Java-based client-server trivia game where multiple users can connect to a server to play a trivia game. Each game session consists of 20 questions, with each question having four possible answers, only one of which is correct. The server handles multiple clients simultaneously and ensures that each client receives a unique set of questions in each game session.

## Features
### Server
Listens for client connections on port 3333.
Handles multiple clients simultaneously.
Reads questions and answers from a text file.
Sends random questions to each client, ensuring no repetition within a single game session.
Evaluates the client's answers and updates their score accordingly.
### Client
Establishes a TCP connection with the server for each game session.
Provides a graphical user interface (GUI) to display questions and possible answers.
Allows the user to select one answer per question.
Tracks time allocated for each question using a timer.
Displays the user's cumulative score at the end of the game.
Allows the user to start a new game after the current one ends.
## File Structure
Server.java: Implements the server-side logic, including handling multiple client connections and managing the trivia questions.
Client.java: Implements the client-side logic, including the GUI for interacting with the user.
Question.java: A class representing a trivia question, containing the question text, four possible answers, and the correct answer index.
questions.txt: A text file containing the trivia questions, where each question is represented by five lines:
The question text.
The correct answer.
The first incorrect answer.
The second incorrect answer.
The third incorrect answer.
## How to Run
Server
Compile Server.java.
Run the server using:
bash
Copy code
java Server
Client
Compile Client.java.
Run the client using:
bash
Copy code
java Client <server-hostname> <time-limit>
<server-hostname>: The hostname of the server where the trivia game is hosted.
<time-limit>: The time in seconds allocated to answer each question.
## Example Usage
Start the server:
bash
Copy code
java Server
Start the client, connecting to the server on localhost with a 10-second time limit per question:
bash
Copy code
java Client localhost 10
## Requirements
Java Development Kit (JDK) 8 or higher.

A text file (questions.txt) formatted as described above.
## Future Improvements
Implement more sophisticated question selection algorithms.
Add support for different question categories.
Enhance the GUI for a better user experience.
## License
This project is open-source and available under the MIT License.
