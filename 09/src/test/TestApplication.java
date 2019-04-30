package test;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class TestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> cls = new HashSet<Class<?>>();
		return super.getClasses();
	}

}
