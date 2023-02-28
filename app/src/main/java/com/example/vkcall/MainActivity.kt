package com.example.vkcall

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.*
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoButton = findViewById<ImageButton>(R.id.imageVideo)
        val microButton = findViewById<ImageButton>(R.id.imageMicro)
        val cancelCallButton = findViewById<ImageButton>(R.id.cancelCall)
        val messageButton = findViewById<ImageButton>(R.id.imageButtonChat)
        val groupButton = findViewById<ImageButton>(R.id.imageButtonGroup)
        val mosaicButton = findViewById<ImageButton>(R.id.imageButtonMosaic)
        val handButton = findViewById<ImageButton>(R.id.imageHand)
        var imageFirstView = findViewById<ImageView>(R.id.first_image)
        val imageSecondView = findViewById<ImageView>(R.id.second_image)
        var buttonOn = true
        var buttonOnTwo = true
        var firstCard = findViewById<CardView>(R.id.first_card)
        var secondCard = findViewById<CardView>(R.id.second_card)

        fun setGradient(view: ImageView, cardView: CardView) {
            val bm = view.drawable.toBitmap()
            val pallete = Palette.from(bm).generate()
            val color: Int = pallete.getDominantColor(1)
            val gradient = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(0xFFF, color)
            )
            gradient.cornerRadius = 20f
            cardView.background = gradient
        }
        setGradient(imageFirstView, firstCard)
        setGradient(imageSecondView, secondCard)


        fun swapViews(view1: View, view2: View){
            val view1Parent = view1.parent as ViewGroup
            val view1Params = view1.layoutParams
            val view2Parent = view2.parent as ViewGroup
            val view2Params = view2.layoutParams
            view1Parent.removeView(view1)
            view2Parent.removeView(view2)
            view2Parent.addView(view1)
            view1Parent.addView(view2)
            view1.layoutParams = view2Params
            view2.layoutParams = view1Params
        }



        videoButton.setOnClickListener{
            if(!buttonOn){
                buttonOn = true
                videoButton.setImageResource(R.drawable.circle_camera_on)
            }else{
                buttonOn = false
                videoButton.setImageResource(R.drawable.circle_camera_off)
                //intent.action = MediaStore.INTENT_ACTION_VIDEO_CAMERA
            }
        }///переписать чтобы работало под несколько разных типов кнопок

        microButton.setOnClickListener {
            if(!buttonOnTwo){
                buttonOnTwo = true
                microButton.setImageResource(R.drawable.circle_mic_on)
            }else{
                buttonOnTwo = false
                microButton.setImageResource(R.drawable.circle_mic_off)
                //intent.action = MediaStore.INTENT_ACTION_VIDEO_CAMERA
            }
        }

        cancelCallButton.setOnClickListener {
            finishAffinity()
        }

        messageButton.setOnClickListener {
            intent = Intent(ACTION_SEND)
            intent.type = "vnd.android-dir/mms-sms"
            startActivity(intent)

        }

        groupButton.setOnClickListener {
            intent = Intent(ACTION_DEFAULT, ContactsContract.Contacts.CONTENT_URI)
            startActivity(intent)

        }

        mosaicButton.setOnClickListener {
            swapViews(firstCard, secondCard)
        }

        handButton.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)

            builder.setMessage("Привет) ")

            builder.setTitle("Поздоровайся со мной")

            builder.setCancelable(false)

            builder.setPositiveButton("Привет)"
            ) { dialog: DialogInterface, _: Int ->
                dialog.cancel()
            }
            builder.setNegativeButton("Пока"
            ) { dialog: DialogInterface, _: Int ->

                dialog.cancel()
            }

            val alertDialog: AlertDialog = builder.create()

            alertDialog.show()

        }


    }






}