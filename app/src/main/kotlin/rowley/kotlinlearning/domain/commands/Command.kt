package rowley.kotlinlearning.domain.commands

interface Command<T> {
    fun execute(): T
}