package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends BasicGameState{	

	Image creditsImg;
	Image backImg;
	int posX, posY;
	int creditsPos = 550;	
	
	public Credits(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		creditsImg = new Image("res/other/creditsImg.png");
		backImg = new Image("res/other/back.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{			
		g.drawImage(backImg, 100, 100);
		g.drawImage(creditsImg, 500, creditsPos);		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		posX = Mouse.getX();
		posY = Mouse.getY();
		
		//credits moving
		creditsPos--;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		if(creditsPos == -1100) creditsPos = 700;
		
		//back button pressed
		if((posX > 100 && posX < 311) && (posY > 570 && posY < 619)){
			if(Mouse.isButtonDown(0)){	
				creditsPos = 550;				
				sbg.enterState(0);				
				posY = 570;
				Mouse.setCursorPosition(posX, posY);
			}
		}
	}
	
	public int getID(){
		return 3;
	}
	
}
