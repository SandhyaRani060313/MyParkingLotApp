package parkinglot;

//import LotProcessService;
//import LotProcessServiceImpl;
//import ConstantsToInput;

public class ParkingLotProcess{
		public void processInput(String line) {
		//splitting entire command using space
		String[] splitLine = line.split(" ");
		String command = splitLine[0].trim().toLowerCase();
		MyParkingLotImpl obj = new MyParkingLotImpl();
		try {
			ConstantsToInput ct = ConstantsToInput.valueOf(command);
			//creating object to interface using implementation class
			LotProcessService slotProcess = new LotProcessServiceImpl();
			//processing commands based on input
			switch(ct) {
			case create_parking_lot:
				slotProcess.createTotalSlots(splitLine[1]);
				break;
			case park:
				slotProcess.createEntry(splitLine[1],splitLine[2]);
				break;
			case leave:
				slotProcess.exitEntry(splitLine[1]);
				break;
			case status:
				slotProcess.viewFilledSlotDetails();
				break;
			case registration_numbers_for_cars_with_colour:
				slotProcess.getTotalSlotInformation(command,splitLine[1]);
				break;
			case slot_numbers_for_cars_with_colour:
				slotProcess.getTotalSlotInformation(command,splitLine[1]);
				break;
			case slot_number_for_registration_number:
				slotProcess.getTotalSlotInformation(command,splitLine[1]);
				break;
			case exit:
				break;
			}
		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid command");
			obj.findInputMethod();
		}
	}
}

