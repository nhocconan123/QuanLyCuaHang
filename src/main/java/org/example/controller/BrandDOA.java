package org.example.controller;

import org.example.connection.MyConnection;
import org.example.model.brands;
import org.example.model.products;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandDOA {
    public List<brands> getAll()
    {
        List<brands> brand=new ArrayList<>();
        try{
            Connection conn= MyConnection.getConnection();
            String sql="select * from brands";
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next())
            {
                brands brands1=new brands();
                brands1.setId(rs.getInt("id"));
                brands1.setBrand_name(rs.getString("brand_name"));
                brands1.setBrand_address(rs.getString("brand_address"));

                brand.add(brands1);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return brand;
    }
    public brands getById(long id) {
        final String sql = "SELECT * FROM `brands` WHERE  `id` = " + id;
        brands b = null;

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                b = new brands();
                b.setId(rs.getInt("id"));
                b.setBrand_name(rs.getString("brand_name"));
                b.setBrand_address(rs.getString("brand_address"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    public List<brands> getAllProductByBrand() {

        try {
            Connection conn= MyConnection.getConnection();
            String query ="select brand_name,count(products.id)  as soluong from products inner join brands on products.brand_id=brands.id group by brand_id";

            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {

                String brand_name = rs.getString("brand_name");
                int soluong  = rs.getInt("soluong");
                System.out.printf("%-20s %-20s \n", brand_name,soluong);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }return null;
    }
    public void insert(brands brand)
    {
        try{
            Connection conn=MyConnection.getConnection();
            String sql=String.format("INSERT INTO `brands` ( `brand_name`, `brand_address`) VALUES ('%s', '%s')",
            brand.getBrand_name(),brand.getBrand_address());
            Statement stmt =conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void delete(long id)
    {
        try{
            Connection conn= MyConnection.getConnection();
            String sql="delete from `brands` where id="+id;
            String sql1="delete from `products` where brand_id="+id;
            Statement stmt=conn.createStatement();
            long rs1=stmt.executeUpdate(sql1);
            long rs=stmt.executeUpdate(sql);
            if(rs==0 || rs1==0)
            {
                System.out.println("xoa that bai");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
