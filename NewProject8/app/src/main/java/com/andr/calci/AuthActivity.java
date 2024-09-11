package com.andr.calci;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.media.SoundPool;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import eightbitlab.com.blurview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import vpdotsindicator.mytelegram.sketchware95.*;
import android.view.inputmethod.*;
import android.provider.*;

public class AuthActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private boolean passVisibility = false;
	private HashMap<String, Object> log = new HashMap<>();
	private double sound = 0;
	private boolean checkup = false;
	private HashMap<String, Object> auth = new HashMap<>();
	public Cryptography ed = new Cryptography();
	private double wide = 0;
	
	private RelativeLayout content;
	private LinearLayout screen;
	private BlurView disability;
	private ImageView imageview1;
	private LinearLayout authMachine;
	private LinearLayout signIn;
	private LinearLayout signUp;
	private LinearLayout space;
	private LinearLayout signinNav;
	private LinearLayout signupNav;
	private TextView inTitle;
	private ImageView inHelp;
	private LinearLayout inBox1;
	private LinearLayout inBox2;
	private ImageView inimg1;
	private EditText inEmail;
	private ImageView inimg2;
	private EditText inPass;
	private ImageView inVisible;
	private TextView upTitle;
	private ImageView upHelp;
	private LinearLayout upBox1;
	private LinearLayout upBox2;
	private ImageView upimg1;
	private EditText upEmail;
	private ImageView upimg2;
	private EditText upPass;
	private ImageView upvisible;
	private TextView inmain;
	private TextView inside;
	private TextView upmain;
	private TextView upside;
	private LinearLayout linear1;
	private ProgressBar progressbar1;
	private TextView loginfo;
	
	private SoundPool effect;
	private ObjectAnimator pageAlpha = new ObjectAnimator();
	private TimerTask delay;
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private SharedPreferences local;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.auth);
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
		imageview1 = findViewById(R.id.imageview1);
		authMachine = findViewById(R.id.authMachine);
		signIn = findViewById(R.id.signIn);
		signUp = findViewById(R.id.signUp);
		space = findViewById(R.id.space);
		signinNav = findViewById(R.id.signinNav);
		signupNav = findViewById(R.id.signupNav);
		inTitle = findViewById(R.id.inTitle);
		inHelp = findViewById(R.id.inHelp);
		inBox1 = findViewById(R.id.inBox1);
		inBox2 = findViewById(R.id.inBox2);
		inimg1 = findViewById(R.id.inimg1);
		inEmail = findViewById(R.id.inEmail);
		inimg2 = findViewById(R.id.inimg2);
		inPass = findViewById(R.id.inPass);
		inVisible = findViewById(R.id.inVisible);
		upTitle = findViewById(R.id.upTitle);
		upHelp = findViewById(R.id.upHelp);
		upBox1 = findViewById(R.id.upBox1);
		upBox2 = findViewById(R.id.upBox2);
		upimg1 = findViewById(R.id.upimg1);
		upEmail = findViewById(R.id.upEmail);
		upimg2 = findViewById(R.id.upimg2);
		upPass = findViewById(R.id.upPass);
		upvisible = findViewById(R.id.upvisible);
		inmain = findViewById(R.id.inmain);
		inside = findViewById(R.id.inside);
		upmain = findViewById(R.id.upmain);
		upside = findViewById(R.id.upside);
		linear1 = findViewById(R.id.linear1);
		progressbar1 = findViewById(R.id.progressbar1);
		loginfo = findViewById(R.id.loginfo);
		net = new RequestNetwork(this);
		local = getSharedPreferences("local", Activity.MODE_PRIVATE);
		
		inHelp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_sensor();
				_logy("null");
				log.put("isDialogUp", "yes");
				iOSDialogBuilder inhelp = new iOSDialogBuilder(AuthActivity.this);
				inhelp.setTitle("Sign In");
				inhelp.setSubtitle("Please fill the following details to access your existing account.\nIf you don't have one try Sign Up");
				inhelp.setBoldPositiveLabel(true);
				inhelp.setCancelable(false);
				inhelp.setPositiveListener("OK",new iOSDialogClickListener() 
				{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
						dialog.dismiss(); 
						log.put("isDialogUp", "no");
						getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
						_sensor();
						_logy("cancel");	 
					}
				})	;
				inhelp.build().show();
			}
		});
		
		inVisible.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_hspass();
				_sensor();
			}
		});
		
		upHelp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_sensor();
				_logy("null");
				log.put("isDialogUp", "yes");
				iOSDialogBuilder uphelp = new iOSDialogBuilder(AuthActivity.this);
				uphelp.setTitle("Sign Up");
				uphelp.setSubtitle("Please fill the following details to create your new account.\nIf you have one try Sign In");
				uphelp.setBoldPositiveLabel(true);
				uphelp.setCancelable(false);
				uphelp.setPositiveListener("OK",new iOSDialogClickListener() 
				{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
						dialog.dismiss(); 
						log.put("isDialogUp", "no");
						getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
						_sensor();
						_logy("cancel");	 
					}
				})	;
				uphelp.build().show();
			}
		});
		
		upvisible.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_hspass();
				_sensor();
			}
		});
		
		inmain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
				((EditText)inEmail).setError(null);
				((EditText)inPass).setError(null);
				checkup = true;
				if (inEmail.getText().toString().isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(inEmail.getText().toString()).matches())) {
					checkup = false;
					((EditText)inEmail).setError("This cannot be an email address");
					inEmail.requestFocus();
				}
				if (inEmail.getText().toString().contains(",") || (inEmail.getText().toString().contains("&") || (inEmail.getText().toString().contains("=") || (inEmail.getText().toString().contains("%") || (inEmail.getText().toString().contains("#") || inEmail.getText().toString().contains("+")))))) {
					checkup = false;
					((EditText)inEmail).setError("This cannot be an email address");
					inEmail.requestFocus();
				}
				if (inPass.getText().toString().length()>=4) {
					if (inPass.getText().toString().contains("\\") || (inPass.getText().toString().contains(",") || inPass.getText().toString().contains("\""))) {
						checkup = false;
						((EditText)inPass).setError("Improper Password format");
						inPass.requestFocus();
					}
				}
				else {
					checkup = false;
					((EditText)inPass).setError("Password must have atleast 4 characters");
					inPass.requestFocus();
				}
				if (!passVisibility) {
					inPass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
					upPass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance()); 
					inVisible.setImageResource(R.drawable.ic_visibility_white);
					upvisible.setImageResource(R.drawable.ic_visibility_white);
					passVisibility = true;
				}
				if (checkup) {
					_logy("Signing in...");
					auth.put("PIN", getString(R.string.webpin));
					auth.put("email", inEmail.getText().toString());
					auth.put("password", inPass.getText().toString());
					auth.put("device", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
					net.setParams(auth, RequestNetworkController.REQUEST_PARAM);
					net.startRequestNetwork(RequestNetworkController.POST, getString(R.string.serverurl).concat("signin.php"), "signin", _net_request_listener);
				}
				_closekeys();
				_sensor();
			}
		});
		
		inside.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_pgLoader("blink");
				delay = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								signIn.setVisibility(View.GONE);
								signinNav.setVisibility(View.GONE);
								signUp.setVisibility(View.VISIBLE);
								signupNav.setVisibility(View.VISIBLE);
								inPass.setText(null);
								upPass.setText(null);
								((EditText)inPass).setError(null);
								((EditText)upPass).setError(null);
							}
						});
					}
				};
				_timer.schedule(delay, (int)(256));
				_sensor();
			}
		});
		
		upmain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
				((EditText)upEmail).setError(null);
				((EditText)upPass).setError(null);
				checkup = true;
				if (upEmail.getText().toString().isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(upEmail.getText().toString()).matches())) {
					checkup = false;
					((EditText)upEmail).setError("This cannot be an email address");
					upEmail.requestFocus();
				}
				if (upEmail.getText().toString().contains(",") || (upEmail.getText().toString().contains("&") || (upEmail.getText().toString().contains("=") || (upEmail.getText().toString().contains("%") || (upEmail.getText().toString().contains("#") || upEmail.getText().toString().contains("+")))))) {
					checkup = false;
					((EditText)upEmail).setError("This cannot be an email address");
					upEmail.requestFocus();
				}
				if (upPass.getText().toString().length()>=4) {
					if (upPass.getText().toString().contains("\\") || (upPass.getText().toString().contains(",") || upPass.getText().toString().contains("\""))) {
						checkup = false;
						((EditText)upPass).setError("Cannot accept this Password");
						upPass.requestFocus();
					}
				}
				else {
					checkup = false;
					((EditText)upPass).setError("Password must have atleast 4 characters");
					upPass.requestFocus();
				}
				if (!passVisibility) {
					inPass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
					upPass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance()); 
					inVisible.setImageResource(R.drawable.ic_visibility_white);
					upvisible.setImageResource(R.drawable.ic_visibility_white);
					passVisibility = true;
				}
				if (checkup) {
					_logy("Creating new account...");
					auth.put("PIN", getString(R.string.webpin));
					auth.put("email", upEmail.getText().toString());
					auth.put("password", upPass.getText().toString());
					auth.put("device", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
					net.setParams(auth, RequestNetworkController.REQUEST_PARAM);
					net.startRequestNetwork(RequestNetworkController.POST, getString(R.string.serverurl).concat("signup.php"), "signup", _net_request_listener);
				}
				_sensor();
			}
		});
		
		upside.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_pgLoader("blink");
				delay = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								signUp.setVisibility(View.GONE);
								signupNav.setVisibility(View.GONE);
								signIn.setVisibility(View.VISIBLE);
								signinNav.setVisibility(View.VISIBLE);
								inPass.setText(null);
								upPass.setText(null);
							}
						});
					}
				};
				_timer.schedule(delay, (int)(256));
				_sensor();
			}
		});
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_response.startsWith("E")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Error code:".concat(_response));
				}
				else {
					if (_tag.equals("signup")) {
						if (_response.equals("u0")) {
							iOSDialogBuilder ia = new iOSDialogBuilder(AuthActivity.this);
							ia.setTitle("Maintenance");
							ia.setSubtitle("The server is currently under maintenance.This may take a while, so please try later");
							ia.setBoldPositiveLabel(true);
							ia.setCancelable(false);
							ia.setPositiveListener("Try Later",new iOSDialogClickListener() 
							{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
									dialog.dismiss(); 
									finishAffinity();	 
								}
							})	;
							ia.build().show();
						}
						else {
							if (_response.equals("uD")) {
								iOSDialogBuilder ia = new iOSDialogBuilder(AuthActivity.this);
								ia.setTitle("Alert");
								ia.setSubtitle("This device has been blocked by the administrators until further notice.");
								ia.setBoldPositiveLabel(true);
								ia.setCancelable(false);
								ia.setPositiveListener("Try Later",new iOSDialogClickListener() 
								{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
										dialog.dismiss(); 	 
									}
								})	;
								ia.build().show();
							}
							else {
								if (_response.equals("uE")) {
									((EditText)upEmail).setError("This email already exists");
									upEmail.requestFocus();
									_logy("cancel");
								}
								else {
									local.edit().putString("T&C_agreement", "0").commit();
									local.edit().putString("PriPo_agreement", "0").commit();
									local.edit().putString("digikey", ed.keyGen(upEmail.getText().toString())).commit();
									local.edit().putString("ID", _response).commit();
									local.edit().putString("password", ed.encrypt(upPass.getText().toString(),local.getString("digikey", ""))).commit();
									_file_en();
									_logy("cancel");
									startActivity(new Intent(AuthActivity.this, MainActivity.class)); Animatoo.animateSwipeRight(AuthActivity.this);
									finish();
								}
							}
						}
					}
					else {
						if (_tag.equals("signin")) {
							if (_response.equals("i0")) {
								iOSDialogBuilder ia = new iOSDialogBuilder(AuthActivity.this);
								ia.setTitle("Maintenance");
								ia.setSubtitle("The server is currently under maintenance.This may take a while, so please try later");
								ia.setBoldPositiveLabel(true);
								ia.setCancelable(false);
								ia.setPositiveListener("Try Later",new iOSDialogClickListener() 
								{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
										dialog.dismiss(); 
										finishAffinity();	 
									}
								})	;
								ia.build().show();
							}
							else {
								if (_response.equals("iD")) {
									iOSDialogBuilder ia = new iOSDialogBuilder(AuthActivity.this);
									ia.setTitle("Alert");
									ia.setSubtitle("This device has been blocked by the administrators until further notice.");
									ia.setBoldPositiveLabel(true);
									ia.setCancelable(false);
									ia.setPositiveListener("I Agree",new iOSDialogClickListener() 
									{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
											dialog.dismiss(); 	 
										}
									})	;
									ia.build().show();
								}
								else {
									if (_response.equals("iE")) {
										((EditText)inEmail).setError("This email does not exist");
										inEmail.requestFocus();
										_logy("cancel");
									}
									else {
										if (_response.equals("iP")) {
											((EditText)inPass).setError("Incorrect password");
											inPass.requestFocus();
											_logy("cancel");
										}
										else {
											local.edit().putString("digikey", ed.keyGen(inEmail.getText().toString())).commit();
											local.edit().putString("ID", _response).commit();
											local.edit().putString("password", ed.encrypt(inPass.getText().toString(),local.getString("digikey", ""))).commit();
											_file_en();
											_logy("cancel");
											startActivity(new Intent(AuthActivity.this, MainActivity.class)); Animatoo.animateSwipeRight(AuthActivity.this);
											finish();
										}
									}
								}
							}
						}
					}
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_logy("null");
				iOSDialogBuilder ai = new iOSDialogBuilder(AuthActivity.this);
				ai.setTitle("No Internet Connection");
				ai.setSubtitle("Please make sure that you have Internet Connection ");
				ai.setBoldPositiveLabel(true);
				ai.setCancelable(false);
				ai.setPositiveListener("Retry",new iOSDialogClickListener() 
				{ 	 @Override 	 public void onClick(iOSDialog dialog) { 		
						dialog.dismiss(); 
						_logy("cancel");	 
					}
				})	;
				ai.setNegativeListener("Try Later", new iOSDialogClickListener() 
				{ 	 
					@Override 	 public void onClick(iOSDialog dialog) { 		
						dialog.dismiss(); 
						finishAffinity();
					}
				});
				ai.build().show();
			}
		};
	}
	
	private void initializeLogic() {
		inEmail.setOnEditorActionListener(new EditText.OnEditorActionListener() 
		{
			    @Override
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) 
			    {
				        if (actionId == 5/*android.view.inputmethod.EditorInfo.IME_ACTION_GO*/)
				        {
					            inPass.requestFocus();
					            ((EditText)inPass).selectAll();
					            return true;
					        }
				    return false;
				    }
		});
		inPass.setOnEditorActionListener(new EditText.OnEditorActionListener() 
		{
			    @Override
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) 
			    {
				        if (actionId == 6)
				        {
					            _closekeys();
					            inmain.performClick();
					            return true;
					        }
				    return false;
				    }
		});
		upEmail.setOnEditorActionListener(new EditText.OnEditorActionListener() 
		{
			    @Override
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) 
			    {
				        if (actionId == 5)
				        {
					            ((EditText)upEmail).setSelection((int)0, (int)0);
					            upPass.requestFocus();
					            ((EditText)upPass).selectAll();
					            return true;
					        }
				    return false;
				    }
		});
		upPass.setOnEditorActionListener(new EditText.OnEditorActionListener() 
		{
			    @Override
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) 
			    {
				        if (actionId == 6)
				        {
					            _closekeys();
					            upmain.performClick();
					            return true;
					        }
				    return false;
				    }
		});
		passVisibility = false;
		log.put("isDialogUp", "no");
		_design();
		_hspass();
		_pgLoader("init");
		effect = new SoundPool((int)(1), AudioManager.STREAM_MUSIC, 0);
		sound = effect.load(getApplicationContext(), R.raw.ioseffect, 1);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		if (log.get("isDialogUp").toString().equals("no")) {
			_logy("cancel");
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (log.get("isDialogUp").toString().equals("no")) {
			_logy("null");
		}
	}
	
	@Override
	public void onBackPressed() {
		
	}
	public void _design() {
		float radius = 100;
		View decorView = getWindow().getDecorView();
		 // ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
		 ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
		 // Optional: // Set drawable to draw in the beginning of each blurred frame. // Can be used in case your layout has a lot of transparent space and your content // gets a too low alpha value after blur is applied.
		 Drawable windowBackground = decorView.getBackground();
		 disability.setupWith(rootView, new RenderScriptBlur(this));
		 // or RenderEffectBlur .setFrameClearDrawable(windowBackground) // Optional .setBlurRadius(radius)
		imageview1.requestLayout();
		imageview1.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 6;
		imageview1.getLayoutParams().width = android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
		inimg1.requestLayout();
		inimg1.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		inimg1.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		inimg2.requestLayout();
		inimg2.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		inimg2.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		upimg1.requestLayout();
		upimg1.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		upimg1.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		upimg2.requestLayout();
		upimg2.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		upimg2.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		inVisible.requestLayout();
		inVisible.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		inVisible.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		upvisible.requestLayout();
		upvisible.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		upvisible.getLayoutParams().width = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 16;
		inHelp.requestLayout();
		inHelp.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 24;
		inHelp.getLayoutParams().width = android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
		upHelp.requestLayout();
		upHelp.getLayoutParams().height = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) / 24;
		upHelp.getLayoutParams().width = android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
		inBox1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFFFF9800, 0x88888888));
		inBox2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFFFF9800, 0x88888888));
		upBox1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFFFF9800, 0x88888888));
		upBox2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)2, 0xFFFF9800, 0x88888888));
		inmain.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)10, Color.TRANSPARENT, 0xFFFF9800));
		upmain.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)10, Color.TRANSPARENT, 0xFFFF9800));
		inside.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)10, 0xFFFF9800, Color.TRANSPARENT));
		upside.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)999, (int)10, 0xFFFF9800, Color.TRANSPARENT));
		inEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int) 32)});
		inPass.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int) 16)});
		upEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int) 32)});
		upPass.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int) 16)});
		wide = SketchwareUtil.getDisplayWidthPixels(getApplicationContext());
		inTitle.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/8);
		upTitle.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/8);
		inEmail.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		inPass.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		upEmail.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		upPass.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		inmain.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		inside.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		upmain.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		upside.setTextSize((int)TypedValue.COMPLEX_UNIT_PX,(int)wide/32);
		screen.setZ((float) (1));
		disability.setZ((float) (-1));
	}
	
	
	public void _hspass() {
		if (passVisibility) {
			inPass.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
			upPass.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance()); 
			inVisible.setImageResource(R.drawable.ic_visibility_off_white);
			upvisible.setImageResource(R.drawable.ic_visibility_off_white);
			passVisibility = false;
		}
		else {
			inPass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
			upPass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance()); 
			inVisible.setImageResource(R.drawable.ic_visibility_white);
			upvisible.setImageResource(R.drawable.ic_visibility_white);
			passVisibility = true;
		}
	}
	
	
	public void _logy(final String _show) {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		if (_show.equals("cancel")) {
			screen.setZ((float) (1));
			disability.setZ((float) (-1));
			inEmail.setEnabled(true);
			inPass.setEnabled(true);
			upEmail.setEnabled(true);
			upPass.setEnabled(true);
			inHelp.setEnabled(true);
			upHelp.setEnabled(true);
			inVisible.setEnabled(true);
			upvisible.setEnabled(true);
			inmain.setEnabled(true);
			inside.setEnabled(true);
			upmain.setEnabled(true);
			upside.setEnabled(true);
		}
		else {
			disability.setZ((float) (1));
			screen.setZ((float) (-1));
			inEmail.setEnabled(false);
			inPass.setEnabled(false);
			upEmail.setEnabled(false);
			upPass.setEnabled(false);
			inHelp.setEnabled(false);
			upHelp.setEnabled(false);
			inVisible.setEnabled(false);
			upvisible.setEnabled(false);
			inmain.setEnabled(false);
			inside.setEnabled(false);
			upmain.setEnabled(false);
			upside.setEnabled(false);
			if (_show.equals("null")) {
				progressbar1.setVisibility(View.GONE);
				loginfo.setVisibility(View.GONE);
			}
			else {
				progressbar1.setVisibility(View.VISIBLE);
				loginfo.setVisibility(View.VISIBLE);
				loginfo.setText(_show);
			}
		}
	}
	
	
	public void _pgLoader(final String _no) {
		if (_no.equals("init")) {
			signUp.setVisibility(View.GONE);
			signupNav.setVisibility(View.GONE);
			signIn.setVisibility(View.VISIBLE);
			signinNav.setVisibility(View.VISIBLE);
		}
		else {
			if (_no.equals("blink")) {
				pageAlpha.setTarget(authMachine);
				pageAlpha.setPropertyName("alpha");
				pageAlpha.setFloatValues((float)(0));
				pageAlpha.setDuration((int)(512));
				pageAlpha.setRepeatMode(ValueAnimator.REVERSE);
				pageAlpha.setRepeatCount((int)(1));
				pageAlpha.setInterpolator(new LinearInterpolator());
				pageAlpha.start();
			}
		}
	}
	
	
	public void _sensor() {
		sound = effect.play((int)(1), 1.0f, 1.0f, 1, (int)(0), 1.0f);
	}
	
	
	public void _closekeys() {
		View view = this.getCurrentFocus();
		if (view != null)
		{
			    InputMethodManager manager= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			    manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	
	
	public void _file_en() {
		if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/resource.dat"))) {
			FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/resource.dat"), ed.encrypt(ed.decrypt(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/resource.dat")),ed.keyGen(Build.ID)),local.getString("digikey", "")));
		}
		if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/source.dat"))) {
			FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/source.dat"), ed.encrypt(ed.decrypt(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tools/base_converter/source.dat")),ed.keyGen(Build.ID)),local.getString("digikey", "")));
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