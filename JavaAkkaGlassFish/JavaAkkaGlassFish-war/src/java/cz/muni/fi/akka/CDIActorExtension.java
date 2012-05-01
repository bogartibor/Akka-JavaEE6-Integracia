/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 *
 * @author $xbogar
 */
public class CDIActorExtension implements Extension{

    private ActorSystem system = null;
    private List<ActorRef> listOfActors = new ArrayList<ActorRef>(); 
    
    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat, BeanManager beanManager) {
        Set<Annotation> annotations = pat.getAnnotatedType().getAnnotations();
        
        AnnotatedType<UntypedActor> at = beanManager.createAnnotatedType(UntypedActor.class);
        //AnnotatedType<SummingActor> atSum = beanManager.createAnnotatedType(SummingActor.class);
        //AnnotatedType<LoggingActor> atLog = beanManager.createAnnotatedType(LoggingActor.class);
        //final InjectionTarget<UntypedActor> it = beanManager.createInjectionTarget(at);
        
        if (annotations.containsAll(at.getAnnotations())) {    
            system = ActorSystem.create("MySystem");
        }
        int i = 1;
        int j = 1;
        int l=1;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAnnotationPresent(at.getAnnotation(Actor.class).annotationType())) {
                if (annotation.annotationType().isAnnotationPresent(at.getAnnotation(Sum.class).annotationType())) {
                    ActorRef actorOf = system.actorOf(new Props(SummingActor.class), "SummingActor number " + i);
                    listOfActors.add(actorOf);
                    i++;
                }
                if (annotation.annotationType().isAnnotationPresent(at.getAnnotation(Log.class).annotationType())) {
                    ActorRef actorOf = system.actorOf(new Props(LoggingActor.class), "LoggingActor number " + j);
                    listOfActors.add(actorOf);
                    j++;
                }
                /*Class<?>[] declaredClasses = annotation.annotationType().getDeclaredClasses();
                for(int k=0; k<declaredClasses.length; k++){
                    if(declaredClasses[k].equals(UntypedActor.class)){
                        //Class<?> declaringClass = declaredClasses[k].getDeclaringClass();
                        //Class<? extends UntypedActor>
                        //if(declaringClass.getClass().isAssignableFrom(UntypedActor.class)){
                            Class<UntypedActor> aClass = (Class<UntypedActor>) declaredClasses[k].getDeclaringClass();
                            ActorRef actorOf = system.actorOf(new Props(aClass), "Actor number " + l);
                            listOfActors.add(actorOf);
                            l++;
                        //}
                    }
                }*/
            }
        }
        
    }
    
    public List<ActorRef> getListOfActors() {
        return listOfActors;
    }   

}
