package app.takahashi.kamesan.calculator

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.takahashi.kamesan.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivityMainBinding

    var firstNumber = 0
    var secondNumber = 0
    var totalNumber = 0
    var operator = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{ setContentView(this.root) }

        // 初期状態の設定
        setInitialState()

        // 各ボタンがタップされたときの処理
        binding.numberButton0.setOnClickListener{ onClickNumber(0) }
        binding.numberButton1.setOnClickListener{ onClickNumber(1) }
        binding.numberButton2.setOnClickListener{ onClickNumber(2) }
        binding.numberButton3.setOnClickListener{ onClickNumber(3) }
        binding.numberButton4.setOnClickListener{ onClickNumber(4) }
        binding.numberButton5.setOnClickListener{ onClickNumber(5) }
        binding.numberButton6.setOnClickListener{ onClickNumber(6) }
        binding.numberButton7.setOnClickListener{ onClickNumber(7) }
        binding.numberButton8.setOnClickListener{ onClickNumber(8) }
        binding.numberButton9.setOnClickListener{ onClickNumber(9) }
        binding.plusButton.setOnClickListener{ operator = "+" }
        binding.minusButton.setOnClickListener{ operator = "-" }
        binding.multiplyButton.setOnClickListener{ operator = "×" }
        binding.clearButton.setOnClickListener { onClickClear() }
        binding.equalButton.setOnClickListener{ onClickEqual() }
    }

    // 初期状態
    private  fun setInitialState(){

        firstNumber = 0
        secondNumber = 0
        totalNumber = 0
        operator = "empty"

        setOperatorDisable()
    }

    // 演算子キーを利用不能にする
    private fun setOperatorDisable(){

        binding.plusButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(150,150,150))
        binding.minusButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(150,150,150))
        binding.multiplyButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(150,150,150))
        binding.equalButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(150,150,150))


        binding.plusButton.isEnabled = false
        binding.minusButton.isEnabled = false
        binding.multiplyButton.isEnabled = false
        binding.equalButton.isEnabled = false

        binding.numberText.text = firstNumber.toString()
    }

    // 演算子キーを使用可能にする処理
    private fun setOperatorEnable(){
        binding.plusButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(240,240,0))
        binding.minusButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(240,240,0))
        binding.multiplyButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(240,240,0))
        binding.equalButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(240,240,0))

        binding.plusButton.isEnabled = true
        binding.minusButton.isEnabled = true
        binding.multiplyButton.isEnabled = true
        binding.equalButton.isEnabled = true
    }

    // 計算式または計算結果を画面に表示する処理
    private fun display(num: Int){
        binding.numberText.text = num.toString()
    }

    // 数字キーが押されたときの処理
    private fun onClickNumber(num: Int) {
        // 演算子が入力されていない
        if(operator == "empty"){
            firstNumber = firstNumber * 10 + num
            display(firstNumber)
            setOperatorEnable()
        }
        // 演算子が入力されている
        else{
            secondNumber = secondNumber * 10 + num
            display(secondNumber)
        }
    }

    // イコールが押されたときの処理
    private fun onClickEqual(){
        totalNumber = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "×" -> firstNumber * secondNumber
            else -> firstNumber
        }
        display(totalNumber)
    }

    // クリアキーが押されたときの処理
    private fun onClickClear(){
        //　初期状態に戻す
        setInitialState()
    }
}