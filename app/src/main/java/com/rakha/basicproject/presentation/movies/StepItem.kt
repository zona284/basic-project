package com.rakha.basicproject.presentation.movies

import android.view.View
import com.rakha.basicproject.R
import com.rakha.basicproject.databinding.ItemListInstructionsBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 *   Created By rakha
 *   10/12/22
 */
class StepItem(private val item: MoviesFragment.Steps): BindableItem<ItemListInstructionsBinding>() {
    override fun bind(viewBinding: ItemListInstructionsBinding, position: Int) {
        viewBinding.apply {
            tvNumber.text = "${item.number}"
            tvStep.text = item.step
        }
    }

    override fun getLayout() = R.layout.item_list_instructions

    override fun initializeViewBinding(view: View) = ItemListInstructionsBinding.bind(view)
}