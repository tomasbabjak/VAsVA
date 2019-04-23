package client;

import testuj.FacadeBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Test {
	private static final String JNDI = "ejb:AE09/09WAR/FacadeBean!testuj.FacadeBeanRemote";

	void skuska() throws NamingException {
			//Context ctx = ContextLoader.createRemoteEjbContext();
			Context ctx = new InitialContext();
			FacadeBeanRemote calcFacade = (FacadeBeanRemote) ctx.lookup(JNDI);

			System.out.println(calcFacade.doPlus(5, 11, -2));
		}
	

}
