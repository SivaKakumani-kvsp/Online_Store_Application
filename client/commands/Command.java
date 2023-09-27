package client.commands;

import java.rmi.RemoteException;

public interface Command {
	public abstract void execute() throws RemoteException;
}