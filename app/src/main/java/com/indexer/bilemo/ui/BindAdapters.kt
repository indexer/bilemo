package com.indexer.bilemo.ui

import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.indexer.bilemo.R


@BindingAdapter("app:countries")
fun setAutoCompleteAdapter(textView: MaterialAutoCompleteTextView, countries: Array<String>) {
    val adapter =
        ArrayAdapter(textView.context, R.layout.support_simple_spinner_dropdown_item, countries)
    textView.setAdapter(adapter)
}


@BindingAdapter("app:OnCheckChanged")
fun setOnCheckChange(
    view: MaterialCheckBox,
    onCheckChangeListener: InverseBindingListener
) {
    view.setOnCheckedChangeListener { compoundButton, b ->
        onCheckChangeListener.onChange()
    }
}


@BindingAdapter("app:OnItemSelected")
fun setOnItemSelected(
    view: MaterialAutoCompleteTextView,
    onItemSelectedListener: InverseBindingListener
) {
    view.setOnItemClickListener { _, view, _, _ ->
        onItemSelectedListener.onChange()
    }
}



