package tg.jackboy.jacksneak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import tg.jackboy.jacksneak.fragments.AddSneakerFragment
import tg.jackboy.jacksneak.fragments.CollectionFragment
import tg.jackboy.jacksneak.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this), R.string.Home_page_title)

        // importer le bottom.........
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_page ->{
                    loadFragment(HomeFragment(this), R.string.Home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page ->{
                    loadFragment(CollectionFragment(this), R.string.Collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_sneaker_page -> {
                    loadFragment(AddSneakerFragment(this), R.string.add_sneaker_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }




    }

    private fun loadFragment(fragment: Fragment,string:  Int) {
        // charger la SneakerRepository
        val repo = SneakerRepository()

        // actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        // mettre a jour la liste de paires
        repo.updateData{
            // injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}