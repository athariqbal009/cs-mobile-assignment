package com.backbase.assignment.moviebox.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilsTest {

    @Test
    fun `when date is empty`() {
        assertThat(Utils.convertDate("")).isEqualTo("Date is not available")
    }

    @Test
    fun `when date is null`() {
        assertThat(Utils.convertDate("null")).isEqualTo("Date is not available")
    }
}