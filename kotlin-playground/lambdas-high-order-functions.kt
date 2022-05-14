fun main() {
    val peopleAges = mutableMapOf<String, Int>(
    	"Fred" to 30,
        "Ann" to 23
    )
    
    peopleAges.put("Barbara", 42)
    // put shorthand
    peopleAges["Pedro"] = 25
    peopleAges["Fred"] = 31
    
    println(peopleAges)
    println("Pedro age: ${peopleAges["Pedro"]}")
    
    // lambdas and high-order functions
    
    // For each
    peopleAges.forEach {
        print("${it.key} is ${it.value}, ")
    }
    
    println()
    
    println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", "))
    
    // filter returns a [LinkedHashMap]
    val filteredNames = peopleAges.filter { it.key.length < 4 }
    println(filteredNames)
    
    // val triple: (Int) -> Int = { value: Int -> value * 3 }
    // lambda expression with single param can use 'it' and omit '->'
    val triple: (Int) -> Int = { it * 3 }
    println(triple(3))
    
    val peopleNames = listOf("Fred", "Ann", "Barbara", "Joe")
    println(peopleNames.sorted())
    
    println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length })

    // Other example

    val words = listOf(
        "about",
        "acute",
        "awesome",
        "balloon",
        "best",
        "brief",
        "class",
        "coffee",
        "creative"
    )
    
    val filterWords = { wordList: List<String>, letter: String -> wordList.filter { it.startsWith(letter, ignoreCase = true) } }
    
    val filteredWords = words
        .filter { it.startsWith('b', ignoreCase = true) }
        .shuffled() // randomly ordered
        .take(2) // take only 2 items
        .sorted() // sort the final words
        
    println(filteredWords)
    
    val cWords = filterWords(words, "c")
        .shuffled()
        .take(1)
        
    println(cWords)
}