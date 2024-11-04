package com.maderajan.cosmosnow.feature.search.filteroptions.date

import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate

sealed interface DateSelectedUiAction : UiAction {
    data class ProvideData(val date: SearchDate?) : DateSelectedUiAction
    data class DateSelected(val date: SearchDate) : DateSelectedUiAction
    data object NavigateBack : DateSelectedUiAction
    data object ApplyFilter : DateSelectedUiAction
}