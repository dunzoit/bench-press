package io.redgreen.benchpress.hellostranger

sealed class HelloStrangerEvent

object EmptyNameEvent:HelloStrangerEvent()
data class ChangeNameEvent(val name:String):HelloStrangerEvent()