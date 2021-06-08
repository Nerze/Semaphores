package MessagePubPrioRedacteur;

public class Lecteur extends Thread{
    MessageBoard messageBoard;


    public Lecteur(MessageBoard messageBoard){
        this.messageBoard=messageBoard;
    }

    public void run(){
        while(true){
            try {
                messageBoard.mutex.acquire();
                if(messageBoard.redacteur>0||messageBoard.demandeRedacteur>0){
                    messageBoard.demandeLecteur++;
                    messageBoard.mutex.release();
                    messageBoard.semLec.acquire();
                    messageBoard.mutex.acquire();
                    messageBoard.demandeLecteur--;
                }
                messageBoard.lecteur++;
                messageBoard.mutex.release();
                for(int i=0;i<5;i++){
                    System.out.println(messageBoard.lire(i));
                }
                messageBoard.mutex.acquire();
                messageBoard.lecteur--;
                if(messageBoard.lecteur==0 && messageBoard.demandeRedacteur>0){
                    messageBoard.semRed.release();
                }
                messageBoard.mutex.release();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
