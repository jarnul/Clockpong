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
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		updateDelay = 1000;
		game = new gameLogic();
		
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
		game.updateGame();
		game.updateGame();
		game.updateGame();
		drawGame(game);


	}
	
	public void update() {
		
	}


}
