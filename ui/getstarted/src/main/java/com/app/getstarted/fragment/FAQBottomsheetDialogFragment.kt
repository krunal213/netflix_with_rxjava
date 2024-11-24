package com.app.getstarted.fragment

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.getstarted.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.cachapa.expandablelayout.ExpandableLayout
import net.cachapa.expandablelayout.ExpandableLayout.OnExpansionUpdateListener

class FAQBottomsheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog_fragment_faq,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        val recyclerView: RecyclerView = view.findViewById(R.id.expandableListView)
        recyclerView.setLayoutManager(LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false))
        recyclerView.setAdapter(SimpleAdapter(recyclerView))
    }

    private class SimpleAdapter(private val recyclerView: RecyclerView) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind()
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener, OnExpansionUpdateListener {
            private val expandableLayout: ExpandableLayout
            private val expandButton: TextView
            private val imageViewOpenClose: ImageView
            private val main: LinearLayout

            init {
                expandableLayout = itemView.findViewById(R.id.expandable_layout)
                expandableLayout.setInterpolator(OvershootInterpolator())
                expandableLayout.setOnExpansionUpdateListener(this)
                expandButton = itemView.findViewById(R.id.expand_button)
                main = itemView.findViewById(R.id.main)
                main.setOnClickListener(this)
                imageViewOpenClose = itemView.findViewById(R.id.imageViewOpenClose)
            }

            fun bind() {
                /*val position = getAdapterPosition()
                val isSelected = position == selectedItem
                expandButton.text = "$position. Tap to expand"
                expandButton.isSelected = isSelected
                expandableLayout.setExpanded(isSelected, false)*/
            }

            override fun onClick(view: View) {
                val holder = recyclerView.getChildViewHolder(view) as ViewHolder?
                if (holder?.expandableLayout?.state == ExpandableLayout.State.EXPANDED){
                    holder.expandableLayout.collapse()
                }
                else{
                    holder?.expandableLayout?.expand()
                }
            }

            override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
                Log.d("ExpandableLayout", "State: $state")
                if (state == ExpandableLayout.State.EXPANDING) {
                    recyclerView.smoothScrollToPosition(getAdapterPosition())
                }

                when(state){
                    ExpandableLayout.State.COLLAPSED->{
                        val animatedVectorDrawable = imageViewOpenClose.drawable as AnimatedVectorDrawable
                        animatedVectorDrawable.reset()
                    }
                    ExpandableLayout.State.EXPANDED->{
                        //imageViewOpenClose.setImageResource(R.drawable.avd_from_open_to_close)
                        val animatedVectorDrawable = imageViewOpenClose.drawable as AnimatedVectorDrawable
                        animatedVectorDrawable.start()
                    }
                }
            }
        }
    }
}