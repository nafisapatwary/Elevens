import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;
    private ArrayList<Card> deck;
    private Deck currentDeck;
    private Rectangle button;
    private Rectangle otherButton;

    public DrawPanel() {
        button = new Rectangle(75, 200, 160, 26);
        otherButton = new Rectangle(275, 50, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
        currentDeck = new Deck(hand);
        deck = currentDeck.getDeck();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 250;
        int y = 100;
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            if (c.getHighlight()) {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            if (i == 2 || i == 5 || i == 8){
                y = y + c.getImage().getHeight() + 10;
                x = 250;
            }
            else{
                x = x + c.getImage().getWidth() + 10;
            }
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 77, 220);
        g.drawString("REPLACE CARDS", 277, 70);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        g.drawRect((int)otherButton.getX(), (int)otherButton.getY(), (int)otherButton.getWidth(), (int)otherButton.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                hand = Card.buildHand();
                //update/create new deck based on new hand
                deck = new Deck(hand).getDeck();
            }

            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                boolean highlighted = hand.get(i).getHighlight();
                if (box.contains(clicked) && highlighted){
                    Card replaced = hand.get(i);
                    hand.remove(i);
                    hand.add(i, currentDeck.getNewCard());
                    replaced.flipHighlight();
                    currentDeck.returnCard(replaced);
                }
                else if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}