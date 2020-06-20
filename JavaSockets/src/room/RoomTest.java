package room;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import server.SocketServerThread;

import org.junit.jupiter.api.Test;

class RoomTest {
	
	
	Room room1 = new Room("tishko");
	Room room2 = new Room("tishko",3);
	Room room3 = new Room("tishko", new ArrayList<SocketServerThread>()); 
	SocketServerThread newMember = new SocketServerThread();
	@Test
	void testGetName() {
		assertTrue(room1.getName().equals("tishko"));
		assertTrue(room2.getName().equals("tishko"));
		assertTrue(room3.getName().equals("tishko"));
	}
	
	@Test
	void testGetMembers() { 
		for(SocketServerThread members:room1.getMembers()) {
			assertTrue(members==null);
		}
		
		for(SocketServerThread members:room2.getMembers()) {
			assertTrue(members==null);
		}
		
		
		
		for(SocketServerThread members:room3.getMembers()) {
			assertTrue(members==null);
		}
		
	}

	@Test
	void testGetMaxMembers() {
		assertTrue(room1.getMaxMembers()==(2));
		assertTrue(room2.getMaxMembers()==(3));
		assertTrue(room3.getMaxMembers()==(0));
	}

	
	@Test
	void testAddMember() {
		int x= room1.getMembers().size();
		room1.addMember(newMember);
		assertTrue(room1.getMembers().size()==x+1);
		
		int y= room2.getMembers().size();
		room2.addMember(newMember);
		assertTrue(room2.getMembers().size()==y+1);
		
		room3.addMember(newMember);
		assertTrue(room3.getMembers().size()==0);
	}

	@Test
	void testRemoveMember() {
		int x= room1.getMembers().size();
		room1.addMember(newMember);
		room1.removeMember(newMember);
		assertTrue(room1.getMembers().size()==x);
		
		int y= room2.getMembers().size();
		room2.addMember(newMember);
		room2.removeMember(newMember);
		assertTrue(room2.getMembers().size()==y);
		
		room3.addMember(newMember);
		room3.removeMember(newMember);
		assertTrue(room3.getMembers().size()==0);
	}


}
