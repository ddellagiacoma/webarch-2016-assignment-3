# Accessing an enterprise bean

1. INTRODUCTION

The topic of this assignment is the Enterprise JavaBean (EJB), which is a server-side software component
that encapsulates the business logic of an application into a single object (the bean).

The first part of the assignment consists of installing WildFly, writing an enterprise bean which exposes a
method for giving a string containing the date and the time of the day and finally deploying the enterprise
bean on WildFly.

Moreover, the second part of the assignment consists of writing a client (a standard Java Application) which
connects to the bean, asks twice the method on the bean and writes the result on screen.

2. IMPLEMENTATION

The Java Enterprise Application named **EntApp** is composed by the stateless EJB **DateTime**. The bean
includes the method **print()** which return a string containing the current time and date.
```java
@Stateless
public class DateTime implements ABean {
  @Override
  public String print() {
    String timeStamp = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
    return timeStamp;
  }
}
```

On the other hand, the client **BeanClient** establishes a connection to WildFly and connects to the bean
**DateTime**. After that, it calls twice the remote method **print()** and prints the result.
```java
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
```
Furthermore, the remote interface **ABean** has been implemented in the following way:
```java
@Remote
public interface ABean {
    public String print();
}
```

## 3. DEPLOYMENT
The Java Enterprise Application **EntApp** has to be built in order to generate the **EntApp.ear** file. The ear file
need to be copied in the following folder:

**JBOSS_HOME/standalone/deployments**

After that, WildFly (in this case version 9.0.1.Final) can be started launching the **standalone.bat** (or
**standalone.sh**) file. The following screen should appear:

![image](https://cloud.githubusercontent.com/assets/24565161/21266479/b2108772-c3a6-11e6-84e5-100b46ce0c82.png)

Therefore, the **BeanClient** can be started running the jar file in the dist folder of the client in this way:

**java -jar BeanClient.jar**

And the result should be similar to this:

![image](https://cloud.githubusercontent.com/assets/24565161/21266500/c46d83d4-c3a6-11e6-8c95-6a137e0eb28d.png)

It important to remember that the **BeanClient** requires the **jboss-client.jar** library to work correctly.
