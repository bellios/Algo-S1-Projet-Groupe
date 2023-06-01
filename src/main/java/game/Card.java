package game;

public class Card {
    private int num; // number of card
    private int point; // point that the card give
    private String imagePath;

    public Card(int num, int point, String imagePath) {
        this.num = num;
        this.point = point;
        this.imagePath = imagePath;
    }

    public int getPoint() {
        return point;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return num +"("+point+')';
    }

}
