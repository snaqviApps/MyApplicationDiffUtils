package ghar.dfw.perm.myapplicationdiffutils.pure.utils

import ghar.dfw.perm.myapplicationdiffutils.SampleListItem

open class Util {
//    fun getCommList(list:List<String>, list2:List<String>) : List<String>{
    fun getCompositeList(list:List<SampleListItem>, list2:List<SampleListItem>) : List<SampleListItem> {
        return list + list2
    }
}