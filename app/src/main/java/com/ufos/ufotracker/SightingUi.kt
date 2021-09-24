package com.ufos.ufotracker

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.ufos.ufotracker.data.UfoType
import com.ufos.ufotracker.databinding.SightingUiElementBinding

/**
 * Custom view for managing the display of a sighting
 *
 * TODO: remove state from view.
 */
class SightingUi @kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = SightingUiElementBinding.inflate(LayoutInflater.from(context), this)

    init {
        this.setOnLongClickListener {
            toggleState()
            return@setOnLongClickListener true
        }
    }

    // might be better to keep this kind of logic outside the custom view
    private val typeToImage = mapOf(
        UfoType.BLOB to R.drawable.ic_blob_small,
        UfoType.LAMPSHADE to R.drawable.ic_ufo_small,
        UfoType.MYSTERIOUS_LIGHTS to R.drawable.ic_lights_small
    )

    private enum class State {
        VIEW, DELETE
    }

    private var currentState = State.VIEW

    fun setTitle(text: String) {
        binding.title.text = text
    }

    fun setSubtitle(text: String) {
        binding.subtitle.text = text
    }

    fun setImage(type: UfoType) {
        val res = typeToImage[type] ?: throw Exception("Missing Type")

        val drawable = colorDrawable(res)
        binding.image.background = drawable
    }

    fun setOnDeleteButtonListener(onClick: () -> Unit) {
        binding.deleteButton.setOnClickListener {
            onClick.invoke()
        }
    }

    private fun toggleState() {
        if (currentState == State.VIEW) {
            binding.deleteButton.visibility = VISIBLE
            currentState = State.DELETE
        } else {
            binding.deleteButton.visibility = GONE
            currentState = State.VIEW
        }
    }

    private fun colorDrawable(drawableId: Int): Drawable {
        val drawable = ContextCompat.getDrawable(context, drawableId)!!
        val wrapDrawable = DrawableCompat.wrap(drawable.mutate())
        DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(context, R.color.alien_green))
        return wrapDrawable
    }
}