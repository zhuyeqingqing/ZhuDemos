package com.example.zhugpt.bean

import java.util.*

class Model {
     var id: String = ""
     var `object`: String = ""
     var created: Long = 0
     var owned_by: String = ""
     var permission: List<ModelPermission> = ArrayList()
     var root: String = ""
     var parent: Any? = Object()


    open class ModelPermission{
        var id: String = ""
        var `object`: String = ""
        var created: Long = 0
        var allow_create_engine: Boolean = false
        var allow_sampling: Boolean = false
        var allow_logprobs: Boolean = false
        var allow_search_indices: Boolean = false
        var allow_view: Boolean = false
        var allow_fine_tuning: Boolean = false
        var organization: String = ""
        var group: Any? = null
        var is_blocking: Boolean = false
    }
}