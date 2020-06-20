package server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import room.Room;

class SocketServerThreadTest {
	
	Socket socket = new Socket();
	ArrayList<SocketServerThread> socketThreads= new ArrayList<SocketServerThread>();
	ArrayList<Room> rooms= new ArrayList<Room>();
	SocketServerThread socketThread = new SocketServerThread(socket, socketThreads, rooms);
    
	
	
	

	@Test
	void testGetSocket() {
		assertTrue(socketThread.getSocket().equals(socket));
	}
	
	@Test
	void testGetServerInput() throws IOException {
		assertTrue(socketThread.getServerInput()==null);
	}
	
	@Test
	void testGetServerOutput() throws IOException {
		assertTrue(socketThread.getServerOutput()==null);
	}
	
	@Test
	void testGetSocketThreads() {
		assertTrue(socketThread.getSocketThreads().equals(socketThreads));
	}
	
	@Test
	void testGetRooms() {
		assertTrue(socketThread.getRooms().equals(rooms));
	}
	
	@Test
	void testIsAlive() {
		assertTrue(socketThread.isAlive()==true);
	}
	
	@Test
	void testkillThread() {
		socketThread.killThread();
		assertTrue(socketThread.isAlive()==false);
	}

}
