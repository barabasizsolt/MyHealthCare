package com.example.myhealthcareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
<<<<<<<<< Temporary merge branch 1
import com.example.myhealthcareapp.register.RegisterFragment
=========
import com.example.myhealthcareapp.login.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myhealthcareapp.register.RegisterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<<<< Temporary merge branch 1
        replaceFragment(RegisterFragment(), R.id.activity_fragment_container)
=========

        replaceFragment(LoginFragment(), R.id.fragment_container)
>>>>>>>>> Temporary merge branch 2
    }

    fun replaceFragment(fragment: Fragment, containerId: Int, addToBackStack:Boolean = false, withAnimation:Boolean = false){
        val transaction = supportFragmentManager.beginTransaction()
<<<<<<<<< Temporary merge branch 1
//        when(withAnimation){
//            true -> {
//                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
//            }
//        }
=========
/*        when(withAnimation){
            true -> {
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            }
        }*/
>>>>>>>>> Temporary merge branch 2
        transaction.replace(containerId, fragment)
        when(addToBackStack){
            true -> {
                transaction.addToBackStack(null)
            }
        }
        transaction.commit()
<<<<<<<<< Temporary merge branch 1
=========
    }

    private fun initBottomNavigation(){
        bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.make_appointment -> {
                    //TODO: implement it
                    true
                }
                R.id.feedback -> {
                    //TODO: implement it
                    true
                }
                R.id.my_appointments -> {
                    //TODO: implement it
                    true
                }
                else -> false
            }
        }
>>>>>>>>> Temporary merge branch 2
    }
}