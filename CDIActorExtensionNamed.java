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
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.*;
import javax.inject.Named;

/**
 *
 * @author $xbogar
 */
public class CDIActorExtension implements Extension{

    private ActorSystem system = null;
    private static final List<ActorRef> listOfActors = new ArrayList<ActorRef>();
    private Map<String, Class<UntypedActor>> valueObjectSet = new HashMap<String, Class<UntypedActor>>();
    final Map<Field, Object> configuredValues = new HashMap<Field, Object>();
    private Map<String, ActorRef> mapOfActors = new HashMap<String, ActorRef>();
    private static int counter = 1;
       
    void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
        Logger.getGlobal().info("beginning the scanning process");
    }
    
    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat, BeanManager bm) {
        final AnnotatedType<T> annotatedType = pat.getAnnotatedType();
        final Class<T> javaClass = annotatedType.getJavaClass();
//        //final Package pkg = javaClass.getPackage();
//        //Set<Annotation> annotations = annotatedType.getAnnotations();
//        
//        //AnnotatedType<UntypedActor> at = beanManager.createAnnotatedType(UntypedActor.class);
//        //AnnotatedType<SummingActor> atSum = beanManager.createAnnotatedType(SummingActor.class);
//        //AnnotatedType<LoggingActor> atLog = beanManager.createAnnotatedType(LoggingActor.class);
//        //final InjectionTarget<UntypedActor> it = beanManager.createInjectionTarget(at);
//        
//        if(annotatedType.isAnnotationPresent(Actor.class)){
//            if(system == null) {
//                system = ActorSystem.create("MySystem");
//            }
//            
//            Class<UntypedActor> actorClass = (Class<UntypedActor>) javaClass;
//            Props props = new Props(actorClass);
//            ActorRef actorOf = system.actorOf(props, "Actor"+ counter);
//            listOfActors.add(actorOf);
//            counter++;
//        }
  
    if(annotatedType.isAnnotationPresent(Named.class)){
            Class<UntypedActor> uaClass = (Class<UntypedActor>) javaClass;
            Named annotation = annotatedType.getAnnotation(Named.class);
            valueObjectSet.put(annotation.value(), uaClass);
    }
}
    
     <X> void processInjectionTarget(@Observes ProcessInjectionTarget<X> pit, BeanManager bm) {
        final InjectionTarget<X> it = pit.getInjectionTarget();
        Set<InjectionPoint> injectionPoints = it.getInjectionPoints();
        AnnotatedType<X> annotatedType = pit.getAnnotatedType();
        Class<X> javaClass = annotatedType.getJavaClass();
        
        Logger.getGlobal().info(pit.getAnnotatedType().getJavaClass().toString());
//        Map<String, Object> valueObjectSet = new HashMap<String, Object>();
//        if(annotatedType.isAnnotationPresent(Named.class)){
//            Named annotation = annotatedType.getAnnotation(Named.class);
//            valueObjectSet.put(annotation.value(), javaClass);
//        }
        Logger.getGlobal().info(valueObjectSet.toString());
        if(!injectionPoints.isEmpty()){
//            Logger.getGlobal().log(Level.INFO, "Injection Points: {0}", injectionTarget.getInjectionPoints().toString());
       
            for(InjectionPoint ip : injectionPoints){
//                Logger.getGlobal().info(ip.getType().toString());
//                Logger.getGlobal().info(ip.getAnnotated().toString());
//                Logger.getGlobal().info(ip.getQualifiers().toString());
                Object objNamed = null;
                String value = null;
                for(Annotation an: ip.getQualifiers()){
                   if(an instanceof Named){
                        Named named = (Named) an;
                        value = named.value();
                        Logger.getGlobal().info(value);
                        objNamed = valueObjectSet.get(value);
                   } 
                }
                
                //Logger.getGlobal().info(objNamed.getClass().getName());
                if(objNamed != null){
                    if(system == null){
                        system = ActorSystem.create("MySystem");
                    }
                    Class<UntypedActor> uaClass = (Class<UntypedActor>) objNamed;
                    Props props = new Props(uaClass);
                    ActorRef actorOf = system.actorOf(props, "Actor" + counter);
                    Logger.getGlobal().info(actorOf.toString());
                    Logger.getGlobal().info(javaClass.getFields().toString());
                    listOfActors.add(actorOf);
//                    Field field;
//                    try {
//                        field = javaClass.getField(value);
//                        field.setAccessible(true);
//                        if (field.getType().isAssignableFrom(value.getClass() ) ) {
//                            configuredValues.put(field, actorOf);
//                        }
//                    } catch (NoSuchFieldException ex) {
//                        Logger.getLogger(CDIActorExtension.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (SecurityException ex) {
//                        Logger.getLogger(CDIActorExtension.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    
                    if (value != null){
                        mapOfActors.put(value, actorOf);
                    }
                    counter++;
                }
                
            }
        } 
            InjectionTarget<X> wrapped = new InjectionTarget<X>() {
 
            @Override
            public void inject(X instance, CreationalContext<X> ctx) {
                it.inject(instance, ctx);
                for (Map.Entry<Field, Object> configuredValue: configuredValues.entrySet()) {
                    try {
                        configuredValue.getKey().set(instance, configuredValue.getValue());
                    }
                    catch (Exception e) {
                        throw new InjectionException(e);
                    }
                }
            }
 
            @Override
            public void postConstruct(X instance) {
                it.postConstruct(instance);
            }
 
            @Override
            public void preDestroy(X instance) {
                it.dispose(instance);
            }
 
            @Override
            public void dispose(X instance) {
                it.dispose(instance);
            }
 
            @Override
            public Set<InjectionPoint> getInjectionPoints() {
                return it.getInjectionPoints();
            }
 
            @Override
            public X produce(CreationalContext<X> ctx) {
                return it.produce(ctx);
            }
             
        };
         
        pit.setInjectionTarget(wrapped);
     }
    
    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
        Logger.getGlobal().info("finished the scanning process");
    }
    public static List<ActorRef> getListOfActors() {
        return Collections.unmodifiableList(listOfActors);
    }   
    
}
