package com.example.myhealthcareapp.makeAppointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.login.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class MakeAppointmentFragment : Fragment() {
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_make_appointment, container, false)

        (activity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            (activity as MainActivity).mAuth.signOut()
            (activity as MainActivity).replaceFragment(LoginFragment(), R.id.fragment_container)
            (activity as MainActivity).bottomNavigation.visibility = View.GONE
        }

        return view
    }
}