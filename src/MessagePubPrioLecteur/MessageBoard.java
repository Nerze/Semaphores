package MessagePubPrioLecteur;

import java.util.concurrent.Semaphore;

public class MessageBoard {
    /**
     * Les messages du Message Board
     */
    String[] message;
    /**
     * Nombre de messages
     */
    final int nbMessage=5;
    /**
     * Blocage de l'accès à message
     */
    Semaphore info;
    /**
     * Blocage de l'accès au nombre de lecteurs
     */
    Semaphore semNbL;
    /**
     * Nombre de lecteurs actifs
     */
    int nbL;

    MessageBoard(){
        message=new String[nbMessage];
        info=new Semaphore(1);
        semNbL=new Semaphore(1);
        nbL=0;
    }

    public String lire(int index){
        try {
            Thread.sleep(100);//Délai artificiel de lecture
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message[index];

    }

    public void ecrire(String mes,int index){
        try {
            Thread.sleep(100);//Délai artificiel d'écriture
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        message[index]=mes;
    }
}
