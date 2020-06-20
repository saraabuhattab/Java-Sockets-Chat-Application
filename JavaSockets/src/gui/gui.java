package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import client.SocketClient;
import message.ChatMessage;
import message.JoinRoomRequest;
import message.JoinRoomResponse;
import server.SocketServer; 
import server.SocketServerThread;
import org.eclipse.swt.widgets.Text; 
import room.Room;
import org.eclipse.swt.widgets.List;
public class gui {

	protected Shell shell;
	private Text name;
	private SocketClient client;
	private Text txtRoomnumber;
	private Text textMessage;
	private ArrayList<String> chatbox2;
	private List chatbox;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			gui window = new gui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1015, 635);
		shell.setText("Java Sockets");
		StackLayout layout = new StackLayout();
		shell.setLayout(layout);
		
		Composite composite = new Composite(shell, SWT.NONE);
		Composite composite_1 = new Composite(shell, SWT.NONE);
		Composite composite_2 = new Composite(shell, SWT.NONE);
		

        //composite.setLayout(layout);
        layout.topControl = composite;
        composite.layout();
		
		Label connection = new Label(composite, SWT.NONE);
		connection.setLocation(355, 386);
		connection.setSize(293, 27);
		connection.setText("Was the connection successful?:  ");
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLocation(372, 237);
		btnNewButton.setSize(200, 84);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				client = new SocketClient(name.getText());
				connection.setText("Was the connection successful?:  Yes");
				
				
			}
		});
		btnNewButton.setText("Connect to Server");
		
		
		
		name = new Text(composite, SWT.BORDER);
		name.setLocation(380, 109);
		name.setSize(169, 46);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLocation(376, 161);
		lblNewLabel.setSize(222, 37);
		lblNewLabel.setText("Please enter your name");
		
		Button btnNext = new Button(composite, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = composite_1;
				shell.layout();
			}
		});
		btnNext.setBounds(433, 471, 93, 35);
		btnNext.setText("NEXT");
		
		
		
		txtRoomnumber = new Text(composite_1, SWT.BORDER);
		txtRoomnumber.setLocation(403, 130);
		txtRoomnumber.setSize(150, 56);
		
		Label lblSelectRoomNumber = new Label(composite_1, SWT.NONE);
		lblSelectRoomNumber.setLocation(393, 63);
		lblSelectRoomNumber.setSize(248, 45);
		lblSelectRoomNumber.setText("Select Room Number");
		Label RoomNumber = new Label(composite_1, SWT.NONE);
		RoomNumber.setLocation(393, 394);
		RoomNumber.setSize(248, 39);
		RoomNumber.setText("Your room number is:   ");
		
		Button btnConnectToRoom = new Button(composite_1, SWT.NONE);
		btnConnectToRoom.setLocation(360, 226);
		btnConnectToRoom.setSize(232, 106);
		btnConnectToRoom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				client.setRoomNum(txtRoomnumber.getText());
				client.connectToServer("localhost", 6000);	
				RoomNumber.setText("Your room number is:   " + client.getRoomNum());
				layout.topControl = composite_2;
			}
		});
		btnConnectToRoom.setText("Connect to Room");
		
		Button btnNext_1 = new Button(composite_1, SWT.NONE);
		btnNext_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = composite_2;
				shell.layout();
			}
		});
		btnNext_1.setBounds(432, 455, 93, 35);
		btnNext_1.setText("NEXT");
		
	
		chatbox = new List(composite_2, SWT.BORDER);
		chatbox.setTouchEnabled(true);
		chatbox.setBounds(567, 63, 376, 435);
		textMessage = new Text(composite_2, SWT.BORDER);
		textMessage.setLocation(128, 142);
		textMessage.setSize(238, 88);
		
		Label lblSendMessage = new Label(composite_2, SWT.NONE);
		lblSendMessage.setLocation(109, 250);
		lblSendMessage.setSize(362, 35);
		lblSendMessage.setText("Type message or file name with path");
		
		Button btnSend = new Button(composite_2, SWT.NONE);
		btnSend.setLocation(195, 342);
		btnSend.setSize(110, 59);
		btnSend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
	
						
						//chatbox2= client.getChatBox();
						
						//for(int i=0;i<chatbox2.size();i++){
							//chatbox.add(client.getChatBox().get(i));
				
						//}
						//client.setNewMessage(false);
						
				
				chatbox.add(client.getName()+ ":  " + textMessage.getText());
				client.send(textMessage.getText());
				textMessage.setText("");
				
			}
		});
		btnSend.setText("Send");
		
		Label lblChat = new Label(composite_2, SWT.NONE);
		lblChat.setBounds(726, 21, 52, 25);
		lblChat.setText("Chat");
		
		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(client.getNewMessage()==true) {
					chatbox.add(client.getMessage());
					client.setNewMessage(false);
				}
				}
			
		});
		btnNewButton_1.setBounds(676, 515, 153, 50);
		btnNewButton_1.setText("Recieve Messages");
		
		Label lblIfYouWantto = new Label(composite_2, SWT.NONE);
		lblIfYouWantto.setBounds(109, 291, 320, 25);
		lblIfYouWantto.setText("To send file, type in \"file path/filename\"");
		int interval = 10; // repeating every 1000 ms
	    new Timer(interval, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
					chatbox.add("hi");
		    }
		}).start();
		


	}
	
	public void update(String message) {
		chatbox.add(message);
	}
}
