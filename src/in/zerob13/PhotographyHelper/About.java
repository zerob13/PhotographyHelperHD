package in.zerob13.PhotographyHelper;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class About extends Activity  {
	/** Called when the activity is first created. */
	TextView about;
	int eggcount;
	// private InterstitialAd mInterstitial;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		
		eggcount=0;
        setContentView(R.layout.about);
        about = (TextView)findViewById(R.id.widget57);
		about.setTextColor(Color.GREEN);
		about.setText("胶片摄影助手 By zerob13\nhttp://www.zerob13.in/\nE-mail:zerob13@gmail.com\n感谢使用本软件，如果您喜欢，希望偶尔点击一下广告支持一下作者，谢谢：）\n");
		about.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				eggcount++;
				if(eggcount==10){
					about.setTextColor(Color.BLUE);
					about.setText("Life is like a boat～\n");
				}
				if(eggcount==11){
					
					about.setText("胶片摄影助手 By zerob13\nhttp://www.zerob13.in/\nE-mail:zerob13@gmail.com\n感谢使用本软件，如果您喜欢，希望偶尔点击一下广告支持一下作者，谢谢：）\n");
					eggcount=0;
				}
			}
			
		});
		

	}

	

}
