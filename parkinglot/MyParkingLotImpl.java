package parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MyParkingLotImpl {
	public static int temp = 0;
	//root method to decide whether the input is through file or command
	public void findInputMethod(){
		System.out.println("");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String subString =".txt";
		Boolean findString;
		
		findString = input.trim().contains(subString);//searches for .txt sequence from input
		ParkingLotProcess smc = new ParkingLotProcess();
		if(findString == true) {
			//if .txt found, this code will execute 
			File inputFile = new File(input);
			try {
				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				String readFile;
				while ((readFile = br.readLine()) != null)  {
					System.out.println("");
					smc.processInput(readFile);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("file not found, please refer document for file path format");//if file path is not correct
				findInputMethod();
			}
		}else {
			//if input is in command format then below code will execute
			temp = 1;// this is used to differentiate file based or command based
			smc.processInput(input);
		}
	}
}
