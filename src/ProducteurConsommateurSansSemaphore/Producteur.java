package ProducteurConsommateurSansSemaphore;

public class Producteur extends Thread {
    ProducteurConsommateur producteurConsommateur;

    public Producteur(String name, ProducteurConsommateur producteurConsommateur) {
        super(name);
        this.producteurConsommateur = producteurConsommateur;
    }

    public void run() {
        while(true) {
            for(int i=0;i<1000;i++){
                producteurConsommateur.deposer(i);
            }
        }
    }
}
