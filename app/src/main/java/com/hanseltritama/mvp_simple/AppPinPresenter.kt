package com.hanseltritama.mvp_simple

import android.R
import android.view.View
import com.hanseltritama.mvp_simple.AppPinContract.Presenter


class AppPinPresenter(private val view: AppPinContract.View) : Presenter {

    private val model: AppPinModel = AppPinModel()

    override fun loadNextScreen() {
        view.navigateNextScreen()
    }

    override fun defaultSettings() {
        view.setButtonColor(R.drawable.btn_ash)
        view.showButtonClick(false)
        view.showTickVisibility(View.INVISIBLE)
    }

    override fun verifyEntries() {
        view.setButtonColor(R.drawable.btn_red)
        view.showButtonClick(true)
        view.showTickVisibility(View.VISIBLE)
    }

    override fun savePassword(password: String) {
        model.password = password
    }

    override fun appendIndvidualPassword(
        first: String?,
        second: String?,
        third: String?,
        fourth: String?
    ): String? {
        val sb = StringBuilder()
        sb.append(first).append(second).append(third).append(fourth)
        return sb.toString()
    }

}