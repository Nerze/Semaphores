package ProducteurConsommateurAvecSemaphore;

public class Producteur extends Thread {
    ProducteurConsommateur semaphore;
    public  Producteur(String name, ProducteurConsommateur semaphore){
        super(name);
        this.semaphore=semaphore;
    }

    public void run(){
        while (true){
            for(int i=0;i<1000;i++) {
                try {
                    semaphore.places.acquire();
                    semaphore.mutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.deposer(i);
                semaphore.articles.release();
                semaphore.mutex.release();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
