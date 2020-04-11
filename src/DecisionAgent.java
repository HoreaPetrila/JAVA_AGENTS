import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
public class DecisionAgent extends Agent {

    private static final long serialVersionUID = 1L;
    boolean MSens; //motion sensor state.
    boolean TSens; //thermal sensor state.
    boolean DState=false; //decision state.
    @Override
    protected void setup() {
// TODO Here goes how this agent behave.
// cyclic behavior to receive sensors state from Sensing Agent.
        addBehaviour(new CyclicBehaviour() {
            private static final long serialVersionUID = 1L;
            @Override
            public void action() {
// TODO Auto-generated method stub
                ACLMessage RMsg = receive(); //receive data.
                if(RMsg != null){ //check message.
//process message content.
                    if (RMsg.getContent().equals("M1")){
                        MSens=true;
                    }
                    else if (RMsg.getContent().equals("M0")){
                        MSens=false;
                    }
                    else if (RMsg.getContent().equals("T1")){
                        TSens=true;
                    }
                    else if (RMsg.getContent().equals("T0")){
                        TSens=false;
                    }
                }
//if both readings are positive.
                //and the last action is to "turn off".
                if(MSens && TSens && !DState){
                    ACLMessage AMsg = new ACLMessage(ACLMessage.INFORM);
                    AMsg.addReceiver(new AID("AAgent", AID.ISLOCALNAME));
                    DState=true; //change it to "turn on".
                    AMsg.setContent("TurnOn"); // turn on the CS.
                    System.out.println("Decision Agent: Turn ON Heater");
                    send(AMsg); //send the message.
                }
// any other sensor states that may change decision to "turn off".
                else if((!MSens || !TSens) && DState){ //and if last action is "turn on".
                    ACLMessage AMsg = new ACLMessage(ACLMessage.INFORM);
                    AMsg.addReceiver(new AID("AAgent", AID.ISLOCALNAME));
                    DState=false; //change it to "turn off".
                    AMsg.setContent("TurnOff"); // turn off the CS.
                    System.out.println("Decision Agent: Turn OFF Heater");
                    send(AMsg); //send the message.
                }
            }
        });
    }
}