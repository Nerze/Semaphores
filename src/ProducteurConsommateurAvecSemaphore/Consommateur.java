package ProducteurConsommateurAvecSemaphore;

public class Consommateur extends Thread {

    ProducteurConsommateur semaphore;

    public  Consommateur(String name, ProducteurConsommateur semaphore){
        super(name);
        this.semaphore=semaphore;
    }

    public void run(){
        int obj;
        while(true){
            try {
                semaphore.articles.acquire();
                semaphore.mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj = semaphore.extraire();
            System.out.println(obj);
            semaphore.places.release();
            semaphore.mutex.release();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
