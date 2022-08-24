package yunho.app.autocrypt.Presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.Data.RemoteDB.Key
import yunho.app.autocrypt.Data.Repository.CenterRepository
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.State.CenterDataState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val Repository: CenterRepository,
) : BaseViewModel() {
    private var _CenterLiveData = MutableLiveData<CenterDataState>(CenterDataState.UnInitialized)
    val CenterLiveData = _CenterLiveData
    override fun fetchData(): Job = viewModelScope.launch {
        _CenterLiveData.postValue(
            CenterDataState.Loading(
                Repository.getCenterList(
                    1,
                    100,
                    Key.KEY
                )
            )
        )
        _CenterLiveData.postValue(CenterDataState.success(Repository.getCenterListFromLocalDB()))
    }

    fun saveCenterToLocalDB(centerEntity: CenterEntity) = viewModelScope.launch {
        Repository.saveCenterData(centerEntity)
    }
}