package com.shopsphere.network;
import java.io.*;import java.net.*;
public class InventoryClient {
 public static void main(String[] args)throws IOException{
  try(Socket socket=new Socket("localhost",5050);BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));PrintWriter out=new PrintWriter(socket.getOutputStream(),true)){
   out.println(args.length==0?"GET_STOCK 1":String.join(" ",args));System.out.println(in.readLine());
  }
 }
}
