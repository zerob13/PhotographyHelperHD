package in.zerob13.PhotographyHelper;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

public class DesActivity extends Activity {

	NotificationManager m_NotificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main2);

		// 启动后删除之前我们定义的
		m_NotificationManager = (NotificationManager) this
				.getSystemService(NOTIFICATION_SERVICE);
		m_NotificationManager.cancel(0);
	}

}