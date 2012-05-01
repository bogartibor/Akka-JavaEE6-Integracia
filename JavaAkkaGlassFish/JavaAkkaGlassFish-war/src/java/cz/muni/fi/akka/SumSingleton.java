/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.akka;

import akka.actor.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
//import javax.inject.Singleton;

/**
 *
 * @author xbogar
 */

@Singleton
@Startup
@Sum
public class SumSingleton{
    
    private List<ActorRef> listOfActors = new ArrayList<ActorRef>(); 
        
    public static SumSingleton getInstance() {
        return SumSingletonHolder.INSTANCE;
    }

    public List<ActorRef> getListOfActors() {
        return listOfActors;
    }

    public void setListOfActors(List<ActorRef> listOfActors) {
        this.listOfActors = listOfActors;
    }
    
    @PostConstruct
    public void initialize(){
        /*ActorSystem system = ActorSystem.create("MySystem");
        int i = 1;
        //SummingActor summingActor;
        for(UntypedActor actor : actors){
            ActorRef sumActor = system.actorOf(new Props(SummingActor.class),"sum: "+actor.toString());
            listOfActors.add(sumActor);
            i++;
        }*/
    }
    
    public ActorRef initSumActor() {
        ActorSystem system = ActorSystem.create("MySystem");
        final ActorRef logging = system.actorOf(new Props(LoggingActor.class), "logging");
        ActorRef sumActor = system.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public UntypedActor create() {
                return new SummingActor(logging);
            }
        }), "summing"); 
        return sumActor;
     }

    
    private static class SumSingletonHolder {

        private static final SumSingleton INSTANCE = new SumSingleton();
    }
}