package client.controllers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import client.commands.AddProductItemCommand;
import client.commands.AddProductToShoppingCartCommand;
import client.commands.BuyShoppingCartCommand;
import client.commands.ClearCommand;
import client.commands.ClearShoppingCartCommand;
import client.commands.Command;
import client.commands.ExitStoreCommand;
import client.commands.GetProductCommand;
import client.commands.GetProductListCommand;
import client.commands.RemoteProductFromShoppingCartCommand;
import client.commands.RemoveProductCommand;
import client.commands.ShowShoppingCartCommand;
import client.commands.UpdateProductItemCommand;
import client.commands.UpdateQuantityProductCommand;
import common.RemoteOnlineStore;
import common.User;
import common.Role;

public class OnlineStoreMenu {
	
	Map<String, String> menuDescriptions = new HashMap<String, String>();
	Map<String, Command> menuItems;
	Map<Integer, String> menuOrder = new HashMap<Integer, String>();
	RemoteOnlineStore producStub;
	Scanner input;
	User user;
	
	public OnlineStoreMenu(RemoteOnlineStore producStub, User user, Scanner input) {
		this.producStub = producStub;
		this.user = user;
		this.input = input;
	}
	public void intializeMenu() {
		menuDescriptions.put("SHOWALL", "Show all products in OnlineStore");
		menuOrder.put(1, "SHOWALL");
		menuDescriptions.put("ADD", "Add a Product to OnlineStore");
		menuOrder.put(2, "ADD");
		menuDescriptions.put("REMOVE", "Remove a Product from OnlineStore");
		menuOrder.put(3, "REMOVE");
		menuDescriptions.put("UPDATE", "Update a Product in OnlineStore");
		menuOrder.put(4, "UPDATE");
		menuDescriptions.put("UPDATEQUANTITY", "Update a Product Stock in OnlineStore");
		menuOrder.put(5, "UPDATEQUANTITY");
		menuDescriptions.put("GETPRODUCT", "Get a Product Details in OnlineStore");
		menuOrder.put(6, "GETPRODUCT");
		menuDescriptions.put("CLEAR", "Clean all products and stock and Initiaize OnlineStore");
		menuOrder.put(7, "CLEAR");
		
		menuDescriptions.put("ADDSHOP", "Add a product to OnlineStore Shopping Cart");
		menuOrder.put(8, "ADDSHOP");
		menuDescriptions.put("SHOWSHOP", "Show products in OnlineStore Shopping Cart");
		menuOrder.put(9, "SHOWSHOP");
		menuDescriptions.put("REMOVEPRODUCTSHOP", "Remove a Product in OnlineStore Shopping Cart");
		menuOrder.put(10, "REMOVEPRODUCTSHOP");
		menuDescriptions.put("CLEARSHOP", "Clear products in OnlineStore Shopping Cart");
		menuOrder.put(11, "CLEARSHOP");
		menuDescriptions.put("BUYSHOP", "Buy products from OnlineStore Shopping Cart");
		menuOrder.put(12, "BUYSHOP");
		menuDescriptions.put("EXIT", "Exit Product Actions");
		menuOrder.put(13, "EXIT");
		
	}
	public void intializeStore() {
		menuItems = new HashMap<String, Command>();
		
		if(this.user.getUserRole() == Role.ADMIN)
		{
			menuItems.put("SHOWALL", new GetProductListCommand(producStub, this.user.getEmailID()));
			menuItems.put("ADD", new AddProductItemCommand(producStub,  this.user.getEmailID(), this.input));
			menuItems.put("UPDATE", new UpdateProductItemCommand(producStub,  this.user.getEmailID(), this.input));
			menuItems.put("REMOVE", new RemoveProductCommand(producStub,  this.user.getEmailID(), this.input));
			menuItems.put("UPDATEQUANTITY", new UpdateQuantityProductCommand(producStub, this.user.getEmailID(), this.input));
			menuItems.put("GETPRODUCT", new GetProductCommand(producStub, this.user.getEmailID(),  this.input));
			menuItems.put("CLEAR", new ClearCommand(producStub, this.user.getEmailID()));
			
			menuItems.put("SHOWSHOP", new ShowShoppingCartCommand(producStub,  this.user.getEmailID()));
			menuItems.put("ADDSHOP", new AddProductToShoppingCartCommand(producStub,  this.user.getEmailID(),  this.input));
			menuItems.put("REMOVEPRODUCTSHOP", new RemoteProductFromShoppingCartCommand(producStub,  this.user.getEmailID(),  this.input));
			menuItems.put("CLEARSHOP", new ClearShoppingCartCommand(producStub, this.user.getEmailID()));
			menuItems.put("BUYSHOP", new BuyShoppingCartCommand(producStub, this.user.getEmailID()));
			menuItems.put("EXIT", new ExitStoreCommand(producStub, this.user.getEmailID()));
		}
		else if(this.user.getUserRole() == Role.CUSTOMER)
		{
			menuItems.put("SHOWALL", new GetProductListCommand(producStub, this.user.getEmailID()));
			menuItems.put("SHOWSHOP", new ShowShoppingCartCommand(producStub,  this.user.getEmailID()));
			menuItems.put("ADDSHOP", new AddProductToShoppingCartCommand(producStub,  this.user.getEmailID(), this.input));
			menuItems.put("REMOVEPRODUCTSHOP", new RemoteProductFromShoppingCartCommand(producStub,  this.user.getEmailID(),  this.input));
			menuItems.put("CLEARSHOP", new ClearShoppingCartCommand(producStub, this.user.getEmailID()));
			menuItems.put("BUYSHOP", new BuyShoppingCartCommand(producStub, this.user.getEmailID()));
			menuItems.put("EXIT", new ExitStoreCommand(producStub, this.user.getEmailID()));
		}
		
	}

	public void runCommand(String operation) throws RemoteException {
		if (this.menuItems.get(operation) == null) {
			System.out.print("Not an appropriate selection");
		} else {
			this.menuItems.get(operation).execute();
		}

	}

	public Map<Integer, String> showOptions() {
		int count = 1;
		Map<Integer, String> menuCommands = new HashMap<Integer, String>();
		for (Entry<Integer, String> entry : this.menuOrder.entrySet()) {
			
			if(menuItems.containsKey(entry.getValue()))
			{
				System.out.println(count + ". " + menuDescriptions.get(entry.getValue()));
				menuCommands.put(count, entry.getValue());
				count = count + 1;
			}
			
		}
		return menuCommands;

	}
}
