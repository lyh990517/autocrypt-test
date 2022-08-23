package yunho.app.autocrypt.Presentation.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.androidx.viewmodel.ext.android.viewModel
import yunho.app.autocrypt.Presentation.BaseActivity
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.DataState
import yunho.app.autocrypt.Presentation.ViewModel.MainViewModel
import yunho.app.autocrypt.R

class MainActivity : BaseActivity<BaseViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val viewModel: MainViewModel by viewModel()

    override fun observeData() {
        viewModel.CenterLiveData.observe(this){ State ->
            when(State){
                is DataState.UnInitialized -> initViews()
                is DataState.Loading -> handleLoading()
                is DataState.success -> handleSuccess(State)
            }
        }
    }

    private fun handleSuccess(state: DataState.success) {
        state.Data.forEach { Center ->
            Log.e("Main","${Center}")
        }
    }

    private fun handleLoading() {

    }

    private fun initViews() {
        Log.e("Main","Init!!")
    }
}