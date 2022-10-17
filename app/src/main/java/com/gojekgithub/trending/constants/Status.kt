package com.gojekgithub.trending.constants

import androidx.annotation.IntDef

@IntDef(
    Status.Success,
    Status.Error,
    Status.Loading,
)
annotation class Status {
    companion object {
        const val Success = 1
        const val Error = 0
        const val Loading = 2
    }
}