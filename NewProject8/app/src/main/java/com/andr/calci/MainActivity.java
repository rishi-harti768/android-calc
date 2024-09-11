package com.andr.calci;

import android.Manifest;
import android.animation.*;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.gdacciaro.iOSDialog.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import android.provider.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String ver = "";
	private HashMap<String, Object> accountget = new HashMap<>();
	private HashMap<String, Object> log = new HashMap<>();
	public Cryptography crypto = new Cryptography();
	
	private RelativeLayout content;
	private LinearLayout screen;
	private BlurView disability;
	private WebView load_anim;
	private LinearLayout linear1;
	private TextView textVer;
	
	private ObjectAnimator iconVisibility = new ObjectAnimator();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private SharedPreferences local;
	private Intent tele = new Intent();
	private TimerTask vis_delay;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
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
		load_anim = findViewById(R.id.load_anim);
		load_anim.getSettings().setJavaScriptEnabled(true);
		load_anim.getSettings().setSupportZoom(true);
		linear1 = findViewById(R.id.linear1);
		textVer = findViewById(R.id.textVer);
		net = new RequestNetwork(this);
		local = getSharedPreferences("local", Activity.MODE_PRIVATE);
		
		load_anim.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				load_anim.setAlpha((float)(1));
				_safeNet();
				vis_delay = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								if (local.getString("ID", "").equals("0")) {
									startActivity(new Intent(MainActivity.this, AuthActivity.class)); Animatoo.animateSwipeLeft(MainActivity.this);
									finish();
								}
								else {
									load_anim.setAlpha((float)(1));
									log.put("checkout", log.get("checkout").toString().concat("0"));
									_terminal();
								}
							}
						});
					}
				};
				_timer.schedule(vis_delay, (int)(1536));
				super.onPageFinished(_param1, _param2);
			}
		});
		
		iconVisibility.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator _param1) {
				_safeNet();
			}
			
			@Override
			public void onAnimationEnd(Animator _param1) {
				if (local.getString("ID", "").equals("0")) {
					startActivity(new Intent(MainActivity.this, AuthActivity.class)); Animatoo.animateSwipeLeft(MainActivity.this);
					finish();
				}
				else {
					load_anim.setAlpha((float)(1));
					log.put("checkout", log.get("checkout").toString().concat("0"));
					_terminal();
				}
			}
			
			@Override
			public void onAnimationCancel(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator _param1) {
				
			}
		});
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_tag.equals("accountget")) {
					if (_response.startsWith("E")) {
						SketchwareUtil.showMessage(getApplicationContext(), "error code: ".concat(_response));
					}
					else {
						log.put("checkout", log.get("checkout").toString().concat("0"));
						accountget.clear();
						accountget = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
						_terminal();
					}
				}
				else {
					
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (_tag.equals("accountget")) {
					_offlineService();
				}
				else {
					if (_tag.equals("proxy")) {
						screen.setZ((float) (-1));
						disability.setZ((float) (1));
						iOSDialogBuilder ia = new iOSDialogBuilder(MainActivity.this);
						ia.setTitle("Alert");
						ia.setSubtitle("Using a VPN or a proxy server is strictly prohibited and may lead to termination of currently logged-in account or even this device");
						ia.setBoldPositiveLabel(true);
						ia.setCancelable(false);
						ia.setPositiveListener("I agree",new iOSDialogClickListener() 
						{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
								dialog.dismiss(); 
								screen.setZ((float) (1));
								disability.setZ((float) (-1));
								finishAffinity();	 
							}
						})	;
						ia.build().show();
					}
					else {
						
					}
				}
			}
		};
	}
	
	private void initializeLogic() {
		if (!local.contains("ID")) {
			local.edit().putString("ID", "0").commit();
		}
		if (!local.contains("T&C_agreement")) {
			local.edit().putString("T&C_agreement", "0").commit();
		}
		if (!local.contains("PriPo_agreement")) {
			local.edit().putString("PriPo_agreement", "0").commit();
		}
		try {
			android.content.pm.PackageInfo verP = this.getPackageManager().getPackageInfo(getPackageName(), 0);
			ver = verP.versionName;
		} catch (android.content.pm.PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		ver = "v".concat(ver);
		local.edit().putString("swVersion", ver).commit();
		if (!FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/release/"))) {
			FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/release/"));
		}
		log.put("checkout", "");
		_design();
		/*

iconVisibility.setPropertyName("alpha");
iconVisibility.setFloatValues((float)(1));
iconVisibility.setDuration((int)(1536));
iconVisibility.setInterpolator(new LinearInterpolator());
iconVisibility.start();
*/
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
	}
	
	
	@Override
	public void onBackPressed() {
		
	}
	
	@Override
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		
	}
	public void _design() {
		screen.setZ((float) (1));
		disability.setZ((float) (-1));
		/*
LinearLayout  = (LinearLayout) findViewById(R.id.);
.getLayoutParams().width=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()));
.getLayoutParams().height=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()));
.requestLayout();
.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 3;
.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 3;
*/
		textVer.setTextSize((int)SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) / 100);
		textVer.setText(local.getString("swVersion", ""));
		float radius = 1;
		View decorView = getWindow().getDecorView();
		 ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
		 Drawable windowBackground = decorView.getBackground();
		 disability.setupWith(rootView, new RenderScriptBlur(this));
		load_anim.getLayoutParams().width=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()));
		load_anim.getLayoutParams().height=(int)(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()));
		load_anim.loadUrl("file:///android_asset/splash/back.html");
		load_anim.getSettings().setBuiltInZoomControls(false);
	}
	
	
	public void _terminal() {
		if (log.get("checkout").toString().equals("00")) {
			if (accountget.containsKey("maintenance")) {
				load_anim.setAlpha((float)(0));
				disability.setZ((float) (1));
				screen.setZ((float) (-1));
				iOSDialogBuilder ia = new iOSDialogBuilder(MainActivity.this);
				ia.setTitle("Maintenance");
				ia.setSubtitle("Server is currently under maintenance. Please try later");
				ia.setBoldPositiveLabel(true);
				ia.setCancelable(false);
				ia.setPositiveListener("Try Later",new iOSDialogClickListener() 
				{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
						dialog.dismiss(); 
						screen.setZ((float) (1));
						disability.setZ((float) (-1));
						load_anim.setAlpha((float)(1));
						finishAffinity();	 
					}
				})	;
				ia.build().show();
			}
			else {
				if (accountget.containsKey("swsrc")) {
					load_anim.setAlpha((float)(0));
					disability.setZ((float) (1));
					screen.setZ((float) (-1));
					iOSDialogBuilder ia = new iOSDialogBuilder(MainActivity.this);
					ia.setTitle("New Update");
					ia.setSubtitle("New version is available. Please update now");
					ia.setBoldPositiveLabel(true);
					ia.setCancelable(false);
					ia.setPositiveListener("Update",new iOSDialogClickListener() 
					{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
							dialog.dismiss(); 
							local.edit().putString("link", accountget.get("swsrc").toString()).commit();
							getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
							screen.setZ((float) (1));
							disability.setZ((float) (-1));
							load_anim.setAlpha((float)(1));
							startActivity(new Intent(MainActivity.this, ReleaseActivity.class)); Animatoo.animateSlideUp(MainActivity.this);
							finish();	 
						}
					})	;
					ia.build().show();
				}
				else {
					if (accountget.containsKey("ban_notice")) {
						load_anim.setAlpha((float)(0));
						disability.setZ((float) (1));
						screen.setZ((float) (-1));
						iOSDialogBuilder ia = new iOSDialogBuilder(MainActivity.this);
						ia.setTitle("Alert");
						ia.setSubtitle("This account has been blocked by the administrators");
						ia.setBoldPositiveLabel(true);
						ia.setCancelable(false);
						ia.setPositiveListener("I Agree",new iOSDialogClickListener() 
						{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
								dialog.dismiss(); 
								screen.setZ((float) (1));
								disability.setZ((float) (-1));
								load_anim.setAlpha((float)(1));
								finishAffinity();	 
							}
						})	;
						ia.setNegativeListener("More", new iOSDialogClickListener() 
						{ 	 
							@Override 	 public void onClick(iOSDialog dialog) { 		
								 
							}
						});
						ia.build().show();
					}
					else {
						if (accountget.containsKey("data")) {
							if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/release/calc.apk"))) {
								FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/release/calc.apk"));
							}
							local.edit().putString("data", accountget.get("data").toString()).commit();
							local.edit().putString("base_converter", accountget.get("base_converter").toString()).commit();
							tele.setClass(getApplicationContext(), HomeActivity.class);
							tele.putExtra("net_inits", "0");
							startActivity(tele);
							Animatoo.animateZoom(MainActivity.this);
							finish();
						}
					}
				}
			}
		}
	}
	
	
	public void _safeNet() {
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
			net.startRequestNetwork(RequestNetworkController.GET, "0.0.0.0", "proxy", _net_request_listener);
		}
		else {
			if (!local.getString("ID", "").equals("0")) {
				accountget.clear();
				accountget.put("PIN", getString(R.string.webpin));
				accountget.put("ID", local.getString("ID", ""));
				accountget.put("password", local.getString("password", ""));
				accountget.put("device", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
				accountget.put("version", local.getString("swVersion", ""));
				accountget.put("request", "|data|content|");
				net.setParams(accountget, RequestNetworkController.REQUEST_PARAM);
				net.startRequestNetwork(RequestNetworkController.POST, getString(R.string.serverurl).concat("accountget.php"), "accountget", _net_request_listener);
			}
		}
	}
	
	
	public void _offlineService() {
		screen.setZ((float) (-1));
		disability.setZ((float) (1));
		iOSDialogBuilder ia = new iOSDialogBuilder(MainActivity.this);
		ia.setTitle("Alert");
		ia.setSubtitle("No internet connection. You can either use our offline services or try later");
		ia.setBoldPositiveLabel(true);
		ia.setCancelable(false);
		ia.setPositiveListener("Try Again",new iOSDialogClickListener() 
		{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
				dialog.dismiss(); 
				screen.setZ((float) (1));
				disability.setZ((float) (-1));
				_safeNet();	 
			}
		})	;
		ia.setNegativeListener("Offline Mode", new iOSDialogClickListener() 
		{ 	 
			@Override 	 public void onClick(iOSDialog dialog) { 		
				dialog.dismiss(); 
				screen.setZ((float) (1));
				disability.setZ((float) (-1));
				tele.setClass(getApplicationContext(), MaincalcActivity.class);
				tele.putExtra("line", "off");
				startActivity(tele);
				Animatoo.animateInAndOut(MainActivity.this);
				finish();
			}
		});
		ia.build().show();
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