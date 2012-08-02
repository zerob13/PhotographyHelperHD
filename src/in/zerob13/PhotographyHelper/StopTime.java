package in.zerob13.PhotographyHelper;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StopTime extends Activity  {
	 /** Called when the activity is first created. */
	Button star;
	CountDownTimer cot;
	NumberPicker min,sec;
	TextView countDown;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stoptime);
        countDown=(TextView)findViewById(R.id.widget35);
        min=(NumberPicker)findViewById(R.id.widget40);
        sec=(NumberPicker)findViewById(R.id.widget41);
        min.mCurrent=1;
        sec.mCurrent=30;
        star=(Button)findViewById(R.id.widget30);
        star.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(min==null)
				      min=(NumberPicker)findViewById(R.id.widget40);
				if(sec==null)
		        sec=(NumberPicker)findViewById(R.id.widget41);
				if(countDown==null)
				countDown=(TextView)findViewById(R.id.widget35);
				if(cot==null){
					int ti;
					countDown.setVisibility(1);
					ti=min.getCurrent()*60+sec.getCurrent();
					min.setEnabled(false);
					sec.setEnabled(false);
				cot=new CountDownTimer((long)ti*1000,1000) {
		     		
		     		@Override
		     		public void onTick(long millisUntilFinished) {
		     			countDown.setText("剩余时间: " + 
		     				millisUntilFinished / 1000+"秒");
		     		
		     		}
		     		
		     		@Override
		     		public void onFinish() {
		     			NotificationManager m_NotificationManager=(NotificationManager)StopTime.this.getSystemService(NOTIFICATION_SERVICE);
		     			 Intent m_Intent;
		     			 PendingIntent m_PendingIntent;
		     			m_Intent=new Intent(StopTime.this,DesActivity.class);
		     			m_PendingIntent=PendingIntent.getActivity(StopTime.this, 0, m_Intent, 0);
		     			Notification  m_Notification=new Notification();
		     		
		     			m_Notification.icon=R.drawable.icon;
		     			m_Notification.tickerText="时间到";
		     			m_Notification.defaults=Notification.DEFAULT_SOUND;
		     			m_Notification.setLatestEventInfo(StopTime.this, "胶片摄影小助手", "定时时间到！",m_PendingIntent );
		     			m_NotificationManager.notify(0,m_Notification);
		     			countDown.setText("时间到!");
		     			Toast.makeText(StopTime.this, "时间到!",10).show();
		     			cot=null;
		     			star.setText("点我开始");
		     			countDown.setVisibility(0);
		     			min.setEnabled(true);
						sec.setEnabled(true);
		     		}
		     	}.start();
		     	star.setText("点我取消");
				}else {
				
				cot.cancel();
				star.setText("点我开始");
				countDown.setVisibility(0);
				cot=null;
				min.setEnabled(true);
				sec.setEnabled(true);
				}
			}
       	 
        });

    }

}
