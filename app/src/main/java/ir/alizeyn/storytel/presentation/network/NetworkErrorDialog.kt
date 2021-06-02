package ir.alizeyn.storytel.presentation.network

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.databinding.DialogNetworkErrorBinding

class NetworkErrorDialog(context: Context, private var retryAction: (() -> Unit)?) :
    Dialog(context, R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen) {

    private var _binding: DialogNetworkErrorBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setBackgroundDrawable(ColorDrawable(Color.rgb(255, 255, 255)))
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
        _binding = DialogNetworkErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCancelable(false)
        setRetryAction {
            showProgressState()
            retryAction?.let { it() }
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
        retryAction = null
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