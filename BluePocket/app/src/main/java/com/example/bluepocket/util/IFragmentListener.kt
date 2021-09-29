package com.example.bluepocket.util

import android.view.View

interface IFragmentListener {
    fun onFragmentClick(view: View)

    fun onRemoveItem(position: Int, item: Any)
}