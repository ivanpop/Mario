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
		g.drawString("X: " + Menu.posX + "\nY: " + Menu.posY + "\nMusic:" + Menu.musicOn + "\nMusic volume: " + 
				Menu.music.getVolume() + "\nSounds: " + Menu.soundOn + "\nSound volume: " + Menu.soundVolume, 1000, 50);	
		
		if (Menu.musicOn) checkMark.draw(703, 103, (float) 2.2);
		if (Menu.soundOn) checkMark.draw(703, 303, (float) 2.2);
		Menu.ttf1.drawString(400, 100, "Enable music:");
		Menu.ttf1.drawString(400, 200, "Music volume:");
		Menu.ttf1.drawString(400, 300, "Enable sounds:");
		Menu.ttf1.drawString(400, 400, "Sounds volume:");
		g.drawRect(700, 100, 40, 40);
		g.drawRect(700, 300, 40, 40);
		g.drawRect(700, 200, 410, 40);
		g.drawRect(700, 400, 410, 40);
		
		if (Menu.music.getVolume() >= 0.1) g.fillRoundRect(710, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.2) g.fillRoundRect(750, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.3) g.fillRoundRect(790, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.4) g.fillRoundRect(830, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.5) g.fillRoundRect(870, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.6) g.fillRoundRect(910, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.65) g.fillRoundRect(950, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.8) g.fillRoundRect(990, 208, 30, 24, 5);
		if (Menu.music.getVolume() >= 0.85) g.fillRoundRect(1030, 208, 30, 24, 5);
		if (Menu.music.getVolume() == 1) g.fillRoundRect(1070, 208, 30, 24, 5);
		
		if (Menu.soundVolume >= 0.1) g.fillRoundRect(710, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.2) g.fillRoundRect(750, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.3) g.fillRoundRect(790, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.4) g.fillRoundRect(830, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.5) g.fillRoundRect(870, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.6) g.fillRoundRect(910, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.65) g.fillRoundRect(950, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.8) g.fillRoundRect(990, 408, 30, 24, 5);
		if (Menu.soundVolume >= 0.85) g.fillRoundRect(1030, 408, 30, 24, 5);
		if (Menu.soundVolume == 1) g.fillRoundRect(1070, 408, 30, 24, 5);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Menu.posX = Mouse.getX();
		Menu.posY = Mouse.getY();		
		
		musicSwitch();
		musicVolumeBar();
		soundSwitch();
		soundVolumeBar();
		
		//back button pressed				
		if(Menu.checkMousePress(100, 311, 570, 619)) sbg.enterState(0);
	}	
	
	public int getID(){
		return 2;
	}
	
	public void musicSwitch(){
		if(Menu.checkMousePress(704, 735, 585, 615)){
			if(Menu.musicOn) {
				Menu.music.stop();
				Menu.musicOn = false;
			}
			else {
				Menu.music.loop(1, Menu.music.getVolume());
				Menu.musicOn = true;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
		}
	}
	
	public void soundSwitch(){
		if(Menu.checkMousePress(704, 735, 385, 415)){
			if(Menu.soundOn) {				
				Menu.soundOn = false;
			}
			else {				
				Menu.soundOn = true;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
		}
	}
	
	public void musicVolumeBar(){
		if(Menu.checkMousePress(710, 740, 488, 511)) Menu.music.setVolume((float) 0.1);
		if(Menu.checkMousePress(750, 780, 488, 511)) Menu.music.setVolume((float) 0.2);
		if(Menu.checkMousePress(790, 820, 488, 511)) Menu.music.setVolume((float) 0.3);
		if(Menu.checkMousePress(830, 860, 488, 511)) Menu.music.setVolume((float) 0.4);
		if(Menu.checkMousePress(870, 900, 488, 511)) Menu.music.setVolume((float) 0.5);
		if(Menu.checkMousePress(910, 940, 488, 511)) Menu.music.setVolume((float) 0.6);
		if(Menu.checkMousePress(950, 980, 488, 511)) Menu.music.setVolume((float) 0.7);
		if(Menu.checkMousePress(990, 1020, 488, 511)) Menu.music.setVolume((float) 0.8);
		if(Menu.checkMousePress(1030, 1060, 488, 511)) Menu.music.setVolume((float) 0.9);
		if(Menu.checkMousePress(1070, 1100, 488, 511)) Menu.music.setVolume(1);		
	}

	public void soundVolumeBar(){
		if(Menu.checkMousePress(710, 740, 288, 311)) Menu.soundVolume = ((float) 0.1);
		if(Menu.checkMousePress(750, 780, 288, 311)) Menu.soundVolume = ((float) 0.2);
		if(Menu.checkMousePress(790, 820, 288, 311)) Menu.soundVolume = ((float) 0.3);
		if(Menu.checkMousePress(830, 860, 288, 311)) Menu.soundVolume = ((float) 0.4);
		if(Menu.checkMousePress(870, 900, 288, 311)) Menu.soundVolume = ((float) 0.5);
		if(Menu.checkMousePress(910, 940, 288, 311)) Menu.soundVolume = ((float) 0.6);
		if(Menu.checkMousePress(950, 980, 288, 311)) Menu.soundVolume = ((float) 0.7);
		if(Menu.checkMousePress(990, 1020, 288, 311)) Menu.soundVolume = ((float) 0.8);
		if(Menu.checkMousePress(1030, 1060, 288, 311)) Menu.soundVolume = ((float) 0.9);
		if(Menu.checkMousePress(1070, 1100, 288, 311)) Menu.soundVolume = (1);	
	}
}
