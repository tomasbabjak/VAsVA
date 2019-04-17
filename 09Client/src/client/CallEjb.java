package client;

import javax.naming.NamingException;

/**
 * Jednoduchy priklad volania stateless session beany (z klient na aplikacny server)
 * na zaklade JNDI
 * 
 * @author Jaroslav Jakubik
 */

public class CallEjb {

	
	public static void main(String[] args) throws NamingException {
	
//		Test test = new Test();
//		test.skuska();

		DBtst tt = new DBtst();
		tt.skuska();

	}
}
