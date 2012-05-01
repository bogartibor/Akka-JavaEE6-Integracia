/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.akka;

import cz.muni.fi.akka.SummingActor.Work;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author xbogar
 */

public class MainClass {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws NamingException {      
        /*ActorSystem system = ActorSystem.create("MySystem");
        final ActorRef logging = system.actorOf(new Props(LoggingActor.class), "logging");
        ActorRef sumActor = system.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public UntypedActor create() {
                return new SummingActor(logging);
            }
        }), "summing");*/
        SumSingleton.getInstance().initSumActor().tell(new Work(new ListHolder(), 3));
/*    
        //get the BeanManager from JNDI
        BeanManager beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
        //CDI uses an AnnotatedType object to read the annotations of a class
        AnnotatedType<CDIActorExtension> type = beanManager.createAnnotatedType(CDIActorExtension.class);
        //The extension uses an InjectionTarget to delegate instantiation, dependency injection 
        //and lifecycle callbacks to the CDI container
        InjectionTarget<CDIActorExtension> it = beanManager.createInjectionTarget(type);
        //each instance needs its own CDI CreationalContext
        CreationalContext ctx = beanManager.createCreationalContext(null);
        //instantiate the framework component and inject its dependencies
        CDIActorExtension instance = it.produce(ctx);  //call the constructor
        it.inject(instance, ctx);  //call initializer methods and perform field injection
        it.postConstruct(instance);  //call the @PostConstruct method

        //destroy the framework component instance and clean up dependent objects
        it.preDestroy(instance);  //call the @PreDestroy method
        it.dispose(instance);  //it is now safe to discard the instance
        ctx.release();  //clean up dependent objects
*/      
    }

    
}