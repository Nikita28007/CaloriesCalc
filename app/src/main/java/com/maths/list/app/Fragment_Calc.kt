package com.maths.list.app

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText

class Fragment_Calc : Fragment() {
    lateinit var weightEditText: TextInputEditText
    lateinit var ageEditText: TextInputEditText
    lateinit var heightEditText: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var kcal = 0
        val view = inflater.inflate(R.layout.calculator_fragment, container, false)
        val radioGroupGender = view.findViewById<RadioGroup>(R.id.genderRadioGroup)
        val maleRadioButton = view.findViewById<RadioButton>(R.id.maleRadioButton)
        val femaleRadioButton = view.findViewById<RadioButton>(R.id.femaleRadioButton)

        weightEditText = view.findViewById(R.id.weightEditText)
        ageEditText = view.findViewById(R.id.ageEditText)
        heightEditText = view.findViewById(R.id.heightEditText)
        val kcalBundle = Bundle()

        view.findViewById<Button>(R.id.calcButton).setOnClickListener {
            if ((weightEditText.text!!.isNotEmpty() && ageEditText.text!!.isNotEmpty() && heightEditText.text!!.isNotEmpty()) && (maleRadioButton.isChecked || femaleRadioButton.isChecked)) {

                when (radioGroupGender.checkedRadioButtonId) {
                    maleRadioButton.id -> {
                        kcal =
                            ((weightEditText.text.toString().toInt() * 10) + (heightEditText.text.toString()
                                .toInt() * 6.25) - (ageEditText.text.toString().toInt() * 5 + 5)).toInt()
                        kcalBundle.putString("kcal", kcal.toString())
                        findNavController().navigate(
                            R.id.action_fragment_Calc_to_fragment_Result,
                            kcalBundle
                        )

                    }

                    femaleRadioButton.id -> {
                        kcal =
                            ((weightEditText.text.toString().toInt() * 10) + (heightEditText.text.toString()
                                .toInt() * 6.25) - (ageEditText.text.toString()
                                .toInt() * 5 - 161)).toInt()
                        kcalBundle.putString("kcal", kcal.toString())
                        findNavController().navigate(
                            R.id.action_fragment_Calc_to_fragment_Result,
                            kcalBundle
                        )
                    }

                }


            } else {

                Toast.makeText(view.context, "Please fill all the fields", Toast.LENGTH_LONG).show()
            }


        }


        /*
        для женщин: масса тела, кг * 10 + рост (см) * 6,25 — возраст (годы) * 5 — 161;
        для мужчин: масса тела, кг * 10 + рост (см) * 6,25 — возраст (годы) * 5 + 5.
         */
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    weightEditText.text?.clear()
                    ageEditText.text?.clear()
                    heightEditText.text?.clear()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }


}