package service

data class List(
        val id : String,
        var name : String,
        val author : String,
        var tasks : Array<Task>
)

