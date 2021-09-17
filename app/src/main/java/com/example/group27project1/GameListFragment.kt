package com.example.group27project1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "CrimeListFragment"

class GameListFragment : Fragment(){

    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: GameAdapter? = null


    private val crimeListViewModel: GameListViewModel by lazy {
        ViewModelProviders.of(this).get(GameListViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total games: ${crimeListViewModel.games.size}")
    }

    companion object {
        fun newInstance(): GameListFragment {
            return GameListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        crimeRecyclerView =
            view.findViewById(R.id.game_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private inner class GameHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val titleTextView: TextView = itemView.findViewById(R.id.game_title)
        val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        val scoreTextView: TextView = itemView.findViewById(R.id.game_score)

    }
    private inner class GameAdapter(var games: List<Game>)
        : RecyclerView.Adapter<GameHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.apply {
                titleTextView.text = game.title.toString()+ "   Team "+game.team1.name.toString()+" VS Team "+game.team2.name.toString()
                dateTextView.text = game.date.toString()
                scoreTextView.text = game.team1.score.toString()+":"+game.team2.score.toString()
            }
        }
    }

    private fun updateUI() {
        val crimes = crimeListViewModel.games
        adapter = GameAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }

}