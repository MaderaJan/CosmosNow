package com.maderajan.cosmosnow.feature.search.filteroptions.date

import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.DateSelect

sealed interface DateSelectedUiAction : UiAction {
    data class ProvideData(val date: DateSelect?) : DateSelectedUiAction
    data class DateSelected(val date: DateSelect) : DateSelectedUiAction
    data object NavigateBack : DateSelectedUiAction
    data object ApplyFilter : DateSelectedUiAction
}