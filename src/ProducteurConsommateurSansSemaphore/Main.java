package ProducteurConsommateurSansSemaphore;

public class Main {
    public static void main(String[] args){
        System.out.println("Version sans Semaphores");
        ProducteurConsommateur prodconso= new ProducteurConsommateur();
        Producteur prod= new Producteur("un producteur",prodconso);
        Consommateur conso=new Consommateur("un consommateur",prodconso);
        conso.start();
        prod.start();
    }
}
