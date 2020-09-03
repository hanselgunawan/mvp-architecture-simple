package com.hanseltritama.mvp_simple

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.hanseltritama.mvp_simple.AppPinContract.Presenter


class MainActivity : AppCompatActivity(),
    AppPinContract.View {
    private var mButton: Button? = null
    private var presenter: Presenter? = null
    private var pin1: EditText? = null
    private var pin2: EditText? = null
    private var pin3: EditText? = null
    private var pin4: EditText? = null
    private var con_pin1: EditText? = null
    private var con_pin2: EditText? = null
    private var con_pin3: EditText? = null
    private var con_pin4: EditText? = null
    private var password1: String? = null
    private var password2: String? = null
    var positive1: ImageView? = null
    var positive2: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = ""
        init()

        presenter = AppPinPresenter(this)
        presenter.defaultSettings()
        mButton.setOnClickListener(object : OnClickListener() {
            fun onClick(view: View?) {
                presenter.loadNextScreen()
            }
        })
    }

    private fun init() {
        mButton = findViewById(R.id.btn_choose_pin)
        pin1 = findViewById(R.id.newpin_one)
        pin2 = findViewById(R.id.newpin_two)
        pin3 = findViewById(R.id.newpin_three)
        pin4 = findViewById(R.id.newpin_four)
        con_pin1 = findViewById(R.id.confirmpin_one)
        con_pin2 = findViewById(R.id.confirmpin_two)
        con_pin3 = findViewById(R.id.confirmpin_three)
        con_pin4 = findViewById(R.id.confirmpin_four)
        pin1.addTextChangedListener(watcher)
        pin2.addTextChangedListener(watcher)
        pin3.addTextChangedListener(watcher)
        pin4.addTextChangedListener(watcher)
        con_pin1.addTextChangedListener(watcher)
        con_pin2.addTextChangedListener(watcher)
        con_pin3.addTextChangedListener(watcher)
        con_pin4.addTextChangedListener(watcher)
        positive1 = findViewById(R.id.positivechecked1)
        positive2 = findViewById(R.id.positivechecked2)
    }

    override fun showButtonClick(b: Boolean) {
        mButton.setEnabled(b)
    }

    override fun setButtonColor(color: Int) {
        mButton.setBackground(resources.getDrawable(color))
    }

    override fun navigateNextScreen() {
        Toast.makeText(this, "Your intent goes here", Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {}
    override fun showTickVisibility(value: Int) {
        positive1.setVisibility(value)
        positive2.setVisibility(value)
    }

    var watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            charSequence: CharSequence,
            i: Int,
            i1: Int,
            i2: Int
        ) {
        }

        override fun onTextChanged(
            charSequence: CharSequence,
            i: Int,
            i1: Int,
            i2: Int
        ) {
            val editText = currentFocus as EditText?
            if (editText != null && editText.length() > 0) {
                val next: View? = editText.focusSearch(View.FOCUS_RIGHT)
                if (next != null) {
                    next.requestFocus()
                }
            }
            password1 = presenter!!.appendIndvidualPassword(
                pin1!!.text.toString(),
                pin2!!.text.toString(),
                pin3!!.text.toString(),
                pin4!!.text.toString()
            )
            password2 = presenter!!.appendIndvidualPassword(
                con_pin1!!.text.toString(),
                con_pin2!!.text.toString(),
                con_pin3!!.text.toString(),
                con_pin4!!.text.toString()
            )
            if (password1 == password2) {
                presenter!!.verifyEntries()
                presenter!!.savePassword(password1!!)
                return
            }
            if (password1 != password2) {
                presenter!!.defaultSettings()
                return
            }
        }

        override fun afterTextChanged(editable: Editable) {}
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
