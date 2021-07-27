package com.example.navigationcomponentdrawerlayout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdrawerlayout.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentABtn.setOnClickListener(this)
        fragmentBBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fragmentABtn -> {
                val action = HomeFragmentDirections.actionHomeFragmentToAFragment()
                findNavController().navigate(action)
            }
            R.id.fragmentBBtn -> {
                val action = HomeFragmentDirections.actionHostFragmentToBFragment()
                findNavController().navigate(action)
            }
        }
    }
}