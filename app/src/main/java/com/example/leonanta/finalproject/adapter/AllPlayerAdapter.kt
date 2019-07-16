package com.example.leonanta.finalproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.model.AllPlayer
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class AllPlayerAdapter(private val items: List<AllPlayer>, private val listener: (AllPlayer) -> Unit)
    : RecyclerView.Adapter<AllPlayerAdapter.ViewHolder>() {

    companion object {
        const val playerCutout = 1
        const val playerName = 2
        const val playerPosition = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AllPlayerAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val playerCutout: ImageView = view.findViewById(AllPlayerAdapter.playerCutout)
        private val playerName: TextView = view.findViewById(AllPlayerAdapter.playerName)
        private val playerPosition: TextView = view.findViewById(AllPlayerAdapter.playerPosition)

        fun bind(player: AllPlayer, clickListener: (AllPlayer) -> Unit) {
            Picasso.get().load(player.strCutout).resize(80, 80).into(playerCutout)
            playerName.text = player.strPlayer
            playerPosition.text = player.strPosition
            itemView.setOnClickListener { clickListener(player) }
        }
    }

    inner class PlayerUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                linearLayout {
                    lparams(width = wrapContent, height = wrapContent)
                    gravity = Gravity.LEFT

                    imageView {
                        id = AllPlayerAdapter.playerCutout
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = AllPlayerAdapter.playerName
                        textSize = 17f
                    }.lparams {
                        topMargin = dip(15)
                        leftMargin = dip(10)
                        padding = dip(5)
                    }
                }

                linearLayout {
                    lparams(width = wrapContent, height = wrapContent)
                    gravity = Gravity.RIGHT

                    textView {
                        text = context.getString(R.string.as_position)
                        textSize = 16f
                    }.lparams {
                        topMargin = dip(15)
                        padding = dip(5)
                    }

                    textView {
                        id = AllPlayerAdapter.playerPosition
                        textSize = 16f
                    }.lparams {
                        topMargin = dip(15)
                        leftMargin = dip(7)
                        padding = dip(5)
                    }
                }
            }
        }
    }

}
