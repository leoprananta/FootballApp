package com.example.leonanta.finalproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.leonanta.finalproject.model.AllTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class TeamAdapter(private val items: List<AllTeam>, private val listener: (AllTeam) -> Unit)
    : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    companion object {
        const val teamBadge = 1
        const val teamName = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val badge: ImageView = view.findViewById(teamBadge)
        private val name: TextView = view.findViewById(teamName)

        fun bind(teams: AllTeam, clickListener: (AllTeam) -> Unit) {
            Picasso.get().load(teams.strTeamBadge).into(badge)
            name.text = teams.strTeam
            itemView.setOnClickListener { clickListener(teams) }
        }
    }

    inner class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = teamBadge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = teamName
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }
}
