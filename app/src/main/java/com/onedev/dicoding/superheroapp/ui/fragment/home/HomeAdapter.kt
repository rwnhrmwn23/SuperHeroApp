package com.onedev.dicoding.superheroapp.ui.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.loadImage
import com.onedev.dicoding.superheroapp.databinding.LayoutListHeroBinding
import com.onedev.dicoding.superheroapp.ui.ItemClicked

class HomeAdapter(private val callback: ItemClicked) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val heroes = ArrayList<Hero>()

    @SuppressLint("NotifyDataSetChanged")
    fun setHeroes(heroes: List<Hero>) {
        this.heroes.clear()
        this.heroes.addAll(heroes)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                imgHero.loadImage(hero.url)
                tvNameHero.text = hero.name
                itemView.setOnClickListener {
                    callback.itemClicked(hero)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val binding =
            LayoutListHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount(): Int = heroes.size

}