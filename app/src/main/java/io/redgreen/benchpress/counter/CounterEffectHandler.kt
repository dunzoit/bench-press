package io.redgreen.benchpress.counter


import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

class CounterEffectHandler{

    fun createEffectHandler(
        interact: Interactor
    ) :ObservableTransformer<CounterEffect, CounterEvent> {
        
        return RxMobius.subtypeEffectHandler<CounterEffect, CounterEvent>()
            .addConsumer(
                ShowErrorEffect::class.java,
                {
                    interact.showError()
                },
                AndroidSchedulers.mainThread()
            )
            .build()
        
    }
        

}

