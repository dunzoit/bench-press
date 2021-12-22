package io.redgreen.benchpress.hellostranger

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HelloStrangerModel(val name:String):Parcelable{

    companion object{
        val EMPTY = HelloStrangerModel("")
    }

    fun emptyName():HelloStrangerModel = copy(name = "")
    fun setName(changeName:String):HelloStrangerModel = copy(name = changeName)
}