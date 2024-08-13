# Java Trivia Game Client-Server Application
## Overview
This project is a Java-based client-server trivia game where multiple users can connect to a server to play a trivia game.

Each game session consists of 20 questions, with each question having four possible answers, only one of which is correct.

The server handles multiple clients simultaneously and ensures that each client receives a unique set of questions in each game session.

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

## Implementation
We are using a meneger class and threads. we have a Question class and a client interface.

We also have a text file of the trivia questions.
## How to Run
Run run1 

Run run2

Press the correct answer and wait for the time to end.
## Example Usage
![PrintScreens](https://github.com/user-attachments/assets/ae1ddcce-962d-4f1c-9cba-04d11964387b)

## Requirements
Java Development Kit (JDK) 8 or higher.

A text file (questions.txt) formatted as described above.
## Future Improvements
Implement more sophisticated question selection algorithms.

Add support for different question categories.

Enhance the GUI for a better user experience.

## License
This project is open-source and available under the MIT License.
