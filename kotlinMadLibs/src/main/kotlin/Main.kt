import java.io.File
import java.io.FileWriter
import java.io.InputStream

fun main(args: Array<String>) {
    // got the ide to work
//    println("Hello World!")

////     checking where the text file was saved
//    writeToFile("testing...testing")

    println("\t\tMad Libs Game")
    println("You provide the words and this program will supply the story.")
    println("Let's practice.")
    println("Choose a plural noun: ")
    var noun = readLine()
    println("It was raining $noun.")
    println()
    println("Let's Play!")
    // variable of type InputStream, assign value of text file and call method
    val inputStream: InputStream = File("test.txt").inputStream()

    // create a dynamic list of type string to hold the text file
    val lineList = mutableListOf<String>()

    // create a list to hold words user will enter
    val listOfWords = mutableListOf<String>()

    // create a list to hold user responses
    val userResponses = mutableListOf<String>()

    // can this hold all the new lines to the story??
    val newStory = mutableListOf<String>()

    // read each line of text file into lineList variable
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    // outputs each line of the file using an angle bracket then line
//    lineList.forEach{println(">  " + it)}

    // practice the replace method to make sure I understand what's happening
//    for (line in lineList) {
//        val new = line.replace("<noun>", "cow")
//        println(new)
//    }
    // bool to continue adding letters to a word starting with the < and ending with the >
    var keepGoing = false

    // hold the user's response to add to the list of user responses
    var wordType = ""

    // reading the file character by character
    val linesOfText = readFileDirectlyAsText("test.txt")

    // trying to figure out how to grab a word from a string of text
//    for (character in linesOfText) {
//        if (character == '<') {
//            wordType += character
//        }
//    }
//        println(wordType)
//        println(character == '<')
//        println(character)

    // go through the file character by character, look for words with < at the beginning
    // and > at the end, add those words to a list
    for (i in linesOfText.indices) {
        if (linesOfText[i] == '<') {
            keepGoing = true
            var j = i
            while (keepGoing) {
                wordType += linesOfText[j]
                j++
                if(linesOfText[j] == '>') {
                    wordType += linesOfText[j]
                    listOfWords.add(wordType)
                    wordType = ""
                    keepGoing = false
                }
            }
        }
    }

//    // checking to see what is in the listOfWords variable
//    println(listOfWords)
//    // check to make sure each word can be accessed using indices
//    println(listOfWords[0])

    // iterate through the list of words to prompt user for nouns, verbs, adjectives, etc.
    for(i in listOfWords.indices) {
        println("Enter a " + listOfWords[i])
        // save the user response to a variable
        var response = readLine()
        // add the user response to the list of user responses
        response?.let { userResponses.add(it) }
    }

//    // checking that all the words were added to the user responses list
//    println(userResponses)

    // find length of the list of words list, minus one so all the indices
    // can be accessed and are within range
    var length = listOfWords.size - 1
    // for each line of the story stored in the lineList variable
    for (line in lineList) {
        // for each user response in the user responses list
        for (i in 0..length) {
            // if the word is in the line
            if (listOfWords[i] in line) {
                // replace the word in the story with the user's word
                val new = line.replace(listOfWords[i], userResponses[i])
                // add the line to the story
                newStory.add(new)
            }
        }
    }

    // print the story with the user responses to the screen
    for(line in newStory) {
        println("\t" + line)
    }
}

// writes to a file using the function input parameter using a string
// trying to see where file path is, so I can create a file in that path for game
fun writeToFile(str:String) {
    try {
        var fo = FileWriter("text.txt")
        fo.write(str)
        fo.close()
    } catch (ex:Exception) {
        println(ex.message)
    }
}

// another function to read a file as a string.
fun readFileDirectlyAsText(fileName: String): String
        = File(fileName).readText(Charsets.UTF_8)
