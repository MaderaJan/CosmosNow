package com.maderajan.cosmosnow.feature.newsdetail.changefont

import com.maderajan.cosmosnow.core.viewmodel.UiAction

sealed interface ChangeNewsFontUiAction : UiAction {
    data object ChangeNewsFont : ChangeNewsFontUiAction
    data object DecreaseFont : ChangeNewsFontUiAction
    data object NavigateBack : ChangeNewsFontUiAction
}