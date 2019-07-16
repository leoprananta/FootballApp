package com.example.leonanta.finalproject.adapter

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.R.color.colorPrimary
import com.example.leonanta.finalproject.model.MatchEvent
import com.example.leonanta.finalproject.utils.Date
import org.jetbrains.anko.*

class MatchAdapter(private val items: List<MatchEvent>, private val listener: (MatchEvent) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    companion object {
        const val dateId = 1
        const val timeId = 2
        const val homeTeamId = 3
        const val homeScoreId = 4
        const val awayTeamId = 5
        const val awayScoreId = 6
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(MainUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val matchDate: TextView = view.findViewById(dateId)
        private val matchTime: TextView = view.findViewById(timeId)
        private val matchHomeTeam: TextView = view.findViewById(homeTeamId)
        private val matchHomeScore: TextView = view.findViewById(homeScoreId)
        private val matchAwayTeam: TextView = view.findViewById(awayTeamId)
        private val matchAwayScore: TextView = view.findViewById(awayScoreId)

        fun bind(detail: MatchEvent, clickListener: (MatchEvent) -> Unit) {
            matchDate.text = Date.getDate(detail.dateEvent)
            matchTime.text = Date.getTime(detail.strTime)
            matchHomeTeam.text = detail.strHomeTeam
            matchHomeScore.text = detail.intHomeScore
            matchAwayTeam.text = detail.strAwayTeam
            matchAwayScore.text = detail.intAwayScore
            itemView.setOnClickListener { clickListener(detail) }
        }
    }

    inner class MainUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(8)

                    textView {
                        gravity = Gravity.CENTER
                        id = dateId
                        text = context.getString(R.string.date)
                        textSize = 16f
                        textColor = ContextCompat.getColor(ctx, colorPrimary)
                    }.lparams(matchParent, wrapContent)

                    textView {
                        gravity = Gravity.CENTER
                        id = timeId
                        text = context.getString(R.string.time)
                        textSize = 16f
                        textColor = ContextCompat.getColor(ctx, colorPrimary)
                    }.lparams(matchParent, wrapContent)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        textView {
                            gravity = Gravity.CENTER
                            id = homeTeamId
                            text = context.getString(R.string.home)
                            textSize = 16f
                        }.lparams(matchParent, wrapContent, 1f)

                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL

                            textView {
                                padding = dip(8)
                                id = homeScoreId
                                text = "0"
                                textSize = 18f
                                setTypeface(null, Typeface.BOLD)
                            }

                            textView {
                                text = context.getString(R.string.vs)
                            }

                            textView {
                                padding = dip(8)
                                id = awayScoreId
                                text = "0"
                                textSize = 18f
                                setTypeface(null, Typeface.BOLD)
                            }
                        }

                        textView {
                            gravity = Gravity.CENTER
                            id = awayTeamId
                            text = context.getString(R.string.away)
                            textSize = 16f
                        }.lparams(matchParent, wrapContent, 1f)
                    }
                }.lparams(matchParent, matchParent) {
                    topMargin = 8
                }
                view {
                    backgroundColor = ContextCompat.getColor(ctx, colorPrimary)
                }.lparams(dip(300), dip(1)) {
                    topMargin = dip(8)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }
    }
}
