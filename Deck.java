import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Deck {

    private Card[] deckOfCards;
    private List<Card> cardList = new ArrayList<Card>(); 

    public Deck() {
        getPackOfCards(); //52 cards
    }

    public List<Card> getCards(){
        return cardList;
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public void getPackOfCards() {
       
      deckOfCards = new Card[52];
      int i = 0;
      for (int j = 0; j < 4; j++){ //suit
          for (int k = 0; k < 13; k++){  //rank
            deckOfCards[i++] = new Card(j,k);
          }
            
      }
        cardList = Arrays.asList(deckOfCards);
    }
    

}

