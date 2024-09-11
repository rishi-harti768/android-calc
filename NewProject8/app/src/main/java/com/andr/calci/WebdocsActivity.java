package com.andr.calci;

import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.gdacciaro.iOSDialog.*;
import com.google.android.material.appbar.AppBarLayout;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import vpdotsindicator.mytelegram.sketchware95.*;

public class WebdocsActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double size = 0;
	private HashMap<String, Object> log = new HashMap<>();
	
	private LinearLayout linear1;
	private ProgressBar progressbar1;
	private WebView webview1;
	private LinearLayout linear2;
	private TextView textview1;
	
	private TimerTask delay;
	private SharedPreferences local;
	private ObjectAnimator anim = new ObjectAnimator();
	private ObjectAnimator click_scaleX = new ObjectAnimator();
	private ObjectAnimator click_scaleY = new ObjectAnimator();
	private Intent tele = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.webdocs);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = findViewById(R.id.linear1);
		progressbar1 = findViewById(R.id.progressbar1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear2 = findViewById(R.id.linear2);
		textview1 = findViewById(R.id.textview1);
		local = getSharedPreferences("local", Activity.MODE_PRIVATE);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				log.put("is_web_loading", "yes");
				delay = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								progressbar1.setProgress((int)webview1.getProgress());
							}
						});
					}
				};
				_timer.scheduleAtFixedRate(delay, (int)(0), (int)(1));
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				log.put("is_web_loading", "no");
				delay.cancel();
				webview1.setVisibility(View.VISIBLE);
				setTitle(webview1.getTitle());
				if (_url.contains("T&C.html")) {
					linear2.setVisibility(View.VISIBLE);
				}
				else {
					if (_url.contains("PriPo.html")) {
						linear2.setVisibility(View.GONE);
						local.edit().putString("PriPo_agreement", "1").commit();
					}
				}
				super.onPageFinished(_param1, _param2);
			}
		});
	}
	
	private void initializeLogic() {
		_design();
		log.put("is_web_loading", "no");
		webview1.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		setTitle(null);
		webview1.getSettings().setBuiltInZoomControls(false);
		linear2.setVisibility(View.GONE);
		webview1.setVisibility(View.INVISIBLE);
		if (getIntent().getStringExtra("webDoc").equals("TnC")) {
			webview1.loadUrl(getString(R.string.webdocs).concat("T&C.html"));
		}
		else {
			if (getIntent().getStringExtra("webDoc").equals("PP")) {
				webview1.loadUrl(getString(R.string.webdocs).concat("PriPo.html"));
			}
			else {
				
			}
		}
		_listners();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
	}
	
	@Override
	public void onBackPressed() {
		if (log.get("is_web_loading").toString().equals("no")) {
			if (getIntent().getStringExtra("webDoc").equals("TnC") && local.getString("T&C_agreement", "").equals("1")) {
				tele.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(tele);
				Animatoo.animateSwipeRight(WebdocsActivity.this);
				finish();
			}
			else {
				if (getIntent().getStringExtra("webDoc").equals("PP") && local.getString("PriPo_agreement", "").equals("1")) {
					tele.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(tele);
					Animatoo.animateSwipeRight(WebdocsActivity.this);
					finish();
				}
			}
		}
	}
	public void _design() {
		size = SketchwareUtil.getDisplayWidthPixels(getApplicationContext());
		textview1.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)size/32);
		textview1.getLayoutParams().width=(int)((int) ((size * 3) / 4));
		textview1.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		if (local.getString("T&C_agreement", "").equals("0")) {
			textview1.setText("I Accept");
			textview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)16, 0xFFFF9800));
		}
		else {
			if (local.getString("T&C_agreement", "").equals("1")) {
				textview1.setText("Accepted");
				textview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)16, 0xFF9E9E9E));
			}
		}
	}
	
	
	public void _listners() {
		textview1.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View _touchedView , MotionEvent event){
				int ev = event.getAction();
				switch (ev) {
					case MotionEvent.ACTION_DOWN:
					
					click_scaleX.cancel();
					click_scaleX.setTarget(textview1);
					click_scaleX.setPropertyName("scaleX");
					click_scaleX.setFloatValues((float)(0.96d));
					click_scaleX.setDuration((int)(64));
					click_scaleY.cancel();
					click_scaleY.setTarget(textview1);
					click_scaleY.setPropertyName("scaleY");
					click_scaleY.setFloatValues((float)(0.96d));
					click_scaleY.setDuration((int)(64));
					click_scaleX.start();
					click_scaleY.start();
					
					break;
					case MotionEvent.ACTION_UP:
					
					click_scaleX.cancel();
					click_scaleX.setTarget(textview1);
					click_scaleX.setPropertyName("scaleX");
					click_scaleX.setFloatValues((float)(1));
					click_scaleX.setDuration((int)(256));
					click_scaleY.cancel();
					click_scaleY.setTarget(textview1);
					click_scaleY.setPropertyName("scaleY");
					click_scaleY.setFloatValues((float)(1));
					click_scaleY.setDuration((int)(256));
					click_scaleX.start();
					click_scaleY.start();
					local.edit().putString("T&C_agreement", "1").commit();
					_design();
					
					break;
				} return true;
			}
		});
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}