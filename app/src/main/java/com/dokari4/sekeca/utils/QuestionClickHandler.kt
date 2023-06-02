package com.dokari4.sekeca.utils

import android.view.View
import com.dokari4.sekeca.data.local.Model

interface QuestionClickHandler {
    fun clickedQuestionItem(view: View, model: Model)
}