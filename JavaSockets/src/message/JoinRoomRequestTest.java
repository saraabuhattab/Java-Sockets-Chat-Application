package message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class JoinRoomRequestTest {

	JoinRoomRequest j=new JoinRoomRequest("Tishko", "papa", false);
    JoinRoomRequest j2=new JoinRoomRequest("Tishko", "papa");
    @BeforeClass
    public static void setUp(){
        

    }
    
    @Test
    public void testGetName(){
        assertTrue(j.getName().equals("Tishko"));
        assertTrue(j2.getName().equals("Tishko"));

    }
    
    @Test
    public void testGetRoomName(){
        assertTrue(j.getRoomName().equals("papa"));
        assertTrue(j2.getRoomName().equals("papa"));

    }
    
    @Test
    public void testIsGroup(){
        assertTrue(j.isGroup()==false);
        assertTrue(j2.isGroup()==false);

    }



}
