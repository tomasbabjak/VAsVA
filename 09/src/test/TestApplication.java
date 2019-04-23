package test;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Definicia aplikacie pre REST sluzby vratane registracie tried pre spracovanie REST requestov
 * @author Jaroslav Jakubik
 */

@ApplicationPath("rest")
public class TestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> cls = new HashSet<Class<?>>();
		cls.add(TestSession.class);
		cls.add(FacadeBean.class);
		return super.getClasses();
	}

}
