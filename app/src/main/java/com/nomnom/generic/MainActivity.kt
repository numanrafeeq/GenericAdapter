package com.nomnom.generic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nomnom.generic.adapters.kotlin.databinding.GenericDataAdapter
import com.nomnom.generic.adapters.kotlin.viewbinding.GenericViewAdapter
import com.nomnom.generic.databinding.ActivityMainBinding
import com.nomnom.generic.databinding.ItemLayoutBinding
import com.nomnom.generic.models.Item

class MainActivity : AppCompatActivity() {


    private lateinit var dataList: MutableList<Item>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initComponent()

    }

    private fun initComponent() {


        initDataList()
        initClicks()
        initRecyclerView()

    }

    private fun initClicks() {

        binding.btnDataBinding.setOnClickListener {
            binding.tvExamples.text = getString(R.string.this_is_generic_adapter_example_with_data_binding)
            initDataBindingRecyclerView()
        }

        binding.btnViewBinding.setOnClickListener {
            binding.tvExamples.text = getString(R.string.this_is_generic_adapter_example_with_view_binding)
            initViewBindingRecyclerView()
        }

    }

    private fun initViewBindingRecyclerView() {


    }

    private fun initDataBindingRecyclerView() {
        // Create and set up the adapter

        val adapter = object : GenericDataAdapter<Item, ItemLayoutBinding>(dataList, R.layout.item_layout) {
            override fun onBindData(model: Item, position: Int, bindingAdapter: ItemLayoutBinding) {

                // Implement how to bind data to your views in the item layout
                bindingAdapter.model = model
            }

            override fun onItemClick(model: Item, position: Int, bindingAdapter: ItemLayoutBinding) {
                // Implement item click handling
                Toast.makeText(this@MainActivity, "Item clicked: ${model.id}", Toast.LENGTH_SHORT).show()
            }
        }

        // Set the adapter to the RecyclerView
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

    }

    private fun initRecyclerView() {

        val adapter = object : GenericViewAdapter<Item, ItemLayoutBinding>(dataList) {
            override fun onBindData(model: Item, position: Int, bindingAdapter: ItemLayoutBinding) {

                // Implement how to bind data to your views in the item layout
                bindingAdapter.model = model
            }

            override fun onItemClick(model: Item, position: Int, bindingAdapter: ItemLayoutBinding) {
                // Implement item click handling
                Toast.makeText(this@MainActivity, "Item clicked: ${model.id}", Toast.LENGTH_SHORT).show()
            }

            override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemLayoutBinding {

                return ItemLayoutBinding.inflate(inflater, parent, false)
            }
        }

        // Set the adapter to the RecyclerView
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun initDataList() {

        dataList = mutableListOf(
            Item("01", "Item 1"),
            Item("02", "Item 2"),
            Item("03", "Item 3"),
            Item("04", "Item 4"),
            Item("05", "Item 5"),
            Item("06", "Item 6"),
            Item("07", "Item 7")
            // Add more items as needed
        )

    }


}