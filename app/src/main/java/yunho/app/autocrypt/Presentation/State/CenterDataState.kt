package yunho.app.autocrypt.Presentation.State

import yunho.app.autocrypt.Data.Entity.CenterEntity

sealed class CenterDataState{
    object UnInitialized : CenterDataState()

    data class Loading (
        val RemoteData : List<CenterEntity>
    ) : CenterDataState()

    data class success(
        val LocalData : List<CenterEntity>
    ) : CenterDataState()

    object Error : CenterDataState()
}
