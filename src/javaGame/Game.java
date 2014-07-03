package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	
	public static final String gameName = "Ryu: The Big Adventure!";
	public static final int menu = 0; //1st state
	public static final int play = 1; //2nd state
	public static final int options = 2; //3rd state
	public static final int credits = 3; //4th state
	
	public Game(String gameName){
		super(gameName);
		this.addState(new Menu(menu)); //1st state
		this.addState(new Play(play)); //2nd state
		this.addState(new Options(options)); //3rd state
		this.addState(new Credits(credits)); //4th state
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}

	public static void main(String[] args) {		
		AppGameContainer appGC;
		try{
			appGC = new AppGameContainer(new Game(gameName));
			appGC.setDisplayMode(1280, 720, false);
			appGC.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

}
