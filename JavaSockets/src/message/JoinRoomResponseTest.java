package message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class JoinRoomResponseTest {

	JoinRoomResponse j=new JoinRoomResponse("connected", false);
    JoinRoomResponse j2=new JoinRoomResponse("connected");
    @BeforeClass
    public void setUp(){
        

    }
    
    @Test
    public void testGetResponse(){
        assertTrue(j.getResponse().equals("connected"));
        assertTrue(j2.getResponse().equals("connected"));

    }
    
    @Test
    public void testGetError(){
        assertTrue(j.isError()==false);
        assertTrue(j2.isError()==false);

    }

}
