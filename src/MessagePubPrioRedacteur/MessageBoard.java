package MessagePubPrioRedacteur;

import java.util.concurrent.Semaphore;

public class MessageBoard {
    String[] message;
    final int nbMessage=5;
    int lecteur,demandeLecteur,redacteur,demandeRedacteur;
    Semaphore mutex,semLec,semRed;

    MessageBoard(){
        message=new String[nbMessage];
        mutex=new Semaphore(1);
        semLec=new Semaphore(0);
        semRed=new Semaphore(0);
        lecteur=0;
        demandeLecteur=0;
        redacteur=0;
        demandeRedacteur=0;
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
