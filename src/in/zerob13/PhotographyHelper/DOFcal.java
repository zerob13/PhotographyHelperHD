package in.zerob13.PhotographyHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class DOFcal extends Activity {
	/** Called when the activity is first created. */
	private String[] film = { "1/2.5", "1/1.8", "1/1.6", "2/3",
			"OLYMPUS E1/E300/E500", "APS-C(Canon)", "APS-C(Nikon)",
			"APS-H(Leica M8系列,Canon1D系列)", "APS film", "35mm胶片/全画幅",
			"6x4.5 film", "6x6 film", "6x7 film", "6x9 film", "4x5 film",
			"5x7 film", "8x10 film" };
	private double[] circle = { 0.005, 0.006, 0.007, 0.008, 0.015, 0.019,
			0.020, 0.023, 0.025, 0.030, 0.045, 0.045, 0.060, 0.07, 0.1, 0.15,
			0.2 };
	double c_R;

	private double prev, post;
	private boolean En;

	ArrayAdapter<String> adapter;
	Spinner filmsize;
	EditText focus, aper, dist;
	TextView jdeph;
	Button getDeph;

	double getPrevDOF(double disp, double foci, double apert, double dis) {
		double result = 0.0;
		if (disp > 0 && foci > 0 && apert > 0 && dis > 0) {
			double temp1 = apert * dis * disp;
			double temp2 = foci * foci + temp1;
			if (temp2 > 0) {
				result = (temp1 * dis) / temp2;
			} else {
				result = 999999999;
			}
		}
		return result;

	}

	double getPostDOF(double disp, double foci, double apert, double dis) {
		double result = 0.0;
		if (disp > 0 && foci > 0 && apert > 0 && dis > 0) {
			double temp1 = apert * dis * disp;
			double temp2 = foci * foci - temp1;
			if (temp2 > 0) {
				result = (temp1 * dis) / temp2;
			} else {
				result = 999999999;
			}
		}
		return result;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dof);
		filmsize = (Spinner) findViewById(R.id.widget129);
		focus = (EditText) findViewById(R.id.widget132);
		aper = (EditText) findViewById(R.id.widget134);
		dist = (EditText) findViewById(R.id.widget136);
		jdeph = (TextView) findViewById(R.id.widget137);
		getDeph = (Button) findViewById(R.id.widget95);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, film);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		filmsize.setAdapter(adapter);
		filmsize.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				c_R = circle[arg2];

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(DOFcal.this, "没选中", 10).show();
			}

		});
		getDeph.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (focus == null)
					focus = (EditText) findViewById(R.id.widget132);
				if (aper == null)
					aper = (EditText) findViewById(R.id.widget134);
				if (dist == null)
					dist = (EditText) findViewById(R.id.widget136);
				java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
				double f = Double.parseDouble(focus.getText().toString());
				double a = Double.parseDouble(aper.getText().toString());
				double d = Double.parseDouble(dist.getText().toString());

				prev = getPrevDOF(c_R, f, a, d);
				post = getPostDOF(c_R, f, a, d);
				double j = prev + post;
				jdeph.setText("前景深: " + df.format(prev) + "mm\n" + "后景深: "
						+ df.format(post) + "mm\n" + "景深：" + df.format(j)
						+ "mm");
				En = false;
				Toast.makeText(DOFcal.this, "点击景深数字转换为英制单位", 5).show();
			}

		});
		jdeph.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				java.text.DecimalFormat df = new java.text.DecimalFormat(
						"#0.00");
				double j = prev + post;
				if (En) {
					jdeph.setText("前景深: " + df.format(prev) + " mm\n" + "后景深: "
							+ df.format(post) + " mm\n" + "景深：" + df.format(j)
							+ " mm");
					Toast.makeText(DOFcal.this, "点击景深数字转换为英制单位", 5).show();
					En = false;
				} else {
					jdeph.setText("前景深: " + df.format(prev / 25.4) + " inch\n"
							+ "后景深: " + df.format(post / 25.4) + " inch\n"
							+ "景深：" + df.format(j / 25.4) + " inch");
					Toast.makeText(DOFcal.this, "点击景深数字转换为公制单位", 5).show();
					En = true;
				}

			}

		});

	}
}
