package in.zerob13.PhotographyHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.youmi.android.AdListener;
import net.youmi.android.AdManager;
import net.youmi.android.AdView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class Main extends Activity implements AdListener {
	/** Called when the activity is first created. */
	Button a, b, c, d, e;
	OnTouchListener mub;
	Thread down;
	ProgressDialog progressDialog;
	Handler handler;
	Thread aa;
	AdView adView;
	SQLiteDatabase sql;
	static {

		AdManager.init("50b2b2e52b464a6e ", "0149a2ee96a8fad1 ", 40, false,
				"0.8beta");

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		getWindow().setBackgroundDrawableResource(R.drawable.back);
		adView = (AdView) findViewById(R.id.adM);
		adView.setAdListener(this);
		a = (Button) findViewById(R.id.widget61);
		b = (Button) findViewById(R.id.widget63);
		c = (Button) findViewById(R.id.widget64);
		d = (Button) findViewById(R.id.widget65);
		e = (Button) findViewById(R.id.widget66);
		a.setTextColor(Color.WHITE);
		b.setTextColor(Color.WHITE);
		c.setTextColor(Color.BLACK);
		d.setTextColor(Color.BLACK);
		e.setTextColor(Color.BLACK);

		/*
		 * a.setBackgroundResource(R.drawable.dofcal);
		 * b.setBackgroundResource(R.drawable.washtime);
		 * c.setBackgroundResource(R.drawable.meter);
		 * d.setBackgroundResource(R.drawable.stoptime);
		 * e.setBackgroundResource(R.drawable.about);
		 */

		a.setText(R.string.DOFcal);
		b.setText(R.string.WashTime);
		c.setText(R.string.Meter);
		d.setText(R.string.StopTime);
		e.setText(R.string.About);
		a.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent();
				mIntent.setClass(Main.this, DOFcal.class);
				startActivity(mIntent);
			}

		});
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean exists = (new File(
						"/data/data/in.zerob13.PhotographyHelper/film"))
						.exists();

				if (!exists) {
					
					progressDialog = ProgressDialog.show(Main.this, "",
							"下载数据库中...");
						aa = new Thread(new Runnable() {
							@Override
							public void run() {
								 downloadFile("http://www.zerob13.in/film.sqlite","/data/data/in.zerob13.PhotographyHelper/film");
								Message msg = new Message();
								msg.what = 1;
								handler.sendMessage(msg);
								Intent mIntent = new Intent();
								mIntent.setClass(Main.this, WashTime.class);
								startActivity(mIntent);

							}
						});
						aa.start();

				} else {
					Intent mIntent = new Intent();
					mIntent.setClass(Main.this, WashTime.class);
					startActivity(mIntent);
				}

			}

		});
		handler = new Handler() {

			public void handleMessage(Message message) {
				if (message.what == 1) {
					progressDialog.dismiss();
				}
			}
		};
		c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent();
				mIntent.setClass(Main.this, Meter.class);
				startActivity(mIntent);
			}

		});
		d.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent();
				mIntent.setClass(Main.this, StopTime.class);
				startActivity(mIntent);
			}

		});
		e.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent();
				mIntent.setClass(Main.this, About.class);
				startActivity(mIntent);
			}

		});
	}

	public void downloadFile(String url, String newPath) {

		URL myFileUrl = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			Log.e("test", "MalformedURLException: " + e.toString());
		}
		try {
			HttpURLConnection conn;
			conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			FileOutputStream newfile = new FileOutputStream(
					"/data/data/in.zerob13.PhotographyHelper/film");
			byte[] buffer = new byte[1444];
			int byteread = 0;
			while ((byteread = is.read(buffer)) != -1) {
				newfile.write(buffer, 0, byteread);
			}
			is.close();
			String command = "chmod 777 "
					+ "/data/data/in.zerob13.PhotographyHelper/film";
			Log.i("test", "command = " + command);
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec(command);
			proc.destroy();

		} catch (Exception e) {
			Log.e("test", "returnNetworkBitMap: " + e.toString());
		} finally {
		}
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