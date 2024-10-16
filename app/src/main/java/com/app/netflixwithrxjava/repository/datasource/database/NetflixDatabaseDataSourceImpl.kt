package com.app.netflixwithrxjava.repository.datasource.database

import javax.inject.Inject

class NetflixDatabaseDataSourceImpl @Inject constructor(
    val netflixDao: NetflixDao
) : NetflixDatabaseDataSource {



}