package com.rakha.basicproject.presentation.movies

import android.view.View
import com.rakha.basicproject.R
import com.rakha.basicproject.databinding.ItemHeaderInstructionsBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 *   Created By rakha
 *   10/12/22
 */
class HeaderItem(private val title: String): BindableItem<ItemHeaderInstructionsBinding>() {
    override fun bind(viewBinding: ItemHeaderInstructionsBinding, position: Int) {
        viewBinding.apply {
            tvName.text = title
        }
    }

    override fun getLayout() = R.layout.item_header_instructions

    override fun initializeViewBinding(view: View) = ItemHeaderInstructionsBinding.bind(view)
}