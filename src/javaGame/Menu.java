package javaGame;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image playBtn, exitBtn, creditsBtn, optionsBtn, quitQ, yesBtn, noBtn;	

	static int posX, posY, difficultyInt = 1;
	
	boolean quitQ1 = false;
	boolean ryuReady = false;
	static boolean musicOn = true, soundOn = true;	
	
	static float soundVolume = 1;
	
	static Music music;
	
	private SpriteSheet ryuReadySheet, ryuStaticSheet;
	private Animation ryuReadyAnimation, ryuStaticAnimation;
	
	static Font font1;
	static TrueTypeFont ttf1;
	
	public Menu(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{		
		font1 = new Font("Impact", Font.BOLD, 40);		
		ttf1 = new TrueTypeFont(font1, true);
		
		music = new Music("res/Sounds/main.wav");	
		
		playBtn = new Image("res/other/start.png");
		exitBtn = new Image("res/other/exit.png");
		creditsBtn = new Image("res/other/credits.png");
		optionsBtn = new Image("res/other/options.png");		
		yesBtn = new Image("res/other/yes.png");
		noBtn = new Image("res/other/no.png");		
		
		ryuReadySheet = new SpriteSheet("res/ryuAnimations/ryuReady.png", 74, 125);
		ryuReadyAnimation = new Animation(ryuReadySheet, 155);
		ryuStaticSheet = new SpriteSheet("res/ryuAnimations/ryuStatic.png", 79, 125);		
		ryuStaticAnimation = new Animation(ryuStaticSheet, 100);			
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gc.setTargetFrameRate(60);
		g.setAntiAlias(true);
		g.setLineWidth(8);
		g.drawString("X: " + posX + "\nY: " + posY, 1000, 50);		
		ttf1.drawString(100, 50, "Welcome to Ryu: The Big Adventure!");
		playBtn.draw(100, 150);
		optionsBtn.draw(100, 250);
		creditsBtn.draw(100, 350);
		exitBtn.draw(100, 450);
		
		if (!music.playing()) music.loop();
		if (!musicOn) music.stop();

		if(!ryuReady) ryuReadyAnimation.draw(620, 160, 400, 400);	
		ryuReadyAnimation.stopAt(6);
		if (ryuReadyAnimation.isStopped()) ryuReady = true;	
		
		if(ryuReady) ryuStaticAnimation.draw(620, 160, 400, 400);		
		
		if (quitQ1) {			
			ttf1.drawString(650, 565, "Are you sure?", Color.white);			
			g.drawRoundRect(498, 560, 550, 150, 20);			
			yesBtn.draw(532, 640);
			noBtn.draw(802, 640);
		}
	}	

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//Get mouse position
		posX = Mouse.getX();
		posY = Mouse.getY();	

		menu(sbg);
	}	
	
	public int getID(){
		return 0;
	}
	
	static boolean checkMousePress(int x1, int x2, int y1, int y2){
		if((posX > x1 && posX < x2) && (posY > y1 && posY < y2) && Mouse.isButtonDown(0)) return true;
		return false;
	}
	
	public void menu(StateBasedGame sbg){
		//play button pressed
		if(checkMousePress(100, 311, 519, 570))	{
			music.stop();
			sbg.enterState(1);				
		}
		
		//options button pressed
		if(checkMousePress(100, 311, 419, 469)) sbg.enterState(2);		
			
		//credits button pressed
		if(checkMousePress(100, 311, 319, 369)) sbg.enterState(3);
		
		//exit button pressed
		if(checkMousePress(100, 311, 219, 269)) quitQ1 = true;		
		
		//quit menu
		if (quitQ1){			
			//if no is pressed
			if(checkMousePress(802, 1012, 29, 80)) quitQ1 = false;			
			
			//if yes is pressed
			if(checkMousePress(532, 741, 29, 80)) System.exit(0);				
		}
	}
}
