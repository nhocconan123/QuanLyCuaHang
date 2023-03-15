package org.example.controller;

import org.example.connection.MyConnection;
import org.example.model.brands;
import org.example.model.products;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductDOA extends BrandDOA{

    public List<products> getAll1()
    {
        List<products> productsList=new ArrayList<>();
        try{
            Connection conn= MyConnection.getConnection();
            String sql="select * from products";

            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            while (rs.next())
            {
                products product= new products();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getInt("product_price"));
                product.setProduct_size(rs.getString("product_size"));
                product.setProduct_color(rs.getString("product_color"));
                productsList.add(product);
            }
            stmt.close();
            conn.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return productsList;
    }
    public void insertProduct(products product)
    {
        try{
            Connection conn= MyConnection.getConnection();
            String sql= String.format("INSERT INTO `products` (`product_name`, `product_price`, `product_size`, `product_color`, `brand_id`) VALUES ( '%s', '%d', '%s', '%s', '%d')",
                    product.getProduct_name(),product.getProduct_price(),product.getProduct_size(),product.getProduct_color(),product.getBrand_id());
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void delete(int id)
    {
        products tmp=getById(id);
        if(tmp==null)
        {
            System.out.println("cap nhat that bai");
            return;
        }
        try {
            Connection conn= MyConnection.getConnection();
            String sql="DELETE FROM `demo_jdbc`.`products` WHERE (`id` = '"+id+"')";
            Statement stmt=conn.createStatement();
            long rs= stmt.executeUpdate(sql);
            if(rs==0)
            {
                System.out.println("xoa that bai");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
public products getById(int id) {
    final String sql = "SELECT * FROM `products` WHERE  `id` = " + id;
    products p = null;

    try {
        Connection conn = MyConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            p = new products();
            p.setId(rs.getInt("id"));
            p.setProduct_name(rs.getString("product_name"));
            p.setProduct_color(rs.getString("product_color"));
            p.setProduct_size(rs.getString("product_size"));
            p.setBrand_id(rs.getInt("brand_id"));
            p.setProduct_price(rs.getInt("product_price"));
        }
        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return p;
}
    public void update(products p,int id)
    {
        products tmp=getById(id);
        if(tmp==null)
        {
            System.out.println("cap nhat that bai");
            return;
        }
        try {
            Connection conn = MyConnection.getConnection();
            String sql="UPDATE `demo_jdbc`.`products` SET `product_name` = '"+p.getProduct_name()+"', `product_price` = '"+p.getProduct_price()+"', `product_size` = '"+p.getProduct_size()+"', `product_color` = '"+p.getProduct_color()+"', `brand_id` = '"+p.getBrand_id()+"' WHERE (`id` = '"+id+"')";
            Statement stmt=conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<products> getAll2()
    {
        final String sql = "select b.brand_name,count(a.brand_id) as count from products as a,brands as b where\n" +
                        "a.brand_id=b.id group by a.brand_id;";
        List<products> productList = new ArrayList<>();
        try{
            Connection conn= MyConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            while (rs.next())
            {
                String name_brand=rs.getString("brand_name");
                int count=rs.getInt("count");
            }System.out.println("name_brand");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(productList);
        return productList;
    }
}
