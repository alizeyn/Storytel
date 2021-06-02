package ir.alizeyn.storytel.data.map

interface Mapper<I, O> {
    fun map(input: I): O
}