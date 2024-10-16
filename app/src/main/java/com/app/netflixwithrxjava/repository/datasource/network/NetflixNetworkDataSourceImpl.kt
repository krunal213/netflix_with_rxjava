package com.app.netflixwithrxjava.repository.datasource.network

import javax.inject.Inject

class NetflixNetworkDataSourceImpl @Inject constructor(
    val netflixService: NetflixService
) : NetflixNetworkDataSource {



}