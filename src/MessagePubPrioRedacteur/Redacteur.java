package MessagePubPrioRedacteur;

public class Redacteur extends Thread{
    /**
     * Le Message Board
     */
    MessageBoard messageBoard;

    public Redacteur(MessageBoard messageBoard){
        this.messageBoard=messageBoard;
    }

    public void run() {
        while (true) {
            try {
                for(int i=0;i<1000;i++) {
                    messageBoard.mutex.acquire();//Bloque les variables
                    if (messageBoard.lecteur > 0 || messageBoard.redacteur > 0 ) {//Si il y a un lecteur ou un rédacteur actif
                        messageBoard.demandeRedacteur++;//Il y a un rédacteur en attente de plus
                        messageBoard.mutex.release();//Libération des variables
                        messageBoard.semRed.acquire();//On bloque le rédacteur
                        messageBoard.mutex.acquire();//Il reprend la main sur les variables
                        messageBoard.demandeRedacteur--;//Un rédacteur en attente de moins
                    }
                    messageBoard.redacteur++;//Il y a un rédacteur de plus
                    messageBoard.mutex.release();//Il libère les variables
                    for (int mes = 0; mes < 5; mes++) {//Il écrit sur le messageboard
                        messageBoard.ecrire("Message num " + mes + " : " + i, mes);
                    }
                    messageBoard.mutex.acquire();//Il a fini d'écrire, il bloque les variables
                    messageBoard.redacteur--;//Un rédacteur actif en moins
                    if(messageBoard.demandeRedacteur>0){//S'il y a au moins au moins un rédacteur en attente
                        messageBoard.semRed.release();//Il libère un rédacteur
                    }
                    else if(messageBoard.demandeLecteur>0){//Sinon s'il y a au moins un lecteur en attente
                        for(int nb=0;nb< messageBoard.demandeLecteur;nb++){//Il libère tous les lecteurs
                            messageBoard.semLec.release();
                        }
                    }
                    messageBoard.mutex.release();//Libère les variables
                    Thread.sleep(500);//Délai d'attente avant de boucler
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
