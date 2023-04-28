package game;


public class main {
    public static void main(String[] args){
        Game game=new Game();
        System.out.println(game.getCards().get(103));
        //déroulement d'une game :
        //Joueur choisi le nombre de joueur IA ou réél
        //le joueur choisi le nombre de tour dans la limite des stocks
        //la partie ce lance ou attend la co des joueurs
        //la partie commence
        //test
    }
}
