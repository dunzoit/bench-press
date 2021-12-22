package io.redgreen.benchpress.hellostranger

import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import com.spotify.mobius.Update

object HelloStrangerLogic : Update<HelloStrangerModel, HelloStrangerEvent, HelloStrangerEffect> {
    override fun update(
        model: HelloStrangerModel, event: HelloStrangerEvent
    ): Next<HelloStrangerModel, HelloStrangerEffect> {

        return when (event) {
            is EmptyNameEvent -> {
                Next.next(model.emptyName(), setOf<HelloStrangerEffect>(ShowErrorEffect))
            }
            is ChangeNameEvent -> {
                Next.next(model.setName(event.name))
            }
        }
    }

}