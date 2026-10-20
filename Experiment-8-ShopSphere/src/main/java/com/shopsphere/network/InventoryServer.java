package com.shopsphere.network;
import java.io.*;import java.net.*;
public class InventoryServer {
 public static void main(String[] args)throws IOException{
  int port=5050;try(ServerSocket server=new ServerSocket(port)){System.out.println("ShopSphere inventory server listening on "+port);
   while(true){Socket socket=server.accept();new Thread(()->handle(socket)).start();}
  }
 }
 private static void handle(Socket s){try(s;BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));PrintWriter out=new PrintWriter(s.getOutputStream(),true)){
  String command=in.readLine(); if(command==null)return;
  if(command.startsWith("GET_STOCK "))out.println("Demo stock for product "+command.substring(9).trim()+": 10");
  else if(command.startsWith("GET_PRODUCT "))out.println("Demo product "+command.substring(12).trim()+" - ShopSphere sample item");
  else out.println("Commands: GET_STOCK <id> | GET_PRODUCT <id>");
 }catch(IOException e){System.err.println("Client error: "+e.getMessage());}}
}
