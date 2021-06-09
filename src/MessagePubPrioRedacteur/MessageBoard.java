package MessagePubPrioRedacteur;

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
     * Nombre de lecteurs actifs
     */
    int lecteur;
    /**
     * Nombre de lecteurs en attente
     */
    int demandeLecteur;
    /**
     * Nombre de rédacteurs actifs
     */
    int redacteur;
    /**
     * Nombre de rédacteurs en attente
     */
    int demandeRedacteur;
    /**
     * Blocage de l'accès à message
     */
    Semaphore mutex;
    /**
     * Blocage de l'accès à lecteur et demandeLecteur
     */
    Semaphore semLec;
    /**
     * Blocage de l'accès à redacteur et demandeRedacteur
     */
    Semaphore semRed;

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
