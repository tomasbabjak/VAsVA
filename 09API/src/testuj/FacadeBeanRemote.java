package testuj;

import javax.ejb.Remote;

/**
 * Remote rozhranie, ktore poskytuje metody pre volanie z klienta (inej JVM)
 * @author Jaroslav Jakubik
 */

@Remote
public interface FacadeBeanRemote {
	public int doPlus(int a, int b, int c);
}
