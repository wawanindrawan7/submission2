package com.dicoding.submission_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.submission_2.databinding.ActivityDetailUserBinding
import com.dicoding.submission_2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.zip.Inflater

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val EXTRA_USER = "extra_user"

        //fragment
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Detail User"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val username = user?.username;
        findUsers(username.toString());

        val sectionsPagerAdapter = FollowPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)

        sectionsPagerAdapter.username = user?.username

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        val bundle = Bundle()
        bundle.getString(EXTRA_USER, username)

    }

    private fun findUsers(username: String) {
        val client = ApiConfig.getApiService().getUserDetail(username);
        client.enqueue(object : Callback<UserResponseItem> {
            override fun onResponse(
                call: Call<UserResponseItem>,
                response: Response<UserResponseItem>
            ) {
                val responseBody = response.body();
                if (responseBody != null) {
                    setData(responseBody);
                }
            }

            override fun onFailure(call: Call<UserResponseItem>, t: Throwable) {
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setData(dataUser: UserResponseItem) {
        binding.tvUsername.text = dataUser.login;

        Glide.with(this)
            .load(dataUser?.avatarUrl)
            .apply(RequestOptions())
            .into(binding.imageView2)

        binding.tvNama.text = dataUser.name;
        binding.tvRepository.text = dataUser.publicRepos.toString();
        binding.tvFollowers.text = dataUser.followers.toString();
        binding.tvFollowing.text = dataUser.following.toString();
        binding.tvLokasi.text = dataUser.location;


    }


}

