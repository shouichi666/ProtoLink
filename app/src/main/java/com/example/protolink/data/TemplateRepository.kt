package com.example.protolink.data

import javax.inject.Inject

interface TemplateRepository {
    suspend fun getT()
}

class TemplateRepositoryImpl
    @Inject
    constructor() : TemplateRepository {
        override suspend fun getT() {}
    }
