package com.maths.list.app

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Fragment_Result : Fragment() {
    private var arg = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.result_calc_fragment, container, false)
        arg = arguments?.getString("kcal").toString()
        val kcalTextview = view.findViewById<TextView>(R.id.caloriesDayResultTextview)
        val proteins = view.findViewById<TextView>(R.id.proteinsResultTextview)
        val carbohydrates = view.findViewById<TextView>(R.id.carbohydratesResultTextview)
        val fats = view.findViewById<TextView>(R.id.fatsResultTextview)

        val proteinsValue = arg.toInt() * 25 / 100
        val carbohydratesValue = arg.toInt() * 50 / 100
        val fatsValue = arg.toInt() * 25 / 100
        startCountAnimation(kcalTextview, arg.toInt(), 1)
        startCountAnimation(proteins, proteinsValue, 2)
        startCountAnimation(carbohydrates, carbohydratesValue, 3)
        startCountAnimation(fats, fatsValue, 4)


        val foodRec = view.findViewById<TextView>(R.id.foodRecResultTextview)
        val textRec = StringBuilder("Variety of Foods: Include a wide variety of foods in your diet to ensure you get all the essential nutrients. Try different types of vegetables, fruits, protein sources, and carbohydrates.\n" +
                "\n" +
                "Protein: Make sure you're getting an adequate amount of protein. Protein helps maintain muscle mass and keeps you feeling full. Great sources of protein include chicken, turkey, fish, eggs, tofu, and dairy products.\n" +
                "\n" +
                "Carbohydrates: Carbohydrates are an important source of energy. Choose complex carbohydrates like whole grains, oats, quinoa, and bulgur. Limit your intake of simple carbohydrates, such as sugar and sweets.\n" +
                "\n" +
                "Fats: Pay attention to the types of fats you consume. Opt for unsaturated fats like olive oil, nuts, and avocado. Limit saturated and trans fats, as they can increase the risk of heart disease.\n" +
                "\n" +
                "Portion Control: Be mindful of portion sizes. Even low-calorie foods can lead to weight gain if consumed in excess.\n" +
                "\n" +
                "Hydration: Drink an adequate amount of water throughout the day. Water can help reduce feelings of hunger and support metabolism.\n" +
                "\n" +
                "Fruits and Vegetables: Increase your consumption of fruits and vegetables. They are rich in vitamins, minerals, and dietary fiber, which can promote a feeling of fullness.\n" +
                "\n" +
                "Moderate Snacking: If you need to snack, choose healthy options like nuts, berries, or low-fat yogurt.\n" +
                "\n" +
                "Food Diary: Keep a food diary to track your calorie intake. This can help you better control your eating habits.")
        foodRec.typeWrite(viewLifecycleOwner,textRec.toString(),1L)

        return view
    }

    private fun startCountAnimation(textView: TextView, endvalue: Int, index: Int) {
        val animator = ValueAnimator.ofInt(0, endvalue)
        animator.duration = 1500
        animator.addUpdateListener { animation ->

            when (index) {
                1 -> {
                    textView.text = StringBuilder(animation.animatedValue.toString() + " kcal")
                }

                2 -> {
                    textView.text =
                        StringBuilder("Proteins: " + animation.animatedValue.toString() + " kcal")
                }

                3 -> {
                    textView.text =
                        StringBuilder("Carbohydrates: " + animation.animatedValue.toString() + " kcal")
                }

                4 -> {
                    textView.text =
                        StringBuilder("Fats: " + animation.animatedValue.toString() + " kcal")
                }
            }
        }
        animator.start()
    }

    private fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
        this@typeWrite.text = ""
        lifecycleOwner.lifecycleScope.launch {
            repeat(text.length) {
                delay(intervalMs)
                this@typeWrite.text = text.take(it + 1)
            }
        }
    }

}