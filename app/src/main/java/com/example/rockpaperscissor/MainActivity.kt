package com.example.rockpaperscissor

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.rockpaperscissor.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var n1 = ""
    var n2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rock.setOnClickListener {
            binding.usr.setImageResource(R.drawable.rock)
            n1 = "rock"
            display()
        }
        binding.paper.setOnClickListener {
            binding.usr.setImageResource(R.drawable.paper)
            n1 = "paper"
            display()
        }
        binding.scissor.setOnClickListener {
            binding.usr.setImageResource(R.drawable.scissor)
            n1 = "scissor"
            display()
        }
    }

    fun display() {
        val rand = Random.nextInt(1, 4)
        when (rand) {
            1 -> {
                n2 = "rock"
                binding.comp.setImageResource(R.drawable.rock)
            }
            2 -> {
                n2 = "paper"
                binding.comp.setImageResource(R.drawable.paper)
            }
            3 -> {
                n2 = "scissor"
                binding.comp.setImageResource(R.drawable.scissor)
            }
        }
        result()
    }

    fun result() {
        val handler = Handler(Looper.getMainLooper())
        if (n1 == n2) {
            handler.postDelayed({
                setDialog()
            }, 1000) // Adjusted delay
        } else if ((n1 == "rock" && n2 == "scissor") || (n1 == "paper" && n2 == "rock") || (n1 == "scissor" && n2 == "paper")) {
            handler.postDelayed({
                setWinDialog()
            }, 1000) // Adjusted delay
        } else {
            handler.postDelayed({
                setLossDialog()
            }, 1000) // Adjusted delay
        }
    }

    private fun setLossDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.loss)

        val closeButton: ImageView = dialog.findViewById(R.id.close)
        closeButton.setOnClickListener {
            dialog.dismiss()
            reset()
        }

        dialog.show()
    }

    private fun setWinDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.won)

        val closeButton: ImageView = dialog.findViewById(R.id.close)
        closeButton.setOnClickListener {
            dialog.dismiss()
            reset()
        }

        dialog.show()
    }

    private fun setDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.tie)

        val closeButton: ImageView = dialog.findViewById(R.id.close)
        closeButton.setOnClickListener {
            dialog.dismiss()
            reset()
        }

        dialog.show()
    }

    fun reset() {
        binding.usr.setImageResource(R.drawable.img)
        binding.comp.setImageResource(R.drawable.img_1)
    }
}
