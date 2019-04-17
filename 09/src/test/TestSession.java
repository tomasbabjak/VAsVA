package test;

import testuj.FacadeBeanRemote;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Stateless session beana s jednoduchou logikou pre vratenie pozdravu
 * Pre publikovanie session beany je pouzity JAXRS a anotacie Path, GET, QueryParam
 */
@Stateless
@LocalBean
@Path("sayHello")
public class TestSession {

//	@EJB
//	private FacadeBeanRemote facade;

	@GET
	public String sayHello(@QueryParam("name") String name) {
		//System.out.println(facade.doPlus(5, 3, 2));
		return "Hello " + name;
	}

}
