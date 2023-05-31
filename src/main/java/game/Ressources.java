package game;

import java.util.concurrent.Semaphore;

public class Ressources {
    private Semaphore mutex = new Semaphore(1);
    private Game game;
    //la sema s'applique pas sur le get et les élément qui en découle
    //Du coup faut trouver un moyen de stocker les ressources de game tout en pouvant utiliser ces méthodes

    public Game getGame() {


            return game;

    }

    public void setGame(Game game) {
        try {
            this.mutex.acquire();
            this.game = game;
            this.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*private boolean[] state = {false,false};
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
    }*/


}
