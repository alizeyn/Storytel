package ir.alizeyn.storytel.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.databinding.FragmentNetworkErrorBinding
import ir.alizeyn.storytel.network.NetworkRetryState
import ir.alizeyn.storytel.viewmodel.NetworkErrorViewModel


@AndroidEntryPoint
class NetworkErrorFragment : DialogFragment() {

    private var _binding: FragmentNetworkErrorBinding? = null
    private val binding get() = _binding!!

    private val networkErrorViewModel: NetworkErrorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNetworkErrorBinding.inflate(inflater, container, false)

        binding.retry.setOnClickListener {

            networkErrorViewModel.retry.value = NetworkRetryState.RETRY
        }

        networkErrorViewModel.retry.observe(viewLifecycleOwner, {

            when (it) {
                NetworkRetryState.RETRY -> {
                    binding.retryProgress.visibility = View.VISIBLE
                    binding.retry.isEnabled = false
                }
                NetworkRetryState.IDLE -> {
                    binding.retryProgress.visibility = View.INVISIBLE
                    binding.retry.isEnabled = true
                }
                NetworkRetryState.RESOLVED -> {
                    dismiss()
                }
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupFullScreenDialog(dialog)
    }

    override fun onDestroyView() {
        Log.i("TAG", "onDestroyView: NetworkErrorFragment Destoryed")
        super.onDestroyView()
        _binding = null
        networkErrorViewModel.retry.value = null
    }

    private fun setupFullScreenDialog(dialog: Dialog?) {

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setStyle(
            STYLE_NORMAL,
            R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen
        )
        dialog?.setCancelable(false)
    }
}