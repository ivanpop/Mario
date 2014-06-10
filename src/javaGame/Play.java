package javaGame;

import java.util.Random;
import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	//bools
	boolean quit = false;
	boolean round1Bool = true;	
	boolean enableInput = false;
	boolean chickenHP = true;
	
	//ryu bools
	boolean ryuStatic = true;
	boolean ryuRight = false;
	boolean ryuLeft = false;
	boolean ryuPunch = false;
	boolean ryuLowKick = false;
	boolean ryuHadouken = false;
	boolean ryuHadoukenBall = false;
	boolean ryuShoryuken = false;
	boolean ryuTatsaku = false;
	boolean ryuHurt = false;
	
	//ryu hadouken ball
	long time, getInitialTime = 0, getInitialTime2 = 0, getInitialTime3 = 0, getInitialTime4 = 0, getInitialTime5 = 0,
			getInitialTime6 = 0, hadoukenBallStart;			
	
	//ryu sounds
	private Sound round1Snd, punchAndKickSnd, hadoukenSnd, shoryukenSnd, tatsakuSnd, hurtSnd, deadSnd, punchedSnd,
				goSnd, chickenSnd;
	
	int round1Scale = 50, ryuDead, staticDuration = 0, timer = 99999;
	
	Image worldMap, round1Image, healthBar, healthBox, mpBox, goImg, timerBcg, chicken, menuBcg, statsBcg;
	
	int[] duration = {200, 200};
	String s;
	
	//ryu pos, mp and hp
	float ryuPositionX = 0;
	float ryuPositionY = -156;
	float shiftX = 100;
	float shiftY = 110;	
	float hadoukenBallX;	
	float ryuMP = 8;
	float ryuHP = 8;
	
	//thug vars
	//thug1	
	int thug1HP = 144;
	float thug1PosX, thug1PosY;
	int moveY1 = 144;
	int moveX1 = 1600;
	boolean showThug1 = true;
	boolean thug1HitRyu = true;
	boolean thug1Dead = false;
	
	//thug2
	int thug2HP = 144;
	float thug2PosX, thug2PosY;
	int moveY2 = 500;
	int moveX2 = 2300;
	boolean showThug2 = true;	
	boolean thug2HitRyu = true;
	boolean thug2Dead = false;
	
	//thug3
	int thug3HP = 144;
	float thug3PosX, thug3PosY;
	int moveY3 = 500;
	int moveX3 = 4300;
	boolean showThug3 = true;
	boolean thug3HitRyu = true;
	boolean thug3Dead = false;
	
	//thug4
	int thug4HP = 144;
	float thug4PosX, thug4PosY;
	int moveY4 = 500;
	int moveX4 = 6300;
	boolean showThug4 = true;
	boolean thug4HitRyu = true;
	boolean thug4Dead = false;
	
	//thug5
	int thug5HP = 144;
	float thug5PosX, thug5PosY;
	int moveY5 = 500;
	int moveX5 = 7300;
	boolean showThug5 = true;
	boolean thug5HitRyu = true;
	boolean thug5Dead = false;
	
	private SpriteSheet ryuStaticSheet, ryuRightSheet, ryuLeftSheet, ryuPunchSheet, ryuLowKickSheet, 
							ryuHadoukenSheet, ryuHadoukenBallSheet, ryuShoryukenSheet, ryuTatsakuSheet,
							fireSheet, ryuHurtSheet, 
							thug1WalkSheet, thug1StaticSheet, thug1DeadSheet, thug1HurtSheet, thug1HitSheet,
							thug2WalkSheet, thug2StaticSheet, thug2DeadSheet, thug2HurtSheet, thug2HitSheet,
							thug3WalkSheet, thug3StaticSheet, thug3DeadSheet, thug3HurtSheet, thug3HitSheet,
							thug4WalkSheet, thug4StaticSheet, thug4DeadSheet, thug4HurtSheet, thug4HitSheet,
							thug5WalkSheet, thug5StaticSheet, thug5DeadSheet, thug5HurtSheet, thug5HitSheet;
	
	private Animation ryuSprite, ryuStaticAnimation, ryuRightAnimation, ryuLeftAnimation, ryuPunchAnimation, 
							ryuLowKickAnimation, ryuHadoukenAnimation, ryuHadoukenBallAnimation, ryuShoryukenAnimation,
							ryuTatsakuAnimation, fireAnimation, ryuHurtAnimation,
							thug1WalkAnimation, thug1StaticAnimation, thug1DeadAnimation, thug1HurtAnimation, thug1Sprite, thug1HitAnimation,
							thug2WalkAnimation, thug2StaticAnimation, thug2DeadAnimation, thug2HurtAnimation, thug2Sprite, thug2HitAnimation, 
							thug3WalkAnimation, thug3StaticAnimation, thug3DeadAnimation, thug3HurtAnimation, thug3Sprite, thug3HitAnimation,
							thug4WalkAnimation, thug4StaticAnimation, thug4DeadAnimation, thug4HurtAnimation, thug4Sprite, thug4HitAnimation,
							thug5WalkAnimation, thug5StaticAnimation, thug5DeadAnimation, thug5HurtAnimation, thug5Sprite, thug5HitAnimation;
	
	//font
	Font font, font1, font2;
	TrueTypeFont ttf, ttf1, ttf2;
	
	public Play(int state){		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/other/world1.png");	
		round1Image = new Image("res/other/round1.png");
		healthBar = new Image("res/other/healthBar.png");
		healthBox = new Image("res/other/health.png");
		mpBox = new Image("res/other/mp.png");
		goImg = new Image("res/other/go.png");
		timerBcg = new Image("res/other/timerBcg.png");
		chicken = new Image("res/other/chicken.png");
		statsBcg = new Image("res/other/statsBcg.png");
		
		font = new Font("Impact", Font.BOLD, 70);
		font1 = new Font("Verdana", Font.BOLD, 46);
	    ttf = new TrueTypeFont(font, true);
	    ttf1 = new TrueTypeFont(font1, true);
	    font2 = new Font("Impact", Font.BOLD, 35);
	    ttf2 = new TrueTypeFont(font2, true);
		
		//sounds
		round1Snd = new Sound("res/Sounds/round1.wav");
		punchAndKickSnd = new Sound("res/Sounds/punch.wav");	
		hadoukenSnd = new Sound("res/Sounds/hadouken.wav");
		shoryukenSnd = new Sound("res/Sounds/shoryuken.wav");
		tatsakuSnd = new Sound("res/Sounds/tatsaku.wav");
		hurtSnd = new Sound("res/Sounds/hurt.wav");
		deadSnd = new Sound("res/Sounds/dead.wav");
		punchedSnd = new Sound("res/Sounds/punched.wav");
		goSnd = new Sound("res/Sounds/go.wav");
		chickenSnd = new Sound("res/Sounds/chicken.wav");
		
		//ryu Animations
		ryuStaticSheet = new SpriteSheet("res/ryuAnimations/ryuStatic.png", 77, 98);		
		ryuStaticAnimation = new Animation(ryuStaticSheet, 100);		
		ryuLeftSheet = new SpriteSheet("res/ryuAnimations/ryuLeft.png", 75, 120);
		ryuLeftAnimation = new Animation(ryuLeftSheet, 100);
		ryuRightSheet = new SpriteSheet("res/ryuAnimations/ryuRight.png", 75, 120);
		ryuRightAnimation = new Animation(ryuRightSheet, 100);	
		ryuPunchSheet = new SpriteSheet("res/ryuAnimations/ryuPunch.png", 110, 125);
		ryuPunchAnimation = new Animation(ryuPunchSheet, 300);		
		ryuLowKickSheet = new SpriteSheet("res/ryuAnimations/ryuLowKick.png", 105, 125);
		ryuLowKickAnimation = new Animation(ryuLowKickSheet, 300);
		ryuHadoukenSheet = new SpriteSheet("res/ryuAnimations/ryuHadouken.png", 114, 90);
		ryuHadoukenAnimation = new Animation(ryuHadoukenSheet, 200);
		ryuHadoukenBallSheet = new SpriteSheet("res/ryuAnimations/HadoukenBall.png", 112, 41);
		ryuHadoukenBallAnimation = new Animation(ryuHadoukenBallSheet, 100);
		ryuShoryukenSheet = new SpriteSheet("res/ryuAnimations/ryuShoryuken.png", 105, 130);
		ryuShoryukenAnimation = new Animation(ryuShoryukenSheet, 200);
		ryuTatsakuSheet = new SpriteSheet("res/ryuAnimations/ryuTatsaku.png", 100, 111);
		ryuTatsakuAnimation = new Animation(ryuTatsakuSheet, 100);
		ryuHurtSheet = new SpriteSheet("res/ryuAnimations/ryuHurt.png", 80, 93);
		ryuHurtAnimation = new Animation(ryuHurtSheet, 500);
		
		ryuSprite = ryuStaticAnimation;
		
		//other animations
		fireSheet = new SpriteSheet("res/other/fire.png", 55, 80);
		fireAnimation = new Animation(fireSheet, 300);
		
		//enemy animations
		//thug1
		thug1WalkSheet = new SpriteSheet("res/enemies/thug1Walk.png", 53, 93);
		thug1WalkAnimation = new Animation(thug1WalkSheet, 300);
		thug1StaticSheet = new SpriteSheet("res/enemies/thug1Static.png", 63, 93);
		thug1StaticAnimation = new Animation(thug1StaticSheet, 150);
		thug1DeadSheet = new SpriteSheet("res/enemies/thug1Dead.png", 95, 93);
		thug1DeadAnimation = new Animation(thug1DeadSheet, 680);
		thug1HurtSheet = new SpriteSheet("res/enemies/thug1Hurt.png", 43, 93);
		thug1HurtAnimation = new Animation(thug1HurtSheet, 10);
		thug1HitSheet = new SpriteSheet("res/enemies/thug1Hit.png", 82, 93);
		thug1HitAnimation = new Animation(thug1HitSheet, 10);
		
		thug1Sprite = thug1StaticAnimation;
		
		//thug2
		thug2WalkSheet = new SpriteSheet("res/enemies/thug2Walk.png", 53, 93);
		thug2WalkAnimation = new Animation(thug2WalkSheet, 300);
		thug2StaticSheet = new SpriteSheet("res/enemies/thug2Static.png", 63, 93);
		thug2StaticAnimation = new Animation(thug2StaticSheet, 150);
		thug2DeadSheet = new SpriteSheet("res/enemies/thug2Dead.png", 95, 93);
		thug2DeadAnimation = new Animation(thug2DeadSheet, 680);
		thug2HurtSheet = new SpriteSheet("res/enemies/thug2Hurt.png", 43, 93);
		thug2HurtAnimation = new Animation(thug2HurtSheet, 10);
		thug2HitSheet = new SpriteSheet("res/enemies/thug2Hit.png", 82, 93);
		thug2HitAnimation = new Animation(thug2HitSheet, 10);
		
		thug2Sprite = thug2StaticAnimation;
		
		//thug3
		thug3WalkSheet = new SpriteSheet("res/enemies/thug3Walk.png", 53, 93);
		thug3WalkAnimation = new Animation(thug3WalkSheet, 300);
		thug3StaticSheet = new SpriteSheet("res/enemies/thug3Static.png", 63, 93);
		thug3StaticAnimation = new Animation(thug3StaticSheet, 150);
		thug3DeadSheet = new SpriteSheet("res/enemies/thug3Dead.png", 122, 93);
		thug3DeadAnimation = new Animation(thug3DeadSheet, 680);
		thug3HurtSheet = new SpriteSheet("res/enemies/thug3Hurt.png", 43, 93);
		thug3HurtAnimation = new Animation(thug3HurtSheet, 10);
		thug3HitSheet = new SpriteSheet("res/enemies/thug3Hit.png", 82, 93);
		thug3HitAnimation = new Animation(thug3HitSheet, 10);
		
		thug3Sprite = thug3StaticAnimation;
		
		//thug4
		thug4WalkSheet = new SpriteSheet("res/enemies/thug4Walk.png", 53, 93);
		thug4WalkAnimation = new Animation(thug4WalkSheet, 300);
		thug4StaticSheet = new SpriteSheet("res/enemies/thug4Static.png", 63, 93);
		thug4StaticAnimation = new Animation(thug4StaticSheet, 150);
		thug4DeadSheet = new SpriteSheet("res/enemies/thug4Dead.png", 95, 93);
		thug4DeadAnimation = new Animation(thug4DeadSheet, 680);
		thug4HurtSheet = new SpriteSheet("res/enemies/thug4Hurt.png", 43, 93);
		thug4HurtAnimation = new Animation(thug4HurtSheet, 10);
		thug4HitSheet = new SpriteSheet("res/enemies/thug4Hit.png", 82, 93);
		thug4HitAnimation = new Animation(thug4HitSheet, 10);
		
		thug4Sprite = thug4StaticAnimation;
		
		//thug5
		thug5WalkSheet = new SpriteSheet("res/enemies/thug5Walk.png", 53, 93);
		thug5WalkAnimation = new Animation(thug5WalkSheet, 300);
		thug5StaticSheet = new SpriteSheet("res/enemies/thug5Static.png", 63, 93);
		thug5StaticAnimation = new Animation(thug5StaticSheet, 150);
		thug5DeadSheet = new SpriteSheet("res/enemies/thug5Dead.png", 95, 93);
		thug5DeadAnimation = new Animation(thug5DeadSheet, 680);
		thug5HurtSheet = new SpriteSheet("res/enemies/thug5Hurt.png", 43, 93);
		thug5HurtAnimation = new Animation(thug5HurtSheet, 10);
		thug5HitSheet = new SpriteSheet("res/enemies/thug5Hit.png", 82, 93);
		thug5HitAnimation = new Animation(thug5HitSheet, 10);
		
		thug5Sprite = thug5StaticAnimation;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		worldMap.draw(ryuPositionX, ryuPositionY);
		
		//ryu HP and MP
		statsBcg.draw(72, 585);
		healthBar.draw(100, 650);
		healthBar.draw(100, 600);
		ttf2.drawString(10, 595, "MP:");
		ttf2.drawString(10, 645, "HP:");
		
		if (ryuHP >= 1) healthBox.draw(108, 656);
		if (ryuHP >= 2) healthBox.draw(153, 656);
		if (ryuHP >= 3) healthBox.draw(198, 656);
		if (ryuHP >= 4) healthBox.draw(243, 656);
		if (ryuHP >= 5) healthBox.draw(288, 656);
		if (ryuHP >= 6) healthBox.draw(333, 656);
		if (ryuHP >= 7) healthBox.draw(378, 656);
		if (ryuHP >= 8) healthBox.draw(423, 656);
		
		if (ryuMP >= 1) mpBox.draw(108, 606);
		if (ryuMP >= 2) mpBox.draw(153, 606);
		if (ryuMP >= 3) mpBox.draw(198, 606);
		if (ryuMP >= 4) mpBox.draw(243, 606);
		if (ryuMP >= 5) mpBox.draw(288, 606);
		if (ryuMP >= 6) mpBox.draw(333, 606);
		if (ryuMP >= 7) mpBox.draw(378, 606);
		if (ryuMP >= 8) mpBox.draw(423, 606);
		
		//draw timer
		timerBcg.draw(604, 607);
		ttf.drawString(610, 600, "" + timer/100, Color.white);		
		
		//round1 animation and statistics
		if (round1Bool) round1Image.draw(500, 100, round1Scale, round1Scale);		
		g.drawString("Time:" + time + "\nMP" + ryuMP + "\nHP" + ryuHP + "\nThug1PosX" + thug1PosX + "\nThug1PosY" + thug1PosY + "\nThug2PosY" + thug2PosY + "\nThug1HP" + thug1HP +"\nHadoukenB" + hadoukenBallX + "\nMove" + moveY1, 1100, 60);
		g.drawString("Ryu X: " + ryuPositionX + "\nRyu Y: " + ryuPositionY, 1100, 20);				
		
		//random animations
		fireAnimation.draw(ryuPositionX + 2000, ryuPositionY + 200);
		
		//ryuAnimations
		if (ryuStatic) ryuSprite.draw(shiftX, shiftY);
		if (ryuLeft) ryuLeftAnimation.draw(shiftX, shiftY);
		if (ryuRight) ryuRightAnimation.draw(shiftX, shiftY);
		if (ryuPunch) ryuPunchAnimation.draw(shiftX, shiftY);
		if (ryuLowKick) ryuLowKickAnimation.draw(shiftX, shiftY);
		if (ryuHadouken) ryuHadoukenAnimation.draw(shiftX, shiftY);
		if (ryuHadoukenBall) ryuHadoukenBallAnimation.draw(hadoukenBallX - 20, shiftY + 15);		
		if (ryuShoryuken) ryuShoryukenAnimation.draw(shiftX, shiftY - 25);
		if (ryuTatsaku) ryuTatsakuAnimation.draw(shiftX, shiftY);
		
		if (ryuHurt) ryuHurtAnimation.draw(shiftX, shiftY); 
		
		//enemy animations
		//thug1
		thug1PosX = ryuPositionX + moveX1;
		thug1PosY = ryuPositionY + moveY1;
		if(showThug1) thug1Sprite.draw(thug1PosX, thug1PosY);
		
		//thug2	
		thug2PosX = ryuPositionX + moveX2;
		thug2PosY = ryuPositionY + moveY2;
		if(showThug2) thug2Sprite.draw(thug2PosX, thug2PosY);
		
		//thug3
		thug3PosX = ryuPositionX + moveX3;
		thug3PosY = ryuPositionY + moveY3;		
		if(showThug3) thug3Sprite.draw(thug3PosX, thug3PosY);
		
		//thug4
		thug4PosX = ryuPositionX + moveX4;
		thug4PosY = ryuPositionY + moveY4;		
		if(showThug4) thug4Sprite.draw(thug4PosX, thug4PosY);
		
		//chicken
		if (chickenHP) chicken.draw(ryuPositionX + 2500, ryuPositionY + 400, 2);			
		
		if(quit == true){
			ttf1.drawString(450, 150, "Game Paused", Color.white);
			timerBcg.draw(520, 290, (float) 1.9);			
			g.drawString("Resume (R)", 550, 300);
			g.drawString("Main Menu (M)", 550, 350);
			g.drawString("Quit Game (Q)", 550, 400);
			if(quit == false) g.clear();
		}
		
		if (goSnd.playing()) goImg.draw(1000, 100);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		time = gc.getTime();		
		Input input = gc.getInput();	
		
		//round1 animation and sound
		if (round1Bool) round1Scale += 4;		
		if (round1Scale >= 100 && round1Scale <= 105) round1Snd.play(1.03f, 0.6f);		
		
		if (round1Scale >= 600 && round1Scale <= 610) {
			round1Bool = false;
			enableInput = true;
			round1Scale = 1;
		}
		
		//update timer 
		if (!quit) timer -= delta * 0.061;		
		
		removeDuplications();
			
		ryuPhysics(input, delta, sbg);		
			
		//menu
		if(input.isKeyDown(Input.KEY_ESCAPE)) quit = true;				
		
		//quit
		if(quit == true){			
			enableInput = false;			
			if(input.isKeyDown(Input.KEY_R)){
				quit = false;
				enableInput = true;
			}
			
			if(input.isKeyDown(Input.KEY_M)) sbg.enterState(0);
			if(input.isKeyDown(Input.KEY_Q)) System.exit(0);			
		}		
		
		//enemy1 interaction------------------------------------------	
		//thug1 AI 
		if (!quit){
			if (ryuPositionX < -170 && thug1HP > 0){
				if(thug1PosY < 117) {
					moveY1 += delta * .1f + 1;
					thug1Sprite = thug1WalkAnimation;
				}
				
				if(thug1PosY > 120) {
					moveY1 -= delta * .1f;
					thug1Sprite = thug1WalkAnimation;
				}		
				
				if(thug1PosX > 170) {
					moveX1 -= delta * .1f;
					thug1Sprite = thug1WalkAnimation;
				}
				
				if(thug1PosX < 150 && !ryuTatsaku) {
					moveX1 += delta * .1f + 1;
					thug1Sprite = thug1WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug1PosX, thug1PosY) && ryuAttack() && !thug1Dead){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug1Sprite = thug1HurtAnimation;
				getInitialTime2 = time;
				thug1HP--;
			}
			
			if(delay(getInitialTime2, 10) && thug1Sprite == thug1HurtAnimation) {			
				thug1Sprite = thug1StaticAnimation;			
			}		
			
			//thug dies
			if(thug1HP <= 0){				
				thug1Sprite = thug1DeadAnimation;
				thug1Dead = true;				
				if(!deadSnd.playing() && showThug1)deadSnd.play();			
				if(delay(getInitialTime2, 2000)){
					showThug1 = false;					
				}
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug1PosX, thug1PosY) && !ryuAttack() && enemyAttackChance() && showThug1 && thug1HitRyu){			
				thug1Sprite = thug1HitAnimation;			
				getInitialTime2 = time;
				if(!punchedSnd.playing()) punchedSnd.play();
				ryuHurt = true;			
				ryuHP--;		
				thug1HitRyu = false;
			}	
			
			if(delay(getInitialTime2, 10) && thug1Sprite == thug1HitAnimation) {			
				if(delay(getInitialTime2, 1000)) {
					thug1Sprite = thug1StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime2, 3000)) thug1HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug1PosX, thug1PosY)){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug1Sprite = thug1HurtAnimation;
				getInitialTime2 = time;
				thug1HP -= 11;
			}
		}
		
		//enemy2 interaction------------------------------------------
		//thug2 AI 
		if (!quit){
			if (ryuPositionX < -900 && thug2HP > 0){
				if(thug1PosY < 117) {
					moveY2 += delta * .1f + 1;
					thug2Sprite = thug2WalkAnimation;
				}
				
				if(thug2PosY > 120) {
					moveY2 -= delta * .1f;
					thug2Sprite = thug2WalkAnimation;
				}		
				
				if(thug2PosX > 170) {
					moveX2 -= delta * .1f;
					thug2Sprite = thug2WalkAnimation;
				}
				
				if(thug2PosX < 150 && !ryuTatsaku) {
					moveX2 += delta * .1f + 1;
					thug2Sprite = thug2WalkAnimation;
				}
			}	
			
			//ryuHitThug
			if(thugAtRyu(thug2PosX, thug2PosY) && ryuAttack() && !thug2Dead){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug2Sprite = thug2HurtAnimation;
				getInitialTime3 = time;
				thug2HP--;
			}
			
			if(delay(getInitialTime3, 10) && thug2Sprite == thug2HurtAnimation) {			
				thug2Sprite = thug2StaticAnimation;			
			}		
			
			//thug dies
			if(thug2HP <= 0){
				thug2Sprite = thug2DeadAnimation;
				thug2Dead = true;
				if(!deadSnd.playing() && showThug2)deadSnd.play();
				if(delay(getInitialTime3, 2000)) showThug2 = false;
			}
			
			//thug hit ryu
			if(thugAtRyu(thug2PosX, thug2PosY) && !ryuAttack() && enemyAttackChance() && showThug2 && thug2HitRyu){			
				thug2Sprite = thug2HitAnimation;
				getInitialTime3 = time;
				if(!punchedSnd.playing()) punchedSnd.play();
				ryuHurt = true;
				ryuHP--;
				thug2HitRyu = false;
			}	
			
			if(delay(getInitialTime3, 10) && thug2Sprite == thug2HitAnimation) {			
				if(delay(getInitialTime3, 1000)) {
					thug2Sprite = thug2StaticAnimation;
					ryuHurt = false;				
				}
			}
			
			if(delay(getInitialTime3, 3000)) thug2HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug2PosX, thug2PosY)){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug2Sprite = thug2HurtAnimation;
				getInitialTime3 = time;
				thug2HP -= 11;
			}
		}
		
		//enemy3 interaction------------------------------------------	
		//thug3 AI  
		if (!quit){
			if (ryuPositionX < -2500 && thug3HP > 0){
				if(thug1PosY < 117) {
					moveY3 += delta * .1f + 1;
					thug3Sprite = thug3WalkAnimation;
				}
				
				if(thug3PosY > 120) {
					moveY3 -= delta * .1f;
					thug3Sprite = thug3WalkAnimation;
				}		
				
				if(thug3PosX > 170) {
					moveX3 -= delta * .1f;
					thug3Sprite = thug3WalkAnimation;
				}
				
				if(thug3PosX < 150 && !ryuTatsaku) {
					moveX3 += delta * .1f + 1;
					thug3Sprite = thug3WalkAnimation;
				}
			}	
			
			//ryuHitThug
			if(thugAtRyu(thug3PosX, thug3PosY) && ryuAttack() && !thug3Dead){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug3Sprite = thug3HurtAnimation;
				getInitialTime4 = time;
				thug3HP--;
			}
			
			if(delay(getInitialTime4, 10) && thug3Sprite == thug3HurtAnimation) {			
				thug3Sprite = thug3StaticAnimation;			
			}		
			
			//thug dies
			if(thug3HP <= 0){
				thug3Sprite = thug3DeadAnimation;
				thug3Dead = true;
				if(!deadSnd.playing() && showThug3)deadSnd.play();
				if(delay(getInitialTime4, 2000)) showThug3 = false;
			}
			
			//thug hit ryu
			if(thugAtRyu(thug3PosX, thug3PosY) && !ryuAttack() && enemyAttackChance() && showThug3 && thug3HitRyu){			
				thug3Sprite = thug3HitAnimation;
				getInitialTime4 = time;
				if(!punchedSnd.playing()) punchedSnd.play();
				ryuHurt = true;
				ryuHP--;
				thug3HitRyu = false;
			}	
			
			if(delay(getInitialTime4, 10) && thug3Sprite == thug3HitAnimation) {			
				if(delay(getInitialTime4, 1000)) {
					thug3Sprite = thug3StaticAnimation;
					ryuHurt = false;
				}
			}
			
			if(delay(getInitialTime4, 3000)) thug3HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug3PosX, thug3PosY)){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug3Sprite = thug3HurtAnimation;
				getInitialTime4 = time;
				thug3HP -= 11;
			}
		}
		
		//enemy4 interaction------------------------------------------	
		//thug4 AI 
		if (!quit){
			if (ryuPositionX < -3600 && thug4HP > 0){
				if(thug4PosY < 117) {
					moveY4 += delta * .1f + 1;
					thug4Sprite = thug4WalkAnimation;
				}
				
				if(thug4PosY > 120) {
					moveY4 -= delta * .1f;
					thug4Sprite = thug4WalkAnimation;
				}		
				
				if(thug4PosX > 170) {
					moveX4 -= delta * .1f;
					thug4Sprite = thug4WalkAnimation;
				}
				
				if(thug4PosX < 150 && !ryuTatsaku) {
					moveX4 += delta * .1f + 1;
					thug4Sprite = thug4WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug4PosX, thug4PosY) && ryuAttack() && !thug4Dead){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug4Sprite = thug4HurtAnimation;
				getInitialTime5 = time;
				thug4HP--;
			}
			
			if(delay(getInitialTime5, 10) && thug4Sprite == thug4HurtAnimation) {			
				thug4Sprite = thug4StaticAnimation;			
			}		
			
			//thug dies
			if(thug4HP <= 0){			
				thug4Sprite = thug4DeadAnimation;
				thug4Dead = true;
				if(!deadSnd.playing() && showThug4)deadSnd.play();			
				if(delay(getInitialTime5, 2000)){
					showThug4 = false;					
				}
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug4PosX, thug4PosY) && !ryuAttack() && enemyAttackChance() && showThug4 && thug4HitRyu){			
				thug4Sprite = thug4HitAnimation;			
				getInitialTime5 = time;
				if(!punchedSnd.playing()) punchedSnd.play();
				ryuHurt = true;			
				ryuHP--;		
				thug4HitRyu = false;
			}	
			
			if(delay(getInitialTime5, 10) && thug4Sprite == thug4HitAnimation) {			
				if(delay(getInitialTime5, 1000)) {
					thug4Sprite = thug4StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime5, 3000)) thug4HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug4PosX, thug4PosY)){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug4Sprite = thug4HurtAnimation;
				getInitialTime5 = time;
				thug4HP -= 11;
			}
		}
		
		//enemy5 interaction------------------------------------------	
		//thug5 AI 
		if (!quit){
			if (ryuPositionX < -4600 && thug5HP > 0){
				if(thug5PosY < 117) {
					moveY5 += delta * .1f + 1;
					thug5Sprite = thug5WalkAnimation;
				}
				
				if(thug5PosY > 120) {
					moveY5 -= delta * .1f;
					thug5Sprite = thug5WalkAnimation;
				}		
				
				if(thug5PosX > 170) {
					moveX5 -= delta * .1f;
					thug5Sprite = thug5WalkAnimation;
				}
				
				if(thug5PosX < 150 && !ryuTatsaku) {
					moveX5 += delta * .1f + 1;
					thug5Sprite = thug5WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug5PosX, thug5PosY) && ryuAttack() && !thug5Dead){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug5Sprite = thug5HurtAnimation;
				getInitialTime6 = time;
				thug5HP--;
			}
			
			if(delay(getInitialTime6, 10) && thug5Sprite == thug5HurtAnimation) {			
				thug5Sprite = thug5StaticAnimation;			
			}		
			
			//thug dies
			if(thug5HP <= 0){			
				thug5Sprite = thug5DeadAnimation;
				thug5Dead = true;
				if(!deadSnd.playing() && showThug5)deadSnd.play();			
				if(delay(getInitialTime6, 2000)){
					showThug5 = false;					
				}
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug5PosX, thug5PosY) && !ryuAttack() && enemyAttackChance() && showThug5 && thug5HitRyu){			
				thug5Sprite = thug5HitAnimation;			
				getInitialTime6 = time;
				if(!punchedSnd.playing()) punchedSnd.play();
				ryuHurt = true;			
				ryuHP--;		
				thug4HitRyu = false;
			}	
			
			if(delay(getInitialTime6, 10) && thug5Sprite == thug5HitAnimation) {			
				if(delay(getInitialTime6, 1000)) {
					thug5Sprite = thug5StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime6, 3000)) thug5HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug5PosX, thug5PosY)){
				if(!punchedSnd.playing()) punchedSnd.play();
				thug5Sprite = thug5HurtAnimation;
				getInitialTime6 = time;
				thug5HP -= 11;
			}
		}
		
		//----------------------------------------------------------------
		
		if(!round1Bool && !quit)showGoSign(input);
	}
	//----------------------------------------------------------------
	
	public void showGoSign(Input input){
		staticDuration++; 
		
		if((staticDuration > 400) && ryuStatic){
			if(!goSnd.playing()){
				goSnd.play();
				staticDuration = 0;				
			}
		}
		
		if(input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_LEFT) || !ryuStatic){
			staticDuration = 0;
		}
	}
	
	public void ryuPhysics(Input input, int delta, StateBasedGame sbg){
		//ryu Up, Down, Left and Right animation			
		if(input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_RIGHT)){			
			if(enableInput) ryuSprite = ryuRightAnimation;
			ryuHurt = false;
		}
		
		else if(input.isKeyDown(Input.KEY_LEFT)){			
			if(enableInput) ryuSprite = ryuLeftAnimation;
			ryuHurt = false;
		} 
		
		else ryuSprite = ryuStaticAnimation;		
		
		//ryu up movement
		if(input.isKeyDown(Input.KEY_UP)){			
			if (movement())	ryuPositionY += delta * .1f + 1;
			if(ryuPositionY > -60) ryuPositionY -= delta * .1f + 1;
		}
		
		//ryu down movement
		if(input.isKeyDown(Input.KEY_DOWN)){
			if (movement()) ryuPositionY -= delta * .1f + 1;
			if(ryuPositionY < -397)	ryuPositionY += delta * .1f + 1;			
		}
	
		//ryu left movement
		if(input.isKeyDown(Input.KEY_LEFT)){
			if (movement()) ryuPositionX += delta * .1f + 3;							
			if(ryuPositionX > 0) ryuPositionX -= delta * .1f + 3;			
		}
		
		//ryu right movement
		if(input.isKeyDown(Input.KEY_RIGHT)){
			if (movement()) ryuPositionX -= delta * .1f + 3;
			if(ryuPositionX < -7715) ryuPositionX += delta * .1f + 3;					
		}	
		
		//punch
		if(input.isKeyPressed(Input.KEY_A) && enableInput){			
			getInitialTime = time;			
			punchAndKickSnd.play();					
			ryuPunch = true;			
		} 	
		
		if(delay(getInitialTime, 400)){	
			ryuPunchAnimation.restart();
			ryuPunch = false;						
		}
		
		//lowKick
		if(input.isKeyPressed(Input.KEY_S) && enableInput){
			getInitialTime = time;
			punchAndKickSnd.play();
			ryuLowKick = true;
		}
		
		if(delay(getInitialTime, 400)){
			ryuLowKickAnimation.restart();
			ryuLowKick = false;
		}
		
		//hadouken
			if(input.isKeyPressed(Input.KEY_D) && ryuMP > 2 && enableInput){
				getInitialTime = hadoukenBallStart = time;
				hadoukenSnd.play();
				ryuHadouken = true;				
				hadoukenBallX = shiftX + 100;
				ryuMP = ryuMP - 2;
			}			
			
			if(delay(getInitialTime, 2000)){
				ryuHadoukenAnimation.restart();
				ryuHadouken = ryuHadoukenBall = false;
			}			
			
			if(ryuHadouken == true && hadoukenBallStart + 950 <= time) ryuHadoukenBall = true;
			
			if(ryuHadoukenBall)	hadoukenBallX += 0.5 * delta;			
			
		//shoryuken			
			if(input.isKeyPressed(Input.KEY_F) && ryuPositionY < -95 && ryuMP > 2 && enableInput){
				getInitialTime = time;
				shoryukenSnd.play();
				ryuShoryuken = true;
				ryuMP = ryuMP - 2;
			}
			
			if(delay(getInitialTime, 600)){
				ryuShoryukenAnimation.restart();
				ryuShoryuken = false;				
			}
			
		//tatsaku
			if(input.isKeyPressed(Input.KEY_G) && ryuMP > 3 && enableInput){
				getInitialTime = time;
				tatsakuSnd.play(0.98f, 1);
				ryuTatsaku = true;
				ryuMP = ryuMP - 3;
			}
			
			if(delay(getInitialTime, 1500)){
				ryuTatsakuAnimation.restart();
				ryuTatsaku = false;
			}
			
			if (ryuTatsaku == true) {
				ryuLeft = ryuRight = ryuStatic = ryuPunch = ryuLowKick = ryuHadouken = ryuShoryuken = ryuHurt = false;
				ryuPositionX -= delta * .1f + 3;
			}
				
			//ryu MP regen
			if (ryuMP < 8 && !quit) ryuMP += 0.004;	
			
			//ryu dead
			if (ryuHP <= 0 && ryuHP > -5) ryuDead = 1;
			
			if (timer / 100 == 0) ryuDead = 1;
			
			if(ryuDead == 1){
				deadSnd.play();
				ryuHP = -6;
				ryuDead = 0;
				sbg.enterState(0);
			}
			
			//ryu hurt by fire
			if (ryuPositionX < -1843 && ryuPositionX > -1938 && ryuPositionY < -60 && ryuPositionY > - 161)	{
				ryuHurt = true;
				ryuHP = ryuHP - 0.04f;
				getInitialTime = time;
			}
			
			//ryu gets chicken
			if (ryuPositionX < -2342 && ryuPositionY < -230 && ryuPositionY > -250 && chickenHP)	{
				ryuHP = 8;				
				if (!chickenSnd.playing()) chickenSnd.play();
				chickenHP = false;
			}
	}
	
	public boolean enemyAttackChance(){		
		Random rand = new Random();			
		
		if(rand.nextInt(1000) > 900) return true;				
		else return false;		
	}
	
	public boolean hadoukenAtThug(float x, float y){
		if(hadoukenBallX < x + 50 && hadoukenBallX > x - 50 && y < 130 && y > 70) return true;
		else return false;
	}
	
	public boolean thugAtRyu(float x, float y){
		if(x < 190 && x > 115 && y < 130 && y > 70)	return true;
		else return false;
	}
	
	public boolean ryuAttack() {
		if(ryuHadouken || ryuLowKick || ryuPunch || ryuShoryuken || ryuTatsaku){			
			return true;			
		}
		else return false;
	}

	public boolean delay(long startTime, int number){
		if(startTime + number <= time) return true;			
		else return false;
	}
	
	public boolean movement(){
		if(ryuPunch == false) {
			if(ryuLowKick == false){
				if(ryuHadouken == false){
					if(ryuShoryuken == false){
						if(ryuTatsaku == false){
							if(enableInput) return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public int getID(){
		return 1;
	}
	
	public void removeDuplications(){
		if (ryuPunch) ryuLeft = ryuRight = ryuStatic = ryuLowKick = ryuHadouken = ryuShoryuken = ryuTatsaku = ryuHurt = false;
		if (ryuLowKick) ryuLeft = ryuRight = ryuStatic = ryuPunch = ryuHadouken = ryuShoryuken = ryuTatsaku = ryuHurt = false;
		if (ryuHadouken) ryuLeft = ryuRight = ryuStatic = ryuPunch = ryuLowKick = ryuShoryuken = ryuTatsaku = ryuHurt = false;
		if (ryuHurt) ryuLeft = ryuRight = ryuStatic = ryuPunch = ryuLowKick = ryuShoryuken = ryuTatsaku = ryuHadouken = false;
		

		
		if (ryuShoryuken == true) {
			ryuLeft = ryuRight = ryuStatic = ryuPunch = ryuLowKick = ryuHadouken = ryuTatsaku = ryuHurt = false;			
			ryuPositionY++;
		}
		
		if(ryuLeft == ryuRight == ryuPunch == false) {
			if(ryuLowKick == false){
				if(ryuHadouken == false){
					if(ryuShoryuken == false){
						if(ryuTatsaku == false){
							if(ryuHurt == false) ryuStatic = true;
						}
					}
				}
			}
		}
	}
		
}
