package com.cofbro.qian.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.cofbro.qian.R
import com.cofbro.qian.utils.KeyboardUtil
import com.hjq.toast.ToastUtils

class CodingDialog(context: Context) : AlertDialog(context) {
    private var positiveClick: ((String) -> Unit)? = null
    private var negativeClick: ((View) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_code_input)
        val layoutParams = window?.attributes
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        layoutParams?.width = context.resources.displayMetrics.widthPixels - 200
        window?.attributes = layoutParams

        val positiveButton = findViewById<TextView>(R.id.tv_positive)
        val negativeButton = findViewById<TextView>(R.id.tv_negative)
        val input = findViewById<EditText>(R.id.tv_code_input)

        input.setOnFocusChangeListener { _, focus ->
            if (focus) {
                window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
            }
//            input.requestFocus()
//            KeyboardUtil.showKeyboard(context, input)
        }

        positiveButton.setOnClickListener {
            val code = input?.text.toString()
            if (code.isNotEmpty()) {
                positiveClick?.invoke(code)
            } else {
                ToastUtils.show("请输入密码或手势密码!")
            }
        }

        negativeButton.setOnClickListener {
            negativeClick?.invoke(it)
        }
    }

    fun setPositiveClickListener(listener: (String) -> Unit) {
        positiveClick = listener
    }

    fun setNegativeClickListener(listener: (View) -> Unit) {
        negativeClick = listener
    }

    override fun show() {
        super.show()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    }
}