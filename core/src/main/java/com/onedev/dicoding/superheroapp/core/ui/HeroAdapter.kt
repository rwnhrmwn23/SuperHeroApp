package com.onedev.dicoding.superheroapp.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.dicoding.superheroapp.core.databinding.LayoutListHeroBinding
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.loadImage

class HeroAdapter : RecyclerView.Adapter<HeroAdapter.HomeViewHolder>() {

    private val heroes = ArrayList<Hero>()
    var onItemClick: ((Hero) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setHeroes(listHero: List<Hero>?) {
        if (listHero == null) return
        heroes.clear()
        heroes.addAll(listHero)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                imgHero.loadImage(hero.url)
                tvNameHero.text = hero.name
                itemView.setOnClickListener {
                    onItemClick?.invoke(hero)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            LayoutListHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount(): Int = heroes.size

}