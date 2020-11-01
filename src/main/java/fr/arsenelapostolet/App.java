package fr.arsenelapostolet;

import sessionBeans.GestionContactRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        if(Boolean.getBoolean("http")) {
            jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080/wildfly-services");
        } else {
            jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
        }
        final Context context = new InitialContext(jndiProperties);
        final GestionContactRemote ejb = (GestionContactRemote) context.lookup("ejb:/efrei-m1-jakartaee-tp4-ejb-1.0-SNAPSHOT/GestionContactBean!sessionBeans.GestionContactRemote");
        System.out.println(ejb.coucouContact("test"));
        ejb.addContact("John","Shepard","john.shepard@n7.citadel" );
        System.out.println(ejb.findContactNameById(1));
    }
}
