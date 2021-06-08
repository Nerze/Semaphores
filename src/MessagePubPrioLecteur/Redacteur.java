package MessagePubPrioLecteur;

public class Redacteur extends Thread{
    MessageBoard messageBoard;

    public Redacteur(MessageBoard messageBoard){
        this.messageBoard=messageBoard;
    }

    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                try {
                    messageBoard.info.acquire();
                    for (int mes = 0; mes < 5; mes++) {
                        messageBoard.ecrire("Message num " + mes + " : " + i,mes);
                    }
                    Thread.sleep(500);
                    messageBoard.info.release();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
