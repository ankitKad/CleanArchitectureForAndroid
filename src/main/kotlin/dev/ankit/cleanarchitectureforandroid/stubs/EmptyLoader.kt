package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyLoaderFragment(
    packageName: String
) = """
    package $packageName.presentation.widget.loader

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.DialogFragment
    import $packageName.databinding.FragmentLoaderBinding

    class LoaderFragment : DialogFragment() {
        private var binding: FragmentLoaderBinding? = null

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentLoaderBinding.inflate(inflater, container, false)
            return binding?.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            dialog?.setCancelable(false)
            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        override fun onDestroy() {
            super.onDestroy()
            binding = null
        }

        companion object {
            fun newInstance() = LoaderFragment()
        }

    }
""".trimIndent()

fun emptyLoaderUtil(
    packageName: String
) = """
    package $packageName.presentation.widget.loader

    import androidx.fragment.app.FragmentManager

    object LoaderUtil {

        private const val LOADER_TAG = "LoaderFragment"

        fun showLoader(fragmentManager: FragmentManager) {
            if (fragmentManager.findFragmentByTag(LOADER_TAG) == null) {
                val loaderFragment = LoaderFragment()
                loaderFragment.show(fragmentManager, LOADER_TAG)
            }
        }

        fun hideLoader(fragmentManager: FragmentManager) {
            val loaderFragment = fragmentManager.findFragmentByTag(LOADER_TAG) as? LoaderFragment
            loaderFragment?.dismissAllowingStateLoss()
        }
    }

""".trimIndent()