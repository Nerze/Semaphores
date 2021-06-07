package ProducteurConsommateurAvecSemaphore;

public class Producteur extends Thread {
    ProducteurConsommateur semaphore;
    public  Producteur(String name, ProducteurConsommateur semaphore){
        super(name);
        this.semaphore=semaphore;
    }

    public void run(){
        int objet = 3;
        while (true){
            try {
                semaphore.places.acquire();
                semaphore.mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.deposer(objet);
            semaphore.mutex.release();
            semaphore.articles.release();
        }
    }
}
