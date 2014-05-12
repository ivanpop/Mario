package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	
	public static final String gameName = "Ryu: The Big Adventure!";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int options = 2;
	public static final int credits = 3;
	
	public Game(String gameName){
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Options(options));
		this.addState(new Credits(credits));
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
