package ProducteurConsommateurSansSemaphore;

import java.util.concurrent.Semaphore;

public class ProducteurConsommateur {
    int[] tampon;
    final int MAX=10;
    int nombreArticleTampon;
    public ProducteurConsommateur(){
        super();
        tampon=new int[MAX];
        nombreArticleTampon = 0;
    }

    void deposer(int obj){
        tampon[nombreArticleTampon]=obj;
        nombreArticleTampon ++;
    }

    int extraire() throws Exception {
        if (nombreArticleTampon <= 0)
            throw new Exception("Pas d'article");
        int ret=tampon[0];
        for(int i=0;i< nombreArticleTampon-1;i++){//Décalage des données en mémoire
            tampon[i]=tampon[i+1];
        }
        nombreArticleTampon --;
        return ret;
    }
}
