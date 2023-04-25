package game;

import java.util.concurrent.Semaphore;

public class Ressources {
    private boolean[] state = {false,false};
    private Semaphore mutex = new Semaphore(1);

    public boolean getState(int id) {
        try {
            this.mutex.acquire();
            if(this.state[id]) {
                this.mutex.release();
                return true ;
            }
            this.mutex.release();
            return false ;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false ;
        }
    }

    public void setState(int id) {
        try {
            this.mutex.acquire();
            this.state[id] = true ;
            this.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
