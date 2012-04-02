/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
/**
 *
 * @author xbogar
 */

public class JavaAkkaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef myActor = system.actorOf(new Props(Actor.class), "myactor");
    }
}
