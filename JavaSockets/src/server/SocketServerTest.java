package server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SocketServerTest {
	
	SocketServer socket = new SocketServer(5);

	
	@Test
	void testGetSocketThreads() {
		for(SocketServerThread members:socket.getSocketThreads())
			assertTrue(members==null);
	}
	
	@Test
	void testGetMaxThreads() {
		assertTrue(socket.getMaxThreads()==5);
	}
	
	@Test
	void testIsAlive() {
		assertTrue(socket.isAlive()==true);
	}

}
