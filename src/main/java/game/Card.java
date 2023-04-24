package game;

public class Card {
    private int num; // number of card
    private int point; // point that the card give

    public Card(int num, int point) {
        this.num = num;
        this.point = point;
    }

    @Override
    public String toString() {
        return "Card{" +
                "num=" + num +
                ", point=" + point +
                '}';
    }
}
