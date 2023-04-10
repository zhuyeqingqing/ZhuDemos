package com.example.myui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myui.R
import com.example.myui.util.ObjectStore

class CommonActivity : AppCompatActivity() {
    private var mFragment: Fragment? = null

    companion object {
        const val INTENT_KEY_FRAGMENT = "key_fragment"
        fun start(context : Context, fragment : Fragment){
            start(context, fragment, null)
        }

        fun start(context: Context, fragment: Fragment, bundle: Bundle?){
            if(context == null){
                return
            }

            var intent : Intent = Intent(context, CommonActivity.javaClass)
            if(bundle != null){
                intent.putExtras(bundle)
                fragment.arguments = bundle
            }
            var fragmentKey = ObjectStore.add(fragment)
            intent.putExtra(INTENT_KEY_FRAGMENT, fragmentKey)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        val key: String? = intent.getStringExtra(INTENT_KEY_FRAGMENT)
        if (!TextUtils.isEmpty(key)) {
            mFragment = ObjectStore.remove(key) as Fragment?
        }

        intent.removeExtra(INTENT_KEY_FRAGMENT)

        if (mFragment != null) {
            supportFragmentManager.beginTransaction().add(R.id.group_fragment, mFragment!!).commit()
        } else {
            finish()
        }

    }


}