package com.rushabh.chatapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rushabh.chatapp.DAL.DAL;
import com.rushabh.chatapp.MainActivity;
import com.rushabh.chatapp.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    TextView name;
    Button signOutBtn;
    SignInButton signInBtn;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    final DAL obj = new DAL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        name = (TextView) findViewById(R.id.name);

        CheckDatabase();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        signInBtn = (SignInButton) findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(this);

//        signOutBtn = (Button) findViewById(R.id.signOutBtn);
//        signOutBtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    Toast.makeText(LogInActivity.this, user.getDisplayName(), Toast.LENGTH_SHORT).show();
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                    Toast.makeText(LogInActivity.this, "Loged out", Toast.LENGTH_SHORT).show();
//                }
//                // ...
//            }
//        };
//        At last
//        startActivity(new Intent(LogInActivity.this,MainActivity.class));
    }

    private void CheckDatabase() {
        obj.openDB();
        String FullName = obj.checkData();
        if (FullName == null) {

        } else {
//            name.setText("Hello" + FullName);
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            intent.putExtra("name",FullName);
            startActivity(intent);
        }
        obj.closeDB();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signInBtn) {
            signIn();
            Log.e("inside","On click");
        }
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.e("inside","Sing in");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("inside","onActivityResult");

        if (requestCode == RC_SIGN_IN) {
            Log.e("inside","If statment");
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("inside","handleSignInResult");
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
//            name.setText("Hello " + account.getDisplayName());
            obj.openDB();
            obj.insertData(account.getDisplayName());
            obj.closeDB();
            Log.e("inside","isSuccess");
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            intent.putExtra("name",account.getDisplayName());
            startActivity(intent);
        }else {
            Log.e("inside","failed");
            Log.e("status"," " + result.getStatus());
        }
    }

//    private void signOut() {
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
//            @Override
//            public void onResult(@NonNull Status status) {
////                name.setText("Signed out");
//            }
//        });
//        obj.openDB();
//        obj.deleteData();
//        obj.closeDB();
//    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mAuth.removeAuthStateListener(mAuthListener);
//    }
}
