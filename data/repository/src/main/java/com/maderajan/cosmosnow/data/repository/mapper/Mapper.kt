package com.maderajan.cosmosnow.data.repository.mapper

fun interface Mapper<I, O> {
    fun map(from: I): O
}