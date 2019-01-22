package com.utildev.kotlin.arch.architecturecomponentskotlin.common.helper

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
  private val observers = CopyOnWriteArraySet<ObserverWrapper<T>>()

  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
    val wrapper = ObserverWrapper(observer)
    observers.add(wrapper)
    super.observe(owner, wrapper)
  }

  override fun removeObservers(owner: LifecycleOwner) {
    observers.clear()
    super.removeObservers(owner)
  }

  override fun removeObserver(observer: Observer<T>) {
    observers.remove(observer)
    super.removeObserver(observer)
  }

  @MainThread
  override fun setValue(t: T?) {
    observers.forEach { it.newValue() }
    super.setValue(t)
  }

  /**
   * Used for cases where T is Void, to make calls cleaner.
   */
  @MainThread
  fun call() {
    value = null
  }

  private class ObserverWrapper<T>(private val observer: Observer<T>) : Observer<T> {

    private val pending = AtomicBoolean(false)

    override fun onChanged(t: T?) {
      if (pending.compareAndSet(true, false)) {
        observer.onChanged(t)
      }
    }

    fun newValue() {
      pending.set(true)
    }
  }
}