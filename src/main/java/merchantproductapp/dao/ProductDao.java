package merchantproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import merchantproductapp.dto.Merchant;
import merchantproductapp.dto.Product;

public class ProductDao {
	EntityManagerFactory fac=Persistence.createEntityManagerFactory("dev");
	EntityManager man=fac.createEntityManager();

	public Product addProduct(int id, Product p) {
		EntityTransaction tran =man.getTransaction();
		tran.begin();
		Merchant m=man.find(Merchant.class, id);
		if(m!=null) {
			m.getProducts().add(p);
			p.setMerchant(m);
			man.persist(m);
			tran.commit();
			return p;
			
		}else {
			return null;
		}
		
		
	}

	public Product updateProduct(Product p) {
		EntityTransaction tran=man.getTransaction();
		Product product=man.find(Product.class, p.getId());
		if(product !=null) {
			tran.begin();
			product.setName(p.getName());
			product.setBrand(p.getBrand());
			product.setCategory(p.getCategory());
			product.setPrice(p.getPrice());
			tran.commit();
		
			return product;
		}
		else {
			return null;
		}
	}

	public List<Product> findProductByMerchantId(int id) {
		Query q=man.createQuery("select m.products from Merchant m where m.id=?1");
		q.setParameter(1, id);
		 List<Product> lp=q.getResultList();
		 return lp;
		
		
	}

	

}
