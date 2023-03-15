package org.example;

import org.example.controller.BrandDOA;
import org.example.controller.ProductDOA;
import org.example.model.brands;
import org.example.model.products;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void menumain()
    {
        System.out.println("_____ Quan Ly Cua Hang _____");
        System.out.println("1. Danh Sach San Pham");
        System.out.println("2. Them san pham");
        System.out.println("3. Xoa san pham  theo ma");
        System.out.println("4. Cap nhat thong tin san pham");
        System.out.println("5. Lấy thông tin hãng sản xuất kèm sản phâm");
        System.out.println("6. Lay ra top 5 san pham co gia cao nhat");
        System.out.println("7. Danh sach hang san suat");
        System.out.println("8. Them hang san xuat");
        System.out.println("9. Xoa hang san xuat");
        System.out.println("0. thoat");
    }
    private static void option1()
    {
        ProductDOA productDOA= new ProductDOA();
        List<products> productList = productDOA.getAll1();
        System.out.printf("%-20s %-20s %-20s %-20s", "STT", "Tên sản phẩm", "Giá sản phẩm", "Màu sắc");
        System.out.println();
        for (products pr:productDOA.getAll1()) {
            System.out.println(pr);
            System.out.printf("%-20d %-20s %-20d %-20s\n", (pr.getId()), pr.getProduct_name(), pr.getProduct_price(), pr.getProduct_color());
        }
//        for (int i = 0; i < productList.size(); i++) {
//            products p = productList.get(i);
//            System.out.printf("%-20d %-20s %-20d %-20s\n", (i+1), p.getProduct_name(), p.getProduct_price(), p.getProduct_color());
//        };
    }
    public static void option2(Scanner sc)
    {
        ProductDOA addproduct= new ProductDOA();
        products product= new products();
        System.out.print("product Name \t");
        product.setProduct_name(sc.nextLine());
        System.out.print("product price \t");
        product.setProduct_price(Integer.parseInt(sc.nextLine()));
        System.out.print("product size \t");
        product.setProduct_size(sc.nextLine());
        System.out.print("product color \t");
        product.setProduct_color(sc.nextLine());
        BrandDOA brandDOA=new BrandDOA();
        List<brands> brands=brandDOA.getAll();
        System.out.printf("%-20s %-20s", "ID Danh Muc", "Tên Danh Muc");
        System.out.println();
        for (brands b:brandDOA.getAll()) {
            System.out.printf("%-20s %-20s \n",b.getId(), b.getBrand_name());
        }
        System.out.print("nhap thuong hieu \t");
        product.setBrand_id(Integer.parseInt(sc.nextLine()));
        addproduct.insertProduct(product);

    }

    private static void option3(Scanner sc)
    {
        System.out.println("nhap id can xoa");
        int id=Integer.parseInt(sc.nextLine());
        ProductDOA productDOA=new ProductDOA();
        productDOA.delete(id);
    }
    private static void option4(Scanner sc)
    {
        ProductDOA productDOA=new ProductDOA();
        products product= new products();
        System.out.print("nhap id \t");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("product Name \t");
        product.setProduct_name(sc.nextLine());
        System.out.print("product price \t");
        product.setProduct_price(Integer.parseInt(sc.nextLine()));
        System.out.print("product size \t");
        product.setProduct_size(sc.nextLine());
        System.out.print("product color \t");
        product.setProduct_color(sc.nextLine());
        BrandDOA brandDOA=new BrandDOA();
        List<brands> brands=brandDOA.getAll();
        System.out.print("chon id cua brand \t");
        for (brands brand: brands) {
            System.out.println(brand.getId()+" "+brand.getBrand_name());
        }
        product.setBrand_id(Integer.parseInt(sc.nextLine()));
        productDOA.update(product,id);
    }
    public static void option5()
    {
        BrandDOA brandDOA= new BrandDOA();
        System.out.printf("%-20s %-20s \n", "Thương hiệu","số lượng sản phẩm");
        brandDOA.getAllProductByBrand();
    }
    public static  void option6()
    {
        System.out.println("6.Sắp xếp danh sach theo gia.");
        ProductDOA productDOA= new ProductDOA();
        List<products> productList = productDOA.getAll1();

        productList.stream()
                .sorted((o1, o2) -> {
                    if (o1.getProduct_price() > o2.getProduct_price()) {
                        return -1;
                    } else if (o1.getProduct_price() < o2.getProduct_price()) {
                        return 1;
                    }
                    return 0;
                })
                .limit(5)
                .forEach(e -> System.out.println(e));
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int option = -1;
        do {
            menumain();
            System.out.println("nhap lua chon cua ban");
            try{
                option = Integer.parseInt(sc.nextLine());
            }catch (Exception e)
            {
                System.out.println("nhap sai dinh dang");
                continue;
            }
            switch (option){
                case 1:
                    option1();
                    break;
                case 2:
                    option2(sc);
                    break;
                case 3:
                    option3(sc);
                    break;
                case 4:
                    option4(sc);
                    break;
                case 5:
                    option5();
                    break;
                case 6:
                    option6();
                    break;
            }
        }while (option !=0);
    }
}