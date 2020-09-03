package com.hanseltritama.mvp_simple

interface AppPinContract {

    interface View {
        fun showButtonClick(b: Boolean)
        fun setButtonColor(color: Int)
        fun navigateNextScreen()
        fun showError(error: String)
        fun showTickVisibility(value: Int)
    }

    interface Presenter {
        fun loadNextScreen()
        fun defaultSettings()
        fun verifyEntries()
        fun savePassword(password: String)
        fun appendIndvidualPassword(
            first: String?,
            second: String?,
            third: String?,
            fourth: String?
        ): String?

    }

}