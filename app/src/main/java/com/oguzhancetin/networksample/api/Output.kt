package com.oguzhancetin.networksample.api

sealed class Output<out T : Any>{
     class Success<out T : Any>(val output : T) : Output<T>()
     class Error(val exception: Exception)  : Output<Nothing>()
     class Loading()  : Output<Nothing>()
}