package com.example.leonanta.finalproject.mvp.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.TeamAdapter
import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.model.AllTeam
import com.example.leonanta.finalproject.mvp.detail.DETAILTEAM
import com.example.leonanta.finalproject.mvp.detail.DetailTeamActivity
import com.example.leonanta.finalproject.utils.invisible
import com.example.leonanta.finalproject.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast


class TeamFragment : Fragment(), TeamView {

    private var allTeam: MutableList<AllTeam> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: TeamPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    lateinit var leagueName: String

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNoData() {
        toast("No Data Available")
    }

    override fun showAllTeam(data: List<AllTeam>) {
        showTeamList(data)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView = inflater.inflate(R.layout.fragment_team, main_container, false)

        setHasOptionsMenu(true)

        val spinner = fragmentView.findViewById<Spinner>(R.id.team_spinner)
        progressBar = fragmentView.findViewById(R.id.team_progBar)
        recyclerView = fragmentView.findViewById(R.id.team_recyclerView)

        val request = APIRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        adapter = TeamAdapter(allTeam) { item: AllTeam -> clicked(item) }
        recyclerView.adapter = adapter


        val spinnerItems = resources.getStringArray(R.array.leagueName)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()

                when (leagueName) {
                    "English Premiere League" -> presenter.getAllTeam("4328")
                    "English League Championship" -> presenter.getAllTeam("4329")
                    "Scottish Premier League" -> presenter.getAllTeam("4330")
                    "German Bundesliga" -> presenter.getAllTeam("4331")
                    "Italian Serie A" -> presenter.getAllTeam("4332")
                    "French Ligue 1" -> presenter.getAllTeam("4334")
                    "Spanish La Liga" -> presenter.getAllTeam("4335")
                    "Greek Superleague Greece" -> presenter.getAllTeam("4336")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        recyclerView.layoutManager = LinearLayoutManager(ctx)

        return fragmentView
    }

    private fun showTeamList(data: List<AllTeam>) {
        allTeam.clear()
        allTeam.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
    }

    private fun clicked(item: AllTeam) {
        ctx.startActivity<DetailTeamActivity>(DETAILTEAM to item)
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
                presenter.getTeamSearch(query.toString())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                when (newText.toString()) {
                    "" -> presenter.getAllTeam("4328")
                }
                return true
            }
        })
    }

}
