package io.redgreen.benchpress.counter


import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class CounterEffectHandler{

    fun createEffectHandler(
        interact: CounterInteractor,
        schedulers: Scheduler
    ) :ObservableTransformer<CounterEffect, CounterEvent> {
        
        return RxMobius.subtypeEffectHandler<CounterEffect, CounterEvent>()
            .addConsumer(
                ShowErrorEffect::class.java,
                {
                    interact.showError()
                },
                schedulers
            )
            .build()
        
    }
        

}

