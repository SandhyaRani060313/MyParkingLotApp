package parkinglot;

public interface LotProcessService {
	public String createTotalSlots(String seperate);
	public String createEntry(String seperate, String seperate2);
	public String exitEntry(String seperate);
	public String viewFilledSlotDetails();
	public String getTotalSlotInformation(String seperate, String seperate2);
	public int size();
}
