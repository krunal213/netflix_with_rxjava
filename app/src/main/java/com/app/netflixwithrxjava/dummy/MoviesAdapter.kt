package com.app.netflixwithrxjava.dummy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.netflixwithrxjava.R
import com.bumptech.glide.Glide

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val movieView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(movieView)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(movieHolder: MovieHolder, position: Int) {
        movieHolder.bind(movies[position])
    }

    class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.findViewById<ImageView>(R.id.movieThumbnailImageView).loadImage(movie.thumbnail)
        }

        private fun ImageView.loadImage(url: String?) {
            Glide.with(this).load(url)
                .centerCrop()
                .into(this)
        }
    }
}