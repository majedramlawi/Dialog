package com.example.fullscreendialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.full_screen_dialog.*

class FullscreenDialogFragment (private val callbackListener: CallbackListener): DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.full_screen_dialog, container, false)
        rootView.findViewById<View>(R.id.button_close).setOnClickListener { dismiss() }
        return rootView
    }
    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSendDataToParent.setOnClickListener {
            //send back data to PARENT fragment using callback
            callbackListener.onDataReceived(textView2.text.toString())
            // Now dismiss the fragment
            dismiss()
        }

    }
    /** The system calls this only when creating the layout in a dialog. */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setLayout(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.MATCH_PARENT);
        //dialog.requestWindowFeature(Window.L)
        return dialog
    }

}