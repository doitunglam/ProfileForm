package com.example.profileform

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    fun checkNull(targets: Array<EditText>): Boolean {

        targets.forEach {
            if (it.text.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    fun register() {
        val firstName = findViewById<TextInputEditText>(R.id.text_firstName);
        val lastName = findViewById<TextInputEditText>(R.id.text_lastName);

        val gender = findViewById<RadioGroup>(R.id.radioGroup_gender);
        val birthday = findViewById<EditText>(R.id.text_birtday);

        val address = findViewById<EditText>(R.id.text_address);
        val email = findViewById<EditText>(R.id.text_email);

        val tou = findViewById<CheckBox>(R.id.checkBox_ToU);

        var check: Boolean = true;

        val inputArray = arrayOf<EditText>(firstName, lastName, birthday, address, email);

        check = checkNull(inputArray);

        if (gender.checkedRadioButtonId == -1) {
            check = false;
        }

        if (!tou.isChecked)
        {
            check = false;
        }


        val text = if(check)  "You're registered" else "Input the missing fields";
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }

    // UX improvement in the keyboard focusing
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this!!.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_register).setOnClickListener { register() }
    }
}