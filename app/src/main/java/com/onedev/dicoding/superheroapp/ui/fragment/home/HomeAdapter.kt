package com.onedev.dicoding.superheroapp.ui.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.loadImage
import com.onedev.dicoding.superheroapp.databinding.LayoutListHeroBinding

class HomeAdapter(private val callback: ItemClicked) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val heroes = ArrayList<HeroEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setHeroes(heroes: List<HeroEntity>) {
        this.heroes.clear()
        this.heroes.addAll(heroes)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: HeroEntity) {
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

    interface ItemClicked {
        fun itemClicked(hero: HeroEntity)
    }

}