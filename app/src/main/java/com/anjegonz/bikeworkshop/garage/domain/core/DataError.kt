package com.anjegonz.bikeworkshop.garage.domain.core

interface DataError: Error {

    /*
     * This is a TypeSafe way handle Errors.
     * It's a bit OVERKILL for this small app, but I wanted to show how I'd normally handle these.
     * This is way more adequate when also handling "Remote" errors.
     * That way you could decide to use Local for the calls with Room and
     * Remote for the API calls, all while being TypeSafe.
     * Then in the viewModel through a when loop you can be exhaustive.
     * I've used similar ways to handle errors on my two projects "Chirp" and "Bookpedia"
     *
     * This looks quite complex, and it IS too much for this project
     * (I debated whether to keep it or dumb it down)
     * But with this you can handle any type of error on any type of project.
     * Password is not complex enough? session expired? Invalid credentials?
     * No permissions? Free Tier limit reached?
     *
     * This can be then used for any type of Error on a function that expects a result.
     * But fundamentally the reason why I decided to include it is because in the challenge it states:
     * "This includes error handling in order to avoid app crashes". So I wanted to make a statement.
     */
    enum class Local: DataError{
        DISK_FULL,
        DATABASE_ERROR,
        UNKNOWN
    }
}