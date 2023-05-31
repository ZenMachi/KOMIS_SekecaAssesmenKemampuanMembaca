package com.dokari4.sekeca

import android.content.Context
import android.view.View

interface QuestionClickHandler {
    fun clickedQuestionItem(view: View, model: Model)
}