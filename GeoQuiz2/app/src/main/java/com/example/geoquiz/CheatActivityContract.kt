package com.example.geoquiz

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class CheatActivityContract : ActivityResultContract<Boolean, Boolean>() {

    override fun createIntent(context: Context, input: Boolean): Intent {
            return Intent(context, CheatActivity::class.java).putExtra(Constatnts.KEY_INDEX.keyValue, input)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return intent?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
    }

    companion object{
        const val EXTRA_ANSWER_SHOWN = "com.bingerdranch.android.geoquiz.answer_shown"
    }
}