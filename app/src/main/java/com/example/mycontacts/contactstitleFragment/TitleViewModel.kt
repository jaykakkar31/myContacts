package com.example.mycontacts.contactstitleFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mycontacts.FormatContacts
import com.example.mycontacts.contactsDatabase.contacts
import com.example.mycontacts.contactsDatabase.contactsDatabaseDao
import kotlinx.coroutines.*

class TitleViewModel(val dataSource: contactsDatabaseDao, application: Application) :
    AndroidViewModel(application) {


     var current=dataSource.getAllContacts()

    var contactsString=Transformations.map(current){
        FormatContacts(it)
    }
    private var viewModelJob = Job()

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataSource.clear()

        }
    }


    //
//    private suspend fun update(contacts: contacts) {
//        withContext(Dispatchers.IO) {
//            database.update(contacts)
//        }
//
//    }
//

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun DeleteAllContacts() {
        viewModelScope.launch {
            // Clear the database table.
            clear()

            // And clear tonight since it's no longer in the database
//            _currentContact.value = null
        }
    }

}