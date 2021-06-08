package MessagePubPrioLecteur;

public class Main {
    public static void main(String[] args){
        MessageBoard mes = new MessageBoard();
        Redacteur redac = new Redacteur(mes);
        Lecteur lec = new Lecteur(mes);
        lec.start();
        redac.start();
    }
}
