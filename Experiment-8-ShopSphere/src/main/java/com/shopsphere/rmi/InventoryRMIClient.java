package com.shopsphere.rmi;
import java.rmi.registry.*;
public class InventoryRMIClient {
 public static void main(String[] args)throws Exception{
  Registry registry=LocateRegistry.getRegistry("localhost",1099);
  InventoryService service=(InventoryService)registry.lookup("ShopSphereInventory");
  int id=args.length>0?Integer.parseInt(args[0]):1;
  System.out.println("Stock for product "+id+": "+service.getStock(id));
 }
}
