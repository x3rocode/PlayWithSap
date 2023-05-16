package com.example.playwithsap.network.repository

//import com.example.playwithsap.network.model.mapper.EmplDtoMapper
import android.util.Log
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.repository.TableRepository
import com.example.playwithsap.domain.util.MyResult
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.TableDataDto

class TableRepositoryImpl constructor(
    private val api: RetrofitApi,

    private val sharedPref: StoreSavedToken = StoreSavedToken
): TableRepository {

    override suspend fun get(): MyResult<List<TableDataDto>> {

        try {
            Log.d("dddd", "Dddd");
            val response = api.getTableData(auth = sharedPref.getAuthToken(), tableName = "zaat0001")
            Log.d("dddd", "Dddd");
            if(response.isSuccessful){
                response.body()?.let {
                    Log.d("dddddddddddddddddddddd", it.toString())
                    return MyResult.Success(it)
                }
            }
            return MyResult.Error(response.message())

        }catch (e: Exception){
            Log.d("dddd", e.message!!);
            return MyResult.Error(e.message!!)
        }
    }
}