package in.zerob13.PhotographyHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class WashTime extends Activity {
	/** Called when the activity is first created. */
	ArrayAdapter<String> adapter;

	SQLiteDatabase sql;

	Spinner filmtype;
	Spinner developer;
	TextView showTable;

	private String[] ftp;
	private String[] dtp;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wash);

		filmtype = (Spinner) findViewById(R.id.widget70);
		developer = (Spinner) findViewById(R.id.widget73);
		showTable = (TextView) findViewById(R.id.widget74);
		
	
		sql = SQLiteDatabase.openDatabase(
				"/data/data/in.zerob13.PhotographyHelper/film", null,
				SQLiteDatabase.OPEN_READONLY);
		Cursor aa = sql.rawQuery("select DISTINCT Fname from Film", null);
		aa.moveToFirst();
		int i, j = aa.getCount();
		ftp = new String[j];
		i = 0;
		while (i < j) {

			ftp[i] = aa.getString(0);
			i++;
			aa.moveToNext();
		}
		aa.close();
		sql.close();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, ftp);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		filmtype.setAdapter(adapter);
		filmtype.setSelection(1);
		filmtype.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (developer == null)
					developer = (Spinner) findViewById(R.id.widget73);
				sql = SQLiteDatabase.openDatabase(
						"/data/data/in.zerob13.PhotographyHelper/film", null,
						SQLiteDatabase.OPEN_READONLY);
				Cursor aa = sql.rawQuery(
						"select DISTINCT Dname from Film where Fname='"
								+ ftp[arg2] + "'", null);
				int j = 0, i = aa.getCount();
				dtp = new String[i];
				aa.moveToFirst();

				while (j < i) {
					dtp[j++] = aa.getString(0);
					aa.moveToNext();
				}
				aa.close();
				sql.close();

				adapter = new ArrayAdapter<String>(WashTime.this,
						android.R.layout.simple_spinner_item, dtp);
				adapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				developer.setAdapter(adapter);
				developer.setSelection(0);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		// cur.close();
		developer.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (showTable == null)
					showTable = (TextView) findViewById(R.id.widget74);
				int f = filmtype.getSelectedItemPosition();
				sql = SQLiteDatabase.openDatabase(
						"/data/data/in.zerob13.PhotographyHelper/film", null,
						SQLiteDatabase.OPEN_READONLY);
				Cursor aa = sql.rawQuery(
						"select Dil,Iso,T35,T120,Temp from Film where Fname='"
								+ ftp[f] + "'" + " and Dname='" + dtp[arg2]
								+ "'", null);
				int j = 0, i = aa.getCount();
				aa.moveToFirst();
				String spa = "|";
				showTable.setText("稀释度" + spa + "ISO" + spa + "35mm胶卷" + spa
						+ "120胶卷" + spa + "温度\n");

				while (j < i) {
					j++;
					String Dil = aa.getString(0);
					String T35 = aa.getString(2), T120 = aa.getString(3);
					if (Dil.equals("stock")) {
						Dil = "未稀释";
					}
					if (T35 == null)
						T35 = "无数据";
					if (T120 == null)
						T120 = "无数据";
					String temp = Dil + spa + aa.getInt(1) + spa + T35 + spa
							+ T120 + spa + aa.getString(4) + "\n";
					showTable.append(temp);
					aa.moveToNext();
				}
				aa.close();
				sql.close();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	

}
