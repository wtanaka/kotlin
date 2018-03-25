package com.wtanaka.kotlin

import com.wtanaka.kotlin.Maybe.Just
import org.junit.Assert.assertEquals
import org.junit.Test

class NinetyNineTest {
    @Test
    fun testOne() {
        assertEquals(Just(4), myLast(listOf(1, 2, 3, 4).toCons()))
        assertEquals(Just('z'), myLast(listOf('x', 'y', 'z').toCons()))
    }

    @Test
    fun testTwo() {
        assertEquals(Just(3), myButLast(listOf(1, 2, 3, 4).toCons()))
        assertEquals(Just('y'), myButLast(('a'..'z').toList().toCons()))
    }

    @Test
    fun testThree() {
        assertEquals(Just(2), elementAt(listOf(1, 2, 3, 4).toCons(), 2))
        assertEquals(Just('e'), elementAt("haskell".map { it }.toCons(), 5))
    }

}