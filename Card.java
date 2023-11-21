public class Card {

	private static final String[] Suit = { "c", "d", "h", "s"};
    private static final String[] Rank = { "A", "2", "3", "4",
										   "5", "6", "7", "8",
										   "9", "X","J", "Q", "K"};

	private final int suit; //contain 1, 2, 3, or 4
	private final int rank;

	
	public Card(final int suit, final int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}

	public int compareSuit(Card crd) {
		return this.getSuit() - crd.getSuit(); // if equals to 0 - same, else different
	}
	
	public String toSuitString() {
		return Suit[suit];
	}

	public String toRankString() {
		return Rank[rank];

	}

	public String toString(){
		return toSuitString() + toRankString();
	}

}
    

    


    

    

