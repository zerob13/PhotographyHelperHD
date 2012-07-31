package in.zerob13.PhotographyHelper;


import net.youmi.android.AdListener;
import net.youmi.android.AdManager;
import net.youmi.android.AdView;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class About extends Activity implements AdListener {
	/** Called when the activity is first created. */
	TextView about;
	AdView adView;
	int eggcount;
	 static{
   	  
		 AdManager.init("50b2b2e52b464a6e ", "0149a2ee96a8fad1 ", 40, false,"0.8beta");
	    	
	    }
	// private InterstitialAd mInterstitial;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		
		eggcount=0;
        setContentView(R.layout.about);
        adView=(AdView)findViewById(R.id.adView);
        adView.setAdListener(this);
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
					about.setText("仅以此软件表示混沌的云最最最喜欢柠茉小姑娘啦啦啦啦啦～\n");
				}
				if(eggcount==11){
					
					about.setText("胶片摄影助手 By zerob13\nhttp://www.zerob13.in/\nE-mail:zerob13@gmail.com\n感谢使用本软件，如果您喜欢，希望偶尔点击一下广告支持一下作者，谢谢：）\n");
					eggcount=0;
				}
			}
			
		});
		

	}

	@Override
	public void onConnectFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveAd() {
		// TODO Auto-generated method stub

	}

	

}
