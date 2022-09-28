package com.oguzhancetin.networksample.repository

import com.oguzhancetin.networksample.api.Output
import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call : suspend()-> Response<T>, error : String) :  T?{
        val result = AgeOfApiOutput(call, error)
        var output : T? = null
        when(result){
            is Output.Success ->
                output = result.output
            is Output.Error -> Log.e("Error", "The $error and the ${result.exception}")
            else -> {}
        }
        return output

    }
    private suspend fun<T : Any> AgeOfApiOutput(call: suspend()-> Response<T> , error: String) : Output<T>{
        val response = call.invoke()
        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else
            Output.Error(IOException("OOps .. Something went wrong due to  $error"))
    }
}