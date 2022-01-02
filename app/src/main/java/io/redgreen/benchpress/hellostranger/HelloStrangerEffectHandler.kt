package io.redgreen.benchpress.hellostranger

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


class HelloStrangerEffectHandler {

    fun createEffectHandler(
        interact: HelloStrangerInteractor,
        scheduler: Scheduler
    ): ObservableTransformer<HelloStrangerEffect, HelloStrangerEvent> {

        return RxMobius.subtypeEffectHandler<HelloStrangerEffect, HelloStrangerEvent>()
            .addConsumer(
                ShowErrorEffect::class.java, {
                    interact.showError()
                }, scheduler
            )
            .build()

    }
}