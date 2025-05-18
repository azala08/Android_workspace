package com.example.mymeditation

object MusicPlaybackNotifier {
    private val observers = mutableListOf<() -> Unit>()

    fun registerObserver(observer: () -> Unit) {
        if (!observers.contains(observer)) {
            observers.add(observer)
        }
    }

    fun unregisterObserver(observer: () -> Unit) {
        observers.remove(observer)
    }

    fun notifyMusicChanged() {
        observers.forEach { it.invoke() }
    }
}
