package ir.alizeyn.storytel.presentation.network

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.databinding.DialogNetworkErrorBinding

class NetworkErrorDialog(context: Context, private val retryAction: () -> Unit) :
    Dialog(context, R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen) {

    private var _binding: DialogNetworkErrorBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        _binding = DialogNetworkErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCancelable(false)
        setRetryAction {
            showProgressState()
            retryAction()
        }
    }

    override fun onStart() {
        super.onStart()
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    private fun setRetryAction(retryAction: () -> Unit) {
        binding.retry.setOnClickListener {
            retryAction.invoke()
        }
    }

    private fun showProgressState() {
        binding.retryProgress.visibility = View.VISIBLE
        binding.retry.isEnabled = false
    }

    fun showIdleState() {
        binding.retryProgress.visibility = View.INVISIBLE
        binding.retry.isEnabled = true
    }
}