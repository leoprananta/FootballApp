package com.example.leonanta.finalproject.mvp.next


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.MatchAdapter
import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.model.MatchEvent
import com.example.leonanta.finalproject.mvp.second.DETAIL
import com.example.leonanta.finalproject.mvp.second.SecondActivity
import com.example.leonanta.finalproject.utils.invisible
import com.example.leonanta.finalproject.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_next.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

class NextFragment : Fragment(), NextView {

    private var matchEvent: MutableList<MatchEvent> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: NextPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    lateinit var leagueId: String

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNoData() {
        toast("No Data Available")
    }

    override fun showLastMatch(data: List<MatchEvent>) {
        showMatchList(data)
    }

    override fun showNextMatch(data: List<MatchEvent>) {
        showMatchList(data)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater.inflate(R.layout.fragment_next, main_container, false)

        setHasOptionsMenu(true)

        val spinner = fragmentView.findViewById<Spinner>(R.id.next_spinner)
        progressBar = fragmentView.findViewById(R.id.next_progBar)
        recyclerView = fragmentView.findViewById(R.id.next_recyclerView)

        val request = APIRepository()
        val gson = Gson()
        presenter = NextPresenter(this, request, gson)
        adapter = MatchAdapter(matchEvent) { item: MatchEvent -> clicked(item) }
        recyclerView.adapter = adapter

        val spinnerItems = resources.getStringArray(R.array.leagueName)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueId = spinner.selectedItem.toString()

                when (leagueId) {
                    "English Premiere League" -> presenter.getNextMatch("4328")
                    "English League Championship" -> presenter.getNextMatch("4329")
                    "Scottish Premier League" -> presenter.getNextMatch("4330")
                    "German Bundesliga" -> presenter.getNextMatch("4331")
                    "Italian Serie A" -> presenter.getNextMatch("4332")
                    "French Ligue 1" -> presenter.getNextMatch("4334")
                    "Spanish La Liga" -> presenter.getNextMatch("4335")
                    "Greek Superleague Greece" -> presenter.getNextMatch("4336")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        recyclerView.layoutManager = LinearLayoutManager(ctx)

        return fragmentView
    }

    private fun clicked(item: MatchEvent) {
        ctx.startActivity<SecondActivity>(DETAIL to item)
    }

    private fun showMatchList(data: List<MatchEvent>) {
        matchEvent.clear()
        matchEvent.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.search_view, menu)

        val searchMenu = menu?.findItem(R.id.searchView)
        val searchView = searchMenu?.actionView as SearchView

        listenSearchView(searchView)
    }

    private fun listenSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getEventSearch(query.toString())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isEmpty()) {
                    leagueId = next_spinner.selectedItem.toString()

                    when (leagueId) {
                        "English Premiere League" -> presenter.getNextMatch("4328")
                    }
                }
                return true
            }
        })
    }
}
