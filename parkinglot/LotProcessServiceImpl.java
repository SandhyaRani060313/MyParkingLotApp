package parkinglot;

import java.util.Arrays;
import java.util.Scanner;

//import MyParkingLotImpl;
//import VehicleInformation;

public class LotProcessServiceImpl implements LotProcessService{

	private static int size =0;
	public String send; //this is used to pass every string to test cases
	private static VehicleInformation[] store = new VehicleInformation[size];
	MyParkingLotImpl obj = new MyParkingLotImpl();

	@Override
	public String createTotalSlots(String seperate) {//used to create lots in parking area
		int addSize = Integer.parseInt(seperate);
		if(addSize > 0) {// it works if entered total lots greater than 0
			if(store.length == 0) {//creates parking lot for first time
				//addSize(addSize);
				size = addSize;
				store = new VehicleInformation[size];
				for(int i=0; i<store.length; i++) {
					store[i] = new VehicleInformation("empty","empty");
				}
				send ="parking slot numbers created";
				System.out.println("Created a parking lot with "+size+" slots");
			}else {
				send="Parking slot already created, do you want to extend the parking lot Y/N:";
				System.out.println(send);
				Scanner in = new Scanner(System.in);
				String input = in.nextLine().trim().toLowerCase();
				if("y".equals(input)) {//for extending parking lot
					int temp = size;
					VehicleInformation[] storeOld = new VehicleInformation[size];
					storeOld= Arrays.copyOf(store, size);
					size = size+addSize;
					store = new VehicleInformation[size];
					store= Arrays.copyOf(storeOld, size);
					for(int i=temp; i<store.length; i++) {
						store[i] = new VehicleInformation("empty","empty");
					}
					send ="parking lot extended";
					System.out.println("Created a parking lot with "+size+" slots");
				}else if("n".equals(input)){
					send ="parking lot is not extended";
					System.out.println(size);
					readInput();
				}else {
					send="invalid input, command failed try again";
					System.out.println(send);
					readInput();
				}
			}
		}else {
			send="please enter slot number greater than zero";
			System.out.println(send);
		}
		readInput();
		return send;
	}

	@Override
	public String createEntry(String vehicleNo, String color) {// used to enter information at entry stage and allocates lot nearer to parking area
		int flag =0;
		if(store.length != 0) {//checks, total lots are created or not then allocates lot
			for(int i =0; i<size; i++) {
				if((store[i].getColor() == "empty")) {// checks for nearer empty lot
					store[i] = new VehicleInformation(vehicleNo,color);
					send = "Allocated";
					System.out.println("Allocated slot number: "+(i+1));
					flag = 1;
					break;
				}else {flag = 0;}
			}
			if(flag == 0) {	//if all lots are full
				send = "Sorry, parking lot is full";
				System.out.println(send);
			}
		}else {
			send ="lot size is not created yet";
			System.out.println(send);}
		readInput();
		return send;
	}

	@Override
	public String exitEntry(String seperate) {//at exit, particular lot is empty
		int exitSlotNumber = (Integer.parseInt(seperate))-1;
		if(store.length != 0) {//checks, total lots are created or not then leave command works

			if((exitSlotNumber >= 0) && (exitSlotNumber < size)) {// checks, if the given value is in between total lots
				if(store[exitSlotNumber].getColor()!="empty") {// checks, if the given value is full or not.
					store[exitSlotNumber] = new VehicleInformation("empty","empty");
					send = "slot number is free now";
					System.out.println("Slot number "+exitSlotNumber+" is free");
				}else {
					send="Already this slot is empty, check the slot number";
					System.out.println(send);
				}
			}else {
				send="please enter correct slot number";
				System.out.println("please enter exit number greater than zero and less than "+(size+1));
			}
		}else {
			send ="lot size is not created yet";
			System.out.println(send);
		}
		readInput();
		return send;
	}

	@Override
	public String viewFilledSlotDetails() {// to view the total filled lots information
		if(store.length != 0) {//checks, total lots are created or not then status command works
			System.out.println("Slot No.\tRegistration No.\tColour");
			for(int i =0; i<size; i++) {
				if(store[i].getColor() == "empty") {
					continue;
				}
				System.out.println((i+1)+"\t\t"+store[i].getVehicleNo()+"\t\t"+store[i].getColor());
			}
		}else {System.out.println("lot size is not created yet");}
		readInput();
		return send;
	}

	@Override
	public String getTotalSlotInformation(String command, String valueToGet) {//to view the information based on the command
		int count = 0;
		if(store.length != 0) {//checks, total lots are created or not then status command works
			switch(command){
			case "registration_numbers_for_cars_with_colour" :
				for(int i =0; i<size; i++) {
					if(store[i].getColor().equalsIgnoreCase(valueToGet)) {
						send = "success";
						System.out.print(store[i].getVehicleNo()+" ");
						break;
					}else {
						count ++;}
				}
				if(count>=size) {
					send="not found";
					System.out.println("not found");
				}
				System.out.println("");
				readInput();
				break;
			case "slot_numbers_for_cars_with_colour":
				for(int i =0; i<size; i++) {

					if(store[i].getColor().equalsIgnoreCase(valueToGet)) {
						send = "success";
						System.out.print((i+1)+" ");
						break;
					}else {
						count ++;}
				}
				if(count>=size) {
					send="not found";
					System.out.println("not found");
				}
				System.out.println("");
				readInput();
				break;
			case "slot_number_for_registration_number":
				for(int i =0; i<size; i++) {
					if(store[i].getVehicleNo().equalsIgnoreCase(valueToGet)) {
						send = "success";
						System.out.print((i+1)+" ");
						break;
					}else {
						count ++;}
				}
				if(count>=size) {
					send="not found";
					System.out.println("not found");
				}
				System.out.println("");
				readInput();
				break;
			}
		}else {
			send="lot size is not created yet";
			System.out.println(send);
		}
		readInput();
		return send;
	}

	public void readInput() {//used to read command every time if it is in not file
		if(MyParkingLotImpl.temp == 1) {
			//System.out.println("in");
			obj.findInputMethod();
		}
	}

	public int size(){
		return size;
	}

	private void addSize(int add) {
		size=size+add;
		store = new VehicleInformation[size];
		for(int i=0; i<store.length; i++) {
			store[i] = new VehicleInformation("empty","empty");
		}
	}
}
