package app;

import org.apache.activemq.broker.BrokerService;

public class MessageBroker_old {

    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        System.out.println("BROCKER START");
        // configure the broker
        broker.addConnector("tcp://localhost:61616");
        broker.start();
    }
}