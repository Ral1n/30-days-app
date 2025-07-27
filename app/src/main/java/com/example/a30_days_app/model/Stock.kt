package com.example.a30_days_app.model

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Stock (
    @StringRes val titleId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionId: Int
    ){

}