package yunho.app.autocrypt.Presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import yunho.app.autocrypt.Data.RemoteDB.Key
import yunho.app.autocrypt.Data.Repository.CenterRepository
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.DataState

class MainViewModel(
    private val Repository : CenterRepository
) :  BaseViewModel() {
    private var _CenterLiveData = MutableLiveData<DataState>(DataState.UnInitialized)
    val CenterLiveData = _CenterLiveData
    override fun fetchData(): Job = viewModelScope.launch{
        _CenterLiveData.postValue(DataState.Loading)
        _CenterLiveData.postValue(DataState.success(Repository.getCenterList(1,100,Key.KEY)))
    }
}