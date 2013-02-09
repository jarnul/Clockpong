package com.example.testiappi;

public class gameLogic {

	private int[][] game; //0 is empty, 1 is player square, 2 is ball
	private int direction; //0 to right, 1 to left
	private int angle;	//-1 to down, 0 straight and 1 up
	private int height; //height of player
	private int width; //width of player
	private int ballSize;
	private int ballLocationX;
	private int ballLocationY;
	
	public gameLogic() {
		game = new int[96][16];
		direction = 0;
		angle = 0;
		ballSize = 2;
		height = 6;
		width = 2;
		ballLocationX = 30;
		ballLocationY = 5;
		
		for (int i=0;i<game.length;++i){
			for (int j=0;j<game[0].length;++j){
				game[i][j]=0; //reseting game-table
			}
		}
		for (int i=0;i<width;++i){
			for (int j=0;j<height;++j){
				game[i][j]=1; //adding player
			}
		}
		for(int i=0;i<2;++i){
			for(int j=0;j<16;++j){
				game[79-i][j]=1; //the right edge, hardcoded 79 :P
			}
		}
		drawBall();
	}
	
	public void movePlayerUp(){
		if(game[0][0]==0) {
			int temp=0;
			for(int i=0;i<game[0].length;++i){
				if (game[0][i]!=0){
					temp=i;
					break;
				}
			}
			for(int i=0;i<width;++i){
				for(int j=0;j<game[0].length;++j){
					game[i][j]=0;
				}
			}
			for(int i=0;i<width;++i){
				for(int j=0;j<height;++j){
					game[i][temp+j-1]=1;
				}
			}
		}
	}
	
	public void movePlayerDown(){
		if(game[0][15]==0) {
			int temp=0;
			for(int i=0;i<game[0].length;++i){
				if (game[0][15-i]!=0){
					temp=i;
					break;
				}
			}
			for(int i=0;i<width;++i){
				for(int j=0;j<game[0].length;++j){
					game[i][j]=0;
				}
			}
			for(int i=0;i<width;++i){
				for(int j=0;j<height;++j){
					game[i][15-(temp+j-1)]=1;
				}
			}
		}
	}
	
	private void drawBall(){
		for(int i=0;i<ballSize;++i){
			for(int j=0;j<ballSize;++j){
				game[ballLocationX+i][ballLocationY+j]=2;
			}
		}
	}
	
	private void removeBall() {
		for (int i=0;i<game.length;++i) {
			for (int j=0;j<game[0].length;++j){
				if (game[i][j]==2)
					game[i][j]=0;
			}
		}
	}
	
	public int[][] getGame(){
		return game;
	}
	
	
	//returns true if game continues, false if game is over (player loses)
	public boolean updateGame(){
		if (direction==0){
			if(ballLocationX==75)
				direction=1;
			else {
				removeBall();
				++ballLocationX;
				drawBall();
			}
		}
		else if(direction==1){
			if(ballLocationX==width+1) {
				if(game[width-1][ballLocationY]==0){
					return false;
				}
				else {
					direction=0;
				}
			}
			else {
				removeBall();
				--ballLocationX;
				drawBall();
			}
		}
		return true;
	}
	
	
}
