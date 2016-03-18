import java.util.Iterator;
import java.util.List;

import com.marse.customer.CustomerDAO;
import com.marse.daofactory.DAOFactory;
import com.marse.model.Customer;


public class Demo {

	public static void main(String[] args) {

		
		CustomerDAO customerDAO=DAOFactory.getInstanceOfCustomer();
		Customer objCustomer=new Customer();
		
		objCustomer.setName("rohit");
		objCustomer.setAcc("4585475945");
		objCustomer.setBankName("SBI");
		objCustomer.setBankBranch("Mumbai");
		objCustomer.setEmail("model@simple.com");
		
		customerDAO.addCustomer(objCustomer);
		
		List<Customer> list=customerDAO.listOfCustomer();
		
		Iterator<Customer> it=list.iterator();
		
		while (it.hasNext()) {

			objCustomer=(Customer) it.next();
			
			System.out.println(objCustomer.getCustId());
			System.out.println(objCustomer.getName());
			System.out.println(objCustomer.getDob());
			System.out.println(objCustomer.getEmail());
			System.out.println(objCustomer.getPan());
			System.out.println(objCustomer.getAddress());
			System.out.println(objCustomer.getBankName());
			System.out.println(objCustomer.getAcc());
			
		}
		
	}

}
