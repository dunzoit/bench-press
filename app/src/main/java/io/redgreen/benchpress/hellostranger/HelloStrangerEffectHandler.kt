package io.redgreen.benchpress.hellostranger

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers


class HelloStrangerEffectHandler {

    fun createEffectHandler(
        interact: Interactor2
    ): ObservableTransformer<HelloStrangerEffect, HelloStrangerEvent> {

        return RxMobius.subtypeEffectHandler<HelloStrangerEffect, HelloStrangerEvent>()
            .addConsumer(
                ShowErrorEffect::class.java, {
                    interact.showError()
                }, AndroidSchedulers.mainThread()
            )
            .build()

    }
}