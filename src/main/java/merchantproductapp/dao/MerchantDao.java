package merchantproductapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import merchantproductapp.dto.Merchant;

public class MerchantDao {
	
	

	EntityManagerFactory fac=Persistence.createEntityManagerFactory("dev");
	EntityManager man=fac.createEntityManager();
	public Merchant saveMerchant(Merchant m) {
		EntityTransaction tran =man.getTransaction();
		tran.begin();
		man.persist(m);
		tran.commit();
		return m;
		
	
	}
	public Merchant updatemerchant(Merchant m) {
		EntityTransaction tran=man.getTransaction();
		tran.begin();
		Merchant mdb=man.find(Merchant.class, m.getId());
		if(mdb!=null) {
			mdb.setName(m.getName());
			mdb.setPhone(m.getPhone());
			mdb.setGst_num(m.getEmail());
			mdb.setEmail(m.getEmail());
			mdb.setPassword(m.getPassword());
			tran.commit();
			return mdb;
		}else {
			return null;
		}
		
				
	}
	public Merchant findMerchantById(int id) {
		Merchant m=man.find(Merchant.class, id);
		 return m;
		
	}
	public Merchant veriflyMerchantByEmailAndPassword(String email, String password) {
		Query q=man.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			Merchant m=(Merchant)q.getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
		
	}
	public Merchant veriflyMerchantByPhoneAndPassword(long phone, String password) {
		Query q=man.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			Merchant m=(Merchant)q.getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
}
