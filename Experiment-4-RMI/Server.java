import java.rmi.Naming;
// import java.rmi.registry.LocateRegistry;

public class Server{

    public static void main(String[] args){
        try{
            // LocateRegistry.createRegistry(1099);
            CalculatorImpl obj = new CalculatorImpl();
            Naming.rebind("rmi://localhost/Calculator", obj);
            System.out.println("RMI Server Started...");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}