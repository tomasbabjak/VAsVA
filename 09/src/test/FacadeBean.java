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
public class FacadeBean implements FacadeBeanRemote{

	@EJB
	private ExecutiveBean exe;

	@GET
    public int doPlus(int a, int b, int c ) {
    	int result = exe.doPlus(exe.doPlus(a, b), c);
    	System.out.println(result);
    	return result;
    }

}
