package ProducteurConsommateurSansSemaphore;

import java.util.concurrent.Semaphore;

public class ProducteurConsommateur {
    int[] tampon;
    final int MAX=100;
    int nombreArticleTampon;
    public ProducteurConsommateur(){
        super();
        tampon=new int[MAX];
        nombreArticleTampon = 0;
    }

    void deposer(int obj){
        //System.out.println("Dépot de : "+obj+" Taille du tampon : "+nombreArticleTampon);
        tampon[nombreArticleTampon]=obj;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nombreArticleTampon ++;
    }

    int extraire() throws Exception {
        if (nombreArticleTampon <= 0)
            throw new Exception("Pas d'article");
        int ret=tampon[0];
        for(int i=0;i< nombreArticleTampon-1;i++){//Décalage des données en mémoire
            tampon[i]=tampon[i+1];
        }
        //System.out.println("Retrait de "+ret+" Taille du tampon : "+nombreArticleTampon);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nombreArticleTampon --;
        return ret;
    }
}
