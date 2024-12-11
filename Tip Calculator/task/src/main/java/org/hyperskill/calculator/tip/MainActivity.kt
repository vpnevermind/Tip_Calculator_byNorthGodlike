package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    var tipPercentValue = BigDecimal(15)
    var amountBillValue = BigDecimal(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.edit_text)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)
        val tipPercent = findViewById<TextView>(R.id.tip_percent_tv)
        val amountBill = findViewById<TextView>(R.id.bill_value_tv)
        val tipAmount = findViewById<TextView>(R.id.tip_amount_tv)


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tipPercentValue = progress.toBigDecimal()
                updateTextViews(tipPercent, amountBill, tipAmount)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.length > 0) {
                        amountBillValue = s.toString().toBigDecimal()
                        updateTextViews(tipPercent, amountBill, tipAmount)
                    } else {
                        amountBill.text = ""
                        tipPercent.text = ""
                        tipAmount.text = ""
                    }
                }
            }
        })


    }
    fun updateTextViews(tipPercent: TextView, amountBill: TextView, tipAmount: TextView) {
        amountBill.text = String.format("Bill Value: $%.2f", amountBillValue)
        tipPercent.text = String.format("Tip: %d%%", tipPercentValue.toInt())
        tipAmount.text = String.format("Tip Amount: $%.2f", amountBillValue * tipPercentValue / BigDecimal(100))
    }


}