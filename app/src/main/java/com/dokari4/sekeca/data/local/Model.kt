package com.dokari4.sekeca.data.local

data class Model(val question: String,)

object questionDatabase {
    fun getHuruf(): List<Model> {
        val question1 = Model("o")
        val question2 = Model("j")
        val question3 = Model("c")
        val question4 = Model("w")
        val question5 = Model("m")
        val question6 = Model("a")
        val question7 = Model("p")
        val question8 = Model("i")
        val question9 = Model("h")
        val question10 = Model("l")

        val itemList: ArrayList<Model>  = ArrayList()
        itemList.add(question1)
        itemList.add(question2)
        itemList.add(question3)
        itemList.add(question4)
        itemList.add(question5)
        itemList.add(question6)
        itemList.add(question7)
        itemList.add(question8)
        itemList.add(question9)
        itemList.add(question10)

        return itemList
    }
}
