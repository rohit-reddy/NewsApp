
package com.rohith.rohithltiassessment.article

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rohith.rohithltiassessment.R
import com.rohith.rohithltiassessment.databinding.FragmentArticleBinding

/**
 * This fragment shows the the status of the News web services transaction.
 */
class ArticlesFragment : Fragment() {

    /**
     * Lazily initialize our [ArticlesViewModel].
     */
    private val viewModel: ArticlesViewModel by lazy {
        ViewModelProvider(this).get(ArticlesViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentArticleBinding.inflate(inflater)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.newsFeed.adapter = ArticleAdapter(ArticleAdapter.OnClickListener {
            viewModel.displayArticleDetails(it)
        })

        viewModel.navigateToSelectedArticle.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    ArticlesFragmentDirections.actionShowDetail(it))
                viewModel.displayArticleDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
