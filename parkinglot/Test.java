package parkinglot;


public class Test {
	
	public static void lotSize_test(LotProcessService lotProcess){
		assertEqual(0, lotProcess.size(), "default lot size is zero", false);
		assertEqual("lot is not created", lotProcess.createEntry("KA01HH1234","White"), "without creating lot size entry is not possible", false);
		assertEqual("lot size is not created yet", lotProcess.exitEntry("1"), "without creating lot size entry is not possible", false);
		assertEqual("lot size is not created yet", lotProcess.getTotalSlotInformation("registration_numbers_for_cars_with_colour","white"), "get vehicle numbers based on color ", false);
		assertEqual("lot size is not created yet", lotProcess.getTotalSlotInformation("slot_numbers_for_cars_with_colour","white"), "get slot numbers based on color ", false);
		assertEqual("lot size is not created yet", lotProcess.getTotalSlotInformation("slot_number_for_registration_number","KA01HH9999"), "get slot numbers based on color ", false);
	}
	
	public static void createLotSize_test(LotProcessService lotProcess){
		assertEqual("please enter slot number greater than zero", lotProcess.createTotalSlots("0"), "create_parking_lot 0 command is not possible", false);
		assertEqual("parking slot numbers created", lotProcess.createTotalSlots("3"), "created slots", false);
		assertEqual("please enter correct slot number", lotProcess.exitEntry("7"), "leave 7 command is not possible", false);
		
	}
	
	public static void allocateLot_test(LotProcessService lotProcess) {
		assertEqual("Allocated", lotProcess.createEntry("KA01HH1234","White"), "entry is possible", false);
		assertEqual("Allocated", lotProcess.createEntry("KA01HH9999","White"), "entry is possible", false);
	}
	
	public static void exitLot_test(LotProcessService lotProcess) {
		assertEqual("please enter correct slot number", lotProcess.exitEntry("0"), "leave 0 command is not possible", false);
		assertEqual("Already this slot is empty, check the slot number", lotProcess.exitEntry("3"), "leave 3 command, is empty so not possible", false);
		assertEqual("slot number is free now", lotProcess.exitEntry("1"), "exit is possible", false);
		assertEqual("please enter correct slot number", lotProcess.exitEntry("7"), "leave 7 command is not possible", false);
		assertEqual("Allocated", lotProcess.createEntry("KA01HH7777","Red"), "slot 1 is allocated because it is nearer to parking place", false);
		assertEqual("Allocated", lotProcess.createEntry("KA01HH2701","Blue"), "slot 3 is allocated because it is empty", false);
		assertEqual("Sorry, parking lot is full", lotProcess.createEntry("KA01HH3141","Black"), "not possible, all slots are full", false);
		//this case is going to be failed if we enter 'n' letter
		System.out.println("This case is going to be failed if we enter 'n' letter because parking lot is not extended ");
		assertEqual("parking lot extended", lotProcess.createTotalSlots("5"), "If we create again, it will possible if we enter y letter", false);
	}
	
	public static void getInformationBasedOnCommand_test(LotProcessService lotProcess) {
		assertEqual("success", lotProcess.getTotalSlotInformation("registration_numbers_for_cars_with_colour","white"), "get vehicle numbers based on color ", false);
		assertEqual("not found", lotProcess.getTotalSlotInformation("registration_numbers_for_cars_with_colour","pink"), "get vehicle numbers based on color ", false);
		assertEqual("success", lotProcess.getTotalSlotInformation("slot_numbers_for_cars_with_colour","white"), "get slot numbers based on color ", false);
		assertEqual("not found", lotProcess.getTotalSlotInformation("slot_numbers_for_cars_with_colour","pink"), "get slot numbers based on color ", false);
		assertEqual("success", lotProcess.getTotalSlotInformation("slot_number_for_registration_number","KA01HH9999"), "get slot numbers based on color ", false);
		assertEqual("not found", lotProcess.getTotalSlotInformation("slot_number_for_registration_number","KA01HH2000"), "get slot numbers based on color ", false);
		
	}
	
	
	public static void assertEqual(String expected, String actual, String message, boolean silentPass){
		if (expected == actual && ! silentPass)
			System.out.printf("--- PASSED: %s\n\n", message);
		if (expected != actual)
			System.out.printf("*** FAILED: (expected: %s, actual: %s) %s\n\n", expected, actual, message);
	}
	
	public static void assertEqualSpecialCase(String expected, String actual, String message, boolean silentPass){
		if (expected == actual && ! silentPass)
			System.out.printf("*** FAILED: (expected: %s, actual: %s) %s\n\n", expected, actual, message);
			if (expected != actual)
			System.out.printf("--- PASSED: %s\n\n", message);
	}
	
	public static void assertEqual(int expected, int actual, String message, boolean silentPass){
		if (expected == actual && ! silentPass)
			System.out.printf("--- PASSED: %s\n\n", message);
		if (expected != actual)
			System.out.printf("*** FAILED: (expected: %d, actual: %d) %s\n\n", expected, actual, message);
	}
	public static void main(String[] args) {

		lotSize_test(new LotProcessServiceImpl());
		createLotSize_test(new LotProcessServiceImpl());
		allocateLot_test(new LotProcessServiceImpl());
		exitLot_test(new LotProcessServiceImpl());
		getInformationBasedOnCommand_test(new LotProcessServiceImpl());

	}

}
