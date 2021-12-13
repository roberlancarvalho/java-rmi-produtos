package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.FactoryConnection;

public class Product extends UnicastRemoteObject implements IProduct {

    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Connection conn;
    private ArrayList<Product> list;

    public Product() throws RemoteException {
        super();
        try {
            this.conn = FactoryConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Product save(Product product) throws RemoteException {

        String sql = "INSERT INTO product(nome, descrição, quantidade, preço) VALUES(?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement( sql );
            stmt.setString( 1, product.getName() );
            stmt.setString( 2, product.getDescription() );
            stmt.setInt( 3, product.getQuantity() );
            stmt.setDouble( 4, product.getPrice() );
            stmt.execute();

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        return product;
    }

    public ArrayList<Product> all() throws RemoteException {
        try {
            this.list = new ArrayList<>();

            String sql = "SELECT * FROM product";

            PreparedStatement stmt = this.conn.prepareStatement( sql );
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Product p = new Product();
                p.setId( result.getInt( "id" ) );
                p.setName( result.getString( "nome" ) );
                p.setDescription( result.getString( "descrição" ) );
                p.setQuantity( result.getInt( "quantidade" ) );
                p.setPrice( result.getDouble( "preço" ) );

                this.list.add( p );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity + ", price=" + price +
                "]";
    }

    public String information() throws RemoteException {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity + ", price=" + price + "]";
    }

}
