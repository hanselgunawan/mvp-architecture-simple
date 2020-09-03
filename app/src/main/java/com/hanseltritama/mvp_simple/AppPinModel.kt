package com.hanseltritama.mvp_simple

class AppPinModel {

    var password: String?
        get() = password
        set(password) {
            this.password = password
        }

    constructor(password: String?) {
        this.password = password
    }

    constructor() {}

}