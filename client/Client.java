package client;

import java.rmi.Naming;
import java.util.Map;
import java.util.Scanner;

import client.controllers.OnlineStoreMenu;

import common.RemoteOnlineStore;
import common.RemoteUser;
import common.Role;
import common.User;

public class Client {
	public static void main(String[] args) {

		try {
			

			String remoteUserName = "//" + args[0] + ":" + args[1] + "/RemoteUser";
			String remoteProduct = "//" + args[0] + ":" + args[1] + "/RemoteOnlineStore";

// to look up the remote user side(server) create stub
			RemoteUser userStub = (RemoteUser) Naming.lookup(remoteUserName);

			RemoteOnlineStore productStub = (RemoteOnlineStore) Naming.lookup(remoteProduct);
			
			
			

			System.out.println("Server found for " + remoteUserName);
//Scanner class for user inputs through readObj
			Scanner readObj = new Scanner(System.in);
			// read customer's input
			boolean done = false;
			 while(!done)
			 {
				 
				System.out.println("1. To register a new user\n" + "2. User login\n" + "3. Exit");
				String option = readObj.nextLine();
				if(option.equals("1"))
				{
					System.out.println("Hi! Are you a new user to this store");
					System.out.println("Please Sign UP by entering your details below");
					// user input for all the nwe user details

					System.out.print("1. Name: ");
					String name = readObj.nextLine();
					// userStub.setName(name);

					System.out.print("2: Email: ");
					String email = readObj.nextLine();
					// userStub.setEmail(email);

					System.out.print("3. Phone: ");
					String phone = readObj.nextLine();
					// userStub.setPhone(phone);

					System.out.print("4: Address details: ");
					String address = readObj.nextLine();
					// userStub.setAddress(address);

					System.out.print("5: Password: ");
					String password = readObj.nextLine();
					// userStub.setAddress(password);
					System.out.print("6: ROLE CUSTOMER OR ADMIN: ");
					String role = readObj.nextLine();
					Role userRole = null;
					if (!role.equalsIgnoreCase(Role.ADMIN.name()) && !role.equalsIgnoreCase(Role.CUSTOMER.name())) {
						System.out.println("Invalid Option");
						break;
					} else {

						if (role.equals(Role.ADMIN.name())) {
							userRole = Role.ADMIN;
						}

						if (role.equals(Role.CUSTOMER.name())) {
							userRole = Role.CUSTOMER;
						}

					}
					User user = new User(name, email, phone, address, password, userRole);
					String remoteMessage = userStub.register(user);
					System.out.println(remoteMessage);

				}
				else if(option.equals("2"))
				{

					System.out.println("Please login by entering details below");
					// user input for all the nwe user details
					System.out.print("1. EmailID: ");
					String email = readObj.nextLine();

					System.out.print("2. Password: ");
					String password = readObj.nextLine();

					User user = userStub.login(email, password);
					if (user == null) {
						System.out.println("Invalid User/Password");
					
					} else {
						System.out.println("User Details");
						System.out.println("Name :" + user.getName());
						System.out.println("Address :" + user.getAddress());
						System.out.println("Phone :" + user.getPhone());
						System.out.println("Email :" + user.getEmailID());
						System.out.println("Role :" + user.getUserRole().name());
						boolean productShow = true;
						OnlineStoreMenu storeMenu = new OnlineStoreMenu(productStub, user, readObj) ;
						storeMenu.intializeMenu();
						storeMenu.intializeStore();
						while(productShow)
						{
							System.out.println("Please select any one of the Action given below by entering 1,2..");	
							Map<Integer, String> menuOptions = storeMenu.showOptions();
							Integer menuOptionSelection = readObj.nextInt();
							if(menuOptions.get(menuOptionSelection) != null  )
							{
								storeMenu.runCommand(menuOptions.get(menuOptionSelection));
								if(menuOptions.get(menuOptionSelection).equals("EXIT"))
								{
									break;
								}
							}
							else {
								System.out.println("Invalid Option Try again");	
							}
						}
						

					}

				}
				else if(option.equals("3"))
				{
					done=true;
					continue;
				}
				else
				{
					System.out.println("No such choice");
				}
			 }
			 readObj.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error: " + e.getMessage());

		}
	}

}
