/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author xbogar
 */

public class Actor extends UntypedActor{

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            log.info("Received String message: {}", message);
        } else {
            unhandled(message);
        }
    }
}