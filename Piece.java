
public class Piece {
	public String hero;
	public String piece_type;
	public int gold;
	public int attack_points;
	
	public Piece(String hero,String piece_type,int gold,int attack_points) {
		this.hero=hero;
		this.piece_type=piece_type;
		this.gold=gold;
		this.attack_points=attack_points;
	}

	public String getHero() {return hero;}
	public void setHero(String hero) {this.hero = hero;}
	
	public String getPiece_type() {return piece_type;}
	public void setPiece_type(String piece_type) {this.piece_type = piece_type;}
	
	public int getGold() {return gold;}
	public void setGold(int gold) {this.gold = gold;}
	
	public int getAttack_points() {return attack_points;}
	public void setAttack_points(int attack_points) {this.attack_points = attack_points;}
}
