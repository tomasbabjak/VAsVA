package client;

import testuj.FacadeBeanRemote;
import testuj.dbtestRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBtst {

    private static final String JNDI = "ejb:AE09/09WAR/dbtest!testuj.dbtestRemote";

    void skuska() throws NamingException {
        //Context ctx = ContextLoader.createRemoteEjbContext();
        Context ctx = new InitialContext();
        dbtestRemote dbtestRemote = (dbtestRemote) ctx.lookup(JNDI);

        System.out.println(dbtestRemote.otestuj("svet"));
    }
}
