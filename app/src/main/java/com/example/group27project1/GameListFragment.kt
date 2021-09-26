package com.example.group27project1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "GameListFragment"

class GameListFragment : Fragment(){

    /**
     * Required interface for hosting activities
     */
    interface Callbacks {
        fun onGameSelected(gameId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = GameAdapter(emptyList())
    private val gameListViewModel: BasketballViewModel by lazy {
        ViewModelProviders.of(this).get(BasketballViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        gameRecyclerView =
            view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)

        gameRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameListViewModel.gameListLiveData.observe(
            viewLifecycleOwner,
            Observer { games ->
                games?.let {
                    Log.i(TAG, "Got games ${games.size}")
                    updateUI(games)
                }
            })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class GameHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var game: Game

        private val titleTextView: TextView = itemView.findViewById(R.id.game_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        private val scoreTextView: TextView = itemView.findViewById(R.id.game_score)

        var teamLogoImageView: ImageView = itemView.findViewById(R.id.team_logo)
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(game: Game) {
            this.game = game
            titleTextView.text = game.teamAName+" VS "+game.teamBName
            dateTextView.text = game.date.toString()
            scoreTextView.text = game.teamAScore.toString()+":"+game.teamBScore.toString()
            //conditionally switch the image drawable to display winning team logo
            if (game.teamAScore > game.teamBScore){
                teamLogoImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_teama))
            } else {
                teamLogoImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_teamb))
            }
        }

        override fun onClick(v: View?) {
            Toast.makeText(context, "${game.id} clicked!", Toast.LENGTH_SHORT)
                .show()
            callbacks?.onGameSelected(game.id)
        }
    }


    private inner class GameAdapter(var games: List<Game>)
        : RecyclerView.Adapter<GameHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val layoutInflater = LayoutInflater.from(context)

            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.bind(game)
        }
    }

    private fun updateUI(games: List<Game>) {
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
    }

}