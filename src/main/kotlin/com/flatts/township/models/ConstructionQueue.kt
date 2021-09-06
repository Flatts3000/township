package com.flatts.township.models

import java.util.*


class ConstructionQueue : AbstractQueue<ConstructionImpl>() {
    private val elements: LinkedList<ConstructionImpl> = LinkedList<ConstructionImpl>()

    override fun iterator(): MutableIterator<ConstructionImpl> {
        return elements.iterator()
    }

    override fun offer(e: ConstructionImpl?): Boolean {
        if (e == null) return false
        elements.add(e)
        return true
    }

    override fun poll(): ConstructionImpl? {
        if (isEmpty()) return null
        val iter: MutableIterator<ConstructionImpl> = elements.iterator()
        val t: ConstructionImpl = iter.next()
        iter.remove()
        return t
    }

    override fun peek(): ConstructionImpl? {
        return elements.firstOrNull();
    }

    override val size: Int
        get() = elements.size
}