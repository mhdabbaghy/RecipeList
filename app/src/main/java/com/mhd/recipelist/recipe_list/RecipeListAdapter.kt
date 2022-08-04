package com.mhd.recipelist.recipe_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mhd.recipelist.databinding.ItemDateBinding
import com.mhd.recipelist.databinding.ItemRecipeBinding
import com.mhd.recipelist.domain.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter(
    private val date: String,
    private val data: List<Recipe>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val TYPE_DATE = 0;
        const val TYPE_RECIPE = 1;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_DATE -> {
                val binding = ItemDateBinding.inflate(inflater, parent, false)
                DateViewHolder(binding)
            }
            TYPE_RECIPE -> {
                val binding = ItemRecipeBinding.inflate(inflater, parent, false)
                RecipeViewHolder(binding)
            }
            else -> {
                throw IllegalArgumentException("View type $viewType is not valid")
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.bind(date)
            is RecipeViewHolder -> holder.bind(data[position - 1])
        }
    }

    override fun getItemCount(): Int = data.size + 1

    override fun getItemViewType(position: Int): Int = if (position == 0)
        TYPE_DATE
    else
        TYPE_RECIPE


    inner class DateViewHolder(private val binding: ItemDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: String) {
            (binding.root as TextView).text = date
        }
    }

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            with(binding) {
                Picasso.get().load(recipe.imageUrl).into(ivImg)
                tvTitle.text = recipe.name
                tvHeadLine.text = recipe.headLine
            }
        }
    }

}