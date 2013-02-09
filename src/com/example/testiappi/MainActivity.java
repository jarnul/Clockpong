package com.example.testiappi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public int updateDelay; //in millis
	public gameLogic game;
	public int movement; //how many pixels to move per frame
	Thread gameThread;
	
	
	public void onPause() {
		super.onPause();
		
		gameThread.interrupt();
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	private void drawGame(gameLogic game){
		int[] graphics = new int[1536]; //96*16=1536, clock's resolution
		for (int i=0;i<game.getGame().length;++i){
			for (int j=0;j<game.getGame()[0].length;++j){
				if(game.getGame()[i][j]!=0)
					graphics[i+j*96]=0;
				else
					graphics[i+j*96]=-1;
			}
		}
		Intent openWatchIntent = new Intent();
		openWatchIntent.setAction("com.smartmadsoft.openwatch.action.GRAPHICS"); 
		openWatchIntent.putExtra("data", graphics);
		openWatchIntent.putExtra("time", updateDelay);
		sendBroadcast(openWatchIntent);
	}
	
	public void moveDown(View view) {
		game.movePlayerDown();
	}
	
	public void moveUp(View view) {
		game.movePlayerUp();
	}
	
	public void gameLost() {
		Intent openWatchIntent = new Intent();
		openWatchIntent.setAction("com.smartmadsoft.openwatch.action.TEXT");
		openWatchIntent.putExtra("line1", "lol noob hävisit");
		openWatchIntent.putExtra("line2", "gg");
		sendBroadcast(openWatchIntent);
	}
	
	public void testi(View view) {
		//Intent testi = new Intent("com.smartmadsoft.openwatch.action.VIBRATE");
		//startActivity(testi);
//		Intent openWatchIntent = new Intent();
//		openWatchIntent.setAction("com.smartmadsoft.openwatch.action.GRAPHICS"); 
////		openWatchIntent.setAction("com.smartmadsoft.openwatch.action.TEXT");
////		openWatchIntent.putExtra("line1", "lol");
////		openWatchIntent.putExtra("line2", "noobs");
//		
//		int[] graphics = new int[1536]; //96*16=1536
//		for (int i = 0;i<1536;i=i+2)
//		graphics[i] = 1; 
//		openWatchIntent.putExtra("data", graphics);
//		openWatchIntent.putExtra("time", 7000);
//		sendBroadcast(openWatchIntent);
//		
//		TextView editText = (TextView) findViewById(R.id.teksti);
//		editText.setText("moi");
		updateDelay = 1500;
		game = new gameLogic();
		movement = 6;
		
		
		
		gameThread = new Thread()
		{
		    @Override
		    public void run() {
		        try {
		        	boolean escape=false;
		            while(true) {
		        		drawGame(game);
		        		for (int i=0;i<movement;++i){
		        			if(!game.updateGame()) {
		        				escape=true;
		        				break;
		        			}
		        		}
		        		if(escape) {
		        			sleep(1000);
		        			gameLost();
		        			break;
		        		}
		                sleep(updateDelay);
		            }
		        } catch (InterruptedException e) {
		            //e.printStackTrace();
		        }
		    }
		};

		gameThread.start();
	}


}
