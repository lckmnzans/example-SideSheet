package com.simple.sidesheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.sidesheet.SideSheetBehavior
import com.google.android.material.sidesheet.SideSheetCallback
import com.google.android.material.sidesheet.SideSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private lateinit var btnShowSideSheetModal: Button
    private lateinit var btnShowSideSheetPersist: Button
    private lateinit var sideSheetModal: SideSheetDialog
    private val openAnimation: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.open_sidesheet_animation)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        btnShowSideSheetModal = findViewById(R.id.btn_show_side_sheet_modal)
        btnShowSideSheetPersist = findViewById(R.id.btn_show_side_sheet_persist)
        sideSheetModal = SideSheetDialog(this)

        btnShowSideSheetModal.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.side_sheet_modal, null)
            val rgModal = view.findViewById<RadioGroup>(R.id.radio_group)
            val tvHeadline = view.findViewById<TextView>(R.id.tv_headline)
            val btnDismiss = view.findViewById<ImageButton>(R.id.btn_dismiss)

            sideSheetModal.setCancelable(false)
            sideSheetModal.setContentView(view)
            sideSheetModal.show()
            tvHeadline.startAnimation(openAnimation)
            btnDismiss.startAnimation(openAnimation)
            rgModal.setOnCheckedChangeListener { radioGroup, i ->
                viewModel.setOptionSelected(i)
                Log.d("ModalSheet", radioGroup.checkedRadioButtonId.toString())
            }

            btnDismiss.setOnClickListener {
                sideSheetModal.dismiss()
            }
        }

        val sideSheet: View = findViewById(R.id.side_sheet)
        val sideSheetBehavior: SideSheetBehavior<*> = SideSheetBehavior.from(sideSheet)
        sideSheetBehavior.state = SideSheetBehavior.STATE_HIDDEN
        sideSheetBehavior.addCallback(object: SideSheetCallback() {
            override fun onStateChanged(sheet: View, newState: Int) {
                //
            }

            override fun onSlide(sheet: View, slideOffset: Float) {
                //
            }
        })
        btnShowSideSheetPersist.setOnClickListener {
            sideSheetBehavior.state = SideSheetBehavior.STATE_EXPANDED
        }
    }
}