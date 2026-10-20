package com.shopsphere.rmi;
import java.rmi.*;import java.rmi.server.*;import java.util.concurrent.ConcurrentHashMap;
public class InventoryServiceImpl extends UnicastRemoteObject implements InventoryService {
 private final ConcurrentHashMap<Integer,Integer> stock=new ConcurrentHashMap<>();
 public InventoryServiceImpl()throws RemoteException{super();stock.put(1,10);stock.put(2,15);stock.put(3,12);}
 public int getStock(int id)throws RemoteException{return stock.getOrDefault(id,0);}
 public boolean updateStock(int id,int quantity)throws RemoteException{if(quantity<0)return false;stock.put(id,quantity);return true;}
}
