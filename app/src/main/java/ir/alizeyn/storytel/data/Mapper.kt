package ir.alizeyn.storytel.data

interface Mapper<I, O> {
    fun map(input: I): O
}