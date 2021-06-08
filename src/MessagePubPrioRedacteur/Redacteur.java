package MessagePubPrioRedacteur;

public class Redacteur extends Thread{
    MessageBoard messageBoard;

    public Redacteur(MessageBoard messageBoard){
        this.messageBoard=messageBoard;
    }

    public void run() {
        while (true) {
            try {
                for(int i=0;i<1000;i++) {
                    messageBoard.mutex.acquire();
                    if (messageBoard.lecteur > 0 || messageBoard.redacteur > 0 || messageBoard.demandeRedacteur > 0) {
                        messageBoard.demandeRedacteur++;
                        messageBoard.mutex.release();
                        messageBoard.semRed.acquire();
                        messageBoard.mutex.acquire();
                        messageBoard.demandeRedacteur--;
                    }
                    messageBoard.redacteur++;
                    messageBoard.mutex.release();
                    for (int mes = 0; mes < 5; mes++) {
                        messageBoard.ecrire("Message num " + mes + " : " + i, mes);
                    }
                    messageBoard.mutex.acquire();
                    messageBoard.redacteur--;
                    if(messageBoard.demandeRedacteur>0){
                        messageBoard.semRed.release();
                    }
                    else if(messageBoard.demandeLecteur>0){
                        for(int nb=0;nb< messageBoard.demandeLecteur;nb++){
                            messageBoard.semLec.release();
                        }
                    }
                    messageBoard.mutex.release();
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
