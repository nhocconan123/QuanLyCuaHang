package org.example.controller;

import org.example.connection.MyConnection;
import org.example.model.brands;

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
            long rs=stmt.executeUpdate(sql);
            long rs1=stmt.executeUpdate(sql1);
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
