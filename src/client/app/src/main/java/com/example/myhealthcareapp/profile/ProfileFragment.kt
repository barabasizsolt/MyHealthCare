package com.example.myhealthcareapp.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.get
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.login.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment() {
    private lateinit var clientFirstName: TextView
    private lateinit var clientLastName: TextView
    private lateinit var clientPersonalCode: TextView
    private lateinit var clientEmail: TextView
    private lateinit var clientRegistrationDate: TextView
    private lateinit var resetPassword: TextView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        clientFirstName = view.findViewById(R.id.first_name)
        clientLastName = view.findViewById(R.id.last_name)
        clientPersonalCode = view.findViewById(R.id.personal_code)
        clientEmail = view.findViewById(R.id.email)
        clientRegistrationDate = view.findViewById(R.id.registration_date)
        resetPassword = view.findViewById(R.id.click_here_text_view)
        logoutButton = view.findViewById(R.id.logout_button)

        (activity as MainActivity).topAppBar.menu[0].isVisible = false
        (activity as MainActivity).topAppBar.menu[1].isVisible = false

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val client = Cache.getClient()
        clientFirstName.text = client.clientFirstName
        clientLastName.text = client.clientLastName
        clientPersonalCode.text = client.clientPersonalCode
        clientEmail.text = client.clientEmail
        clientRegistrationDate.text = getDate((activity as MainActivity).mAuth.currentUser?.metadata?.creationTimestamp!!)
        logoutButton.setOnClickListener{
            (activity as MainActivity).mAuth.signOut()
            (activity as MainActivity).topAppBar.visibility = View.GONE
            (activity as MainActivity).replaceFragment(LoginFragment(), R.id.fragment_container)
            (activity as MainActivity).bottomNavigation.visibility = View.GONE
        }
    }

    private fun getDate(timeStamp: Long): String? {
        val formatter = SimpleDateFormat("MMM dd, yyy", Locale.getDefault())
        return formatter.format(timeStamp)
    }
}