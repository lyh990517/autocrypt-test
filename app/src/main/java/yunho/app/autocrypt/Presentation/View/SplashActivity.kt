package yunho.app.autocrypt.Presentation.View

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import yunho.app.autocrypt.Presentation.BaseActivity
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.State.CenterDataState
import yunho.app.autocrypt.Presentation.ViewModel.MainViewModel
import yunho.app.autocrypt.databinding.ActivityMainBinding

class SplashActivity : BaseActivity<BaseViewModel>() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override val viewModel: MainViewModel by viewModel()

    override fun observeData() {
        viewModel.CenterLiveData.observe(this) { State ->
            when (State) {
                is CenterDataState.UnInitialized -> initViews()
                is CenterDataState.Loading -> handleLoading(State)
                is CenterDataState.success -> handleSuccess(State)
                is CenterDataState.Error -> handleError()
            }
        }
    }

    private fun handleLoading(state: CenterDataState.Loading) = with(binding) {
        progressBar.isVisible = true
        loadingText.text = "On Loading!!"
        state.RemoteData.forEachIndexed { LoadingCount, Center ->
            Log.e("save", "${LoadingCount + 1}% ${Center}")
            viewModel.saveCenterToLocalDB(Center)
        }
    }

    private fun handleError() {
    }

    private fun handleSuccess(state: CenterDataState.success) = with(binding) {
        state.LocalData.forEach { Center ->
            Log.e("In Database", "${Center}")
        }
        Handler().postDelayed(Runnable {
            progressBar.isVisible = false
            loadingText.text = "Loading Complete!!"
            val intent = Intent(this@SplashActivity, MapActivity::class.java)
            startActivity(intent)
        }, 2000)

    }


    private fun initViews() {
        Log.e("Main", "Init!!")
    }
}