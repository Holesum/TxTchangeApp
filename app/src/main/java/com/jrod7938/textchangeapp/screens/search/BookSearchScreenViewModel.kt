/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2023, Jrod7938, Khang-ALe, jesma14, Holesum
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jrod7938.textchangeapp.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.jrod7938.textchangeapp.model.MBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel for the book search screen
 *
 * @property db FirebaseFirestore the firestore database
 * @property _loading MutableLiveData<Boolean> the loading state of the screen
 * @property loading LiveData<Boolean> the loading state of the screen
 * @property _message MutableStateFlow<String?> the message to display
 * @property message StateFlow<String?> the message to display
 * @property _books MutableLiveData<List<MBook>> the list of books
 * @property books LiveData<List<MBook>> the list of books
 */
class BookSearchScreenViewModel : ViewModel() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    private val _books = MutableLiveData<List<MBook>>()
    val books: LiveData<List<MBook>> = _books

    /**
     * Search for a book by title
     *
     * @param title String the title of the book to search for
     *
     * @return Unit
     */
    fun searchBookByTitle(title: String) {
        db.collection("books")
            .whereEqualTo("title", title)
            .get()
            .addOnSuccessListener { result ->
                val bookList = result.map { document ->
                    MBook.fromDocument(document)
                }
                _books.value = bookList
            }.addOnFailureListener { exception ->
                _message.value = exception.message
            }
    }

    /**
     *  Search books by category
     *
     *  @param category String the category of the books to search for
     *
     *  @return Unit
     */
    fun searchBooksByCategory(category: String) {
        db.collection(category)
            .get()
            .addOnSuccessListener { result ->
                val bookList = result.map { document ->
                    MBook.fromDocument(document)
                }
                _books.value = bookList
            }.addOnFailureListener { exception ->
                _message.value = exception.message
            }
    }
    /**
     * Search for book by isbn
     *
     * @param isbn String of the isbn of book to search for
     *
     * @return Unit
     */
    fun searchBookByISBN(isbn : String) {
        db.collection("books")
            .whereEqualTo("isbn", isbn)
            .get()
            .addOnSuccessListener { result ->
                val bookList = result.map { document ->
                    MBook.fromDocument(document)
                }
                _books.value = bookList
            }.addOnFailureListener { exception ->
                _message.value = exception.message
            }
    }
    /**
     * Search for book by author
     *
     * @param author String of the author of books to search for
     *
     * @return Unit
     */
    fun searchBookByAuthor(author : String) {
        db.collection("books")
            .whereEqualTo("author", author)
            .get()
            .addOnSuccessListener { result ->
                val bookList = result.map { document ->
                    MBook.fromDocument(document)
                }
                _books.value = bookList
            }.addOnFailureListener { exception ->
                _message.value = exception.message
            }
    }
}