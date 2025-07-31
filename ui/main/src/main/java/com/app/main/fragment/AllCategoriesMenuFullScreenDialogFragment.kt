package com.app.main.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.main.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AllCategoriesMenuFullScreenDialogFragment : DialogFragment(), View.OnClickListener {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_NetflixWithRxJava_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.dialog_fragment_categories_menu_full_screen, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCategories).apply {
            adapter = CategoriesMenuAdapter(this@AllCategoriesMenuFullScreenDialogFragment)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    itemPosition: Int,
                    parent: RecyclerView
                ) {
                    if (itemPosition == 0) {
                        outRect.top = 300
                    } else if (parent.layoutManager?.itemCount == (itemPosition + 1)) {
                        outRect.bottom = 160
                    }
                }
            })
        }
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener(this)
    }

    inner class CategoriesMenuAdapter(val onClickListener: View.OnClickListener) :
        RecyclerView.Adapter<CategoriesMenuAdapter.CategoriesMenuViewHolder>() {

        val categories = listOf(
            "All Categories",
            "Available for Download",
            "Indian",
            "US",
            "British",
            "European",
            "Asian",
            "Reality & Talk",
            "Action",
            "Dramas",
            "Sci-Fi & Fantasy",
            "Crime",
            "Sports",
            "Family",
            "Kids",
            "Docuseries",
            "Romance",
            "Thriller",
            "Horror",
            "Teen",
            "Anime",
            "Science & Nature",
            "Food & Travel",
            "Audio Description in English"
        )

        inner class CategoriesMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ) = CategoriesMenuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )

        override fun onBindViewHolder(
            holder: CategoriesMenuAdapter.CategoriesMenuViewHolder,
            position: Int
        ) {
            val textView = holder.itemView as TextView
            textView.text = categories[position]
            textView.setOnClickListener(onClickListener)
        }

        override fun getItemCount() = categories.size

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.floatingActionButton -> {
                dismiss()
            }

            R.id.textViewCategory -> {
                with(recyclerView.adapter as CategoriesMenuAdapter) {
                    with(categories[recyclerView.getChildAdapterPosition(v)]) {
                        findNavController().navigate(
                            arguments?.getInt("destination") ?: 0,
                            Bundle().apply {
                                putString("label", this@with)
                            }
                        )
                    }
                }
            }
        }
    }
}