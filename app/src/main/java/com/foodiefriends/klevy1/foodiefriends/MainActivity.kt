package com.foodiefriends.klevy1.foodiefriends

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

        // listener to take user back to sign-in screen
        mAuth.addAuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser == null) {
                // launch sign in activity
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }

        // Setup toolbar on UI
        setSupportActionBar(toolbar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        val fragment : Fragment = RestaurantListFragment()
//        this.supportFragmentManager.beginTransaction().replace(R.id.container_id, fragment).commit()


    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wishlist -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my_profile -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getData() : List<String> {
        // get restaurant data from firebase database here
        return listOf("Main Restaurant 1", "Main Restaurant 2", "Main Restaurant 3", "Main Restaurant 4", "Main Restaurant 5")
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
