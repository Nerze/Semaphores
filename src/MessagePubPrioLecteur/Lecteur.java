package MessagePubPrioLecteur;

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
                messageBoard.semNbL.acquire();//On bloque l'accès au nombre de lecteurs
                messageBoard.nbL++;//Un lecteur de plus
                if(messageBoard.nbL==1){//Si on est le premier lecteur
                    messageBoard.info.acquire();//On bloque l'accès au Message Board
                }
                messageBoard.semNbL.release();//On débloque l'accès au nombre de lecteurs
                for (int mes = 0; mes < 5; mes++) {//On lit le messageBoard
                    System.out.println(messageBoard.lire(mes));
                }
                messageBoard.semNbL.acquire();//On bloque l'accès au nombre de lecteurs
                messageBoard.nbL--;//Un lecteur de moins
                if(messageBoard.nbL==0){//S'il n'y a plus de lecteurs
                    messageBoard.info.release();//On débloque l'accès au Message Board
                }
                messageBoard.semNbL.release();//On débloque l'accès au nombre de lecteurs
                Thread.sleep(1000);//Délai d'attente avant de boucler
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}
