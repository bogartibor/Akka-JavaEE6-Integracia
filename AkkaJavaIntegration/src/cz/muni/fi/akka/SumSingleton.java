/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.akka;

//import javax.ejb.Startup;
//import javax.ejb.Singleton;
import akka.actor.*;
import javax.inject.Singleton;

/**
 *
 * @author xbogar
 */

@Singleton
//@Startup
public class SumSingleton {
    
    private SumSingleton() {
    }
    
    public static SumSingleton getInstance() {
        return SumSingletonHolder.INSTANCE;
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
    
    /*public int compute(int i, int j) {
        return i+j;
    }*/
    
    private static class SumSingletonHolder {

        private static final SumSingleton INSTANCE = new SumSingleton();
    }
    
    /*public static void main(String[] args) {
         SumSingleton objekt = SumSingleton.getInstance();
         objekt.compute(1,2);
     }*/
}