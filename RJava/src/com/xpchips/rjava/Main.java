package com.xpchips.rjava;

// Importing Modules
import java.util.Scanner;

// Importing Classes
import com.xpchips.rjava.player.User; // User
import com.xpchips.rjava.clients.*; // Clients

// Main Class
public class Main {
	
	// Attributes
	private static boolean debug = false; // Debug Mode
	private static boolean running = true; // Program Status
	
	// Main Menu Code (performed after ran)
	public void MainMenu() {
		Scanner scanner = new Scanner(System.in);
		
		while (running) {
			try {
				System.out.println("<---> RJava Program <--->");
				System.out.println("Nothing more but a testing program to practice Java.");
				System.out.println("Select an Option below:");
				System.out.println("1. Settings");
				System.out.println("2. Select Client");
				System.out.println("3. Solo");
				System.out.println("4. Join");
				System.out.println("5. Host");
				System.out.println("6. Quit");
				
				int usrInt = scanner.nextInt();
				scanner.nextLine();
				System.out.println("");
				
				switch (usrInt) {
				case 1: // Settings
					Settings(scanner);
					break;
				case 2: // Client Selection
					ClientSel(scanner);
					break;
				case 3: // Play (Solo)
					Play(scanner, 1);
					break;
				case 4: // Play (Join)
					Play(scanner, 2);
					break;
				case 5: // Play (Host)
					Play(scanner, 3);
					break;
				case 6: // Exit
					running = false;
					break;
				default: // User entered invalid option
					System.out.println("Invalid option! Please try again!");
					System.out.println("");	
				}
				
				// DataType error
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("");
				System.out.println("Invalid Data! Please try again!");
				System.out.println("");
			}
		}
		scanner.close();
	}
	
	// Settings Code
	public void Settings(Scanner scanner) {
		// Initiating of User Class
		User cUser = new User();
		
		// Flag whether user data already exists
		boolean flag = cUser.getFileStatus();
		
		System.out.println("<---> Settings Page <--->");
		System.out.println("");
		
		// If exist, display current user data and possible changes that could be made
		if (flag) {
			cUser.LoadPlayerData(); // Load data into attributes
			
			System.out.println("Username: " + cUser.getUserName());
			System.out.println("UserID: " + cUser.getUserID());
			System.out.println("Charapp: " + cUser.getCharApp());
			System.out.println("Server IP: " + cUser.getServerIP());
			System.out.println("Server Port: " + cUser.getServerPort());
			System.out.println("");
		}
		
		// Loop input incase bad datatype
		flag = false;
		
		while (!flag) {
			try {
				// Temporary variables
				String temp = "NULL";
				flag = true;
				
				// Does user want to modify details?
				do {
					System.out.println("Would you like to Create/Modify User Save Data? Yes or No?");
					temp = scanner.nextLine();
					System.out.println("");
				} while (temp.toUpperCase().equals("YES") && temp.toUpperCase().equals("NO"));

				
				// User deciding new details
				if (temp.toUpperCase().equals("YES")) {
					System.out.println("Enter Username:");
					String userName = scanner.nextLine(); // Read UserName
					
					System.out.println("");
					
					System.out.println("Enter User ID:");
					int userID = scanner.nextInt(); // Read UserID
					scanner.nextLine();
					
					System.out.println("");
					
					System.out.println("Enter Charapp:");
					String charApp = scanner.nextLine(); // Read CharApp
					
					System.out.println("");
					
					System.out.println("Enter Server IP:");
					String serverIP = scanner.nextLine(); // Read ServerIP
					
					System.out.println("");
					
					System.out.println("Enter Server Port:");
					int serverPort = scanner.nextInt(); // Read ServerPort
					scanner.nextLine();
					
					System.out.println("");
					
					// Call CreatePlayerData method in User Class
					cUser.CreatePlayerData(userName, userID, charApp, serverIP, serverPort);
				}
				
				// DataType Check
			} catch (Exception e) {
				flag = false;
				System.out.println("");
				System.out.println("Invalid Input!");
			}
			System.out.println("");
		}
	}
	
	// Client Selection Code
	public void ClientSel(Scanner scanner) {
		// Flag for inputs
		int usrInt = 0;
		
		System.out.println("--- Clients Page ---");
		
		// Prompting input (Between 1-5)
		while (usrInt < 1 || usrInt > 5) {
			try {
				System.out.println("Please select a Client below:");
				System.out.println("1. 2008");
				System.out.println("2. 2009");
				System.out.println("3. 2010");
				System.out.println("4. 2011");
				System.out.println("5. Go Back");
				
				usrInt = scanner.nextInt();
				
				// DataType Check
			} catch (Exception e) {
				usrInt = 0;
				System.out.println("Invalid Input!");
			}
			scanner.nextLine();
			System.out.println("");
			
			// Client Class to save Client Selected
			Client cClient = new Client();
			boolean temp = true;
			
			// Perform action based on input
			switch (usrInt) {
			case 1:
				cClient = new Eight(); // 2008
				break;
			case 2:
				cClient = new Nine(); // 2009
				break;
			case 3:
				cClient = new Ten(); // 2010
				break;
			case 4:
				cClient = new Eleven(); // 2011
				break;
			case 5:
				temp = false; // Go Back
				break;
			default:
				temp = false; // Out of Bounds
				System.out.println("Invalid Option!");
				break;
			}
			
			// Save if 1-4 was selected
			if (temp) {
				cClient.saveClientNumber();
			}
		} 
	}
	
	// Play Code (Solo + Host + Join)
	public void Play(Scanner scanner, int mode) {
		// Initialising Client Class
		Client cClient = new Client();
		
		// Initialising User Class
		User cUser = new User();
		
		// Flag for executing 'Try 2'
		boolean flag = false;
		
		try {
			// Load User Data and Client Number into Attribute(s)
			flag = true;
			cUser.LoadPlayerData();
			cClient.loadClientNumber();
		
			// Throw message if failed to load
		} catch (Exception exception) {
			flag = false;			
			System.out.println("Fail to load User Data!");
			System.out.println("Please ensure you have created User Data!");
		}
		
		if (flag) {
			// Try 2 Local Variables
			String clientExec = "";
			String clientPath = "";
			String clientParam = "";
			
			// Load Client
			cClient = cClient.loadSpecificClient();
			
			// Fetch Values
			clientExec = cClient.getClientExec();
			clientPath = cClient.getClientPath();
			try {
				switch (mode) {
				case 1: // Solo
					clientParam = "";
					break;
				case 2: // Join
					clientParam = "";
					break; // Host
				case 3:
					clientParam = "";
					break;
				default:
					System.out.println("Error in deciding Parameters!");
					System.out.println("Please ensure valid Save Data exist and a Client has been selected!");
					break;
				}
				
				System.out.println(clientPath + clientExec);
				Process process = new ProcessBuilder((clientPath + clientExec), clientParam).start();
			} catch (Exception e) {
				
			}
		}
	}
	
	// First executed
	public static void main(String[] args) {
		Main cMain = new Main();
		cMain.MainMenu();
	}
}