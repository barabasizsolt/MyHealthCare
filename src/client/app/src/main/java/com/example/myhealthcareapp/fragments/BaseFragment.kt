package com.example.myhealthcareapp.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    lateinit var mActivity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }
}