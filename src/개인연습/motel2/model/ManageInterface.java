package 개인연습.motel2.model;

import java.util.List;

public interface ManageInterface {
	
	public void checkIn(int roomNumber);
	public void checkOut(int roomNumber);
	public List<Room> getRooms();
	
}
