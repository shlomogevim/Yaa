package com.example.yaa

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class Drawing(context: Context?) : View(context) {
    private lateinit var brush: Paint
    private lateinit var lineBrush: Paint
    lateinit var pen: Paint


    override fun draw(canvas: Canvas?) {

      /*  val bitmap = BitmapFactory.decodeResource(resources, R.drawable.paulo)
        canvas?.drawBitmap(
            bitmap, (measuredWidth / 2 - bitmap.width / 2).toFloat(),
            (measuredHeight / 2 - bitmap.height / 2).toFloat(), null
        )*/


         brush= Paint(Paint.ANTI_ALIAS_FLAG)
         lineBrush= Paint(Paint.ANTI_ALIAS_FLAG)
         brush.color= Color.parseColor("blue")
         lineBrush.color=Color.MAGENTA
         lineBrush.strokeWidth=13f
         canvas!!.drawCircle((measuredWidth/2).toFloat(), (measuredHeight/2).toFloat(),98f,brush)
        canvas.drawLine(0f,0f, (measuredWidth/2).toFloat(), (measuredHeight/2).toFloat(),lineBrush)

      /*  pen= Paint(Paint.ANTI_ALIAS_FLAG)
        pen.color=Color.BLUE
        pen.textSize=96f
        pen.textAlign=Paint.Align.CENTER

        canvas!!.drawText("Hello Word", (measuredWidth/2).toFloat(), (measuredHeight/2).toFloat(),pen)

    */    canvas?.save()



        super.draw(canvas)
    }
}