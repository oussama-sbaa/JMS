import javax.jms.*;
import javax.naming.*;
import java.lang.*;

public class Emetteur{

    public static void main(String[] args) {
       
        try{
        InitialContext context = new InitialContext();
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/queueConnectionFactory");
        Queue queue = (Queue) context.lookup("jms/queueDestination");
        QueueConnection connection = connectionFactory.createQueueConnection();
        QueueSession session = connection.createQueueSession(false,Session.CLIENT_ACKNOWLEDGE);
        connection.start();
           
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
       
        msg.setText("I'm in Question Five ......");
        sender.send(msg);

           }catch(Exception ne){
            ne.printStackTrace();
            System.exit(0);
        }
         System.exit(0);

         }
          

}
