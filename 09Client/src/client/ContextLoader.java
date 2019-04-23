package client;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextLoader {
	
	private static final String HOST = "localhost";
	private static final String PORT = "8080";
	
	
	private ContextLoader(){
		
	}
	
	public static Context createRemoteEjbContext() throws NamingException {

		Hashtable<Object, Object> props = new Hashtable<>();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put(Context.SECURITY_PRINCIPAL,"testuser");
	    props.put(Context.SECURITY_CREDENTIALS,"testpassword");
	    
        props.put("jboss.naming.client.ejb.context", false);
        props.put("org.jboss.ejb.client.scoped.context", true);

        props.put("endpoint.name", "client-endpoint");
        props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
        props.put("remote.connections", "default");
        props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);

        props.put(Context.PROVIDER_URL, "remote://" + HOST + ":" + PORT);
        props.put("remote.connection.default.host", HOST);
        props.put("remote.connection.default.port", PORT);
       
        
        return new InitialContext(props);
	}

}
