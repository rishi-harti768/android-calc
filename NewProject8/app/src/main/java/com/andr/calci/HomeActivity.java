package com.andr.calci;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.gdacciaro.iOSDialog.*;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import vpdotsindicator.mytelegram.sketchware95.*;
import android.view.inputmethod.*;
import android.provider.*;
import java.net.*;

public class HomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> log = new HashMap<>();
	private double tap = 0;
	private HashMap<String, Object> accountupdate = new HashMap<>();
	private HashMap<String, Object> accountget = new HashMap<>();
	public Cryptography crypto = new Cryptography();
	private HashMap<String, Object> toollet = new HashMap<>();
	private String tooljson = "";
	
	private ArrayList<String> explode = new ArrayList<>();
	private ArrayList<String> explode_get = new ArrayList<>();
	private ArrayList<String> explode_set = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tools = new ArrayList<>();
	
	private RelativeLayout content;
	private LinearLayout screen;
	private BlurView disability;
	private LinearLayout linear3;
	private LinearLayout dropIcon;
	private LinearLayout linear9;
	private LinearLayout linear5;
	private ImageView accountic;
	private LinearLayout uname;
	private LinearLayout empty;
	private ImageView icsettings;
	private ImageView icon;
	private LinearLayout linear10;
	private ImageView icshare;
	private LinearLayout linear11;
	private TabLayout indicator;
	private ViewPager viewpager1;
	private LinearLayout linear6;
	private LinearLayout undiplate;
	private LinearLayout stdiplate;
	private LinearLayout abdiplate;
	private LinearLayout Lddiplate;
	private TextView unHeader;
	private LinearLayout linear2;
	private LinearLayout linear7;
	private ImageView unicacc;
	private LinearLayout linear4;
	private TextView unuserid;
	private EditText unnewun;
	private TextView unbtncancel;
	private TextView unbtnconfirm;
	private TextView stheader;
	private Switch stsdsw;
	private Switch stvibsw;
	private TextView stbtnabout;
	private TextView stbtntnc;
	private TextView stbtnpp;
	private LinearLayout linear8;
	private TextView stbtnsout;
	private TextView stbtndone;
	private TextView abheader;
	private TextView abname;
	private TextView abcontact;
	private TextView abemail;
	private ProgressBar progress;
	private TextView reason;
	
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private SharedPreferences local;
	private TimerTask delay;
	private Vibrator vibe;
	private SoundPool sound;
	private Intent intent = new Intent();
	private TimerTask toolslide_delay;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		content = findViewById(R.id.content);
		screen = findViewById(R.id.screen);
		disability = findViewById(R.id.disability);
		linear3 = findViewById(R.id.linear3);
		dropIcon = findViewById(R.id.dropIcon);
		linear9 = findViewById(R.id.linear9);
		linear5 = findViewById(R.id.linear5);
		accountic = findViewById(R.id.accountic);
		uname = findViewById(R.id.uname);
		empty = findViewById(R.id.empty);
		icsettings = findViewById(R.id.icsettings);
		icon = findViewById(R.id.icon);
		linear10 = findViewById(R.id.linear10);
		icshare = findViewById(R.id.icshare);
		linear11 = findViewById(R.id.linear11);
		indicator = findViewById(R.id.indicator);
		viewpager1 = findViewById(R.id.viewpager1);
		linear6 = findViewById(R.id.linear6);
		undiplate = findViewById(R.id.undiplate);
		stdiplate = findViewById(R.id.stdiplate);
		abdiplate = findViewById(R.id.abdiplate);
		Lddiplate = findViewById(R.id.Lddiplate);
		unHeader = findViewById(R.id.unHeader);
		linear2 = findViewById(R.id.linear2);
		linear7 = findViewById(R.id.linear7);
		unicacc = findViewById(R.id.unicacc);
		linear4 = findViewById(R.id.linear4);
		unuserid = findViewById(R.id.unuserid);
		unnewun = findViewById(R.id.unnewun);
		unbtncancel = findViewById(R.id.unbtncancel);
		unbtnconfirm = findViewById(R.id.unbtnconfirm);
		stheader = findViewById(R.id.stheader);
		stsdsw = findViewById(R.id.stsdsw);
		stvibsw = findViewById(R.id.stvibsw);
		stbtnabout = findViewById(R.id.stbtnabout);
		stbtntnc = findViewById(R.id.stbtntnc);
		stbtnpp = findViewById(R.id.stbtnpp);
		linear8 = findViewById(R.id.linear8);
		stbtnsout = findViewById(R.id.stbtnsout);
		stbtndone = findViewById(R.id.stbtndone);
		abheader = findViewById(R.id.abheader);
		abname = findViewById(R.id.abname);
		abcontact = findViewById(R.id.abcontact);
		abemail = findViewById(R.id.abemail);
		progress = findViewById(R.id.progress);
		reason = findViewById(R.id.reason);
		net = new RequestNetwork(this);
		local = getSharedPreferences("local", Activity.MODE_PRIVATE);
		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		accountic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("none")) {
					_sensor();
					explode_get.clear();
					explode_get = new ArrayList<String>(Arrays.asList(crypto.decrypt(local.getString("data", ""),local.getString("digikey", "")).split(";")));
					unnewun.setText(explode_get.get((int)(0)));
					unuserid.setText("ID: ".concat(local.getString("ID", "")));
					explode_get.clear();
					_logy("username");
				}
			}
		});
		
		uname.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				accountic.performClick();
			}
		});
		
		icsettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("none")) {
					_sensor();
					explode.clear();
					explode = new ArrayList<String>(Arrays.asList(crypto.decrypt(local.getString("data", ""),local.getString("digikey", "")).split(";")));
					_logy("settings");
					if (explode.get((int)(1)).equals("0")) {
						stsdsw.setChecked(false);
					}
					else {
						if (explode.get((int)(1)).equals("1")) {
							stsdsw.setChecked(true);
						}
					}
					if (explode.get((int)(2)).equals("0")) {
						stvibsw.setChecked(false);
					}
					else {
						if (explode.get((int)(2)).equals("1")) {
							stvibsw.setChecked(true);
						}
					}
					explode.clear();
				}
			}
		});
		
		icshare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("none")) {
					_sensor();
					Intent i = new Intent(android.content.Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.serverurl).concat("download.php")); startActivity(Intent.createChooser(i,"Link"));
				}
			}
		});
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				log.put("tool_slide_delay", (double)(0));
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("settings")) {
					_sensor();
					_logy("dismiss");
				}
				else {
					if (log.get("home_current_dialog").toString().equals("about")) {
						_sensor();
						_logy("settings");
					}
				}
			}
		});
		
		stdiplate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		unuserid.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_sensor();
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", local.getString("ID", "")));
				SketchwareUtil.showMessage(getApplicationContext(), "ID has been copied to clipboard");
			}
		});
		
		unnewun.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.contains(";")) {
					unnewun.setText(_charSeq.replace(";", ""));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		unbtncancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("username")) {
					_sensor();
					_logy("dismiss");
				}
			}
		});
		
		unbtnconfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("username")) {
					_sensor();
					_closekeys();
					if (!unnewun.getText().toString().equals(((AutoTypeTextView)uname.getChildAt((int)0)).getText().toString())) {
						explode_get.clear();
						explode_get = new ArrayList<String>(Arrays.asList(crypto.decrypt(local.getString("data", ""),local.getString("digikey", "")).split(";")));
						local.edit().putString("data", crypto.encrypt(unnewun.getText().toString().concat(";".concat(explode_get.get((int)(1)).concat(";".concat(explode_get.get((int)(2)))))),local.getString("digikey", ""))).commit();
						explode_set.clear();
						log.put("home_tick", (double)(0));
						accountupdate.clear();
						accountupdate.put("PIN", getString(R.string.webpin));
						accountupdate.put("ID", local.getString("ID", ""));
						accountupdate.put("password", local.getString("password", ""));
						accountupdate.put("device", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
						accountupdate.put("data", local.getString("data", ""));
						accountupdate.put("version", local.getString("swVersion", ""));
						_secureHttp(accountupdate, "accountset.php", "accountset");
						_setUserName();
					}
					_logy("dismiss");
				}
			}
		});
		
		stsdsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					tap = sound.play((int)(1), 1.0f, 1.0f, 1, (int)(0), 1.0f);
				}
			}
		});
		
		stvibsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					vibe.vibrate((long)(64));
				}
			}
		});
		
		stbtnabout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("settings")) {
					_sensor();
					_logy("about");
				}
			}
		});
		
		stbtntnc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("settings")) {
					_sensor();
					intent.setClass(getApplicationContext(), WebdocsActivity.class);
					intent.putExtra("webDoc", "TnC");
					startActivity(intent);
					Animatoo.animateSwipeLeft(HomeActivity.this);
				}
			}
		});
		
		stbtnpp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("settings")) {
					_sensor();
					intent.setClass(getApplicationContext(), WebdocsActivity.class);
					intent.putExtra("webDoc", "PP");
					startActivity(intent);
					Animatoo.animateSwipeLeft(HomeActivity.this);
				}
			}
		});
		
		stbtnsout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("settings")) {
					_sensor();
					_logy("dismiss");
					delay.cancel();
					log.put("home_tick", (double)(0));
					local.edit().putString("ID", "0").commit();
					if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/resource.dat"))) {
						FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/resource.dat"), crypto.encrypt(crypto.decrypt(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/resource.dat")),local.getString("digikey", "")),crypto.keyGen(Build.ID)));
					}
					if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/source.dat"))) {
						FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/source.dat"), crypto.encrypt(crypto.decrypt(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/source.dat")),local.getString("digikey", "")),crypto.keyGen(Build.ID)));
					}
					startActivity(new Intent(HomeActivity.this, MainActivity.class)); Animatoo.animateFade(HomeActivity.this);
					finish();
				}
			}
		});
		
		stbtndone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (log.get("home_current_dialog").toString().equals("settings")) {
					explode_get.clear();
					explode_get = new ArrayList<String>(Arrays.asList(crypto.decrypt(local.getString("data", ""),local.getString("digikey", "")).split(";")));
					if (stsdsw.isChecked()) {
						explode_get.set((int)1, "1");
					}
					else {
						explode_get.set((int)1, "0");
					}
					if (stvibsw.isChecked()) {
						explode_get.set((int)2, "1");
					}
					else {
						explode_get.set((int)2, "0");
					}
					local.edit().putString("data", crypto.encrypt(explode_get.get((int)(0)).concat(";".concat(explode_get.get((int)(1)).concat(";".concat(explode_get.get((int)(2)))))),local.getString("digikey", ""))).commit();
					explode_get.clear();
					_sensor();
					log.put("home_tick", (double)(0));
					accountupdate.clear();
					accountupdate.put("PIN", getString(R.string.webpin));
					accountupdate.put("ID", local.getString("ID", ""));
					accountupdate.put("password", local.getString("password", ""));
					accountupdate.put("device", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
					accountupdate.put("data", local.getString("data", ""));
					accountupdate.put("version", local.getString("swVersion", ""));
					_secureHttp(accountupdate, "accountset.php", "accountset");
					_logy("dismiss");
				}
			}
		});
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (!local.getString("ID", "").equals("0")) {
					if (_tag.equals("accountget")) {
						if (_response.startsWith("E")) {
							SketchwareUtil.showMessage(getApplicationContext(), "Server connection lost.\nError code: ".concat(_response));
						}
						else {
							accountget.clear();
							accountget = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
							if (accountget.containsKey("data")) {
								local.edit().putString("data", accountget.get("data").toString()).commit();
								_setUserName();
							}
							else {
								delay.cancel();
								log.put("home_tick", (double)(0));
								startActivity(new Intent(HomeActivity.this, MainActivity.class)); Animatoo.animateFade(HomeActivity.this);
								finish();
							}
						}
					}
					else {
						if (_tag.equals("accountset")) {
							if (_response.startsWith("E")) {
								SketchwareUtil.showMessage(getApplicationContext(), "Server connection lost.\nError code: ".concat(_response));
							}
							else {
								accountget.clear();
								accountget = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
								if (accountget.containsKey("data")) {
									local.edit().putString("data", accountget.get("data").toString()).commit();
									_setUserName();
								}
								if (accountget.containsKey("forcestop")) {
									if (accountget.get("forcestop").toString().equals("1")) {
										delay.cancel();
										log.put("home_tick", (double)(0));
										startActivity(new Intent(HomeActivity.this, MainActivity.class)); Animatoo.animateFade(HomeActivity.this);
										finish();
									}
								}
							}
						}
						else {
							
						}
					}
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				delay.cancel();
				log.put("home_tick", (double)(0));
				startActivity(new Intent(HomeActivity.this, MainActivity.class)); Animatoo.animateFade(HomeActivity.this);
				finish();
			}
		};
	}
	
	private void initializeLogic() {
		_design();
		sound = new SoundPool((int)(1), AudioManager.STREAM_MUSIC, 0);
		tap = sound.load(getApplicationContext(), R.raw.ioseffect, 1);
		uname.addView(new AutoTypeTextView(HomeActivity.this));
		((AutoTypeTextView)uname.getChildAt((int)0)).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
		unnewun.setOnEditorActionListener(new TextView.OnEditorActionListener()
		{
			    @Override
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			    {
				        if(actionId== 6)
				        {
					            unbtnconfirm.performClick();
					        }
				        return false;
				    }
		});
		log.put("home_current_dialog", "none");
		log.put("home_recursive", "");
		log.put("home_data_updating", "no");
		_logy("dismiss");
		_setUserName();
		_drawbtn();
		if (local.getString("T&C_agreement", "").equals("0")) {
			intent.setClass(getApplicationContext(), WebdocsActivity.class);
			intent.putExtra("webDoc", "TnC");
			startActivity(intent);
			Animatoo.animateSwipeLeft(HomeActivity.this);
		}
		else {
			if (local.getString("PriPo_agreement", "").equals("0")) {
				intent.setClass(getApplicationContext(), WebdocsActivity.class);
				intent.putExtra("webDoc", "PP");
				startActivity(intent);
				Animatoo.animateSwipeLeft(HomeActivity.this);
			}
		}
		if (getIntent().hasExtra("net_inits")) {
			log.put("home_tick", (double)(0));
			/*
delay = new TimerTask() {
@Override
public void run() {
runOnUiThread(new Runnable() {
@Override
public void run() {
accountget.clear();
accountget.put("PIN", getString(R.string.webpin));
accountget.put("ID", local.getString("ID", ""));
accountget.put("password", local.getString("password", ""));
accountget.put("device", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
accountget.put("version", local.getString("swVersion", ""));
accountget.put("request", "|data|");
net.setParams(accountget, RequestNetworkController.REQUEST_PARAM);
net.startRequestNetwork(RequestNetworkController.POST, getString(R.string.serverurl).concat("accountget.php"), "accountget", _net_request_listener);
}
});
}
};
_timer.schedule(delay, (int)(16384));
*/
			delay = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							log.put("home_tick", (double)((double)log.get("home_tick") + 1));
							if ((double)log.get("home_tick") > 255) {
								log.put("home_tick", (double)(0));
								accountget.clear();
								accountget.put("PIN", getString(R.string.webpin));
								accountget.put("ID", local.getString("ID", ""));
								accountget.put("password", local.getString("password", ""));
								accountget.put("device", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
								accountget.put("version", local.getString("swVersion", ""));
								accountget.put("request", "|data|");
								_secureHttp(accountget, "accountget.php", "accountget");
							}
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(delay, (int)(0), (int)(64));
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		/*
if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
    SketchwareUtil.showMessage(getApplicationContext(), "yes");
} else {
    SketchwareUtil.showMessage(getApplicationContext(), "no");
}
*/
		log.put("tool_slide_delay", (double)(0));
		toolslide_delay = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						log.put("tool_slide_delay", (double)((double)log.get("tool_slide_delay") + 1));
						if ((double)log.get("tool_slide_delay") == 8) {
							log.put("tool_slide_delay", (double)(0));
							if (viewpager1.getCurrentItem() == 1) {
								viewpager1.setCurrentItem((int)0);
							}
							else {
								viewpager1.setCurrentItem((int)viewpager1.getCurrentItem() + 1);
							}
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(toolslide_delay, (int)(0), (int)(1024));
	}
	
	@Override
	public void onBackPressed() {
		if (log.get("home_current_dialog").toString().equals("none")) {
			_sensor();
			iOSDialogBuilder ia = new iOSDialogBuilder(HomeActivity.this);
			ia.setTitle("Exit?");
			ia.setSubtitle("Are you sure that you want to exit?");
			ia.setBoldPositiveLabel(true);
			ia.setCancelable(false);
			ia.setPositiveListener("Exit",new iOSDialogClickListener() 
			{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
					_sensor();
					finishAffinity();	 
				}
			})	;
			ia.setNegativeListener("Cancel", new iOSDialogClickListener() 
			{ 	 
				@Override 	 public void onClick(iOSDialog dialog) { 		
					_sensor();
					dialog.dismiss(); 
					getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
				}
			});
			ia.build().show();
		}
		else {
			if (log.get("home_current_dialog").toString().equals("username") || log.get("home_current_dialog").toString().equals("settings")) {
				_sensor();
			}
			else {
				if (log.get("home_current_dialog").toString().equals("about")) {
					_sensor();
				}
			}
		}
	}
	
	public void _sensor() {
		explode_get.clear();
		explode_get = new ArrayList<String>(Arrays.asList(crypto.decrypt(local.getString("data", ""),local.getString("digikey", "")).split(";")));
		if (explode_get.get((int)(1)).equals("1")) {
			tap = sound.play((int)(1), 1.0f, 1.0f, 1, (int)(0), 1.0f);
		}
		if (explode_get.get((int)(2)).equals("1")) {
			vibe.vibrate((long)(64));
		}
		explode_get.clear();
	}
	
	
	public void _design() {
		float radius = 32;
		View decorView = getWindow().getDecorView();
		 ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
		 Drawable windowBackground = decorView.getBackground();
		 disability.setupWith(rootView, new RenderScriptBlur(this));
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		LinearLayout dropIcon = (LinearLayout) findViewById(R.id.dropIcon);
		dropIcon.getLayoutParams().width=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()));
		dropIcon.getLayoutParams().height=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 2);
		dropIcon.setBackgroundDrawable(getResources().getDrawable(R.drawable.homedrop));
		icon.requestLayout();
		icon.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 5;
		icon.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 5;
		icon.setElevation((float)128);
		accountic.requestLayout();
		accountic.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		accountic.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		icsettings.requestLayout();
		icsettings.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		icsettings.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		undiplate.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)16, (int)2, 0xFFFF9800, 0xFF000000));
		LinearLayout undiplate = (LinearLayout) findViewById(R.id.undiplate);
		undiplate.getLayoutParams().width=(int)((SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) * 4) / 5);
		undiplate.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		unHeader.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/24);
		unicacc.requestLayout();
		unicacc.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		unicacc.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		unuserid.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		unnewun.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		unbtncancel.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		unbtnconfirm.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		unbtncancel.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFFFF9800, 0xFF000000));
		unbtnconfirm.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFF000000, 0xFFFF9800));
		stdiplate.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)16, (int)2, 0xFFFF9800, 0xFF000000));
		LinearLayout stdiplate = (LinearLayout) findViewById(R.id.stdiplate);
		stdiplate.getLayoutParams().width=(int)((SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) * 4) / 5);
		stdiplate.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		stheader.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/24);
		stsdsw.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		stvibsw.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		stbtnabout.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		stbtntnc.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		stbtnpp.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		stbtnsout.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		stbtndone.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		stbtnsout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFFFF9800, 0xFF000000));
		stbtndone.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFF000000, 0xFFFF9800));
		abdiplate.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)16, (int)2, 0xFFFF9800, 0xFF000000));
		LinearLayout abdiplate = (LinearLayout) findViewById(R.id.abdiplate);
		abdiplate.getLayoutParams().width=(int)((SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) * 4) / 5);
		abdiplate.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		abheader.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/24);
		abname.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		abcontact.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		abemail.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
		icshare.requestLayout();
		icshare.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		icshare.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 10;
		LinearLayout linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear11.getLayoutParams().width=(int)(android.widget.LinearLayout.LayoutParams.MATCH_PARENT);
		linear11.getLayoutParams().height=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 8);
		viewpager1.setPageTransformer(true, new BackgroundToForegroundTransformer());
	}
	
	
	public void _autotype() {
	}
	public static class AutoTypeTextView extends TextView {
		
		    public static int PRECISSION_LOW = 8;
		    public static int PRECISSION_MED = 9;
		    public static int PRECISSION_HIGH = 11;
		    
		    private int decryptionSpeed = 10;
		    private int encryptionSpeed = 10;
		    private int typingSpeed =100;
		    private int precision = 5;
		    private String animateEncryption = "";
		    private String animateDecryption = "";
		    private String animateTextTyping = "";
		    private String animateTextTypingWithMistakes = "";
		
		    private Handler handler;
		    private int counter=0;
		    private boolean misstakeFound = false;
		    private boolean executed = false;
		    private Random ran = new Random();
		    public String misstakeValues = "qwertyuiop[]asdfghjkl;zxcvbnm,./!@#$^&*()_+1234567890";
		    private String encryptedText;
		    private int countLetter=0;
		    private int cocatation=0;
		
		    public AutoTypeTextView(Context context) {
			        super(context);
			    }
		
		    public AutoTypeTextView(Context context, AttributeSet attrs) {
			        super(context, attrs);
			    }
		
		    private void setupAttributes() {
			        if(animateTextTyping!=null)
			            setTextAutoTyping(animateTextTyping);
			
			        if(animateTextTypingWithMistakes!=null) {
				            if (precision < 6)
				                precision = 6;
				            setTextAutoTypingWithMistakes(animateTextTypingWithMistakes, precision);
				        }
			
			        if(animateDecryption!=null)
			            animateDecryption(animateDecryption);
			
			        if(animateEncryption!=null)
			            animateEncryption(animateEncryption);
			    }
		
		    public void setTextAutoTyping(final String text) {
			        if(!executed) {
				            executed = true;
				            counter = 0;
				            handler = new Handler();
				            handler.postDelayed(new Runnable() {
					                @Override
					                public void run() {
						                    setText(text.substring(0, counter));
						                    counter++;
						                    if (text.length() >= counter) {
							                        postDelayed(this, getTypingSpeed());
							                    } else {
							                        executed = false;
							                    }
						                }
					            }, getTypingSpeed());
				        }
			    }
		
		    public void setTextAutoTypingWithMistakes(final String text, final int precission) {
			        if(!executed) {
				            executed = true;
				            counter = 0;
				            handler = new Handler();
				            ran = new Random();
				            handler.postDelayed(new Runnable() {
					                @Override
					                public void run() {
						                    int num = ran.nextInt(10) + 1;
						                    if (num > precission && counter > 1 && !misstakeFound) {
							                        setText(chooseTypeOfMistake(text, counter));
							                        counter--;
							                    } else {
							                        counter++;
							                        setText(text.substring(0, counter));
							                        misstakeFound = false;
							                    }
						                    if (text.length() > counter) {
							                        postDelayed(this, getTypingSpeed());
							                    } else {
							                        executed = false;
							                    }
						                }
					            }, getTypingSpeed());
				        }
			    }
		
		    public void animateDecryption(final String text) {
			        encryptedText = text;
			        ran = new Random();
			        handler = new Handler();
			        cocatation = ran.nextInt(10);
			        counter = 0;
			        countLetter = 0;
			        if(!executed) {
				            executed = true;
				            for(int i=0; i<text.length(); i++) {
					                encryptedText = replaceCharAt(encryptedText, i, misstakeValues.charAt(ran.nextInt(misstakeValues.length())));
					                setText(encryptedText);
					            }
				                handler = new Handler();
				                handler.postDelayed(new Runnable() {
					                    @Override
					                    public void run() {
						                        if(counter <= cocatation) {
							                            encryptedText = replaceCharAt(encryptedText,countLetter,misstakeValues.charAt(ran.nextInt(misstakeValues.length())));
							                            setText(encryptedText);
							                            counter++;
							                        } else {
							                            encryptedText = replaceCharAt(encryptedText, countLetter, text.charAt(countLetter));
							                            setText(encryptedText);
							                            countLetter++;
							                            cocatation = ran.nextInt(10);
							                            counter = 0;
							                        }
						                        if(text.length() > countLetter) {
							                            postDelayed(this, getDecryptionSpeed());
							                        } else {
							                            executed = false;
							                        }
						                    }
					                }, getDecryptionSpeed());
				        }
			    }
		
		    public void animateEncryption(final String text) {
			        encryptedText = text;
			        ran = new Random();
			        handler = new Handler();
			        cocatation = ran.nextInt(10);
			        counter = 0;
			        countLetter = 0;
			        if(!executed) {
				            executed = true;
				            handler = new Handler();
				            handler.postDelayed(new Runnable() {
					                @Override
					                public void run() {
						                    if(counter <= cocatation) {
							                        encryptedText = replaceCharAt(encryptedText,countLetter,misstakeValues.charAt(ran.nextInt(misstakeValues.length())));
							                        setText(encryptedText);
							                        counter++;
							                    } else {
							                        countLetter++;
							                        cocatation = ran.nextInt(10);
							                        counter = 0;
							                    }
						                    if(text.length() > countLetter) {
							                        postDelayed(this, getDecryptionSpeed());
							                    } else {
							                        executed = false;
							                    }
						                }
					            }, getDecryptionSpeed());
				        }
			    }
		
		    private String chooseTypeOfMistake(String text, int counter) {
			        int misstake = ran.nextInt(3)+1;
			        String result = text.substring(0,counter);
			        switch(misstake) {
				            case 1 :
				                result = text.substring(0,counter-1) + randomChar();
				                break;
				            case 2 :
				                switch (ran.nextInt(2)+1) {
					                    case 1:
					                        result = text.substring(0, counter - 1) + String.valueOf(text.charAt(counter)).toLowerCase();
					                        break;
					                    case 2:
					                        result = text.substring(0, counter-1) + String.valueOf(text.charAt(counter)).toUpperCase();
					                        break;
					                }
				                break;
				            case 3 :
				                result = text.substring(0, counter-1);
				                break;
				        }
			        misstakeFound = true;
			        return result;
			    }
		
		    private char randomChar() {
			        return misstakeValues.charAt(ran.nextInt(misstakeValues.length()));
			    }
		
		    public static String replaceCharAt(String text, int pos, char c) {
			        return text.substring(0, pos) + c + text.substring(pos + 1);
			    }
		
		    public int getTypingSpeed() {
			        return typingSpeed;
			    }
		
		    public void setTypingSpeed(int typingSpeed) {
			        this.typingSpeed = typingSpeed;
			    }
		
		    public int getDecryptionSpeed() {
			        return decryptionSpeed;
			    }
		
		    public void setDecryptionSpeed(int decryptionSpeed) {
			        this.decryptionSpeed = decryptionSpeed;
			    }
		
		    public int getEncryptionSpeed() {
			        return encryptionSpeed;
			    }
		
		    public void setEncryptionSpeed(int encryptionSpeed) {
			        this.encryptionSpeed = encryptionSpeed;
			    }
		
		    public boolean isRunning() {
			        return executed;
			    }
	}
	{
	}
	
	
	public void _setUserName() {
		explode_get.clear();
		explode_get = new ArrayList<String>(Arrays.asList(crypto.decrypt(local.getString("data", ""),local.getString("digikey", "")).split(";")));
		log.put("home_current_name", explode_get.get((int)(0)));
		explode_get.clear();
		if (!((AutoTypeTextView)uname.getChildAt((int)0)).getText().toString().equals(log.get("home_current_name").toString())) {
			((AutoTypeTextView)uname.getChildAt((int)0)).setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
			((AutoTypeTextView)uname.getChildAt((int)0)).setTextColor(0xFFFFFFFF);
			((AutoTypeTextView)uname.getChildAt((int)0)).setText("");
			delay = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							((AutoTypeTextView)uname.getChildAt((int)0)).setTypingSpeed((int) 64);
							((AutoTypeTextView)uname.getChildAt((int)0)).setTextAutoTyping(log.get("home_current_name").toString());
						}
					});
				}
			};
			_timer.schedule(delay, (int)(256));
		}
	}
	
	
	public void _selection() {
		
	}
	
	
	public void _logy(final String _type) {
		undiplate.setVisibility(View.GONE);
		stdiplate.setVisibility(View.GONE);
		abdiplate.setVisibility(View.GONE);
		Lddiplate.setVisibility(View.GONE);
		if (_type.equals("dismiss")) {
			log.put("home_current_dialog", "none");
			screen.setZ((float) (1));
			disability.setZ((float) (-1));
		}
		else {
			disability.setZ((float) (1));
			screen.setZ((float) (-1));
			if (_type.equals("username")) {
				log.put("home_current_dialog", "username");
				undiplate.setVisibility(View.VISIBLE);
			}
			else {
				if (_type.equals("settings")) {
					log.put("home_current_dialog", "settings");
					stdiplate.setVisibility(View.VISIBLE);
				}
				else {
					if (_type.equals("about")) {
						log.put("home_current_dialog", "about");
						abdiplate.setVisibility(View.VISIBLE);
					}
					else {
						if (_type.equals("load")) {
							log.put("home_current_dialog", "load");
							Lddiplate.setVisibility(View.VISIBLE);
						}
					}
				}
			}
		}
	}
	
	
	public void _closekeys() {
		View view = this.getCurrentFocus();
		if (view != null)
		{
			    InputMethodManager manager= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			    manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	
	
	public void _drawbtn() {
		/*
.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);


.getLayoutParams().width=(int)((SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) * 3) / 4);
.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
.getLayoutParams().width=(int)((SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) * 3) / 4);
.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0x88888888));
if (!local.getString("base_converter", "").equals("NaN")) {

.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)999, 0xFFFF9800));
}
*/
		tools.clear();
		toollet.clear();
		toollet = new HashMap<>();
		{
			try{
					java.io.InputStream tooljsonIn = HomeActivity.this.getAssets().open("tools/tool1.json");
					           int tooljsonSi = tooljsonIn.available();
					           byte[] tooljsonBu = new byte[tooljsonSi];
					           tooljsonIn.read(tooljsonBu);
					           tooljsonIn.close();
					           tooljson = new String(tooljsonBu, "UTF-8");
			}catch(Exception e){
					 
			}
		}
		toollet = new Gson().fromJson(tooljson, new TypeToken<HashMap<String, Object>>(){}.getType());
		tools.add(toollet);
		toollet = new HashMap<>();
		{
			try{
					java.io.InputStream tooljsonIn = HomeActivity.this.getAssets().open("tools/tool2.json");
					           int tooljsonSi = tooljsonIn.available();
					           byte[] tooljsonBu = new byte[tooljsonSi];
					           tooljsonIn.read(tooljsonBu);
					           tooljsonIn.close();
					           tooljson = new String(tooljsonBu, "UTF-8");
			}catch(Exception e){
					 
			}
		}
		toollet = new Gson().fromJson(tooljson, new TypeToken<HashMap<String, Object>>(){}.getType());
		toollet.put("src", local.getString("base_converter", ""));
		tools.add(toollet);
		if (toollet.get("src").toString().equals("hidden")) {
			tools.remove((int)(1));
		}
		viewpager1.setAdapter(new Viewpager1Adapter(tools));
		/*
GestureDetectorCompat tgd= new GestureDetectorCompat(this, new TapGestureListener());
viewpager1.setOnTouchListener(new View.OnTouchListener()
{
	@Override
	public boolean onTouch(View view,MotionEvent event)
	{
		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
     
break;
case MotionEvent.ACTION_UP:
    SketchwareUtil.showMessage(getApplicationContext(), "clicked");
break;
case MotionEvent.ACTION_MOVE:
    return (false);
break;
		}
		return true;
	}
});
*/
		indicator.setupWithViewPager(viewpager1,true);
		viewpager1.setOnTouchListener(new OnTouchListener()
		{
				float fX=0,lX=0,fY=0,lY=0, mag=0;
				@Override
				public boolean onTouch(View view,MotionEvent event)
				{
						switch(event.getAction())
						{
								case MotionEvent.ACTION_DOWN:
									fX=event.getX();
									fY=event.getY();
								break;
								case MotionEvent.ACTION_UP:
									lX=event.getX();
									lY=event.getY();
									mag=(float)Math.sqrt(Math.pow((fX-lX),2)+Math.pow((fY-lY),2));
									if(mag<=32)
									{
											_toolsClicked();
									}
								break;
								case MotionEvent.ACTION_MOVE:
									return false;
						}
						return false;
				}
		});
	}
	
	
	public void _lib1() {
	}
	public static abstract class BaseTransformer implements androidx.viewpager.widget.ViewPager.PageTransformer {
			protected abstract void onTransform(View view, float position);
			@Override
			public void transformPage(View view, float position) {
					onPreTransform(view, position);
					onTransform(view, position);
					onPostTransform(view, position);
			}
			protected boolean hideOffscreenPages() {
					return true;
			}
			protected boolean isPagingEnabled() {
					return false;
			}
			protected void onPreTransform(View view, float position) {
					final float width = view.getWidth();
					view.setRotationX(0);
					view.setRotationY(0);
					view.setRotation(0);
					view.setScaleX(1);
					view.setScaleY(1);
					view.setPivotX(0);
					view.setPivotY(0);
					view.setTranslationY(0);
					view.setTranslationX(isPagingEnabled() ? 0f : -width * position);
					if (hideOffscreenPages()) {
							view.setAlpha(position <= -1f || position >= 1f ? 0f : 1f);
					} else {
							view.setAlpha(1f);
					}
			}
			protected void onPostTransform(View view, float position) {
			}
	}
	public static class AccordionTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					view.setPivotX(position < 0 ? 0 : view.getWidth());
					view.setScaleX(position < 0 ? 1f + position : 1f - position);
			}
	}
	public static class BackgroundToForegroundTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					final float height = view.getHeight();
					final float width = view.getWidth();
					final float scale = min(position < 0 ? 1f : Math.abs(1f - position), 0.5f);
					view.setScaleX(scale);
					view.setScaleY(scale);
					view.setPivotX(width * 0.5f);
					view.setPivotY(height * 0.5f);
					view.setTranslationX(position < 0 ? width * position : -width * position * 0.25f);
			}
			private static final float min(float val, float min) {
					return val < min ? min : val;
			}
	}
	public static class CubeInTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					view.setPivotX(position > 0 ? 0 : view.getWidth());
					view.setPivotY(0);
					view.setRotationY(-90f * position);
			}
			@Override
			public boolean isPagingEnabled() {
					return true;
			}
	}
	public static class CubeOutTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					view.setPivotX(position < 0f ? view.getWidth() : 0f);
					view.setPivotY(view.getHeight() * 0.5f);
					view.setRotationY(90f * position);
			}
			@Override
			public boolean isPagingEnabled() {
					return true;
			}
	}
	public static class DefaultTransformer extends BaseTransformer {
			@Override protected void onTransform(View view, float position) {}
			@Override public boolean isPagingEnabled() {
					return true;
			}
	}
	public static class DepthPageTransformer extends BaseTransformer {
			private static final float MIN_SCALE = 0.75f;
			@Override
			protected void onTransform(View view, float position) {
					if (position <= 0f) {
							view.setTranslationX(0f);
							view.setScaleX(1f);
							view.setScaleY(1f);
					} else if (position <= 1f) {
							final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
							view.setAlpha(1 - position);
							view.setPivotY(0.5f * view.getHeight());
							view.setTranslationX(view.getWidth() * -position);
							view.setScaleX(scaleFactor);
							view.setScaleY(scaleFactor);
					}
			}
			@Override
			protected boolean isPagingEnabled() {
					return true;
			}
	}
	public static class ZoomOutTranformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					final float scale = 1f + Math.abs(position);
					view.setScaleX(scale);
					view.setScaleY(scale);
					view.setPivotX(view.getWidth() * 0.5f);
					view.setPivotY(view.getHeight() * 0.5f);
					view.setAlpha(position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
					if(position == -1){
							view.setTranslationX(view.getWidth() * -1);
					}
			}
	}
	public static class StackTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					view.setTranslationX(position < 0 ? 0f : -view.getWidth() * position);
			}
	}
	public static class TabletTransformer extends BaseTransformer {
			private static final Matrix OFFSET_MATRIX = new Matrix();
			private static final Camera OFFSET_CAMERA = new Camera();
			private static final float[] OFFSET_TEMP_FLOAT = new float[2];
			@Override
			protected void onTransform(View view, float position) {
					final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);
					view.setTranslationX(getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
					view.setPivotX(view.getWidth() * 0.5f);
					view.setPivotY(0);
					view.setRotationY(rotation);
			}
			protected static final float getOffsetXForRotation(float degrees, int width, int height) {
					OFFSET_MATRIX.reset();
					OFFSET_CAMERA.save();
					OFFSET_CAMERA.rotateY(Math.abs(degrees));
					OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
					OFFSET_CAMERA.restore();
					OFFSET_MATRIX.preTranslate(-width * 0.5f, -height * 0.5f);
					OFFSET_MATRIX.postTranslate(width * 0.5f, height * 0.5f);
					OFFSET_TEMP_FLOAT[0] = width;
					OFFSET_TEMP_FLOAT[1] = height;
					OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
					return (width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
			}
	}
	public static class ZoomInTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					final float scale = position < 0 ? position + 1f : Math.abs(1f - position);
					view.setScaleX(scale);
					view.setScaleY(scale);
					view.setPivotX(view.getWidth() * 0.5f);
					view.setPivotY(view.getHeight() * 0.5f);
					view.setAlpha(position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
			}
	}
	public static class ZoomOutSlideTransformer extends BaseTransformer {
			private static final float MIN_SCALE = 0.85f;
			private static final float MIN_ALPHA = 0.5f;
			@Override
			protected void onTransform(View view, float position) {
					if (position >= -1 || position <= 1) {
							final float height = view.getHeight();
							final float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
							final float vertMargin = height * (1 - scaleFactor) / 2;
							final float horzMargin = view.getWidth() * (1 - scaleFactor) / 2;
							view.setPivotY(0.5f * height);
							if (position < 0) {
									view.setTranslationX(horzMargin - vertMargin / 2);
							} else {
									view.setTranslationX(-horzMargin + vertMargin / 2);
							}
							view.setScaleX(scaleFactor);
							view.setScaleY(scaleFactor);
							view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
					}
			}
	}
	public static class ForegroundToBackgroundTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					final float height = view.getHeight();
					final float width = view.getWidth();
					final float scale = min(position > 0 ? 1f : Math.abs(1f + position), 0.5f);
					view.setScaleX(scale);
					view.setScaleY(scale);
					view.setPivotX(width * 0.5f);
					view.setPivotY(height * 0.5f);
					view.setTranslationX(position > 0 ? width * position : -width * position * 0.25f);
			}
			private static final float min(float val, float min) {
					return val < min ? min : val;
			}
	}
	public static class ParallaxPageTransformer extends BaseTransformer {
		    private final int viewToParallax;
		    public ParallaxPageTransformer(final int viewToParallax) {
			        this.viewToParallax = viewToParallax;
			    }
		    @Override
		    protected void onTransform(View view, float position) {
			        int pageWidth = view.getWidth();
			        if (position < -1) {
				            view.setAlpha(1);
				        } else if (position <= 1) {
				            view.findViewById(viewToParallax).setTranslationX(-position * (pageWidth / 2));
				        } else {
				            view.setAlpha(1);
				        }
			    }
	}
	public static class RotateDownTransformer extends BaseTransformer {
			private static final float ROT_MOD = -15f;
			@Override
			protected void onTransform(View view, float position) {
					final float width = view.getWidth();
					final float height = view.getHeight();
					final float rotation = ROT_MOD * position * -1.25f;
					view.setPivotX(width * 0.5f);
					view.setPivotY(height);
					view.setRotation(rotation);
			}
			@Override
			protected boolean isPagingEnabled() {
					return true;
			}
	}
	public static class RotateUpTransformer extends BaseTransformer {
			private static final float ROT_MOD = -15f;
			@Override
			protected void onTransform(View view, float position) {
					final float width = view.getWidth();
					final float rotation = ROT_MOD * position;
					view.setPivotX(width * 0.5f);
					view.setPivotY(0f);
					view.setTranslationX(0f);
					view.setRotation(rotation);
			}
			@Override
			protected boolean isPagingEnabled() {
					return true;
			}
	}
	public static class DrawFromBackTransformer implements androidx.viewpager.widget.ViewPager.PageTransformer {
			private static final float MIN_SCALE = 0.75f;
			@Override
			public void transformPage(View view, float position) {
					int pageWidth = view.getWidth();
					if (position < -1 || position > 1) {
							view.setAlpha(0);
							return;
					}
					if (position <= 0) {
							view.setAlpha(1 + position);
							view.setTranslationX(pageWidth * -position);
							float scaleFactor = MIN_SCALE
									+ (1 - MIN_SCALE) * (1 - Math.abs(position));
							view.setScaleX(scaleFactor);
							view.setScaleY(scaleFactor);
							return;
					}
					if (position > 0.5 && position <= 1) {
							view.setAlpha(0);
							view.setTranslationX(pageWidth * -position);
							return;
					}
					if (position > 0.3 && position <= 0.5) {
							view.setAlpha(1);
							view.setTranslationX(pageWidth * position);
							float scaleFactor = MIN_SCALE;
							view.setScaleX(scaleFactor);
							view.setScaleY(scaleFactor);
							return;
					}
					if (position <= 0.3) {
							view.setAlpha(1);
							view.setTranslationX(pageWidth * position);
							float v = (float) (0.3 - position);
							v = v >= 0.25f ? 0.25f : v;
							float scaleFactor = MIN_SCALE + v;
							view.setScaleX(scaleFactor);
							view.setScaleY(scaleFactor);
					}
			}
	}
	public static class FlipHorizontalTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					final float rotation = 180f * position;
					view.setVisibility(rotation > 90f || rotation < -90f ? View.INVISIBLE : View.VISIBLE);
					view.setPivotX(view.getWidth() * 0.5f);
					view.setPivotY(view.getHeight() * 0.5f);
					view.setRotationY(rotation);
			}
	}
	public static class FlipVerticalTransformer extends BaseTransformer {
			@Override
			protected void onTransform(View view, float position) {
					final float rotation = -180f * position;
					view.setVisibility(rotation > 90f || rotation < -90f ? View.INVISIBLE : View.VISIBLE);
					view.setPivotX(view.getWidth() * 0.5f);
					view.setPivotY(view.getHeight() * 0.5f);
					view.setRotationX(rotation);
			}
	}
	{
	}
	
	
	public void _toolsClicked() {
		if (log.get("home_current_dialog").toString().equals("none")) {
			toollet = new HashMap<>();
			toollet = tools.get((int)viewpager1.getCurrentItem());
			if (toollet.get("code_name").toString().equals("simple_calc")) {
				intent.setClass(getApplicationContext(), MaincalcActivity.class);
				intent.putExtra("line", "on");
			}
			else {
				if (toollet.get("code_name").toString().equals("base_converter")) {
					intent.setClass(getApplicationContext(), BasecvrtrActivity.class);
				}
				else {
					
				}
			}
			if (toollet.get("src").toString().equals("local") || toollet.get("src").toString().contains("https://")) {
				startActivity(intent);
				Animatoo.animateInAndOut(HomeActivity.this);
			}
			_sensor();
		}
	}
	
	
	public void _secureHttp(final HashMap<String, Object> _postmap, final String _url, final String _tag) {
		String iface="";
		boolean isSniffing=false;
		try
		{
				for(NetworkInterface networkinterface : Collections.list(NetworkInterface.getNetworkInterfaces()))
				{
						if(networkinterface.isUp())
						iface=networkinterface.getName();
						if(iface.contains("tun")|| iface.contains("ppp")|| iface.contains("pptp"))
						{
								isSniffing=true;
						}
				}
		}
		catch(Exception e)
		{
				e.printStackTrace();
		}
		if (isSniffing) {
			delay.cancel();
			log.put("home_tick", (double)(0));
			startActivity(new Intent(HomeActivity.this, MainActivity.class)); Animatoo.animateFade(HomeActivity.this);
			finish();
		}
		else {
			net.setParams(_postmap, RequestNetworkController.REQUEST_PARAM);
			net.startRequestNetwork(RequestNetworkController.POST, getString(R.string.serverurl).concat(_url), _tag, _net_request_listener);
		}
	}
	
	public class Viewpager1Adapter extends PagerAdapter {
		
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		
		public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}
		
		public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getApplicationContext();
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}
		
		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}
		
		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}
		
		@Override
		public CharSequence getPageTitle(int pos) {
			// Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + String.valueOf(pos);
		}
		
		@Override
		public Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.toolview, _container, false);
			
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout bglayout = _view.findViewById(R.id.bglayout);
			final LinearLayout imgholder = _view.findViewById(R.id.imgholder);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			textview1.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/32);
			bglayout.getLayoutParams().width=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext())/4)*3;
			bglayout.getLayoutParams().height=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) /8) ;
			imgholder.getLayoutParams().width=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) /8);
			imgholder.getLayoutParams().height=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) /8);
			toollet = new HashMap<>();
			toollet = _data.get((int)_position);
			if (toollet.get("src").toString().equals("notify")) {
				textview1.setText(toollet.get("pre_name").toString());
				textview1.setTextColor(0xFF000000);
				bglayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.gray_round));
				Glide.with(getApplicationContext()).load(Uri.parse(getString(R.string.serverurl).concat("albums/".concat(toollet.get("pre_img").toString())))).into(imageview1);
			}
			else {
				if (toollet.get("src").toString().equals("local") || toollet.get("src").toString().contains("https://")) {
					textview1.setText(toollet.get("name").toString());
					textview1.setTextColor(0xFFFFFFFF);
					bglayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange_round));
					Glide.with(getApplicationContext()).load(Uri.parse(getString(R.string.serverurl).concat("albums/".concat(toollet.get("img").toString())))).into(imageview1);
				}
			}
			/*
imageview1.getLayoutParams().width=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 8);
imageview1.getLayoutParams().height=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 8);
LinearLayout bglayout = (LinearLayout) findViewById(R.id.bglayout);
bglayout.getLayoutParams().width=(int)((SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 4) * 3);
bglayout.getLayoutParams().height=(int)(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
*/
			
			_container.addView(_view);
			return _view;
		}
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