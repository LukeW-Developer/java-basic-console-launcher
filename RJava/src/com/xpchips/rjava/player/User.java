package com.xpchips.rjava.player;

// Importing Modules
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

// User Class
public class User {
	
	// <--- Attributes --->
	// Player Attributes
	private String userName;
	private int userID;
	private String charApp;
	
	// Online Attributes
	private String serverIP;
	private int serverPort;
	
	// File Attributes
	File playerData = new File("playerData.csv");
	
	// <--- Getter Methods --->
	// Get UserName
	public String getUserName() {
		return userName;
	}
	
	// Get UserID
	public int getUserID() {
		return userID;
	}
	
	// Get CharApp
	public String getCharApp() {
		return charApp;
	}
	
	// Get Server IP
	public String getServerIP() {
		return serverIP;
	}
	
	// Get Server Port
	public int getServerPort() {
		return serverPort;
	}
	
	// <--- Procedures --->
	// Get File Status
	public boolean getFileStatus() {
		// Set flag to 'false' by default
		boolean temp = false;
		
		// Set flag to 'true' if exists
		if (playerData.exists()) {
			temp = true;
		}
		
		// Return value
		return temp;
	}
	
	// Creates Player Save Data
	public void CreatePlayerData(String paramUserName, int paramUserID, String paramCharApp, String paramServerIP, int paramServerPort) {
		try {
			// Combine all user variables into a single string
			String temp = paramUserName + "," + paramUserID + "," + paramCharApp + "," + paramServerIP + "," + paramServerPort;
			
			// Create the file
			playerData.createNewFile(); // Create File
			
			// Write user data to file
			FileWriter writeData = new FileWriter(playerData);
			writeData.write(temp);
			writeData.close();
			
			// Throw error if something broke
		} catch (Exception e) {
			System.out.println("Error Report. Please Report the Following to Developer!");
			System.err.println(e.getMessage());
		}
	}
	
	// Loads Player Save Data
	public void LoadPlayerData() {
		try {
			// Object to read file
			Scanner readData = new Scanner(playerData);
			
			// Grab data from file
			String line = readData.nextLine();
			readData.close();
			
			// Remove delimiter = store data in array
			String[] data = line.split(",");
			
			// Load data into attributes
			if (data.length == 5) {
				this.userName = data[0].trim();
				this.userID = Integer.parseInt(data[1].trim());
				this.charApp = data[2].trim();
				this.serverIP = data[3].trim();
				this.serverPort = Integer.parseInt(data[4].trim());
			}
			
			// Throw error if something broke
		} catch (Exception e) {
			System.out.println("Error Report. Please Report the Following to Developer!");
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}
}
