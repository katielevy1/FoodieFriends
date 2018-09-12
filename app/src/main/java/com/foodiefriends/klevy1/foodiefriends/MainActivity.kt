package com.foodiefriends.klevy1.foodiefriends

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private var mFirebaseUser: FirebaseUser? = null

    private var mUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mAuth.currentUser
        if (mFirebaseUser == null) {
            // User is not signed-in so launch sign-in activity
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
            finish()
            return
        } else {
            mUsername = mFirebaseUser?.displayName
        }

        // Setup toolbar on UI
        setSupportActionBar(toolbar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    // Takes email address and password, validates them, and then creates a new user
    fun createAccount(email : String, password : String) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Auth", "createUserWithEmail:success")
                        val user : FirebaseUser? = mAuth.currentUser
        //                        updateUI(user)
                    } else {
                        // If sign-in fails, display a message to the user
                        Log.w("Auth", "createUserWithEmail:failure ${task.exception}")
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
        //                        updateUI(null)
                    }
                }
    }

}
