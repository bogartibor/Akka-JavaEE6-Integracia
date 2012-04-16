/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.akka;

import akka.actor.*;
import cz.muni.fi.akka.SummingActor.Work;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author xbogar
 */

public class MainClass {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {      
        /*ActorSystem system = ActorSystem.create("MySystem");
        final ActorRef logging = system.actorOf(new Props(LoggingActor.class), "logging");
        ActorRef sumActor = system.actorOf(new Props(new UntypedActorFactory() {
            @Override
            public UntypedActor create() {
                return new SummingActor(logging);
            }
        }), "summing");*/
        SumSingleton.getInstance().initSumActor().tell(new Work(new ListHolder(), 3));
    }

    
}