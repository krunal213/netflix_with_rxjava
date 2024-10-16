package com.app.netflixwithrxjava.repository

import com.app.netflixwithrxjava.repository.datasource.database.NetflixDatabaseDataSource
import com.app.netflixwithrxjava.repository.datasource.network.NetflixNetworkDataSource
import javax.inject.Inject

class NetflixRepositoryImpl @Inject constructor(
    val netflixNetworkDataSource: NetflixNetworkDataSource,
    //val netflixDatabaseDataSource: NetflixDatabaseDataSource
) : NetflixRepository {

}