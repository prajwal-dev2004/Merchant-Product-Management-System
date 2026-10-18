package merchantproductapp.controller;

import java.util.List;
import java.util.Scanner;

import merchantproductapp.dao.MerchantDao;
import merchantproductapp.dao.ProductDao;
import merchantproductapp.dto.Merchant;
import merchantproductapp.dto.Product;

public class MerchantController {
	static Scanner sc=new Scanner(System.in);
	static MerchantDao mdao=new MerchantDao();
	static ProductDao pdao=new ProductDao();
	public static void main(String[] args) {
		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find Merchant by id");
		System.out.println("4.Verifly Merchant by email and password");
		System.out.println("5.Verifly Merchant by phone and password");
		System.out.println("6.Add Product");
		System.out.println("7.Update Product");
		System.out.println("8.find Product By Marchant id");
		System.out.println("enter the choice? ");
		
		int choice=sc.nextInt();
		switch(choice) {
		case 1:saveMerchant();
		break;
		case 2:updateMerchant();
		break;
		case 3:findMerchantById();
		break;
		case 4:veriflyMerchantByEmailAndPassword();
		break;
		case 5:veriflyMerchantByPhoneAndPassword();
		break;
		case 6:addProduct();
		break;
		case 7:updateProduct();
		break;
		case 8:findProductByMercahntId();
		break;
		default:
			break;
		}
	}
	
	private static void findProductByMercahntId() {
		System.out.println("enter the merchant id");
		int id=sc.nextInt();
		List<Product> p=pdao.findProductByMerchantId(id);
		if(p.size()>0) {
			for(Product product:p) {
				System.out.println(product);
			}
		}else {
			System.out.println("enter the correct merchant id");
		}
		
		
	}

	private static void updateProduct() {
		Product p=new Product();
		System.out.println("enter the product id,name,brand ,category,price");
		p.setId(sc.nextInt());
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setPrice(sc.nextDouble());
		Product product=pdao.updateProduct(p);
		if(product!=null) {
			System.out.println("product is updated");
			
		}else {
			System.out.println("please enter correct product details of id");
		}
		
	}

	private static void addProduct() {
		System.out.println("enter the merchat id");
		int id=sc.nextInt();
		Product p=new Product();
		System.out.println("enter product name,brand,category ,price");
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setPrice(sc.nextDouble());
		Product product=pdao.addProduct(id,p);
		if(product!=null) {
			System.out.println("product is added");
		}else {
			System.out.println("enter the correct merchant id");
		}
		
		
	}

	private static void veriflyMerchantByPhoneAndPassword() {
		System.out.println("enter the phone number");
		long phone=sc.nextLong();
		System.out.println("enter the password");
		String password=sc.next();
		Merchant m=mdao.veriflyMerchantByPhoneAndPassword(phone ,password);
		if(m!=null) {
			System.out.println("merchant verified");
		}else {
			System.out.println("merchant not verified");
		}
		
	}

	private static void veriflyMerchantByEmailAndPassword() {
		System.out.println("enter the merchant email");
		String email=sc.next();
		System.out.println("enter the merchant password");
		String password=sc.next();
		Merchant m=mdao.veriflyMerchantByEmailAndPassword( email, password);
		if(m!=null) {
			System.out.println("merchant is verified");
		}else {
			System.out.println("merchant not verifiedS");
		}
		
		}

	private static void saveMerchant() {
		System.out.println("enter the merchant info--name,phone,gst,email,passsword");
		String name=sc.next();
		long ph=sc.nextLong();
		String gst=sc.next();
		String em=sc.next();
		String pw=sc.next();
		
		Merchant m=new Merchant();
		m.setName(name);
		m.setPhone(ph);
		m.setGst_num(gst);
		m.setEmail(em);
		m.setPassword(pw);
		Merchant mdb=mdao.saveMerchant(m);
		
		System.out.println("Merchant info is saved with id "+ mdb.getId());
		
	}
	
	private static void updateMerchant() {
		System.out.println("enter the merchant info to update-id,name,phone,gst,em,pw");
		Merchant m=new Merchant();
		m.setId(sc.nextInt());
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setGst_num(sc.next());
		m.setEmail(sc.next());
		m.setPassword(sc.next());
		Merchant mdb=mdao.updatemerchant(m);
		if(mdb!=null) {
			System.out.println("merchant info updated");
		}else {
			System.out.println("unable to update the merchant info since id is invalid");
		}
		
	}
	
	private static void findMerchantById() {
		System.out.println("enter the merchant id");
		int id=sc.nextInt();
		Merchant m=mdao.findMerchantById(id);
		if(m!=null) {
			System.out.println(m);
		}
		else {
			System.out.println("merchant not found based on id so id invalid enter correct id");
		}
			
	}
	

}
