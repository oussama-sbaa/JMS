import javax.jms.* ; 
import javax.ejb.* ; 

@MessageDriven(mappedName="jms/queueDestination",
activationConfig = { @ActivationConfigProperty(
	propertyName = "messageSelector",
	propertyValue = "recepteur = 'Oussama'")})
		public class Recepteur implements MessageListener {
	
			public void onMessage(Message msg) {
				TextMessage textMsg = null;
				try {
					if ( msg instanceof TextMessage ){
						textMsg = (TextMessage) msg;
						System.out.println ("Message recu : " + textMsg.getText( ));
					}  
					else{ 
						System.out.println ("Message pas de type texte");
					} 
				}
				catch (JMSException e) {
					e.printStackTrace( );
					
				}
		
			}
		}
