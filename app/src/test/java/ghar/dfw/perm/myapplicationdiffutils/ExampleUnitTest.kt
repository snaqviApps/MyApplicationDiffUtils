package ghar.dfw.perm.myapplicationdiffutils

import android.os.Parcelable.CONTENTS_FILE_DESCRIPTOR
import ghar.dfw.perm.myapplicationdiffutils.model.data.Current
import ghar.dfw.perm.myapplicationdiffutils.model.data.Location
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun validateConditionText() {
        val locationResult = Location(
            "US",
            0.0,
            "",
            0,
            0.0,
            "",
            "",
            ""
        )
        assert(locationResult.country.isNotEmpty())
    }

}