package rowley.kotlinlearning.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

class ExtensionUtilsKtTest {
    @Test
    fun toDateString() {
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test
    fun toDateStringFullFormat() {
        assertEquals("Monday, October 19, 2015", 1445275635000L.toDateString(DateFormat.FULL))
    }
}