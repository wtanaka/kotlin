package com.wtanaka.kotlin

import com.wtanaka.kotlin.Maybe.Just
import com.wtanaka.kotlin.Maybe.None

sealed class Maybe<out T> {
    object None : Maybe<Nothing>()
    data class Just<T>(val t: T) : Maybe<T>()
}

sealed class ConsList<out A> {
    abstract val head: A
    abstract val tail: ConsList<A>
}

data class Cons<out T>(override val head: T, override val tail: ConsList<T>) : ConsList<T>()

object Nil : ConsList<Nothing>() {
    override val head: Nothing
        get() {
            throw NoSuchElementException("head of an empty list")
        }

    override val tail: ConsList<Nothing>
        get() {
            throw NoSuchElementException("tail of an empty list")
        }
}

fun <T> List<T>.toCons(): ConsList<T> {
    return this.foldRight<T, ConsList<T>>(Nil, { elem, acc -> Cons(elem, acc) })
}

/**
 * Problem 1, find the last element of a list.
 */
fun <T> myLast(list: ConsList<T>): Maybe<T> = when (list) {
    is Nil -> None
    is Cons -> when (list.tail) {
        is Nil -> Just(list.head)
        is Cons -> myLast(list.tail)
    }
}

/**
 * Problem 2, find the last but one element of a list.
 */
fun <T> myButLast(list: ConsList<T>): Maybe<T> = when (list) {
    is Nil -> None
    is Cons -> when (list.tail) {
        is Nil -> None
        is Cons -> when (list.tail.tail) {
            is Nil -> Just(list.head)
            is Cons -> myButLast(list.tail)
        }
    }
}

/**
 * Problem 3, Find the K'th element of a list.
 * The first element in the list is number 1.
 */
fun <T> elementAt(list: ConsList<T>, index: Int): Maybe<T> = when (list) {
    is Nil -> None
    is Cons -> when {
        index < 1 -> None
        index == 1 -> Just(list.head)
        else -> elementAt(list.tail, index - 1)
    }
}

/**
 * Problem 4, Find the number of elements of a list.
 */
fun <T> myLength(list: ConsList<T>): Int = when (list) {
    is Nil -> 0
    is Cons -> 1 + myLength(list.tail)
}
