package MessagePubPrioLecteur;

import java.util.concurrent.Semaphore;

public class MessageBoard {
    String[] message;
    final int nbMessage=5;
    Semaphore info;
    Semaphore semNbL;
    int nbL;

    MessageBoard(){
        message=new String[nbMessage];
        info=new Semaphore(1);
        semNbL=new Semaphore(1);
        nbL=0;
    }

    public String lire(int index){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message[index];

    }

    public void ecrire(String mes,int index){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        message[index]=mes;
    }
}
