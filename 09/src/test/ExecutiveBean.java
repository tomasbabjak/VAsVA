package test;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Jednoducha executive beana v ktorej je implementovana konkretna biznis logika
 * Ide o stateless session beanu, ktora neimplementuje remote rozhranie a je teda
 * pre externych klientov skryta.
 *  
 * @author Jaroslav Jakubik
 */
@Stateless
@LocalBean
public class ExecutiveBean {

    public int doPlus(int a, int b) {
    	return a + b;
    }

}
