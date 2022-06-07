package com.sinngjpeg.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sinngjpeg.simulator.databinding.ActivityDetailBinding
import com.sinngjpeg.simulator.domain.Match

class DetailActivity : AppCompatActivity() {

    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            Glide.with(this).load(it.homeTeam.image).into(binding.ivHomeTeam)
            Glide.with(this).load(it.awayTeam.image).into(binding.ivAwayTeam)
            supportActionBar?.title = it.place.name
            binding.tvHomeTeamName.text = it.homeTeam.name
            binding.tvAwayTeamName.text = it.awayTeam.name
            binding.rbHomeTeamStars.rating = it.homeTeam.stars.toFloat()
            binding.rbAwayTeamStars.rating = it.awayTeam.stars.toFloat()
            binding.tvDescription.text = it.description
            when {
                it.homeTeam.score != null ->
                    binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }
            when {
                it.awayTeam.score != null ->
                    binding.tvAwayTeamScore.text = it.homeTeam.score.toString()
            }
        }
    }
}