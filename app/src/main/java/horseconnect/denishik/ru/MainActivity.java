package horseconnect.denishik.ru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.util.*;

import java.util.*;

import android.widget.LinearLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
	public final int REQ_CD_LOGIN = 101;
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private  com.google.android.material.bottomsheet.BottomSheetDialog dialogLogin;
	private  MediaPlayer mediaPlayer;
	
	private LinearLayout linear1;
	private CollapsingToolbarLayout collapsingtoolbar1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private VideoView videoview1;
	private GridView gridview1;
	
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private GoogleSignInClient login;
	private PhoneAuthProvider.OnVerificationStateChangedCallbacks authPhone;
	private PhoneAuthProvider.ForceResendingToken authPhone_resendToken;
	private TimerTask timer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		collapsingtoolbar1 = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbar1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		videoview1 = (VideoView) findViewById(R.id.videoview1);
		MediaController videoview1_controller = new MediaController(this);
		videoview1.setMediaController(videoview1_controller);
		gridview1 = (GridView) findViewById(R.id.gridview1);
		auth = FirebaseAuth.getInstance();
		
		videoview1.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
			@Override
			public void onPrepared(MediaPlayer _mediaPlayer){
				mediaPlayer = _mediaPlayer;
				_mediaPlayer.setVolume(0f, 0f); _mediaPlayer.setLooping(true); 
				_mediaPlayer.start();
				videoview1.setMediaController(null);
				_scaleVideo();
			}
		});
		
		videoview1.setOnErrorListener(new MediaPlayer.OnErrorListener(){
			@Override
			public boolean onError(MediaPlayer _mediaPlayer, int _what, int _extra){
				OtherUtil.showMessage(getApplicationContext(), String.valueOf((long)(_what)).concat(String.valueOf((long)(_extra))));
				return true;
			}
		});
		
		videoview1.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
			@Override
			public void onCompletion(MediaPlayer _mediaPlayer){
				
			}
		});
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
		});
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					dialogLogin.dismiss();
					_esditAppBar();
				}
				else {
					OtherUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		_onLoadingSheet();
		videoview1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.horseBg));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			case REQ_CD_LOGIN:
			if (_resultCode == Activity.RESULT_OK) {
				
Task<GoogleSignInAccount> _task = GoogleSignIn.getSignedInAccountFromIntent(_data);
				
				
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _onLoadingSheet () {
		final com.google.android.material.bottomsheet.BottomSheetDialog dialog = new com.google.android.material.bottomsheet.BottomSheetDialog(MainActivity.this); View lay = getLayoutInflater().inflate(R.layout.loading_view, null); dialog.setContentView(lay);
		final LinearLayout linear1 = (LinearLayout)lay.findViewById(R.id.linear1); 
		
		final LinearLayout linear2 = (LinearLayout)lay.findViewById(R.id.linear2); 
		
		final LinearLayout linear3 = (LinearLayout)lay.findViewById(R.id.linear3); 
		dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
		dialog.show();
		dialog.setCancelable(false);
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable(); wd.setColor(Color.TRANSPARENT); wd.setCornerRadius((int)10f);
		linear1.setBackground(wd);
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							dialog.dismiss();
							_esditAppBar();
						}
					});
				}
			};
			_timer.schedule(timer, (int)(2000));
		}
		else {
			dialog.dismiss();
			_onLoginView();
		}
	}
	
	
	public void _onLoginView () {
		dialogLogin = new com.google.android.material.bottomsheet.BottomSheetDialog(MainActivity.this);
		View dialogLoginV;
		dialogLoginV = getLayoutInflater().inflate(R.layout.login_view,null );
		dialogLogin.setContentView(dialogLoginV);
		dialogLogin.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
		final EditText login = (EditText) dialogLoginV.findViewById(R.id.login);
		final EditText password = (EditText) dialogLoginV.findViewById(R.id.password);
		final com.google.android.gms.common.SignInButton googleLogin = (com.google.android.gms.common.SignInButton) dialogLoginV.findViewById(R.id.googleLogin);
		final LinearLayout go = (LinearLayout) dialogLoginV.findViewById(R.id.go);
		final LinearLayout phoneLogin = (LinearLayout) dialogLoginV.findViewById(R.id.phoneLogin);
		dialogLogin.setCancelable(false);
		dialogLogin.show();
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ((5 < login.getText().toString().length()) && (5 < password.getText().toString().length())) {
					auth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_sign_in_listener);
				}
				else {
					OtherUtil.CustomToast(getApplicationContext(), "Чтото не так", 0xFFF44336, 10, Color.TRANSPARENT, 20, OtherUtil.BOTTOM);
				}
			}
		});
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				OtherUtil.showKeyboard(getApplicationContext());
			}
		});
		password.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				OtherUtil.showKeyboard(getApplicationContext());
			}
		});
		googleLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		phoneLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	
	public void _esditAppBar () {
		//set height appbar
		AppBarLayout appbar = (AppBarLayout) findViewById(R.id._app_bar);
		    float heightDp = getResources().getDisplayMetrics().heightPixels / 3;
		    CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)appbar.getLayoutParams();
		    lp.height = (int)heightDp;
		linear2.setVisibility(View.VISIBLE);
		_scaleVideo();
	}
	
	
	public void _scaleVideo () {
		//scale video
		float videoRatio = mediaPlayer.getVideoWidth() / (float) 
		mediaPlayer.getVideoHeight();
		         float screenRatio = videoview1.getWidth() / (float) 
		         videoview1.getHeight();
		         float scaleX = videoRatio / screenRatio;
		         if (scaleX >= 1f) {
			             videoview1.setScaleX(scaleX);
			         } else {
			             videoview1.setScaleY(1f / scaleX);
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
