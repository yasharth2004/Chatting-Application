# Chatting-Application

Chatting Application

This Java-based Chatting Application consists of a client-server model that allows real-time communication between users. It features two main components: a client interface and a server interface.

Client Application:

	•	User Interface: Uses JFrame, JPanel, and other Swing components to create a chat window with a modern design. Features include a profile image, status indicator, and action buttons.
	•	Message Handling: Sends and receives messages in real-time. Messages are displayed in the chat window with timestamps.
	•	Connection: Connects to the server via a socket on port 6001, utilizing DataOutputStream and DataInputStream for message exchange.

Server Application:

	•	User Interface: Similar to the client, the server interface is built using Swing components, featuring a chat window with a profile image and status indicator.
	•	Message Handling: Listens for incoming connections and messages from clients. Displays incoming messages with timestamps and manages communication with multiple clients.
	•	Connection: Listens on port 6001, accepting connections from clients and using DataInputStream and DataOutputStream for communication.
