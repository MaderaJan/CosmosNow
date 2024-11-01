package com.maderajan.cosmosnow.data.mapper

fun interface Mapper<I, O> {
    fun map(from: I): O
}