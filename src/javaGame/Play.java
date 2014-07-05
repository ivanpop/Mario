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
	boolean winState = false;	
	
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
			getInitialTime6 = 0, getInitialTime7 = 0, getInitialTime8 = 0, getInitialTime9 = 0, getInitialTime10 = 0,
					getInitialTime11 = 0, hadoukenBallStart;			
	
	//ryu sounds
	private Sound round1Snd, youWinSnd, youLoseSnd, punchAndKickSnd, hadoukenSnd, shoryukenSnd, tatsakuSnd, hurtSnd, deadSnd, punchedSnd,
				goSnd, chickenSnd;
	
	int round1Scale = 50, youWinScale1 = 50, youWinScale2 = 50, ryuDead, staticDuration = 0, timer = 9999;
	
	Image worldMap, round1Image, youWinImage, healthBar, healthBox, mpBox, goImg, timerBcg, chicken, menuBcg, statsBcg;
	
	int[] duration = {200, 200};	
	
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
	int moveX5 = 6800;
	boolean showThug5 = true;
	boolean thug5HitRyu = true;
	boolean thug5Dead = false;
	
	//thug6
	int thug6HP = 144;
	float thug6PosX, thug6PosY;
	int moveY6 = 500;
	int moveX6 = 7300;
	boolean showThug6 = true;
	boolean thug6HitRyu = true;
	boolean thug6Dead = false;
	
	//thug7
	int thug7HP = 144;
	float thug7PosX, thug7PosY;
	int moveY7 = 500;
	int moveX7 = 7800;
	boolean showThug7 = true;
	boolean thug7HitRyu = true;
	boolean thug7Dead = false;
	
	//thug8
	int thug8HP = 144;
	float thug8PosX, thug8PosY;
	int moveY8 = 500;
	int moveX8 = 8200;
	boolean showThug8 = true;
	boolean thug8HitRyu = true;
	boolean thug8Dead = false;
	
	//thug9
	int thug9HP = 144;
	float thug9PosX, thug9PosY;
	int moveY9 = 500;
	int moveX9 = 8500;
	boolean showThug9 = true;
	boolean thug9HitRyu = true;
	boolean thug9Dead = false;
	
	//thug10
	int thug10HP = 288;
	float thug10PosX, thug10PosY;
	int moveY10 = 500;
	int moveX10 = 9000;
	boolean showThug10 = true;
	boolean thug10HitRyu = true;
	boolean thug10Dead = false;
	
	private SpriteSheet ryuStaticSheet, ryuRightSheet, ryuLeftSheet, ryuPunchSheet, ryuLowKickSheet, 
							ryuHadoukenSheet, ryuHadoukenBallSheet, ryuShoryukenSheet, ryuTatsakuSheet,
							fireSheet, ryuHurtSheet, ryuWin1Sheet, ryuWin2Sheet, ryuReadySheet, 
							thug1WalkSheet, thug1StaticSheet, thug1DeadSheet, thug1HurtSheet, thug1HitSheet,
							thug2WalkSheet, thug2StaticSheet, thug2DeadSheet, thug2HurtSheet, thug2HitSheet,
							thug3WalkSheet, thug3StaticSheet, thug3DeadSheet, thug3HurtSheet, thug3HitSheet,
							thug4WalkSheet, thug4StaticSheet, thug4DeadSheet, thug4HurtSheet, thug4HitSheet,
							thug5WalkSheet, thug5StaticSheet, thug5DeadSheet, thug5HurtSheet, thug5HitSheet,
							thug6WalkSheet, thug6StaticSheet, thug6DeadSheet, thug6HurtSheet, thug6HitSheet,
							thug7WalkSheet, thug7StaticSheet, thug7DeadSheet, thug7HurtSheet, thug7HitSheet,
							thug8WalkSheet, thug8StaticSheet, thug8DeadSheet, thug8HurtSheet, thug8HitSheet,
							thug9WalkSheet, thug9StaticSheet, thug9DeadSheet, thug9HurtSheet, thug9HitSheet,
							thug10WalkSheet, thug10StaticSheet, thug10DeadSheet, thug10HurtSheet, thug10HitSheet;
	
	private Animation ryuSprite, ryuStaticAnimation, ryuRightAnimation, ryuLeftAnimation, ryuPunchAnimation, 
							ryuLowKickAnimation, ryuHadoukenAnimation, ryuHadoukenBallAnimation, ryuShoryukenAnimation,
							ryuTatsakuAnimation, fireAnimation, ryuHurtAnimation, ryuWin1Animation, ryuWin2Animation, ryuReadyAnimation,
							thug1WalkAnimation, thug1StaticAnimation, thug1DeadAnimation, thug1HurtAnimation, thug1Sprite, thug1HitAnimation,
							thug2WalkAnimation, thug2StaticAnimation, thug2DeadAnimation, thug2HurtAnimation, thug2Sprite, thug2HitAnimation, 
							thug3WalkAnimation, thug3StaticAnimation, thug3DeadAnimation, thug3HurtAnimation, thug3Sprite, thug3HitAnimation,
							thug4WalkAnimation, thug4StaticAnimation, thug4DeadAnimation, thug4HurtAnimation, thug4Sprite, thug4HitAnimation,
							thug5WalkAnimation, thug5StaticAnimation, thug5DeadAnimation, thug5HurtAnimation, thug5Sprite, thug5HitAnimation,
							thug6WalkAnimation, thug6StaticAnimation, thug6DeadAnimation, thug6HurtAnimation, thug6Sprite, thug6HitAnimation,
							thug7WalkAnimation, thug7StaticAnimation, thug7DeadAnimation, thug7HurtAnimation, thug7Sprite, thug7HitAnimation,
							thug8WalkAnimation, thug8StaticAnimation, thug8DeadAnimation, thug8HurtAnimation, thug8Sprite, thug8HitAnimation,
							thug9WalkAnimation, thug9StaticAnimation, thug9DeadAnimation, thug9HurtAnimation, thug9Sprite, thug9HitAnimation,
							thug10WalkAnimation, thug10StaticAnimation, thug10DeadAnimation, thug10HurtAnimation, thug10Sprite, thug10HitAnimation;
	
	//font
	Font font, font1, font2;
	TrueTypeFont ttf, ttf1, ttf2;
	
	public Play(int state){		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/other/world1.png");	
		round1Image = new Image("res/other/round1.png");
		youWinImage = new Image("res/other/youWin.png");
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
		youWinSnd = new Sound("res/Sounds/youWin.wav");
		youLoseSnd = new Sound("res/Sounds/youLose.wav");		
		
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
		ryuWin1Sheet = new SpriteSheet("res/ryuAnimations/ryuWin1.png", 70, 123);
		ryuWin1Animation = new Animation(ryuWin1Sheet, 250);
		ryuWin2Sheet = new SpriteSheet("res/ryuAnimations/ryuWin2.png", 70, 100);
		ryuWin2Animation = new Animation(ryuWin2Sheet, 100);
		ryuReadySheet = new SpriteSheet("res/ryuAnimations/ryuReady.png", 74, 125);
		ryuReadyAnimation = new Animation(ryuReadySheet, 200);
		
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
		thug2DeadSheet = new SpriteSheet("res/enemies/thug2Dead.png", 97, 93);
		thug2DeadAnimation = new Animation(thug2DeadSheet, 680);
		thug2HurtSheet = new SpriteSheet("res/enemies/thug2Hurt.png", 43, 93);
		thug2HurtAnimation = new Animation(thug2HurtSheet, 10);
		thug2HitSheet = new SpriteSheet("res/enemies/thug2Hit.png", 82, 93);
		thug2HitAnimation = new Animation(thug2HitSheet, 10);
		
		thug2Sprite = thug2StaticAnimation;
		
		//thug3
		thug3WalkSheet = new SpriteSheet("res/enemies/thug3Walk.png", 53, 93);
		thug3WalkAnimation = new Animation(thug3WalkSheet, 200);
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
		thug4DeadSheet = new SpriteSheet("res/enemies/thug4Dead.png", 101, 93);
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
		
		//thug6
		thug6WalkSheet = new SpriteSheet("res/enemies/thug6Walk.png", 53, 93);
		thug6WalkAnimation = new Animation(thug6WalkSheet, 300);
		thug6StaticSheet = new SpriteSheet("res/enemies/thug6Static.png", 63, 93);
		thug6StaticAnimation = new Animation(thug6StaticSheet, 150);
		thug6DeadSheet = new SpriteSheet("res/enemies/thug6Dead.png", 116, 93);
		thug6DeadAnimation = new Animation(thug6DeadSheet, 680);
		thug6HurtSheet = new SpriteSheet("res/enemies/thug6Hurt.png", 43, 93);
		thug6HurtAnimation = new Animation(thug6HurtSheet, 10);
		thug6HitSheet = new SpriteSheet("res/enemies/thug6Hit.png", 82, 93);
		thug6HitAnimation = new Animation(thug6HitSheet, 10);
		
		thug6Sprite = thug6StaticAnimation;
		
		//thug7
		thug7WalkSheet = new SpriteSheet("res/enemies/thug7Walk.png", 53, 93);
		thug7WalkAnimation = new Animation(thug7WalkSheet, 300);
		thug7StaticSheet = new SpriteSheet("res/enemies/thug7Static.png", 63, 93);
		thug7StaticAnimation = new Animation(thug7StaticSheet, 150);
		thug7DeadSheet = new SpriteSheet("res/enemies/thug7Dead.png", 122, 93);
		thug7DeadAnimation = new Animation(thug7DeadSheet, 680);
		thug7HurtSheet = new SpriteSheet("res/enemies/thug7Hurt.png", 43, 93);
		thug7HurtAnimation = new Animation(thug7HurtSheet, 10);
		thug7HitSheet = new SpriteSheet("res/enemies/thug7Hit.png", 82, 93);
		thug7HitAnimation = new Animation(thug7HitSheet, 10);
		
		thug7Sprite = thug7StaticAnimation;
		
		//thug8
		thug8WalkSheet = new SpriteSheet("res/enemies/thug8Walk.png", 53, 93);
		thug8WalkAnimation = new Animation(thug8WalkSheet, 300);
		thug8StaticSheet = new SpriteSheet("res/enemies/thug8Static.png", 35, 93);
		thug8StaticAnimation = new Animation(thug8StaticSheet, 150);
		thug8DeadSheet = new SpriteSheet("res/enemies/thug8Dead.png", 107, 93);
		thug8DeadAnimation = new Animation(thug8DeadSheet, 680);
		thug8HurtSheet = new SpriteSheet("res/enemies/thug8Hurt.png", 43, 93);
		thug8HurtAnimation = new Animation(thug8HurtSheet, 10);
		thug8HitSheet = new SpriteSheet("res/enemies/thug8Hit.png", 82, 93);
		thug8HitAnimation = new Animation(thug8HitSheet, 10);
		
		thug8Sprite = thug8StaticAnimation;
		
		//thug9
		thug9WalkSheet = new SpriteSheet("res/enemies/thug9Walk.png", 53, 93);
		thug9WalkAnimation = new Animation(thug9WalkSheet, 300);
		thug9StaticSheet = new SpriteSheet("res/enemies/thug9Static.png", 63, 93);
		thug9StaticAnimation = new Animation(thug9StaticSheet, 150);
		thug9DeadSheet = new SpriteSheet("res/enemies/thug9Dead.png", 100, 93);
		thug9DeadAnimation = new Animation(thug9DeadSheet, 680);
		thug9HurtSheet = new SpriteSheet("res/enemies/thug9Hurt.png", 43, 93);
		thug9HurtAnimation = new Animation(thug9HurtSheet, 10);
		thug9HitSheet = new SpriteSheet("res/enemies/thug9Hit.png", 82, 93);
		thug9HitAnimation = new Animation(thug9HitSheet, 10);
		
		thug9Sprite = thug9StaticAnimation;
		
		//thug10
		thug10WalkSheet = new SpriteSheet("res/enemies/thug10Walk.png", 90, 140);
		thug10WalkAnimation = new Animation(thug10WalkSheet, 300);
		thug10StaticSheet = new SpriteSheet("res/enemies/thug10Static.png", 95, 150);
		thug10StaticAnimation = new Animation(thug10StaticSheet, 150);
		thug10DeadSheet = new SpriteSheet("res/enemies/thug10Dead.png", 164, 140);
		thug10DeadAnimation = new Animation(thug10DeadSheet, 680);
		thug10HurtSheet = new SpriteSheet("res/enemies/thug10Hurt.png", 65, 140);
		thug10HurtAnimation = new Animation(thug10HurtSheet, 10);
		thug10HitSheet = new SpriteSheet("res/enemies/thug10Hit.png", 123, 140);
		thug10HitAnimation = new Animation(thug10HitSheet, 10);
		
		thug10Sprite = thug10StaticAnimation;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		worldMap.draw(ryuPositionX, ryuPositionY);
		g.setAntiAlias(true);
		
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
		
		//youWin animation
		if (winState) youWinImage.draw(500, 100, youWinScale1, youWinScale1);	
		
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
		
		//thug5
		thug5PosX = ryuPositionX + moveX5;
		thug5PosY = ryuPositionY + moveY5;		
		if(showThug5) thug5Sprite.draw(thug5PosX, thug5PosY);
		
		//thug6
		thug6PosX = ryuPositionX + moveX6;
		thug6PosY = ryuPositionY + moveY6;		
		if(showThug6) thug6Sprite.draw(thug6PosX, thug6PosY);
		
		//thug7
		thug7PosX = ryuPositionX + moveX7;
		thug7PosY = ryuPositionY + moveY7;		
		if(showThug7) thug7Sprite.draw(thug7PosX, thug7PosY);
		
		//thug8
		thug8PosX = ryuPositionX + moveX8;
		thug8PosY = ryuPositionY + moveY8;		
		if(showThug8) thug8Sprite.draw(thug8PosX, thug8PosY);
		
		//thug9
		thug9PosX = ryuPositionX + moveX9;
		thug9PosY = ryuPositionY + moveY9;		
		if(showThug9) thug9Sprite.draw(thug9PosX, thug9PosY);
		
		//thug10
		thug10PosX = ryuPositionX + moveX10;
		thug10PosY = ryuPositionY + moveY10;		
		if(showThug10) thug10Sprite.draw(thug10PosX, thug10PosY);
		
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
		
		round1Animation();
		menu(input, sbg);
		removeDuplications();
		drawThugs(delta);
		ryuPhysics(input, delta, sbg);	
		youWinAnimation(sbg);

		//update timer 
		if (!quit && enableInput) timer -= delta * 0.061;		
		
		//end game
		if (thug1HP < 0 && thug2HP < 0 && thug3HP < 0 && thug4HP < 0 && thug5HP < 0 && thug6HP < 0 && thug7HP < 0 && thug8HP < 0 && thug9HP < 0 && thug10HP < 0 ) winState = true;		
		
		//show go sign
		if (!round1Bool && !quit && !winState)showGoSign(input);
	}	
	
	public void menu(Input input, StateBasedGame sbg){
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
		
	}
	
	public void round1Animation(){
		//round1 animation and sound
		if (round1Bool) round1Scale += 4;		
		if (round1Scale >= 100 && round1Scale <= 105) round1Snd.play(1, Menu.soundVolume);		
		
		if (round1Scale >= 600 && round1Scale <= 610) {
			round1Bool = false;
			enableInput = true;
			round1Scale = 1;
		}
	}
	
	public void youWinAnimation(StateBasedGame sbg){		
		if (winState) {			
			youWinScale1 += 3;
			youWinScale2 += 2;
			enableInput = false;
			ryuSprite = ryuReadyAnimation;
			ryuSprite.stopAt(6);
			
			if (ryuSprite.isStopped()){				
				ryuSprite = ryuWin1Animation;
				ryuSprite.stopAt(3);
				
				if(ryuSprite.isStopped()) ryuSprite = ryuWin2Animation;
			}			
		}
		
		if (youWinScale1 >= 100 && youWinScale1 <= 105) youWinSnd.play(1, Menu.soundVolume);
		
		if (youWinScale1 >= 600 && youWinScale1 <= 610) youWinScale1 -= 3;	
		
		if (youWinScale2 >= 1200 && youWinScale1 <= 1210) sbg.enterState(0);	
		
	}
	
	public void drawThugs(int delta){
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!deadSnd.playing() && showThug1) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime2, 2000)){
					showThug1 = false;					
				}
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug1PosX, thug1PosY) && !ryuAttack() && enemyAttackChance() && showThug1 && thug1HitRyu){			
				thug1Sprite = thug1HitAnimation;			
				getInitialTime2 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!deadSnd.playing() && showThug2) deadSnd.play(1, Menu.soundVolume);
				if(delay(getInitialTime3, 2000)) showThug2 = false;
			}
			
			//thug hit ryu
			if(thugAtRyu(thug2PosX, thug2PosY) && !ryuAttack() && enemyAttackChance() && showThug2 && thug2HitRyu){			
				thug2Sprite = thug2HitAnimation;
				getInitialTime3 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!deadSnd.playing() && showThug3) deadSnd.play(1, Menu.soundVolume);
				if(delay(getInitialTime4, 2000)) showThug3 = false;
			}
			
			//thug hit ryu
			if(thugAtRyu(thug3PosX, thug3PosY) && !ryuAttack() && enemyAttackChance() && showThug3 && thug3HitRyu){			
				thug3Sprite = thug3HitAnimation;
				getInitialTime4 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!deadSnd.playing() && showThug4) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime5, 2000)) showThug4 = false;
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug4PosX, thug4PosY) && !ryuAttack() && enemyAttackChance() && showThug4 && thug4HitRyu){			
				thug4Sprite = thug4HitAnimation;			
				getInitialTime5 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug4Sprite = thug4HurtAnimation;
				getInitialTime5 = time;
				thug4HP -= 11;
			}
		}
		
		//enemy5 interaction------------------------------------------	
		//thug5 AI 
		if (!quit){
			if (ryuPositionX < -4100 && thug5HP > 0){
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
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
				if(!deadSnd.playing() && showThug5) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime6, 2000)) showThug5 = false;
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug5PosX, thug5PosY) && !ryuAttack() && enemyAttackChance() && showThug5 && thug5HitRyu){			
				thug5Sprite = thug5HitAnimation;			
				getInitialTime6 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				ryuHurt = true;			
				ryuHP--;		
				thug5HitRyu = false;
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
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug5Sprite = thug5HurtAnimation;
				getInitialTime6 = time;
				thug5HP -= 11;
			}
		}
		
		//enemy6 interaction------------------------------------------	
		//thug6 AI 
		if (!quit){
			if (ryuPositionX < -4600 && thug6HP > 0){
				if(thug6PosY < 117) {
					moveY6 += delta * .1f + 1;
					thug6Sprite = thug6WalkAnimation;
				}
				
				if(thug6PosY > 120) {
					moveY6 -= delta * .1f;
					thug6Sprite = thug6WalkAnimation;
				}		
				
				if(thug6PosX > 170) {
					moveX6 -= delta * .1f;
					thug6Sprite = thug6WalkAnimation;
				}
				
				if(thug6PosX < 150 && !ryuTatsaku) {
					moveX6 += delta * .1f + 1;
					thug6Sprite = thug6WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug6PosX, thug6PosY) && ryuAttack() && !thug6Dead){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug6Sprite = thug6HurtAnimation;
				getInitialTime7 = time;
				thug6HP--;
			}
			
			if(delay(getInitialTime7, 10) && thug6Sprite == thug6HurtAnimation) {			
				thug6Sprite = thug6StaticAnimation;			
			}		
			
			//thug dies
			if(thug6HP <= 0){			
				thug6Sprite = thug6DeadAnimation;
				thug6Dead = true;
				if(!deadSnd.playing() && showThug6) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime7, 2000)) showThug6 = false;	
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug6PosX, thug6PosY) && !ryuAttack() && enemyAttackChance() && showThug6 && thug6HitRyu){			
				thug6Sprite = thug6HitAnimation;			
				getInitialTime7 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				ryuHurt = true;			
				ryuHP--;		
				thug6HitRyu = false;
			}	
			
			if(delay(getInitialTime7, 10) && thug5Sprite == thug6HitAnimation) {			
				if(delay(getInitialTime7, 1000)) {
					thug6Sprite = thug6StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime7, 3000)) thug6HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug6PosX, thug6PosY)){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug6Sprite = thug6HurtAnimation;
				getInitialTime7 = time;
				thug6HP -= 11;
			}
		}
		
		//enemy7 interaction------------------------------------------	
		//thug7 AI 
		if (!quit){
			if (ryuPositionX < -5100 && thug7HP > 0){
				if(thug7PosY < 117) {
					moveY7 += delta * .1f + 1;
					thug7Sprite = thug7WalkAnimation;
				}
				
				if(thug7PosY > 120) {
					moveY7 -= delta * .1f;
					thug7Sprite = thug7WalkAnimation;
				}		
				
				if(thug7PosX > 170) {
					moveX7 -= delta * .1f;
					thug7Sprite = thug7WalkAnimation;
				}
				
				if(thug7PosX < 150 && !ryuTatsaku) {
					moveX7 += delta * .1f + 1;
					thug7Sprite = thug7WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug7PosX, thug7PosY) && ryuAttack() && !thug7Dead){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug7Sprite = thug7HurtAnimation;
				getInitialTime8 = time;
				thug7HP--;
			}
			
			if(delay(getInitialTime8, 10) && thug7Sprite == thug7HurtAnimation) {			
				thug7Sprite = thug7StaticAnimation;			
			}		
			
			//thug dies
			if(thug7HP <= 0){			
				thug7Sprite = thug7DeadAnimation;
				thug7Dead = true;
				if(!deadSnd.playing() && showThug7) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime8, 2000)) showThug7 = false;
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug7PosX, thug7PosY) && !ryuAttack() && enemyAttackChance() && showThug7 && thug7HitRyu){			
				thug7Sprite = thug7HitAnimation;			
				getInitialTime8 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				ryuHurt = true;			
				ryuHP--;		
				thug7HitRyu = false;
			}	
			
			if(delay(getInitialTime8, 10) && thug7Sprite == thug7HitAnimation) {			
				if(delay(getInitialTime8, 1000)) {
					thug7Sprite = thug7StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime8, 3000)) thug7HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug7PosX, thug7PosY)){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug7Sprite = thug7HurtAnimation;
				getInitialTime8 = time;
				thug7HP -= 11;
			}
		}
		
		//enemy8 interaction------------------------------------------	
		//thug8 AI 
		if (!quit){
			if (ryuPositionX < -5500 && thug8HP > 0){
				if(thug8PosY < 117) {
					moveY8 += delta * .1f + 1;
					thug8Sprite = thug8WalkAnimation;
				}
				
				if(thug8PosY > 120) {
					moveY8 -= delta * .1f;
					thug8Sprite = thug8WalkAnimation;
				}		
				
				if(thug8PosX > 170) {
					moveX8 -= delta * .1f;
					thug8Sprite = thug8WalkAnimation;
				}
				
				if(thug8PosX < 150 && !ryuTatsaku) {
					moveX8 += delta * .1f + 1;
					thug8Sprite = thug8WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug8PosX, thug8PosY) && ryuAttack() && !thug8Dead){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug8Sprite = thug8HurtAnimation;
				getInitialTime9 = time;
				thug8HP--;
			}
			
			if(delay(getInitialTime9, 10) && thug8Sprite == thug8HurtAnimation) {			
				thug8Sprite = thug8StaticAnimation;			
			}		
			
			//thug dies
			if(thug8HP <= 0){			
				thug8Sprite = thug8DeadAnimation;
				thug8Dead = true;
				if(!deadSnd.playing() && showThug8) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime9, 2000)) showThug8 = false;
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug8PosX, thug8PosY) && !ryuAttack() && enemyAttackChance() && showThug8 && thug8HitRyu){			
				thug8Sprite = thug8HitAnimation;			
				getInitialTime9 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				ryuHurt = true;			
				ryuHP--;		
				thug8HitRyu = false;
			}	
			
			if(delay(getInitialTime9, 10) && thug8Sprite == thug8HitAnimation) {			
				if(delay(getInitialTime9, 1000)) {
					thug8Sprite = thug8StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime9, 3000)) thug8HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug8PosX, thug8PosY)){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug8Sprite = thug8HurtAnimation;
				getInitialTime9 = time;
				thug8HP -= 11;
			}
		}
		
		//enemy9 interaction------------------------------------------	
		//thug9 AI 
		if (!quit){
			if (ryuPositionX < -5800 && thug9HP > 0){
				if(thug9PosY < 117) {
					moveY9 += delta * .1f + 1;
					thug9Sprite = thug9WalkAnimation;
				}
				
				if(thug9PosY > 120) {
					moveY9 -= delta * .1f;
					thug9Sprite = thug9WalkAnimation;
				}		
				
				if(thug9PosX > 170) {
					moveX9 -= delta * .1f;
					thug9Sprite = thug9WalkAnimation;
				}
				
				if(thug9PosX < 150 && !ryuTatsaku) {
					moveX9 += delta * .1f + 1;
					thug9Sprite = thug9WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug9PosX, thug9PosY) && ryuAttack() && !thug9Dead){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug9Sprite = thug9HurtAnimation;
				getInitialTime10 = time;
				thug9HP--;
			}
			
			if(delay(getInitialTime10, 10) && thug9Sprite == thug9HurtAnimation) {			
				thug9Sprite = thug9StaticAnimation;			
			}		
			
			//thug dies
			if(thug9HP <= 0){			
				thug9Sprite = thug9DeadAnimation;
				thug9Dead = true;
				if(!deadSnd.playing() && showThug9) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime10, 2000)) showThug9 = false;
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug9PosX, thug9PosY) && !ryuAttack() && enemyAttackChance() && showThug9 && thug9HitRyu){			
				thug9Sprite = thug9HitAnimation;			
				getInitialTime10 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				ryuHurt = true;			
				ryuHP--;		
				thug9HitRyu = false;
			}	
			
			if(delay(getInitialTime10, 10) && thug9Sprite == thug9HitAnimation) {			
				if(delay(getInitialTime10, 1000)) {
					thug9Sprite = thug9StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime10, 3000)) thug9HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug9PosX, thug9PosY)){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug9Sprite = thug9HurtAnimation;
				getInitialTime10 = time;
				thug9HP -= 11;
			}
		}
		
		//enemy10 interaction------------------------------------------	
		//thug10 AI 
		if (!quit){
			if (ryuPositionX < -6300 && thug10HP > 0){
				if(thug10PosY < 117) {
					moveY10 += delta * .1f + 1;
					thug10Sprite = thug9WalkAnimation;
				}
				
				if(thug10PosY > 120) {
					moveY10 -= delta * .1f;
					thug10Sprite = thug10WalkAnimation;
				}		
				
				if(thug10PosX > 170) {
					moveX10 -= delta * .1f;
					thug10Sprite = thug10WalkAnimation;
				}
				
				if(thug10PosX < 150 && !ryuTatsaku) {
					moveX10 += delta * .1f + 1;
					thug10Sprite = thug10WalkAnimation;
				}
			}
			
			//ryuHitThug
			if(thugAtRyu(thug10PosX, thug10PosY) && ryuAttack() && !thug10Dead){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug10Sprite = thug10HurtAnimation;
				getInitialTime10 = time;
				thug10HP--;
			}
			
			if(delay(getInitialTime11, 10) && thug10Sprite == thug10HurtAnimation) {			
				thug10Sprite = thug10StaticAnimation;			
			}		
			
			//thug dies
			if(thug10HP <= 0){			
				thug10Sprite = thug10DeadAnimation;
				thug10Dead = true;
				if(!deadSnd.playing() && showThug10) deadSnd.play(1, Menu.soundVolume);			
				if(delay(getInitialTime11, 2000)) showThug10 = false;
			}		
			
			//thug hit ryu
			if(thugAtRyu(thug10PosX, thug10PosY) && !ryuAttack() && enemyAttackChance() && showThug10 && thug10HitRyu){			
				thug10Sprite = thug10HitAnimation;			
				getInitialTime11 = time;
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				ryuHurt = true;			
				ryuHP--;		
				thug10HitRyu = false;
			}	
			
			if(delay(getInitialTime11, 10) && thug10Sprite == thug10HitAnimation) {			
				if(delay(getInitialTime11, 1000)) {
					thug10Sprite = thug10StaticAnimation;
					ryuHurt = false;				
				}			
			}	
			
			if(delay(getInitialTime11, 3000)) thug10HitRyu = true;
			
			//thug hit by hadouken
			if(ryuHadouken &&  hadoukenAtThug(thug10PosX, thug10PosY)){
				if(!punchedSnd.playing()) punchedSnd.play(1, Menu.soundVolume);
				thug8Sprite = thug10HurtAnimation;
				getInitialTime11 = time;
				thug10HP -= 11;
			}
		}
		
		//----------------------------------------------------------------
	}
	
	public void showGoSign(Input input){
		staticDuration++; 
		
		if((staticDuration > 400) && ryuStatic){
			if(!goSnd.playing()){
				goSnd.play(1, Menu.soundVolume);
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
			punchAndKickSnd.play(1, Menu.soundVolume);					
			ryuPunch = true;			
		} 	
		
		if(delay(getInitialTime, 400)){	
			ryuPunchAnimation.restart();
			ryuPunch = false;						
		}
		
		//lowKick
		if(input.isKeyPressed(Input.KEY_S) && enableInput){
			getInitialTime = time;
			punchAndKickSnd.play(1, Menu.soundVolume);
			ryuLowKick = true;
		}		
		
		if(delay(getInitialTime, 400)){
			ryuLowKickAnimation.restart();
			ryuLowKick = false;
		}
		
		//hadouken
		if(input.isKeyPressed(Input.KEY_D) && ryuMP > 2 && enableInput){
			getInitialTime = hadoukenBallStart = time;
			hadoukenSnd.play(1, Menu.soundVolume);
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
			shoryukenSnd.play(1, Menu.soundVolume);
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
			tatsakuSnd.play(1, Menu.soundVolume);
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
			deadSnd.play(1, Menu.soundVolume);
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
			if (!chickenSnd.playing()) chickenSnd.play(1, Menu.soundVolume);
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
