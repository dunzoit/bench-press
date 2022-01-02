package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity
import kotlinx.android.synthetic.main.counter_activity.*

class CounterActivity : BaseActivity<ModelCounter, CounterEvent, CounterEffect>(), CounterInteractor {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CounterActivity::class.java))
        }
    }

    override fun layoutResId(): Int {
        return R.layout.counter_activity
    }

    override fun setup() {
        incrementButton.setOnClickListener { eventSource.notifyEvent(IncrementCounterEvent) }
        decrementButton.setOnClickListener { eventSource.notifyEvent(DecrementCounterEvent) }
    }

    override fun initialModel(): ModelCounter {
        return ModelCounter.ZERO
    }

    override fun updateFunction(
        model: ModelCounter,
        event: CounterEvent
    ): Next<ModelCounter, CounterEffect> {
        return CounterLogic.update(model, event)
    }

    override fun render(model: ModelCounter) {
        counterTextView.text = model.count
    }

    override fun effectHandler(): ObservableTransformer<CounterEffect, CounterEvent> {
        return CounterEffectHandler().createEffectHandler(
            interact = this@CounterActivity,
            schedulers = AndroidSchedulers.mainThread()
        )
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.lower_limit), Toast.LENGTH_SHORT).show()
    }
}



