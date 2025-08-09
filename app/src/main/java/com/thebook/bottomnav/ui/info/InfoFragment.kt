package com.thebook.bottomnav.ui.info

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.thebook.bottomnav.R

/**
 * InfoFragment - Displays detailed information about selected movies
 * 
 * Converted to Kotlin from Java. This fragment is navigated to when users
 * click on movie items in the HomeFragment and displays movie details.
 */
class InfoFragment : Fragment() {

    private val viewModel: InfoViewModel by viewModels()

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.info_fragment, container, false)
        
        val textView: TextView = root.findViewById(R.id.text_info)
        val imageView: ImageView = root.findViewById(R.id.info_image)

        // Observe ViewModel data
        viewModel.text.observe(viewLifecycleOwner) { text ->
            // Text is set from arguments below, so this is commented out
            // textView.text = text
        }

        // Handle arguments passed from navigation
        arguments?.let { args ->
            val title = args.getString("title")
            val poster = args.getString("poster")
            
            // Set the title
            title?.let { textView.text = it }
            
            // Load image from cache if poster is available
            poster?.let {
                val pathToPicture = "${requireContext().cacheDir}/$it"
                val bitmap = BitmapFactory.decodeFile(pathToPicture)
                bitmap?.let { imageView.setImageBitmap(it) }
            }
        }

        return root
    }
}
