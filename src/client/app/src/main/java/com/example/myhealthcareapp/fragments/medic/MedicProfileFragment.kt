package com.example.myhealthcareapp.fragments.medic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.fragments.login.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class MedicProfileFragment : BaseFragment() {
    private lateinit var medicFullName: TextView
    private lateinit var medicContact: TextView
    private lateinit var medicHireDate: TextView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_medic_profile, container, false)

        medicFullName = view.findViewById(R.id.medic_name)
        medicContact = view.findViewById(R.id.medic_contact)
        medicHireDate = view.findViewById(R.id.hire_date)

        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.profile_title)
        (mActivity as MainActivity).profileIcon.isVisible = false

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: set data from current Medic object.

        logoutButton = view.findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            (mActivity as MainActivity).mAuth.signOut()
            (mActivity as MainActivity).replaceFragment(LoginFragment(), R.id.fragment_container)
        }
    }
}