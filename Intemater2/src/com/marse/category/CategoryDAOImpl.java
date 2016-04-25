package com.marse.category;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Category;
import com.marse.model.User;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public List<Category> listOfCategory() {

		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		String hqlQuery="From Category c";
		
		Query query=session.createQuery(hqlQuery);
		
		List<Category> list=query.list();
		
		return list;
	}

	@Override
	public void addCategory(Category objCategory) {

		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		session.save(objCategory);
		tx.commit();
		session.close();
	
	}

	@Override
	public void updateCategory(Category objCategory) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
			/*String hql="UPDATE User u SET u.userStatus= :status WHERE u.userId= :userId";
			  int updatedRow=session.createQuery(hql)
						.setString("status", "I")
						.setInteger("userId",userId)
						.executeUpdate();*/
			
			session.update(objCategory);
		
			System.out.println("Rows updated from updateCategory() ");
			tx.commit();
			session.close();
		
	}

	@Override
	public void deleteCategory(Category objCategory) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		session.delete(objCategory);
		tx.commit();
		session.close();
		
		
	}
	
	@Override
	public List<Category> listOfCatagory() {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		Criteria crit = (Criteria) session.
                 		 createCriteria(Category.class).
                 		 setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Category> list = crit.list();
		
		return list;
		
	}

}
