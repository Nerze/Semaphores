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
        return message[index];
    }

    public void ecrire(String mes,int index){
        message[index]=mes;
    }
}
