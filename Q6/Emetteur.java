import javax.naming.*;
import javax.jms.*;


public class Emetteur {

    public static void main(String[] args) throws Exception {

        InitialContext messaging = new InitialContext();
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) messaging.lookup("jms/DemoConnectionFactory");
        Queue queue = (Queue) messaging.lookup("jms/DemoQueue");
        QueueConnection connection = queueConnectionFactory.createQueueConnection();
        QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);

        try  {

            connection.start();

            QueueSender sender = session.createSender(queue);

            TextMessage msg = session.createTextMessage();

            int cpt=1;
            String receiver;

            int index = 0;
            if(index >= args.length) receiver = "All";
            else receiver = args[0];

            for (cpt=1;cpt<6;cpt++){
                msg.setText("Hello"+ " - "+ cpt+" fois.");
                msg.setStringProperty("destinataire",receiver);
                sender.send(msg);
                System.out.println("Message envoye : "+msg.getText() );
                
            }
        

        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            connection.close();
            Runtime.getRuntime().exit(0);
        }
        

       


        



    }
}
