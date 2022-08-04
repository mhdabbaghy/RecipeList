package com.mhd.recipelist.recipe_list.use_case

import com.mhd.recipelist.result.Result
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetDateUseCase @Inject constructor() {

    operator fun invoke(pattern: String): Result<String> {
        return try {
            val date = Calendar.getInstance().time
            val df = SimpleDateFormat(pattern, Locale.getDefault())
            Result.Success(df.format(date))
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }


}