package ProducteurConsommateurAvecSemaphore;

import java.util.concurrent.Semaphore;

public class ProducteurConsommateur {
    int[] tampon;
    final int MAX=100;
    Semaphore mutex; //Accès au tampon
    Semaphore places; //Nombre de places disponibles dans le tampon
    Semaphore articles; //Nombre d'articles dans le tampon
    public ProducteurConsommateur(){
        super();
        mutex=new Semaphore(1);
        places=new Semaphore(MAX);
        articles=new Semaphore(0);
        tampon=new int[MAX];
    }

    void deposer(int obj){
        System.out.println("Dépot de : "+obj+" Taille du tampon : "+articles.availablePermits());
        tampon[articles.availablePermits()]=obj;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int extraire(){
        int ret=tampon[0];
        for(int i=0;i< articles.availablePermits()-1;i++){//Décalage des données en mémoire
            tampon[i]=tampon[i+1];
        }
        System.out.println("Retrait de "+ret+" Taille du tampon : "+articles.availablePermits());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
