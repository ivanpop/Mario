package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends BasicGameState{	

	Image creditsImg;
	Image backImg;	
	int creditsPos = 550;	
	
	public Credits(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		creditsImg = new Image("res/other/creditsImg.png");
		backImg = new Image("res/other/back.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{		
		backImg.draw(100, 100);
		creditsImg.draw(500, creditsPos);		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		Menu.posX = Mouse.getX();
		Menu.posY = Mouse.getY();
		
		//credits moving
		creditsPos--;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		if(creditsPos == -1100) creditsPos = 700;
		
		//back button pressed
		if(Menu.checkMousePress(100, 311, 570, 619)){			
			creditsPos = 550;				
			sbg.enterState(0);				
		}
	}
	
	public int getID(){
		return 3;
	}
	
}
