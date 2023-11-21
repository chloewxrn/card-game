import java.util.Comparator;

public class Player implements Comparator<Card>{
  // player's name 
  private String name ;
  private int score;
  private int point;
  private String result;
  
  public Player() {  
  }
  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  } 
  public int getScore() {
    return score;
  }
  public void setScore(int winnerScore) {
    score += winnerScore;
  }
  public int getPoint() {
    return point;
  }
  public void setPoint(int point) {
    this.point = point;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public String getResult() {
    return result;
  }

  @Override
  public int compare(Card crd1, Card crd2) {
    // compare the suit
    int answer = crd1.getSuit() - crd2.getSuit();
    
    // if same then compare by rank
    if(answer == 0){
      answer = crd1.getRank() - crd2.getRank();
    }
    return answer;
  }

}