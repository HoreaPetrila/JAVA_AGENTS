import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import org.jsoup.Connection;

public class SensorAgent extends Agent {

    boolean MSens; //motion sensor state.
    boolean TSens; //thermal sensor state.
    private static final long serialVersionUID = 1L;
    @Override
    protected void setup() {
// TODO Here goes how this agent behave.
// First Ticker Behavior to simulate reading from
//Motion sensor each 2 Seconds.
        addBehaviour(new TickerBehaviour(this, 5000){
            private static final long serialVersionUID = 1L;
            protected void onTick() {
// randomly select 1 or 2.
                int i = (int) (Math.floor(Math.random() * 2) + 1);
//                try {
//                    i = RequestService.sendGetTemp();
//                    System.out.println(i);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                ACLMessage MSMsg = new ACLMessage(ACLMessage.INFORM);
                MSMsg.addReceiver(new AID("DAgent", AID.ISLOCALNAME));
                if (i==1){
                    MSens=true; //there is a thermal reading.
                    MSMsg.setContent("M1"); //true
                }
                else if (i==2){
                    MSens=false; //there is no thermal reading.
                    MSMsg.setContent("M0"); //false
               }
//                switch (i) {
//                    case 1:
//                        MSens=true; //there is motion.
//                        MSMsg.setContent("M1"); //true
//                    case 2:
//                        MSens=false; // no motion.
//                        MSMsg.setContent("M0"); //false
//                }
                send(MSMsg);
                System.out.println("Sensing Agent: Motion Sensor= " + MSens);
            }
        });
// Second Ticker Behaviour to simulate reading from
//Thermal sensor each 3 Seconds.
        addBehaviour(new TickerBehaviour(this, 3000){
            private static final long serialVersionUID = 1L;
            protected void onTick() {
// randomly select 1 or 2.
//                try {
//                    RequestService.sendGetTemp();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                int i = (int) (Math.floor(Math.random() * 2) + 1);
                ACLMessage TSMsg = new ACLMessage(ACLMessage.INFORM);
                TSMsg.addReceiver(new AID("DAgent", AID.ISLOCALNAME));
                if (i==1){
                    TSens=true; //there is a thermal reading.
                    TSMsg.setContent("T1"); //true
                }
                else if (i==2){
                    TSens=false; //there is no thermal reading.
                    TSMsg.setContent("T0"); //false
                }
                send(TSMsg);
                System.out.println("Sensing Agent: Thermal Sensor= " + TSens);

            }
        });
    }
}