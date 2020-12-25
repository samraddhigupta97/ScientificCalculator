package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    var one: TextView? = null
    var two: TextView? = null
    var three: TextView? = null
    var four: TextView? = null
    var five: TextView? = null
    var six: TextView? = null
    var seven: TextView? = null
    var eight: TextView? = null
    var nine: TextView? = null
    var zero: TextView? = null

    var plus: TextView? = null
    var minus: TextView? = null
    var multiply: TextView? = null
    var divide: TextView? = null
    var modulo: TextView? = null
    var equals: TextView? = null

    var sin: TextView? = null
    var cos: TextView? = null
    var tan: TextView? = null
    var log: TextView? = null
    var openBrac: TextView? = null
    var closedBrac: TextView? = null
    var square: TextView? = null
    var squareroot: TextView? = null
    var factorial: TextView? = null
    var power: TextView? = null


    var changeSign: TextView? = null

    var decimal: TextView? = null

    var expression: TextView? = null
    var result: TextView? = null

    var ac: TextView? = null
    var backspace: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)

        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        multiply = findViewById(R.id.multiply)
        divide = findViewById(R.id.divide)
        modulo = findViewById(R.id.modulo)
        equals = findViewById(R.id.equals)

        sin = findViewById(R.id.sin)
        cos = findViewById(R.id.cos)
        tan = findViewById(R.id.tan)
        log = findViewById(R.id.log)
        square = findViewById(R.id.sqr)
        squareroot = findViewById(R.id.sqrRoot)
        factorial = findViewById(R.id.factorial)
        power = findViewById(R.id.power)

        openBrac = findViewById(R.id.openbracket)
        closedBrac = findViewById(R.id.closebracket)

        changeSign = findViewById(R.id.changeSign)

        decimal = findViewById(R.id.decimal)

        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)

        ac = findViewById(R.id.clear)
        backspace = findViewById(R.id.backspace)

        one?.setOnClickListener { appendText("1", true) }
        two?.setOnClickListener { appendText("2", true) }
        three?.setOnClickListener { appendText("3", true) }
        four?.setOnClickListener { appendText("4", true) }
        five?.setOnClickListener { appendText("5", true) }
        six?.setOnClickListener { appendText("6", true) }
        seven?.setOnClickListener { appendText("7", true) }
        eight?.setOnClickListener { appendText("8", true) }
        nine?.setOnClickListener { appendText("9", true) }
        zero?.setOnClickListener { appendText("0", true) }

        plus?.setOnClickListener { appendText("+", false) }
        minus?.setOnClickListener { appendText("-", false) }
        multiply?.setOnClickListener { appendText("*", false) }
        divide?.setOnClickListener { appendText("/", false) }
        modulo?.setOnClickListener { appendText("%", false) }

        decimal?.setOnClickListener { appendText(".", true) }

        sin?.setOnClickListener{appendText("sin",false)}
        cos?.setOnClickListener{appendText("cos",false)}
        tan?.setOnClickListener{appendText("tan",false)}
        log?.setOnClickListener{appendText("log",false)}
        square?.setOnClickListener{appendText("^2",false)}
        squareroot?.setOnClickListener{appendText("√",false)}
        factorial?.setOnClickListener{appendText("!",true)}
        power?.setOnClickListener{appendText("^",false)}

        openBrac?.setOnClickListener{appendText("(",false)}
        closedBrac?.setOnClickListener{appendText(")",false)}

        changeSign?.setOnClickListener {
            result?.text = ""
            result?.hint = ""
            if (expression?.text?.length!! > 0 && expression?.text?.get(0) == '-') {
                expression?.text = expression?.text?.substring(1)
            } else {
                expression?.text = "-" + expression?.text
            }
        }

        equals?.setOnClickListener {
            try {
                val expr = ExpressionBuilder(expression?.text.toString()).build()
                val answer = expr.evaluate()
                result?.text = answer.toString()
            } catch (e: Exception) {
                result?.text = e.message
            }
        }

        backspace?.setOnClickListener {
            result?.text = ""
            result?.hint = ""
            val value = expression?.text
            if (value?.isNotEmpty()!!) {
                expression?.text = value.substring(0, value.length - 1)
            }
        }

        ac?.setOnClickListener {
            expression?.text = ""
            result?.text = ""
            result?.hint = ""
        }
    }

    private fun appendText(value: String, toBeCleared: Boolean) {

        if (result?.text != "") {
            expression?.text = ""
        }

        if (toBeCleared) {
            result?.text = ""
            expression?.append(value)
        } else {
            expression?.append(result?.text)
            expression?.append(value)
            result?.text = ""
        }

        result?.hint = expression?.text
    }
}