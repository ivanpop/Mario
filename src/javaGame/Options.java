package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Options extends BasicGameState{	

	Image backImg, checkMark, powerBar;
			
	
	public Options(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		backImg = new Image("res/other/back.png");
		checkMark = new Image("res/other/checkmark1.png");		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{		
		backImg.draw(100, 100);		
		g.drawString("X: " + Menu.posX + "\nY: " + Menu.posY + " " + Menu.musicOn + "\nMusic volume " + Menu.music.getVolume(), 1000, 50);	
		if (Menu.musicOn) checkMark.draw(703, 203, (float) 2.2);		
		Menu.ttf1.drawString(400, 200, "Enable music:");
		Menu.ttf1.drawString(400, 300, "Music volume:");
		g.drawRect(700, 200, 40, 40);
		g.drawRect(700, 300, 410, 40);		
		
		if (Menu.music.getVolume() >= 0.1) g.fillRoundRect(710, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.2) g.fillRoundRect(750, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.3) g.fillRoundRect(790, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.4) g.fillRoundRect(830, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.5) g.fillRoundRect(870, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.6) g.fillRoundRect(910, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.65) g.fillRoundRect(950, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.8) g.fillRoundRect(990, 308, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.85) g.fillRoundRect(1030, 308, 30, 24, 5);
		if (Menu.music.getVolume() == 1) g.fillRoundRect(1070, 308, 30, 24, 5);		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Menu.posX = Mouse.getX();
		Menu.posY = Mouse.getY();		
		
		musicVolumeBar();
		musicSwitch();
		
		//back button pressed				
		if(Menu.checkMousePress(100, 311, 570, 619)) sbg.enterState(0);
	}	
	
	public int getID(){
		return 2;
	}
	
	public void musicSwitch(){		
		if(Menu.checkMousePress(704, 735, 485, 515)){
			if(Menu.musicOn) {
				Menu.music.stop();
				Menu.musicOn = false;
			}
			else {
				Menu.music.loop();
				Menu.musicOn = true;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
		}
	}
	
	public void musicVolumeBar(){
		if(Menu.checkMousePress(710, 740, 388, 411)) Menu.music.setVolume((float) 0.1);
		if(Menu.checkMousePress(750, 780, 388, 411)) Menu.music.setVolume((float) 0.2);
		if(Menu.checkMousePress(790, 820, 388, 411)) Menu.music.setVolume((float) 0.3);
		if(Menu.checkMousePress(830, 860, 388, 411)) Menu.music.setVolume((float) 0.4);
		if(Menu.checkMousePress(870, 900, 388, 411)) Menu.music.setVolume((float) 0.5);
		if(Menu.checkMousePress(910, 940, 388, 411)) Menu.music.setVolume((float) 0.6);
		if(Menu.checkMousePress(950, 980, 388, 411)) Menu.music.setVolume((float) 0.7);
		if(Menu.checkMousePress(990, 1020, 388, 411)) Menu.music.setVolume((float) 0.8);
		if(Menu.checkMousePress(1030, 1060, 388, 411)) Menu.music.setVolume((float) 0.9);
		if(Menu.checkMousePress(1070, 1100, 388, 411)) Menu.music.setVolume(1);		
	}
}
