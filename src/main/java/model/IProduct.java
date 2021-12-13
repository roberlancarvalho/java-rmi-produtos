package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IProduct extends Remote {

    Product save(Product product) throws RemoteException;

    ArrayList<Product> all() throws RemoteException;

    String information() throws RemoteException;
}
