package MessagePubPrioRedacteur;

public class Lecteur extends Thread{
    /**
     * Le Message Board
     */
    MessageBoard messageBoard;


    public Lecteur(MessageBoard messageBoard){
        this.messageBoard=messageBoard;
    }

    public void run(){
        while(true){
            try {
                messageBoard.mutex.acquire();//On prend l'accès sur les variables
                if(messageBoard.redacteur>0||messageBoard.demandeRedacteur>0){
                    messageBoard.demandeLecteur++;//Un lecteur de plus en attente
                    messageBoard.mutex.release();//Libération des variables
                    messageBoard.semLec.acquire();//Bloquer le lecteur
                    messageBoard.mutex.acquire();//Reprend l'accès sur les variables
                    messageBoard.demandeLecteur--;//Un lecteur de moins en attente
                }
                messageBoard.lecteur++;//Il y a un lecteur actif de plus
                messageBoard.mutex.release();//Libère les variables
                for(int i=0;i<5;i++){//Lit le messageBoard
                    System.out.println(messageBoard.lire(i));
                }
                messageBoard.mutex.acquire();//Il a fini de lire, il rebloque les variables
                messageBoard.lecteur--;//Un lecteur actif de moins
                if(messageBoard.lecteur==0 && messageBoard.demandeRedacteur>0){//S'il n'y a plus de lecteur actif, et qu'au moins un rédacteur en attente
                    messageBoard.semRed.release();//On libère un rédacteur
                }
                messageBoard.mutex.release();//On débloque les variable
                Thread.sleep(1000);//Délai d'attente avant de boucler
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
