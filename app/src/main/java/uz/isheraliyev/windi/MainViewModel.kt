package uz.isheraliyev.windi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel : ViewModel() {
    companion object {
        val authChannel: Channel<Unit> = Channel(Channel.BUFFERED)
    }

    private val _internalAuthChannel: Channel<Unit> = Channel(Channel.BUFFERED)
    val authChannelFlow = _internalAuthChannel.receiveAsFlow()
}