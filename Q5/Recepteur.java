import javax.jms.*;
import javax.naming.*;
import java.lang.*;

public class Recepteur{

    public static void main(String[] args) {
        try{
            InitialContext context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/queueConnectionFactory");
            Queue queue = (Queue) context.lookup("jms/queueDestination");
            QueueConnection connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false,Session.CLIENT_ACKNOWLEDGE);
            connection.start();
               
            QueueReceiver receiver = session.createReceiver(queue);
            TextMessage msg = (TextMessage) receiver.receive();
            System.out.println("Message : "+msg.getText());
            Thread.sleep(10000);
            msg.acknowledge();
           

        }catch(Exception ne){
            ne.printStackTrace();
            System.exit(0);
        }
         System.exit(0);
       
         
    }  

}
