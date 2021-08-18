package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
	


    private final int totalCards = 6;
    private final double spacing = 0.05;
    private final double height= 1.75;
    private final double suit = 0.35;
    private final double name= 0.1;

    
    private ArrayList<PlayingCard> cards = new ArrayList<PlayingCard>();
    
    
    public CardPanel() {
    	Border border = BorderFactory.createTitledBorder("Card Panel");
    	this.setBorder(border);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
    
    public void clearCards() {
        cards.clear();
        repaint();
    }
    
    public void drawNextCard(PlayingCard card) {
        cards.add(card);
        repaint();
    }

    public void drawBustCard(PlayingCard card) {
        cards.add(card);
        repaint();
    }
    


    
    @Override
    //draw card into frame
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(cards.isEmpty()) {
            return;
        }

        int Spacing = (int) ((this.getWidth() / totalCards) * spacing);
        double Cwidth = (this.getWidth() - Spacing * (totalCards + 1)) / totalCards;
        double Cheight = Cwidth * height;
        Dimension Cdimension = new Dimension((int) Cwidth, (int) Cheight);
        Point topLeftCorner = new Point(Spacing, Spacing);

        PlayingCard bustCard = cards.get(cards.size()-1);
        
        for(PlayingCard card : cards) {
            if(card == bustCard) {
     
                drawGrayCard(g, topLeftCorner, Cdimension);                
            }
            else {
            	
                drawCard(g, topLeftCorner, Cdimension);
            }
            
            drawCardValue(g, card, topLeftCorner, Cdimension);
          
            drawCardSuit(g, card, topLeftCorner, Cdimension);
            topLeftCorner.x += Cdimension.width + Spacing;                
        }
        g.dispose();
    }

    //draw card frame
    private void drawCard(Graphics g, Point topLeftCorner, Dimension Cdimension) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(topLeftCorner.x, topLeftCorner.y, Cdimension.width, Cdimension.height, 30, 30);
    }

    //draw card background
    private void drawGrayCard(Graphics g, Point topLeftCorner, Dimension Cdimension) {
        g.setColor(Color.GRAY);
        g.fillRoundRect(topLeftCorner.x, topLeftCorner.y, Cdimension.width, Cdimension.height, 30, 30);
    }

    //include the value in the card
    private void drawCardValue(Graphics g, PlayingCard card, Point topLeftCorner, Dimension Cdimension) {
        String cardName = getCardName(card);
        g.setColor(getCardColour(card));
        int space = (int) (Cdimension.width * name);
        g.setFont(new Font("default", Font.BOLD, space));
        Dimension cardNameSize = new Dimension(g.getFontMetrics().stringWidth(cardName),
                                    (int) g.getFontMetrics().getLineMetrics(cardName, g).getHeight());

        g.drawString(cardName, topLeftCorner.x + space, topLeftCorner.y + space + (int) (cardNameSize.height / 2));
        g.drawString(cardName, topLeftCorner.x + Cdimension.width - cardNameSize.width - space, topLeftCorner.y + Cdimension.height - space);
    }

    //add suit to card
    private void drawCardSuit(Graphics g, PlayingCard card, Point topLeftCorner, Dimension Cdimension) {
        BufferedImage image;
        try {
            image = ImageIO.read(getSuitUrl(card));
            int width = (int) (Cdimension.width * suit);
            int x = 1*(topLeftCorner.x + Cdimension.width / 2 - width / 2);
            int y = 1*(topLeftCorner.y + Cdimension.height / 2 - width / 2);
            g.drawImage(image, x, y, width, width, null);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private URL getSuitUrl(PlayingCard card) {

        String suit;
        switch (card.getSuit()) {
            case DIAMONDS:
                suit = "diamonds.png";
                break;

            case HEARTS:
                suit = "hearts.png";
                break;

            case CLUBS:
                suit = "clubs.png";
                break;

            default:
                suit = "spades.png";
                break;
        }
        return this.getClass().getResource("/img/" + suit);
    }

    private String getCardName(PlayingCard card) {
        switch (card.getValue()) {
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            default:
                return card.getValue().toString().substring(0, 1);
        }
    }

    private Color getCardColour(PlayingCard card) {
        switch (card.getSuit()) {
            case DIAMONDS:
            case HEARTS:
                return Color.RED;
            case CLUBS:
            case SPADES:
            default:
                return Color.BLACK;
        }
    }

}
