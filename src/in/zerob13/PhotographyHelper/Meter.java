package in.zerob13.PhotographyHelper;

import net.youmi.android.AdListener;
import net.youmi.android.AdManager;
import net.youmi.android.AdView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Meter extends Activity   implements AdListener{
	/** Called when the activity is first created. */
	private String[] iso = { "40", "100", "200", "400" };
	private String[] weather = { "阴晦天气", "阴晴天气", "薄云遮日", "光亮日光" };
	private String[] obj = { "明亮景物", "光亮景物", "普通景物", "阴影景物" };
	private String[] ape17 = { "22", "16", "11", "8/9", "5.6/6.3", "4/4.5",
			"2.8", "1.5" };
	private String[] ape21 = { "32", "22", "16", "11", "8/9", "5.6/6.3",
			"4/4.5", "2.8" };
	private String[] ape24 = { "45", "32", "22", "16", "11", "8/9", "5.6/6.3",
			"4/4.5" };
	private String[] ape27 = { "63", "45", "32", "22", "16", "11", "8/9",
			"5.6/6.3" };
	private String[] Kua17 = { "1/4", "1/8", "1/15", "1/30", "1/60", "1/125",
			"1/300", "1/500", "1/1000" };
	Spinner T1S1;
	Spinner T1S2;
	Spinner T1S3;
	Spinner lsa;
	TextView lst;
	AdView adView;
	int[] pro = new int[4];
	ArrayAdapter<String> adapter;
	static{
	   	  
		AdManager.init("50b2b2e52b464a6e ", "0149a2ee96a8fad1 ", 40, false,"0.8beta");  
    	
    }
	void setls() {
		if (lsa == null) {
			lsa = (Spinner) findViewById(R.id.widget111);
		}
		switch (pro[0]) {
		case 0:
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, ape17);
			adapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			break;
		case 1:
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, ape21);
			adapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			break;
		case 2:
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, ape24);
			adapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			break;
		case 3:
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, ape27);
			adapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			break;
		default:
			break;

		}
		lsa.setAdapter(adapter);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meter);
		adView=(AdView)findViewById(R.id.adMeter);
        adView.setAdListener(this);
		pro[0] = pro[1] = pro[2] = pro[3] = 1;
		lsa = (Spinner) findViewById(R.id.widget111);
		lst = (TextView) findViewById(R.id.widget112);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, iso);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		T1S1 = (Spinner) findViewById(R.id.widget107);
		T1S2 = (Spinner) findViewById(R.id.widget108);
		T1S3 = (Spinner) findViewById(R.id.widget110);
		T1S1.setAdapter(adapter);
		T1S1.setSelection(1);
		T1S1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				pro[0] = arg2;
				setls();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Meter.this, "没选中", 10).show();
			}

		});

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, weather);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		T1S2.setAdapter(adapter);
		T1S2.setSelection(1);
		T1S2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				pro[1] = arg2;
				setls();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Meter.this, "没选中", 10).show();
			}

		});

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, obj);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		T1S3.setAdapter(adapter);
		T1S3.setSelection(1);
		T1S3.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				pro[2] = arg2;
				setls();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Meter.this, "没选中", 10).show();
			}

		});
		setls();
		lsa.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				pro[3] = arg2;
				int i, t2, t3, sa;
				String[] temp = new String[8];
				int j = 0;
				t2 = pro[1];
				t3 = pro[2];
				if (lst == null)
					lst = (TextView) findViewById(R.id.widget112);
				sa = t2 - t3;
				if (sa < 0) {
					sa *= -1;
					sa = 1 - sa;
					j = 0;
					while (sa < 0) {
						temp[j++] = "已经超过限制，暂无数据";
						sa++;
					}
					for (i = sa; j < 8; j++, i++) {
						temp[j] = new String(Kua17[i]);
					}

				} else if (sa > 0) {
					sa++;
					for (i = sa, j = 0; j < 8; j++, i++) {
						if (i < Kua17.length)
							temp[j] = new String(Kua17[i]);
						else {
							temp[j] = "已经超过限制，暂无数据";
						}
					}
				} else {
					for (i = 1, j = 0; j < 8; i++, j++) {
						temp[j] = new String(Kua17[i]);
					}
				}

				lst.setText("推荐快门：" + temp[pro[3]]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Meter.this, "没选中", 10).show();
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
