import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {

    private val _user1Prefs = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val user1Prefs: StateFlow<Map<String, Boolean>> = _user1Prefs

    private val _user2Prefs = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val user2Prefs: StateFlow<Map<String, Boolean>> = _user2Prefs

    fun toggleUser1Pref(pref: String) {
        val current = _user1Prefs.value.toMutableMap()
        current[pref] = !(current[pref] ?: false)
        _user1Prefs.value = current
    }

    fun toggleUser2Pref(pref: String) {
        val current = _user2Prefs.value.toMutableMap()
        current[pref] = !(current[pref] ?: false)
        _user2Prefs.value = current
    }

    fun setUser1Prefs(prefs: List<String>) {
        _user1Prefs.value = prefs.associateWith { true }
    }

    fun setUser2Prefs(prefs: List<String>) {
        _user2Prefs.value = prefs.associateWith { true }
    }
}