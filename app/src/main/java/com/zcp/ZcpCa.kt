package com.zcp


class ZcpCa {
    private fun isRightArray(array: ArrayList<Int>): HashSet<Answer> {
        val resultSet = HashSet<Answer>()
        val sum = array.sum()
        if (sum % 2 != 0 || array.size == 0) {
            return resultSet
        }
        for (i in 0 until 1.shl(array.size)) {
            val toBinaryString = Integer.toBinaryString(i)
            val resultList = ArrayList<Int>()
            val nextList = ArrayList<Int>(array)
            toBinaryString.forEachIndexed { index, c ->
                if (c == '1') {
                    resultList.add(array[index])
                    nextList.remove(array[index])
                }
            }
            if (resultList.sum() == sum / 2) {
                resultSet.add(Answer().apply {
                    oneSet = resultList
                    nextSet = nextList
                })
            }
        }
        return resultSet

    }

    fun getResult(test: ArrayList<Int>): HashSet<Answer> {
        val result = HashSet<Answer>()
        for (i in 0 until 1.shl(test.size)) {
            val toBinaryString = Integer.toBinaryString(i)
            val temList = ArrayList<Int>()
            toBinaryString.forEachIndexed { index, c ->
                if (c == '1') {
                    temList.add(test[index])
                }
            }
            val rightArray = isRightArray(temList)
            if (rightArray.size > 0) {
                result.addAll(rightArray)
            }
        }
        return result
    }
}

class Answer {
    fun getSize(): Int {
        return oneSet.size + nextSet.size
    }

    var oneSet: List<Int> = ArrayList()
        set(value) {
            field = value.sortedBy { it }
        }
    var nextSet: List<Int> = ArrayList()
        set(value) {
            field = value.sortedBy { it }
        }

    override fun toString(): String {
        return "Answer(oneSet=$oneSet, nextSet=$nextSet)\n"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Answer
        if (oneSet == other.nextSet && nextSet == other.oneSet) {
            return true
        }
        if (oneSet != other.oneSet) return false
        if (nextSet != other.nextSet) return false
        return true
    }

    override fun hashCode(): Int {
        var result = oneSet.hashCode()
        result = 31 * result + nextSet.hashCode()
        return result
    }


}


