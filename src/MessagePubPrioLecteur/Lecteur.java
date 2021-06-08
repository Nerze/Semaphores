package MessagePubPrioLecteur;

public class Lecteur extends Thread{
    MessageBoard messageBoard;


    public Lecteur(MessageBoard messageBoard){
        this.messageBoard=messageBoard;
    }

    public void run(){
        while(true){
            try {
                messageBoard.semNbL.acquire();
                messageBoard.nbL++;
                if(messageBoard.nbL==1){
                    messageBoard.info.acquire();
                }
                messageBoard.semNbL.release();
                for (int mes = 0; mes < 5; mes++) {
                    System.out.println(messageBoard.lire(mes));

                }
                messageBoard.semNbL.acquire();
                messageBoard.nbL--;
                if(messageBoard.nbL==0){
                    messageBoard.info.release();
                }
                messageBoard.semNbL.release();
                Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}
