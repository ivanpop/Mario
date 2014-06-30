package javaGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image playBtn, exitBtn, creditsBtn, optionsBtn, quitQ,
			yesBtn, noBtn;	

	int posX, posY;
	boolean quitQ1 = false;
	boolean marioReady = false;
	
	private Music music;
	private SpriteSheet ryuReadySheet, ryuStaticSheet;
	private Animation ryuReadyAnimation, ryuStaticAnimation;	
	
	public Menu(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{		
		music = new Music("res/Sounds/main.wav");	
		
		playBtn = new Image("res/other/start.png");
		exitBtn = new Image("res/other/exit.png");
		creditsBtn = new Image("res/other/credits.png");
		optionsBtn = new Image("res/other/options.png");
		quitQ = new Image("res/other/quitQ.png");
		yesBtn = new Image("res/other/yes.png");
		noBtn = new Image("res/other/no.png");		
		
		ryuReadySheet = new SpriteSheet("res/ryuAnimations/ryuReady.png", 74, 125);
		ryuReadyAnimation = new Animation(ryuReadySheet, 155);
		ryuStaticSheet = new SpriteSheet("res/ryuAnimations/ryuStatic.png", 79, 125);		
		ryuStaticAnimation = new Animation(ryuStaticSheet, 100);			
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gc.setTargetFrameRate(60);
		g.drawString("X: " + posX + "\nY: " + posY, 1000, 50);
		g.drawString("Welcome to Ryu: The Big Adventure!", 100, 50);		
		playBtn.draw(100, 100);
		optionsBtn.draw(100, 200);
		creditsBtn.draw(100, 300);
		exitBtn.draw(100, 400);
		if (!music.playing()) music.loop();	

		if(!marioReady) ryuReadyAnimation.draw(555, 100, 400, 400);	
		ryuReadyAnimation.stopAt(6);
		if (ryuReadyAnimation.isStopped()){
			marioReady = true;
		}	
		
		if(marioReady) ryuStaticAnimation.draw(555, 100, 400, 400);		
		
		if (quitQ1) {
			quitQ.draw(330, 520);
			yesBtn.draw(430, 600);
			noBtn.draw(700, 600);
		}
	}	

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//Get mouse position
		posX = Mouse.getX();
		posY = Mouse.getY();	

		//play button pressed
		if((posX > 100 && posX < 311) && (posY > 570 && posY < 619)){
			if(Mouse.isButtonDown(0)){
				music.stop();
				sbg.enterState(1);				
			}
		}
				
		//exit button pressed
		if((posX > 100 && posX < 311) && (posY > 269 && posY < 318)){
			if(Mouse.isButtonDown(0)){
				quitQ1 = true;				
			}
		}
		
		//credits button pressed
		if((posX > 100 && posX < 311) && (posY > 370 && posY < 420)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(3);
			}
		}
		
		//options button pressed
		if((posX > 100 && posX < 311) && (posY > 470 && posY < 520)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(2);
			}
		}
		
		//quit menu
		if (quitQ1){
			
			//if no is pressed
			if((posX > 700 && posX < 908) && (posY > 70 && posY < 120)){
				if(Mouse.isButtonDown(0)){
					quitQ1 = false;
				}
			}
			
			//if yes is pressed
			if((posX > 430 && posX < 639) && (posY > 70 && posY < 120)){
				if(Mouse.isButtonDown(0)){
					System.exit(0);					
				}
			}
		}
		
	}	
	
	public int getID(){
		return 0;
	}
	
}
