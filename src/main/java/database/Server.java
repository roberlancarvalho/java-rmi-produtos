package database;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import model.Product;

public class Server {

    public static void main(String[] args) {

        try {

            Product p = new Product();

            String url = "rmi://localhost/product";

            LocateRegistry.createRegistry( 1099 );
            Naming.bind( url, p );

            System.out.println( "Aguardando clientes..." );


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
