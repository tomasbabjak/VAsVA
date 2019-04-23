package test;

import testuj.FacadeBeanRemote;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Facade beana, ktora prevola executivnu beanu pre vykonanie konkretnej logiky
 * Na facade je implementovane remote rozhranie aby vybrane metody (v tomto pripade
 * iba doPlus) boli k dispozicii pre ostatnych klientov.
 * 
 * @author Jaroslav Jakubik
 *
 */

@Stateless
@LocalBean
@Path("calc")
public class FacadeBean{

	@EJB
	private ExecutiveBean exe;

	@GET
    public int doPlus(@QueryParam("key") int a ) {
    	int result = exe.doPlus(exe.doPlus(a, a), a);
    	System.out.println(result);
    	return result;
    }

}
