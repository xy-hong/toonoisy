package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

import toonoisy.Online;
import toonoisy.Room;



public class RoomTest {
	
	private static Room room;

	@BeforeClass
	public static void testRoom() {
		room = new Room("public");
		assertNotNull(room);
	}

	@Test
	public void testGetRoomName() {
		String roomName = room.getRoomName();
		assertSame("public", roomName);
	}

	@Test
	public void testGetMembers() {
		List list = room.getMembersNames();
		for (Object object : list) {
			System.out.println(object);
		}
	}

	/*@Test
	public void testPutStringOnline() {
		Online o = new Online("001@qq.com");
		room.put(o.getId(), o);
		Online o1 = new Online("002@qq.com");
		room.put(o1.getId(), o1);
	}*/

	@AfterClass
	public static void testRemoveObject() {
		room.remove("001@qq.com");
		room.remove("002@qq.com");
	}

}
