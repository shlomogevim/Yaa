package com.example.yaa

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // 1
    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    // 2
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    private val paint = Paint()
    private val mouthPath = Path()
    private var size = 0

    // 3
    var happinessState = HAPPY
        set(state) {
            field = state
            // 4
            invalidate()
        }

    // 5
    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // 6
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView,
            0, 0)

        // 7
        // Extract custom attributes into member variables
        happinessState = typedArray.getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor = typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor = typedArray.getColor(R.styleable.EmotionalFaceView_borderColor,
            DEFAULT_BORDER_COLOR)
        borderWidth = typedArray.getDimension(R.styleable.EmotionalFaceView_borderWidth,
            DEFAULT_BORDER_WIDTH)

        // 8
        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 1
        size = Math.min(measuredWidth, measuredHeight)
// 2
        setMeasuredDimension(size, size)


    }

    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        // 1
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        // 2
        val radius = size / 2f

        // 3
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // 4
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        // 5
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        // 1
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

// 2
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

// 3
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)

    }

    private fun drawMouth(canvas: Canvas) {

        // Clear
        mouthPath.reset()

        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        if (happinessState == HAPPY) {
            // Happy mouth path
            mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
        } else {
            // Sad mouth path
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        }

        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        // Draw mouth path
        canvas.drawPath(mouthPath, paint)
    }

    override fun onSaveInstanceState(): Parcelable {
        // 1
        val bundle = Bundle()
        // 2
        bundle.putLong("happinessState", happinessState)
        // 3
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        // 4
        var viewState = state
        if (viewState is Bundle) {
            // 5
            happinessState = viewState.getLong("happinessState", HAPPY)
            // 6
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
    }

}