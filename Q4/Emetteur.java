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
		QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		connection.start();
			
        QueueSender sender = session.createSender(queue);
        TextMessage msg = session.createTextMessage();
		
		for(int i=0; i<10; i++){
				if(args.length >0 && i<args.length){
					msg = session.createTextMessage();
					msg.setText("The message from sender with receiver"); 
					msg.setStringProperty("receiver",args[i]);
					sender.send(msg);
				}else
				{
					msg = session.createTextMessage();
					msg.setText("The message from sender without receiver"); 
					sender.send(msg);
				}
			}
           }catch(Exception ne){
			ne.printStackTrace();
			System.exit(0);
		}
		 System.exit(0);
		 }
		   

}
