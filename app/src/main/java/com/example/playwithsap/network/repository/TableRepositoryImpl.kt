package com.example.playwithsap.network.repository

//import com.example.playwithsap.network.model.mapper.EmplDtoMapper
import android.util.Log
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.repository.TableRepository
import com.example.playwithsap.domain.util.MyResult
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.TableDataDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TableRepositoryImpl constructor(
    private val api: RetrofitApi,

    private val sharedPref: StoreSavedToken = StoreSavedToken
): TableRepository {

    override suspend fun get(): MyResult<List<TableDataDto>> {

        try {
            Log.d("dddd", "Dddd");
            val response = api.getTableData(auth = sharedPref.getAuthToken(), tableName = "zaat0001")
            Log.d("dddd", "$response");
            if(response.isSuccessful){
                val gson = Gson()
                val mapAdapter = gson.getAdapter(object: TypeToken<List<Map<String, Any?>>>() {})

                response.body()?.let {jsonString ->
                    val model: List<Map<String, Any?>> = mapAdapter.fromJson(jsonString)
                    Log.d("modellllllllll",jsonString)
                    Log.d("modellllllllll",model.toString())
                    //Log.d("dddddddddddddddddddddd", it.toString())
                    //return MyResult.Success(model)
                }
            }
            return MyResult.Error(response.message())

        }catch (e: Exception){
            Log.d("dddd", e.message!!);
            return MyResult.Error(e.message!!)
        }
    }
}