package beans;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BeanClient {

    public static void main(String[] args) {

        try {
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            jndiProps.put("jboss.naming.client.ejb.context", true);

            InitialContext initialContext = new InitialContext(jndiProps);

            ABean abean = (ABean) initialContext.lookup("EntApp/EntApp-ejb/DateTime!beans.ABean");
            
            System.out.println("\n" + abean.print() + "\n" + abean.print());

        } catch (NamingException e) {
            System.err.println(e);
        }
    }
}