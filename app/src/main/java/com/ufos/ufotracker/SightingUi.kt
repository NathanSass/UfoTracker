package com.ufos.ufotracker

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.ufos.ufotracker.databinding.SightingUiElementBinding
import java.time.Instant
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH

class SightingUi @kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = SightingUiElementBinding.inflate(LayoutInflater.from(context), this)

    private val typeToImage = mapOf(
        UfoType.BLOB to R.drawable.ic_blob_small,
        UfoType.LAMPSHADE to R.drawable.ic_ufo_small,
        UfoType.MYSTERIOUS_LIGHTS to R.drawable.ic_lights_small
    )

    fun setTitle(title: String) {
        binding.title.text = title
    }

    fun setImage(type: UfoType) {
        val res = typeToImage[type] ?: throw Exception("Missing Type")

        val drawable = colorDrawable(res)
        binding.image.background = drawable
    }

    private fun colorDrawable(drawableId: Int): Drawable {
        val drawable = ContextCompat.getDrawable(context, drawableId)!!
        val wrapDrawable = DrawableCompat.wrap(drawable.mutate())
        DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(context, R.color.alien_green))
        return wrapDrawable
    }
}