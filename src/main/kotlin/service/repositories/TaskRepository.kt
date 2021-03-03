package service.repositories

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import service.models.Lists
import service.models.Task
import service.models.TaskEntity

object TaskRepository: Repository<Task> {
    override fun findAll(): Iterable<Task> = transaction {
        TaskEntity.all().map(TaskEntity::toTask)
    }

    override fun findOneById(id: Int): Task? = transaction {
        TaskEntity.findById(id)?.toTask()
    }

    override fun deleteOneById(id: Int) = transaction {
        TaskEntity[id].delete()
    }

    override fun insertOne(model: Task): Unit = transaction {
        TaskEntity.new {
            this.description = model.description
            this.isDone = model.isDone
            this.priority = model.priority
            this.title = model.title
        }
    }

    override fun updateOne(model: Task) = transaction {
        val task = TaskEntity.findById(model.id)
        task?.description = model.description
        task?.isDone = model.isDone
        task?.priority = model.priority
        task?.title = model.title
    }
}