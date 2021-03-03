package service

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import service.models.Lists
import service.models.Tasks

fun connect() {
    val config = HikariConfig()
    config.jdbcUrl = "jdbc:mysql://localhost:3306/todos"
    config.username = "root"
    config.password = ""
    config.setDriverClassName("com.mysql.jdbc.Driver")

    Database.connect(HikariDataSource(config))

    createTables()
}

private fun createTables() {
    transaction {
        SchemaUtils.create(Lists, Tasks)
    }
}