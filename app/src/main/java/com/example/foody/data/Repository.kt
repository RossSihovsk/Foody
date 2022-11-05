package com.example.foody.data

import com.example.foody.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
     val remoteDataSource: RemoteDataSource
) {

}