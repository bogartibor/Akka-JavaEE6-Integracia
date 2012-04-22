/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.akka;

import akka.actor.*;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
//import javax.inject.Singleton;

/**
 *
 * @author xbogar
 */

@Singleton
@Startup
public class SumSingleton {
    
    public static SumSingleton getInstance() {
        return SumSingletonHolder.INSTANCE;
    }
    
    @PostConstruct
    public void initialize(){
    
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