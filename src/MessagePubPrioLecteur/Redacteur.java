package MessagePubPrioLecteur;

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
            for (int i = 0; i < 100; i++) {
                try {
                    messageBoard.info.acquire();//On bloque l'accès au Message Board
                    for (int mes = 0; mes < 5; mes++) {//On écrit dans le Message Board
                        messageBoard.ecrire("Message num " + mes + " : " + i,mes);
                    }
                    messageBoard.info.release();//On débloque l'accès au Message Board
                    Thread.sleep(500);//Délai d'attente avant de boucler
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
