package com.xpchips.rjava.clients;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Client {
	// <---  Attributes --->
	// Client Attributes
	public int clientVersion;
	public String clientPath;
	public String clientExec;
	
	// File Attributes
	File cliNumDir = new File("clientNumber.txt");
	
	// <--- GETTERS and SETTERS Methods -->
	// Get client version <-- GET
	public int getClientVersion() {
		return clientVersion;
	}
	
	// Set client version <-- SET
	public void setClientVersion(int paramClientVersion) {
		this.clientVersion = paramClientVersion;
	}
	
	// Get client path <-- SET
	public String getClientPath() {
		return clientPath;
	}
	
	// Set client path <-- SET
	public void setClientPath(String paramClientPath) {
		this.clientPath = paramClientPath;
	}
		
	// Get client executable <-- GET
	public String getClientExec() {
		return clientExec;
	}
	
	// Set client executable <-- SET
	public void setClientExec(String paramClientExec) {
		this.clientExec = paramClientExec;
	}
	
	// <--- Procedures --->
	// Get Client Status
			public boolean getFileStatus() {
				// Set flag to 'false' by default
				boolean temp = false;
				
				// Set flag to 'true' if exists
				if (cliNumDir.exists()) {
					temp = true;
				}
				
				// Return value
				return temp;
			}
	
	// Save client number user selected
	public void saveClientNumber() {
		try {
			// Create file
			cliNumDir.createNewFile();
			
			// Write to it
			FileWriter cliNumWrite = new FileWriter(cliNumDir);
			cliNumWrite.write(String.valueOf(this.clientVersion));
			cliNumWrite.close();
			
			// Throw exception if something broke
		} catch (Exception e) {
			System.out.println("Error Report. Please Report the Following to Developer!");
			System.err.println(e.getMessage());
		}
	}
	
	// Load client number user selected
	public void loadClientNumber() {
		int line = 0;
		
		try {
			// Read from line
			Scanner cliNumRead = new Scanner(cliNumDir);
			
			// Grab data and convert to integer
			line = Integer.parseInt(cliNumRead.nextLine());
			
			// Close connection
			cliNumRead.close();
			
			// Throw exception if something broke
		} catch (Exception e) {
			System.out.println("Error Report. Please Report the Following to Developer!");
			System.err.println(e.getMessage());
			System.out.println("");
			
			line = 2008;
		}
		
		// Load value into attribute
		this.clientVersion = line;
	}
	
	public Client loadSpecificClient() {
		// Initialising Client Class
		Client cClient;
		
		// Initialising Constructor from appropriate class
		switch (this.clientVersion) {
		case 2008: return new Eight();
		case 2009: return new Nine();
		case 2010: return new Ten();
		case 2011: return new Eleven();
		default:
			System.out.println("Error Report. Failed to load Client Values!");
			System.out.println("Please Report to Developer with Screenshots of the Program Window!");
			return null;
		}
	}
}