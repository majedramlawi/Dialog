package com.example.fullscreendialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.example_dialog.*


class ExampleDialog (private val callbackListener: CallbackListener): DialogFragment() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.AppTheme_FullScreenDialog
        )
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.example_dialog, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        toolbar!!.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar!!.title = "Some Title"
        toolbar!!.inflateMenu(R.menu.example_dialog)

        toolbar!!.setOnMenuItemClickListener { item: MenuItem? ->
            callbackListener.onDataReceived(editTextTextPersonName.text.toString())
            dismiss()
            true
        }

        btnSendToParent.setOnClickListener {
            //send back data to PARENT fragment using callback
            callbackListener.onDataReceived(editTextTextPersonName.text.toString())
            // Now dismiss the fragment
            dismiss()
        }

    }

    companion object {
        const val TAG = "example_dialog"
//        fun display(fragmentManager: FragmentManager?): ExampleDialog {
//            val exampleDialog = ExampleDialog(this)
//            exampleDialog.show(fragmentManager!!, TAG)
//            return exampleDialog
//        }
    }
}