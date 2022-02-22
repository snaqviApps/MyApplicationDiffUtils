package ghar.dfw.perm.myapplicationdiffutils.pure.utils

import ghar.dfw.perm.myapplicationdiffutils.SampleListItem

// Recommended for Only Large-collections
open class Sequencing<T> {
    fun toSequence(list : List<T>) : Sequence<T> {
        return list.asSequence()
    }
}


//open class Sequencing<T>(t: List<SampleListItem>) { -----> approach: 01
//    fun<T> toSequence(list : List<T>) : Sequence<T> {
//        return list.asSequence()
//    }
//}