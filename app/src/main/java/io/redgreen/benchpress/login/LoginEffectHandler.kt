package io.redgreen.benchpress.login

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

class LoginEffectHandler {

    fun createEffectHandler(
        interact: LoginInteractor,
        schedulers: Scheduler
    ): ObservableTransformer<LoginEffect, LoginEvent> {
        return RxMobius.subtypeEffectHandler<LoginEffect, LoginEvent>()
            .addConsumer(
                LoginButtonClickEffect::class.java, {
                    interact.openLoader()
                }, schedulers
            )
            .build()
    }

}