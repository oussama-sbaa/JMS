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
			QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			connection.start();
				
			QueueReceiver receiver ;
			TextMessage msg ;
				
			if(args.length>0)
			{
				for (int i=0;i<args.length ;i++ ) {
						receiver = session.createReceiver(queue,"The receiver ='"+args[i]+"'");
						msg = (TextMessage) receiver.receive();
						System.out.println("The receiver name: "+args[i]);
						System.out.println("The message received is : "+msg.getText());
						Thread.sleep(4000);
				}

			}
			if(args.length==0)
			{
				receiver = session.createReceiver(queue);
				msg = (TextMessage) receiver.receive();
				System.out.println("The Receiver name: unnamed");
				System.out.println("The message received is : "+msg.getText());
				Thread.sleep(Integer.parseInt("3000"));

			}

        }catch(Exception ne){
			ne.printStackTrace();
			System.exit(0);
		}
		 System.exit(0);
		 
    }  

}
