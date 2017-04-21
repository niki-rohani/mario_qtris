package ressource.mdls;

public class Player {

	String name;
	int serial;
	int victory;
	int score;
	int highscore;
	
	public Player() {
		name = "guest";
		serial = 1;
		victory = 0;
		score = 0;
		highscore = 0;
	}
	
	public Player(String name, int serial, int victory, int score, int hightscore) {
		this.name = " ";
		this.name.concat(name);
		this.serial = serial;
		victory = 0;
		score = 0;
	}
	
	public void loadPlayer (String[] player) {
		this.name = player[0];
		this.serial = Integer.parseInt(player[1]);
		this.victory = Integer.parseInt(player[2]);
		this.score = Integer.parseInt(player[3]);
	}
	
	public void score(int s) {
		
		if (s>3)
			score = score + s * 4 ;
		else
			score = score + s ;
	}
	
	public int getScore(){
		return score;
	}

	public Player copy() {
		return new Player (this.name,this.serial,this.victory, this.score, highscore);
	}
}
