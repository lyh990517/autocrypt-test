package yunho.app.autocrypt.Presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Job

abstract class BaseActivity<vm : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel : vm
    private lateinit var fetchJob: Job
    abstract fun observeData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
        fetchJob = viewModel.fetchData()
    }

    override fun onDestroy() {
        if(fetchJob.isActive){
            fetchJob.cancel()
        }
        super.onDestroy()
    }
}