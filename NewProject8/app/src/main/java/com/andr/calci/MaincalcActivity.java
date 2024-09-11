package com.andr.calci;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.media.SoundPool;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.gdacciaro.iOSDialog.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import org.json.*;
import vpdotsindicator.mytelegram.sketchware95.*;
import java.math.*;

public class MaincalcActivity extends AppCompatActivity {
	
	private String operator = "";
	private boolean displayEnabled = false;
	private double initPoint = 0;
	private String positive = "";
	private String negative = "";
	private String s1 = "";
	private String s2 = "";
	private double size = 0;
	private double index = 0;
	private double typesound = 0;
	public Cryptography crypto = new Cryptography();
	
	private ArrayList<String> explode = new ArrayList<>();
	private ArrayList<String> terms = new ArrayList<>();
	
	private LinearLayout frame;
	private TextView display;
	private LinearLayout keypad;
	private LinearLayout strip1;
	private LinearLayout strip2;
	private LinearLayout strip3;
	private LinearLayout strip4;
	private LinearLayout strip5;
	private TextView btnclear;
	private TextView btnnegation;
	private TextView btnpercent;
	private TextView btndivide;
	private TextView btnseven;
	private TextView btneight;
	private TextView btnnine;
	private TextView btnmultipliy;
	private TextView btnfour;
	private TextView btnfive;
	private TextView btnsix;
	private TextView btnsubtract;
	private TextView btnone;
	private TextView btntwo;
	private TextView btnthree;
	private TextView btnadd;
	private LinearLayout btnzero;
	private TextView btndot;
	private TextView btnequal;
	private TextView labzero;
	
	private Vibrator vibe;
	private SoundPool effect;
	private SharedPreferences local;
	private Intent tele = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.maincalc);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		frame = findViewById(R.id.frame);
		display = findViewById(R.id.display);
		keypad = findViewById(R.id.keypad);
		strip1 = findViewById(R.id.strip1);
		strip2 = findViewById(R.id.strip2);
		strip3 = findViewById(R.id.strip3);
		strip4 = findViewById(R.id.strip4);
		strip5 = findViewById(R.id.strip5);
		btnclear = findViewById(R.id.btnclear);
		btnnegation = findViewById(R.id.btnnegation);
		btnpercent = findViewById(R.id.btnpercent);
		btndivide = findViewById(R.id.btndivide);
		btnseven = findViewById(R.id.btnseven);
		btneight = findViewById(R.id.btneight);
		btnnine = findViewById(R.id.btnnine);
		btnmultipliy = findViewById(R.id.btnmultipliy);
		btnfour = findViewById(R.id.btnfour);
		btnfive = findViewById(R.id.btnfive);
		btnsix = findViewById(R.id.btnsix);
		btnsubtract = findViewById(R.id.btnsubtract);
		btnone = findViewById(R.id.btnone);
		btntwo = findViewById(R.id.btntwo);
		btnthree = findViewById(R.id.btnthree);
		btnadd = findViewById(R.id.btnadd);
		btnzero = findViewById(R.id.btnzero);
		btndot = findViewById(R.id.btndot);
		btnequal = findViewById(R.id.btnequal);
		labzero = findViewById(R.id.labzero);
		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		local = getSharedPreferences("local", Activity.MODE_PRIVATE);
		
		//OnTouch
		btnclear.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _colorSwap(false, display);
					if (btnclear.getText().toString().equals("AC")) {
						_colorSwap(false, display);
						operator = "null";
						terms.clear();
					}
					else {
						if (btnclear.getText().toString().equals("C")) {
							display.setText("0");
							btnclear.setText("AC");
							operator = "";
						}
					}
					_sensor();
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnnegation.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 if (!display.getText().toString().equals("0")) {
						_colorSwap(false, display);
						operator = "";
						if (display.getText().toString().contains("-")) {
							display.setText(display.getText().toString().replace("-", ""));
						}
						else {
							display.setText("-".concat(display.getText().toString()));
						}
					}
					_sensor();
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnpercent.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 if (!display.getText().toString().equals("0")) {
						arith cal = new arith();
						s1 = display.getText().toString();
						s2 = "100";
						display.setText(cal.quo(s1,s2));
					}
					_sensor();
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btndivide.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btnDMASonclick("oper÷", btndivide);
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnseven.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("7");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btneight.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("8");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnnine.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("9");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnmultipliy.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btnDMASonclick("oper×", btnmultipliy);
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnfour.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("4");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnfive.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("5");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnsix.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("6");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnsubtract.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btnDMASonclick("oper-", btnsubtract);
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnone.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("1");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btntwo.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("2");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnthree.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btn1_9onclick("3");
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnadd.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _btnDMASonclick("oper+", btnadd);
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnzero.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 _colorSwap(false, display);
					if (display.getText().toString().equals("Error")) {
						display.setText("0");
					}
					if (operator.contains("oper")) {
						terms.add(display.getText().toString());
						display.setText("0");
						terms.add(operator);
					}
					if (display.getText().toString().length()<9) {
						if (display.getText().toString().equals("0")) {
							display.setText("0");
							operator = "";
						}
						else {
							btnclear.setText("C");
							display.setText(display.getText().toString().concat("0"));
						}
					}
					_sensor();
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btndot.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 if (!display.getText().toString().contains(".") || operator.contains("oper")) {
						btnclear.setText("C");
						_colorSwap(false, display);
						if (display.getText().toString().equals("Error")) {
							display.setText("0");
						}
						if (operator.contains("oper")) {
							terms.add(display.getText().toString());
							display.setText("0");
							terms.add(operator);
						}
						if (display.getText().toString().length()<12) {
							if (display.getText().toString().equals("0")) {
								display.setText("0.");
								operator = "";
							}
							else {
								display.setText(display.getText().toString().concat("."));
							}
						}
					}
					_sensor();
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		//OnTouch
		btnequal.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 if (!display.getText().toString().equals("Error")) {
						if (operator.equals("") || operator.contains("oper")) {
							_colorSwap(true, btnequal);
							terms.add(display.getText().toString());
							operator = "";
							_solve();
						}
					}
					_sensor();
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
	}
	
	private void initializeLogic() {
		_ui();
		operator = "null";
		effect = new SoundPool((int)(1), AudioManager.STREAM_MUSIC, 0);
		typesound = effect.load(getApplicationContext(), R.raw.ioseffect, 1);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		display.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View _view, MotionEvent _motionEvent){
				switch((int)_motionEvent.getAction()) {
					case ((int)MotionEvent.ACTION_DOWN): {
						displayEnabled = true;
						initPoint = _motionEvent.getX();
						break;
					}
					case ((int)MotionEvent.ACTION_MOVE): {
						if (displayEnabled) {
							if (Math.abs(_motionEvent.getX() - initPoint) > (SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 18)) {
								displayEnabled = false;
								if (!display.getText().toString().equals("0")) {
									display.setText(display.getText().toString().substring((int)(0), (int)((display.getText().toString().length())-1)));
									_sensor();
									if (display.getText().toString().equals("") || display.getText().toString().equals("-")) {
										display.setText("0");
									}
								}
							}
						}
						break;
					}
					case ((int)MotionEvent.ACTION_UP): {
						if (display.getText().toString().equals("0")) {
							btnclear.setText("AC");
							operator = "null";
							_colorSwap(false, display);
						}
						break;
					}
				}
				return true;
			}
		});
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	@Override
	public void onBackPressed() {
		_sensor();
		if (getIntent().getStringExtra("line").equals("on")) {
			tele.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(tele);
			Animatoo.animateSwipeRight(MaincalcActivity.this);
			finish();
		}
		else {
			if (getIntent().getStringExtra("line").equals("off")) {
				finishAffinity();
			}
		}
	}
	public void _ui() {
		size = (SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) - 140) / 7;
		if (((size * 4) + 100) > SketchwareUtil.getDisplayWidthPixels(getApplicationContext())) {
			size = (SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) - 100) / 4;
		}
		btnclear.getLayoutParams().width=(int)((int) (size));
		btnclear.getLayoutParams().height=(int)((int) (size));
		btnclear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFAAAAAA));
		btnnegation.getLayoutParams().width=(int)((int) (size));
		btnnegation.getLayoutParams().height=(int)((int) (size));
		btnnegation.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFAAAAAA));
		btnpercent.getLayoutParams().width=(int)((int) (size));
		btnpercent.getLayoutParams().height=(int)((int) (size));
		btnpercent.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFAAAAAA));
		btndivide.getLayoutParams().width=(int)((int) (size));
		btndivide.getLayoutParams().height=(int)((int) (size));
		btndivide.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnseven.getLayoutParams().width=(int)((int) (size));
		btnseven.getLayoutParams().height=(int)((int) (size));
		btnseven.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btneight.getLayoutParams().width=(int)((int) (size));
		btneight.getLayoutParams().height=(int)((int) (size));
		btneight.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnnine.getLayoutParams().width=(int)((int) (size));
		btnnine.getLayoutParams().height=(int)((int) (size));
		btnnine.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnmultipliy.getLayoutParams().width=(int)((int) (size));
		btnmultipliy.getLayoutParams().height=(int)((int) (size));
		btnmultipliy.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnfour.getLayoutParams().width=(int)((int) (size));
		btnfour.getLayoutParams().height=(int)((int) (size));
		btnfour.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnfive.getLayoutParams().width=(int)((int) (size));
		btnfive.getLayoutParams().height=(int)((int) (size));
		btnfive.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnsix.getLayoutParams().width=(int)((int) (size));
		btnsix.getLayoutParams().height=(int)((int) (size));
		btnsix.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnsubtract.getLayoutParams().width=(int)((int) (size));
		btnsubtract.getLayoutParams().height=(int)((int) (size));
		btnsubtract.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnone.getLayoutParams().width=(int)((int) (size));
		btnone.getLayoutParams().height=(int)((int) (size));
		btnone.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btntwo.getLayoutParams().width=(int)((int) (size));
		btntwo.getLayoutParams().height=(int)((int) (size));
		btntwo.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnthree.getLayoutParams().width=(int)((int) (size));
		btnthree.getLayoutParams().height=(int)((int) (size));
		btnthree.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnadd.getLayoutParams().width=(int)((int) (size));
		btnadd.getLayoutParams().height=(int)((int) (size));
		btnadd.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		LinearLayout btnzero = (LinearLayout) findViewById(R.id.btnzero);
		btnzero.getLayoutParams().width=(int)((int) ((size * 2) + 20));
		btnzero.getLayoutParams().height=(int)((int) (size));
		btnzero.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		labzero.getLayoutParams().width=(int)((int) (size));
		labzero.getLayoutParams().height=(int)((int) (size));
		labzero.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, Color.TRANSPARENT));
		btndot.getLayoutParams().width=(int)((int) (size));
		btndot.getLayoutParams().height=(int)((int) (size));
		btndot.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
		btnequal.getLayoutParams().width=(int)((int) (size));
		btnequal.getLayoutParams().height=(int)((int) (size));
		btnequal.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnclear.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnnegation.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnpercent.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btndivide.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnseven.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btneight.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnnine.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnmultipliy.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnfour.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnfive.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnsix.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnsubtract.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnone.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btntwo.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnthree.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnadd.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btndot.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		labzero.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		btnequal.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		display.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)(size/2));
		display.getLayoutParams().width=(int)((int) ((size * 4) + 100));
		display.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
	}
	
	
	public void _solve() {
		arith cal= new arith();
		positive = "0";
		negative = "0";
		while(terms.contains("oper÷")){
			index = terms.indexOf("oper÷");
			s1 = terms.get((int)(index - 1));
			s2 = terms.get((int)(index + 1));
			if (s2.equals("0")) {
				display.setText("Error");
				terms.clear();
			}
			else {
				terms.set((int)index, cal.quo(s1, s2));
				terms.remove((int)(index + 1));
				terms.remove((int)(index - 1));
			}
		}
		while(terms.contains("oper×")){
			index = terms.indexOf("oper×");
			s1 = terms.get((int)(index - 1));
			s2 = terms.get((int)(index + 1));
			terms.set((int)index, cal.pro(s1, s2));
			terms.remove((int)(index + 1));
			terms.remove((int)(index - 1));
		}
		while(terms.contains("oper+")){
			terms.remove((int)(terms.indexOf("oper+")));
		}
		while(terms.contains("oper-")){
			index = terms.indexOf("oper-");
			if (terms.get((int)(index + 1)).contains("-")) {
				terms.set((int)index + 1, terms.get((int)(index + 1)).replace("-", ""));
			}
			else {
				terms.set((int)index + 1, "-".concat(terms.get((int)(index + 1))));
			}
			terms.remove((int)(index));
		}
		for(int _repeat89 = 0; _repeat89 < (int)(terms.size()); _repeat89++) {
			if (terms.get((int)(_repeat89)).contains("-")) {
				s1 = negative;
				s2 = terms.get((int)(_repeat89));
				negative = cal.add(s1,s2);
			}
			else {
				s1 = positive;
				s2 = terms.get((int)(_repeat89));
				positive = cal.add(s1,s2);
			}
		}
		if (!display.getText().toString().equals("Error")) {
			s1 = cal.add(positive,negative);
			display.setText(s1);
			terms.clear();
		}
	}
	
	
	public void _sensor() {
		if (local.contains("data")) {
			explode.clear();
			explode = new ArrayList<String>(Arrays.asList(crypto.decrypt(local.getString("data", ""),local.getString("digikey", "")).split(";")));
			if (!(explode.size() == 0)) {
				if (explode.get((int)(2)).equals("1")) {
					vibe.vibrate((long)(64));
				}
				if (explode.get((int)(1)).equals("1")) {
					typesound = effect.play((int)(1), 1.0f, 1.0f, 1, (int)(0), 1.0f);
				}
			}
			explode.clear();
		}
	}
	
	
	public void _colorSwap(final boolean _t, final TextView _view) {
		btnadd.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnsubtract.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnmultipliy.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btndivide.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnequal.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
		btnadd.setTextColor(0xFFFFFFFF);
		btnsubtract.setTextColor(0xFFFFFFFF);
		btnmultipliy.setTextColor(0xFFFFFFFF);
		btndivide.setTextColor(0xFFFFFFFF);
		btnequal.setTextColor(0xFFFFFFFF);
		if (_t) {
			_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFFFFFF));
			_view.setTextColor(0xFFFF9800);
		}
	}
	
	
	public void _btn1_9onclick(final String _str) {
		btnclear.setText("C");
		_colorSwap(false, display);
		if (display.getText().toString().equals("Error")) {
			display.setText("0");
		}
		if (operator.contains("oper")) {
			terms.add(display.getText().toString());
			display.setText("0");
			terms.add(operator);
		}
		if (display.getText().toString().length()<9) {
			if (display.getText().toString().equals("0")) {
				display.setText(_str);
				operator = "";
			}
			else {
				display.setText(display.getText().toString().concat(_str));
			}
		}
		_sensor();
	}
	
	
	public void _btnDMASonclick(final String _str, final TextView _txtview) {
		if (!display.getText().toString().equals("Error")) {
			if (operator.equals("") || operator.contains("oper")) {
				_colorSwap(true, _txtview);
				operator = _str;
			}
		}
		_sensor();
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