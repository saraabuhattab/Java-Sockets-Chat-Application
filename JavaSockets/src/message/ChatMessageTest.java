package message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.BeforeClass;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.Serializable;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ChatMessageTest {

	ChatMessage c=new ChatMessage("Tishko", "0", ChatMessageType.ROOM);
    ChatMessage c1=new ChatMessage("Tishko", "0", ChatMessageType.ROOM, "wagwan");
    //ChatMessage c2=new ChatMessage("Tishko", "0", ChatMessageType.ROOM, readFile("Hi.txt"));
    //ChatMessage c3=new ChatMessage("Tishko", "0", ChatMessageType.ROOM, "Hi.txt", readFile("Hi.txt"));
    
    
    @Test
    public void testGetSenderName(){
        assertTrue(c.getSenderName().equals("Tishko"));
        assertTrue(c1.getSenderName().equals("Tishko"));
        //assertTrue(c2.getSenderName().equals("Tishko"));
        //assertTrue(c3.getSenderName().equals("Tishko"));
    }
    
    @Test
    public void testGetRoomName(){
        assertTrue(c.getRoomName().equals("0"));
        assertTrue(c1.getRoomName().equals("0"));
        //assertTrue(c2.getRoomName().equals("0"));
        //assertTrue(c3.getRoomName().equals("0"));

    }
    
    @Test
    public void testGetType(){
        assertTrue(c.getType()==ChatMessageType.ROOM);
        assertTrue(c1.getType()==ChatMessageType.ROOM);
        //assertTrue(c2.getType()==ChatMessageType.ROOM);
        //assertTrue(c3.getType()==ChatMessageType.ROOM);

    }
    @Test
    public void testGetMessage(){
        assertTrue(c1.getMessage().equals("wagwan"));
        //assertTrue(c3.getMessage().equals("Hi.txt"));

    }
    /*@Test
    public void testGetFile(){
        try{
            assertTrue(c2.getFile().equals(Files.readAllBytes(Paths.get("Hi.txt"))));
            assertTrue(c2.getFile().equals(Files.readAllBytes(Paths.get("Hi.txt"))));
        }catch(IOException e){
            
        }

    }*/
    
    private byte[] readFile(String filePath) throws IOException {
        try{
            return Files.readAllBytes(Paths.get(filePath));
        }catch(IOException e){}
        return null;
    }

}
