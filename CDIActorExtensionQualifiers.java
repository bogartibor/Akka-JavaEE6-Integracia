/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 *
 * @author $xbogar
 */
public class CDIActorExtension implements Extension{

    private ActorSystem system = null;
    private static final List<ActorRef> listOfActors = new ArrayList<ActorRef>(); 
    private static int counter = 1;
       
    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
        final AnnotatedType<T> annotatedType = pat.getAnnotatedType();
        final Class<T> javaClass = annotatedType.getJavaClass();
        //final Package pkg = javaClass.getPackage();
        //Set<Annotation> annotations = annotatedType.getAnnotations();
        
        //AnnotatedType<UntypedActor> at = beanManager.createAnnotatedType(UntypedActor.class);
        //AnnotatedType<SummingActor> atSum = beanManager.createAnnotatedType(SummingActor.class);
        //AnnotatedType<LoggingActor> atLog = beanManager.createAnnotatedType(LoggingActor.class);
        //final InjectionTarget<UntypedActor> it = beanManager.createInjectionTarget(at);
        
        if(annotatedType.isAnnotationPresent(Actor.class)){
            if(system == null) {
                system = ActorSystem.create("MySystem");
            }
            
            Class<UntypedActor> actorClass = (Class<UntypedActor>) javaClass;
            Props props = new Props(actorClass);
            ActorRef actorOf = system.actorOf(props, "Actor"+ counter);
            listOfActors.add(actorOf);
            counter++;
        }
        
//        if (annotatedType.isAnnotationPresent(Actor.class) && system == null) {    
//            system = ActorSystem.create("MySystem");
//        }
//        if (javaClass.getName().equals(pkg.getName()+"."+"SummingActor")){
//            ActorRef actorOf = null;
//            if (system != null){
//                Props props = new Props(SummingActor.class);
//                actorOf = system.actorOf(props, "Actor"+ counter + "sum");
//            }
//            Logger.getGlobal().info(actorOf.toString());
//            listOfActors.add(actorOf);
//            counter++;
//        }
//        if (javaClass.getName().equals(pkg.getName()+"."+"LoggingActor")){
//            ActorRef actorOf = null;
//            if (system != null){
//                Props props = new Props(LoggingActor.class);
//                actorOf = system.actorOf(props, "Actor" + counter + "log");
//            }
//            Logger.getGlobal().info(actorOf.toString());
//            listOfActors.add(actorOf);
//            counter++;
//        }
        
        
    }
    
    public static List<ActorRef> getListOfActors() {
        return Collections.unmodifiableList(listOfActors);
    }   
    
}
