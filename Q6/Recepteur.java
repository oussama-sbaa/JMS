package appv4;

import javax.naming.*;
import javax.jms.*;

public class Recepteur {

    public static void main(String[] args) throws Exception {

        InitialContext messaging = new InitialContext();
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) messaging.lookup("jms/DemoConnectionFactory");
        Queue queue = (Queue) messaging.lookup("jms/DemoQueue");
        QueueConnection connection = queueConnectionFactory.createQueueConnection();
        QueueSession session = connection.createQueueSession(false,Session.CLIENT_ACKNOWLEDGE);

        try {

            connection.start();


            QueueReceiver receiver;
            if(args.length > 1) receiver = session.createReceiver(queue,"destinataire='"+args[1]+"'");
            else receiver = session.createReceiver(queue);

            while(true){
                TextMessage message = (TextMessage) receiver.receive();

                int sleepTime = Integer.parseInt(args[0]);
                String receiverName ;
                int index = 1;

                if(index >= args.length ) receiverName = "Unknown";
                else receiverName = args[index];
                 

                System.out.println("Recepteur : "+receiverName);                                               
                System.out.println("Message Reçu: " + message.getText());

                Thread.sleep(sleepTime);
            }
            


        }catch(Exception e ){
            e.printStackTrace();
        }finally {
            connection.close();
            Runtime.getRuntime().exit(0);
        }
        
   
        

    }
}
