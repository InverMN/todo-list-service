package service.repositories

import org.jetbrains.exposed.dao.IntEntity

interface Repository<M> {
    fun findAll(): Iterable<M>
    fun findOneById(id: Int): M?
    fun deleteOneById(id: Int): Unit
    fun insertOne(model: M): Unit
    fun updateOne(model: M): Unit
}