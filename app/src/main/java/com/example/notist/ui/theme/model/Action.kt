/*
 *   Copyright © 2021-2022 PSPDFKit GmbH. All rights reserved.
 *
 *   THIS SOURCE CODE AND ANY ACCOMPANYING DOCUMENTATION ARE PROTECTED BY INTERNATIONAL COPYRIGHT LAW
 *   AND MAY NOT BE RESOLD OR REDISTRIBUTED. USAGE IS BOUND TO THE PSPDFKIT LICENSE AGREEMENT.
 *   UNAUTHORIZED REPRODUCTION OR DISTRIBUTION IS SUBJECT TO CIVIL AND CRIMINAL PENALTIES.
 *   This notice may not be removed from this file.
 */

package com.pspdfkit.catalog.ui.model

import androidx.datastore.preferences.core.Preferences

sealed class Action {

    // Examples
    data class ToggleExampleSection(val sectionTitle: String) : Action()

    // Preferences
    data class PreferenceChanged<T : Any>(val key: Preferences.Key<out T>, val value: T) : Action()
    data class TogglePreferenceSection(val sectionTitle: String) : Action()
    data class PreferenceButtonTapped(val key: Preferences.Key<String>) : Action()
    data class SwitchExamplesLanguageButtonTapped(val desiredLanguage: String) : Action()

    // Topbar
    object SettingsButtonTapped : Action()
    object SearchButtonTapped : Action()
    object CancelSearchButtonTapped : Action()
    object UpButtonTapped : Action()
    data class SearchQueryChanged(val searchQuery: String) : Action()

    // System
    object ShowExamplesLanguageSnackbar : Action()
}

typealias Dispatcher = (Action) -> Unit
