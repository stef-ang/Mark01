package com.stef_ang.mark01.viewitem

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.stef_ang.mark01.R
import com.stef_ang.mark01.databinding.ItemTextViewBinding

class TextViewItem(var text: String? = null): AbstractBindingItem<ItemTextViewBinding>() {

    override val type: Int
        get() = R.id.item_text_view

    override fun bindView(binding: ItemTextViewBinding, payloads: List<Any>) {
        binding.textView.text = text
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemTextViewBinding {
        return ItemTextViewBinding.inflate(inflater, parent, false)
    }
}