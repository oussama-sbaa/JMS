import javax.ejb.Stateless;	



@Stateless 
public class TraiteMessage implements ITraiteMessage {
	
	public void traitement(){ System.out.println("Le traitement du message est en cours ...");} 
	
}
