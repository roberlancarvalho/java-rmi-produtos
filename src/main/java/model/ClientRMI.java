package model;

import java.rmi.Naming;
import java.util.List;

public class ClientRMI {

    public static void main(String[] args) {

        try {

            IProduct product = (IProduct) Naming.lookup( "rmi://localhost:1099/product" );

            List<Product> list = product.all();

            for (IProduct p : list) {
                System.out.println( p.information() );
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
