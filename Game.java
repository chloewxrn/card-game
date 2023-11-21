import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Game {
    public List<Player> players = new ArrayList<>();
    private Deck cards = new Deck();
    private Map<Player, Queue<Card>> playerCardsMap = new LinkedHashMap<>();
    private int numOfPlayers;

    public Game() {}
    public Game(String name) {
        players.add(new Player(name));
    }
    public void addPlayer(String name) {
        players.add(new Player(name));
    }
    public int getNumOfPlayers() {
        return numOfPlayers;
    }
    public void distributeCardsToPlayers() {
        cards.shuffle();
        if (players.size() == 3) {
            int k = 0;
            int c = 0;
            for (Player player : players) {
                Queue<Card> test = new LinkedList<>();
                int maxCards = 18 + k - c;
                for (int i = k; i < maxCards; i++) {
                    test.add(cards.getCards().get(i));
                    if (c == 0)
                        c++;
                }
                k = maxCards;
                playerCardsMap.put(player, test);
            }
        }
        else {

            int k = 0;
            for (Player player : players) {
                Queue<Card> test = new LinkedList<>();
                int maxCards = 26 + k;
                for (int i = k; i < maxCards; i++) {
                    test.add(cards.getCards().get(i));
                }
                k = maxCards;
                playerCardsMap.put(player, test);
            }
           
        }
    }
    
    public void winnerOf3PlPhase() {
        Player lostPlayer = players.get(0);
        for (Player player:players) 
            lostPlayer = lostPlayer.getScore() < player.getScore() ? lostPlayer : player;
        players.remove(lostPlayer);
        playerCardsMap.remove(lostPlayer);
        System.out.println ("***** " + players.get(0).getName() + " and "
                            + players.get(1).getName() + " proceed to 2-Player phase *****");
    }
    public void winnerOf2PlPhase() {
        if (players.get(0).getScore() > players.get(1).getScore())
            players.remove(players.get(1));
        else
            players.remove(players.get(0));
        System.out.println ("***** " + players.get(0).getName() + " is the WINNER! *****");
    }

    
    public void availableCardsList() {
        //int numberOfCards = playerCardsMap.get(player).size();
        //if (cardsList == null)
        //    cardsList = playerCardsMap.get(player.getName());
        System.out.println ("\nAvailable Cards: ");
        for (Map.Entry<Player, Queue<Card>> pl: playerCardsMap.entrySet()) {
            
            System.out.print (pl.getKey().getName() + ":");
            List<Card> valueList = new ArrayList<>(pl.getValue());
            for (int i = 0; i < valueList.size(); i++) {
                System.out.print(" " + valueList.get(i));
                if ((i+1) % 5 == 0)
                    System.out.print(",");
            }
            System.out.println();
        }
    }
   
    // sorted card, "|Point=" and "|Win"
    public void sortedCardsAtHand_getWinner() {
        String name;
        int points;
        int max = 0;
        List<Integer> pointList = new ArrayList<>();
        List<Card> cardList = new ArrayList<Card>();
        List<String> nameList = new ArrayList<>();
        
        for (Map.Entry<Player, Queue<Card>> pl: playerCardsMap.entrySet()) {
            name = pl.getKey().getName();
            nameList.add(name);
        
            List<Card> cardInHand = new ArrayList<Card>();

            for (int i = 0; i < 5; i++) {
                // add to List<Card> and remove from Queue<Card>
                cardInHand.add(pl.getValue().remove());
            }

            Collections.sort(cardInHand, new Player()); //Sort by comparator
            
            int maxPointOfEachPlayer = compareCard(cardInHand); // get the max point of each player

            pl.getKey().setPoint(maxPointOfEachPlayer); // then set it to point

            points = pl.getKey().getPoint(); // get the point setted just now
            
            pointList.add(points); // add player's point into list

            max = Collections.max(pointList); // get the max point among player
                        
            for(int i = 0; i < cardInHand.size(); i++) {
                cardList.add(cardInHand.get(i));
            }
   
        } 

        // display sorted card, "|Point=" and "|Win"
        for (int i = 0; i < players.size(); i++){
            System.out.print(nameList.get(i) + ":");
            for (int j = 0; j < 5; j++){
                System.out.print(" " + cardList.get(j)); 
            }
            for(int m = 0; m <5; m++)
                cardList.remove(0);
            
            if(pointList.get(i) == max) {
                System.out.print(" | Points = " + max + " | Win");
                players.get(i).setScore(max); // set max score for the player
            }
            else
                System.out.print(" | Points = " + pointList.get(i) );
            System.out.println();
        }
    }

    // cummulative points of each player at the end of each round
    public void displayScores() {
        System.out.println ("Score: ");
        for (int i = 0; i < players.size(); i++) {
            System.out.println (players.get(i).getName() + " = " + players.get(i).getScore());
        }
    }

    // compare the cards of each player and sum them up
    public int compareCard(List<Card> cardInHand) {
        List<Integer> cardPoint = new ArrayList<>();
        int sum = 0;

        for(int i=1; i < cardInHand.size(); i++) {
            Card currentCard = cardInHand.get(i);
            Card previousCard = cardInHand.get(i-1);

            if(currentCard.compareSuit(previousCard) == 0) { // if two cards are same suit
                if(sum == 0) { // if this type of suit card never added before
                    if(previousCard.getRank() >= 10) { // if the previous card is J, Q or K
                        sum += 10;
                    }
                    else { // the previous card is A to 10
                        sum += previousCard.getRank()+1;
                    }
                }

                if(currentCard.getRank() >= 10) { // if the current card is J, Q or K
                    sum += 10;
                }
                else { // the current card is A to 10
                    sum += currentCard.getRank()+1;
                }

            }
            else {
                cardPoint.add(sum); // add the sum from previous same suit cards to list
                sum = 0; // clear the sum for reuse later

                // for the previous different suit card
                if(previousCard.getRank() >= 10) { // if the previous card is J, Q or K
                    sum += 10;
                }
                else { // the previous card is A to 10
                    sum += previousCard.getRank()+1;
                }
                cardPoint.add(sum); // add the first different suit card to list
                sum = 0; // clear the sum for reuse later

                // for the current different suit card
                if(currentCard.getRank() >= 10) { // if the current card is J, Q or K
                    sum += 10;
                }
                else { // the current card is A to 10
                    sum += currentCard.getRank()+1;
                }
                cardPoint.add(sum); // add the single suit card to list
                sum = 0; // clear the sum for reuse later
            }

            if(i == cardInHand.size()-1) {
                cardPoint.add(sum);
            }
        }

        // get the value from the suit that gives the highest point
        int highestPoint = Collections.max(cardPoint);

        cardPoint.clear(); // clear the list for the next player

        return highestPoint;

    }


}
        


