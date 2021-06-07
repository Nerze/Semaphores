package ProducteurConsommateurAvecSemaphore;

public class Main {
    public static void main(String[] args){
        ProducteurConsommateur prodconso= new ProducteurConsommateur();
        Producteur prod= new Producteur("un producteur",prodconso);
        Consommateur conso=new Consommateur("un consommateur",prodconso);
        conso.start();
        prod.start();
    }
}
