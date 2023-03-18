fun resume(marks: Triple<String, Int, List<Double>>): Pair<String, Double> {
    return Pair(marks.first, marks.third.sum()/marks.third.size)
}