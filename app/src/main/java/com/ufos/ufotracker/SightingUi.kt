package com.ufos.ufotracker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ufos.ufotracker.databinding.SightingUiElementBinding

class SightingUi @kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = SightingUiElementBinding.inflate(LayoutInflater.from(context), this)

    fun setTitle(title: String) {
        binding.title.text = title
    }
}