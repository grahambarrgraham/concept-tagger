package util

import java.io._
import java.util.UUID.randomUUID

object UUIDGenerator {

  def createSentence(numberOfWords : Int) : String = {
    val builder : StringBuilder = new StringBuilder
    (1 to numberOfWords).foreach(_ => builder.append(randomUUID).append(' '))
    builder.toString
  }

  def writeUUIDFile(size : Int, filename : String) : Unit = {
    val writer = new PrintWriter(new File(filename))
    (1 to size).foreach(_ => writer.write(s"$randomUUID\n"))
    writer.close()
  }

  def main(args : Array[String]) : Unit = {
    writeUUIDFile(1000000, "/tmp/uuid-1M.txt")
  }

}
