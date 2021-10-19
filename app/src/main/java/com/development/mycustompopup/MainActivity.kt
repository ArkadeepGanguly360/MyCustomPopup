package com.development.mycustompopup

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.development.mycustompopup.databinding.ActivityMainBinding
import com.development.mycustompopup.databinding.PopupScreenBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var createFullScreenDialog: Dialog? = null
    private var createNormalDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        createFullScreenPopup()
        createNormalPopup()

        binding.btFullPopup.onClick()
        binding.btNormalPopup.onClick()
    }

    private fun createFullScreenPopup() {
        createFullScreenDialog = Dialog(this)
        createFullScreenDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        createFullScreenDialog!!.setCancelable(true)
        createFullScreenDialog!!.setCanceledOnTouchOutside(true)

        var popupFullScreenBinding: PopupScreenBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.popup_screen, binding.root as ViewGroup,
            false)

        createFullScreenDialog!!.setContentView(popupFullScreenBinding.root)

        popupFullScreenBinding.btSubmit.setOnClickListener {
            popupFullScreenBinding.etTitle.text
            createFullScreenDialog!!.dismiss()
        }

        createFullScreenDialog!!.setOnDismissListener {
            popupFullScreenBinding.etTitle.setText("")
        }
        val window = createFullScreenDialog!!.window
        Objects.requireNonNull(window)!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    private fun createNormalPopup() {
        createNormalDialog = Dialog(this)
        createNormalDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        createNormalDialog!!.setCancelable(true)
        createNormalDialog!!.setCanceledOnTouchOutside(true)

        var popupBinding: PopupScreenBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.popup_screen, binding.root as ViewGroup,
            false)

        createNormalDialog!!.setContentView(popupBinding.root)

        popupBinding.btSubmit.setOnClickListener {
            createNormalDialog!!.dismiss()
        }

        createNormalDialog!!.setOnDismissListener {
           popupBinding.etTitle.setText("")
        }
        val window = createNormalDialog!!.getWindow()
        Objects.requireNonNull(window)!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    private fun View.onClick() {
        this.setOnClickListener {
            when (it.id) {
                binding.btFullPopup.id -> {
                    if (createFullScreenDialog != null)
                        createFullScreenDialog!!.show()
                }
                binding.btNormalPopup.id -> {
                    if (createNormalDialog != null)
                        createNormalDialog!!.show()
                }
            }
        }
    }
}