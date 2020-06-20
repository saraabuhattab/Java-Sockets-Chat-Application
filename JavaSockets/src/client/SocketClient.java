package client;

import message.ChatMessage;
import message.ChatMessageType;
import message.JoinRoomRequest;
import message.JoinRoomResponse;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketClient {
    private String name;
    private Socket socket;
    private ObjectInputStream clientInput;
    private ObjectOutputStream clientOutput;
    private String roomNum;
    private boolean newMessage = false;
    private String mess; 
    private ArrayList<String> chatbox=new ArrayList<String>();

    

    public SocketClient(String name) {
        this.name = name;
    }

    /**public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String name;
            do {
                System.out.print("Please enter your name: ");
                name = sc.nextLine();
            } while (name.trim().equals(""));
            SocketClient client = new SocketClient(name);
            client.connectToServer("localhost", 6000);
        } catch (Exception e) {
            System.out.println("Could not connect to server!");
        }
    }
    **/
    
  
    public void setRoomNum(String roomNum){
    	this.roomNum= roomNum;
    }
    public String getRoomNum() {
    	return roomNum;
    }
   
    public void send(String textMessage) {
    	try {
    		String input;
            //System.out.print("You: ");
            input = textMessage;
            if (input.trim().equalsIgnoreCase("leave")) {
            } else if (input.trim().equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (input.startsWith("file ")) {
                try {
                    sendToServer(new ChatMessage(name, roomNum, ChatMessageType.ROOM, input.substring(5), readFile(input.substring(5))));
                } catch (IOException e) {
                    System.out.println("File not found in the location specified");
                }
            } else {
                sendToServer(new ChatMessage(name, roomNum, ChatMessageType.ROOM, input));
                chatbox.add("You: " + input);
                newMessage=true;
            }
        
    	}
    	catch(IOException e){
    		
    	}
    	
    }
    
    public ArrayList<String> getChatBox() {
    	return chatbox;
    }
    private void initiate() {
    	String room;
        try {
           
            JoinRoomResponse response;
            while (true) {
   
                room = roomNum;
                if (room.trim().equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
              
                sendToServer(new JoinRoomRequest(name, room));
                response = (JoinRoomResponse) getServerReply();
               // System.out.println(response.getResponse());
                if (response.isError()) {
                    continue;
                }
                break;  
            }
            new Thread(() -> listenToServer()).start();
        } catch (Exception e) {
            // Do nothing to terminate the program
        }
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getClientInput() {
        return clientInput;
    }

    public ObjectOutputStream getClientOutput() {
        return clientOutput;
    }

  
    
    private void listenToServer() {
        while (true) {
            try {
                ChatMessage receivedMessage = (ChatMessage) getServerReply();
                if (receivedMessage.getFile() != null) {
                    writeFile(receivedMessage.getMessage(), receivedMessage.getFile());
                    mess="Received file: " + Paths.get(receivedMessage.getMessage()).getFileName().toString();
                	chatbox.add(mess);
                	newMessage=true;
                } else {
                    //System.out.println(receivedMessage.getSenderName() + ": " + receivedMessage.getMessage());
                    mess=receivedMessage.getSenderName() + ": " + receivedMessage.getMessage();
                	chatbox.add(mess);
                	newMessage=true;
                }
            } catch (EOFException e) {
                System.out.println("Connection was lost with server!");
                break;
            } catch (Exception e) {
                System.out.println("An error occurred while receiving the message from the server!");
            }
        }
    }

    
    public void setNewMessage(boolean x) {
    	newMessage=x;
    }
    
    public boolean getNewMessage() {
    	return newMessage;
    }
    
    public String getMessage() {
    	return mess;
    }

    
    private void writeFile(String filePath, byte[] bytes) throws IOException {
        Files.write(Paths.get(filePath).getFileName(), bytes);
    }

    private byte[] readFile(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    public void sendToServer(Object message) throws IOException {
        clientOutput.writeObject(message);
        clientOutput.flush();
    }

    public Object getServerReply() throws IOException, ClassNotFoundException {
        return clientInput.readObject();
    }

    public void connectToServer(String serverAddress, int serverPort){
    	try {
        socket = new Socket(serverAddress, serverPort);
        clientInput = new ObjectInputStream(socket.getInputStream());
        clientOutput = new ObjectOutputStream(socket.getOutputStream());
        initiate();
    	}
    	catch(IOException e){
	    	}
	    }
    }
