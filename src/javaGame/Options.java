package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Options extends BasicGameState{	

	Image backImg, checkMark;
	int posX, posY;
	boolean musicOn = true;
	
	public Options(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		backImg = new Image("res/other/back.png");
		checkMark = new Image("res/other/checkmark1.png");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(backImg, 100, 100);
		
		if (musicOn) g.drawImage(checkMark, 553, 203);
		
		g.drawString("Enable music:", 400, 200);
		g.drawRect(550, 200, 20, 20);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		posX = Mouse.getX();
		posY = Mouse.getY();
		
		//back button pressed
		if((posX > 100 && posX < 311) && (posY > 570 && posY < 619)){
			if(Mouse.isButtonDown(0)){									
				sbg.enterState(0);				
				posY = 570;
				Mouse.setCursorPosition(posX, posY);
			}
		}	
		
		//music on/off
		if((posX > 550 && posX < 569) && (posY > 500 && posY < 520)){
			if(Mouse.isButtonDown(0)){									
				musicOn = !musicOn;	
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}				
			}
		}
		
	}
	
	
	public int getID(){
		return 2;
	}
	
}
