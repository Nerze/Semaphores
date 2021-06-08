package ProducteurConsommateurSansSemaphore;

public class Consommateur extends Thread {

    ProducteurConsommateur producteurConsommateur;

    public  Consommateur(String name, ProducteurConsommateur producteurConsommateur){
        super(name);
        this.producteurConsommateur = producteurConsommateur;
    }

    public void run(){
        int obj;
        while(true)
            try {
                obj = producteurConsommateur.extraire();
                System.out.println(obj);
                Thread.sleep(100);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }
}
