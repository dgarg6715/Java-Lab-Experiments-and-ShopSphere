package com.shopsphere.rmi;
import java.rmi.registry.*;
public class InventoryRMIServer {
 public static void main(String[] args)throws Exception{
  Registry registry=LocateRegistry.createRegistry(1099);
  registry.rebind("ShopSphereInventory",new InventoryServiceImpl());
  System.out.println("RMI inventory service ready on port 1099");
 }
}
