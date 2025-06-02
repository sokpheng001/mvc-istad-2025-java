package view;

import controller.ProductController;
import model.dto.ProductCreateDto;
import model.dto.ProductResponseDto;
import model.dto.UpdateProductDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    private static final ProductController productController
             = new ProductController();
    private static void thumbnail(){
        System.out.println("============================");
        System.out.println("      Product Inventory     ");
        System.out.println("============================");
        System.out.println("""
                1. Get All Products
                2. Add New Product
                3. Update Product 
                4. Find Product by UUID
                5. Delete Product by UUID
                6. Exit """);
    }

    public static void home(){
        while (true){
            thumbnail();
            System.out.print("[+] Insert option: ");
            switch (new Scanner(System.in).nextInt()){
                case 1->{
                    productController
                            .getAllProducts()
                            .forEach(System.out::println);
                }
                case 2->{
                    System.out.print("[+] Insert Product Name: ");
                    String pName = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert Expire Year: ");
                    int year = new Scanner(System.in).nextInt();
                    System.out.print("[+] Insert Expire Month: ");
                    int month = new Scanner(System.in).nextInt();
                    System.out.print("[+] Insert Expire Day: ");
                    int day = new Scanner(System.in).nextInt();
                    ProductCreateDto productCreateDto
                             = new ProductCreateDto(pName, Date.valueOf(LocalDate.of(year, month, day)));
                    ProductResponseDto product = productController.insertNewProduct(productCreateDto);
                    System.out.println(product);
                }
                case 3->{
                    System.out.print("[+] Insert Product Uuid: ");
                    String uuid = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert new Product Name: ");
                    String newPName = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert new Expire Year: ");
                    int year = new Scanner(System.in).nextInt();
                    System.out.print("[+] Insert new Expire Month: ");
                    int month = new Scanner(System.in).nextInt();
                    System.out.print("[+] Insert new Expire Day: ");
                    int day = new Scanner(System.in).nextInt();
                    ProductResponseDto updatedProduct = productController
                            .updateProductByUuid(uuid,
                                    new UpdateProductDto(newPName,
                                            Date.valueOf(LocalDate.of(year, month, day))));
                    System.out.println(updatedProduct);
                }
                case 4->{
                    System.out.print("[+] Insert product uuid: ");
                    String uuid = new Scanner(System.in).nextLine();
                    System.out.println(productController.getProductByUuid(uuid));
                }
                case 5->{
                    System.out.print("[+] Insert product uuid: ");
                    String uuid = new Scanner(System.in).nextLine();
                    System.out.println(productController.deleteProductByUuid(uuid));
                }
            }
        }
    }
}
